package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *  coding in zss
 *  2018.9.26
 *  移植自outcall 根据需求已更改
 */
@Api(tags={"主页信息接口(移植自outcall _ coding zss)"})
@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private PbxExtsService pbxExtsService;
    @Autowired
    private UccNoticeService uccNoticeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private PbxGatewaysService pbxGatewaysService;
    @Autowired
    private PbxQueuesService pbxQueuesService;
    @Autowired
    private UccUserService uccUserService;
    @Autowired
    private UccDeptsService uccDeptsService;
    @Autowired
    private LoginLogService loginLogService;
    /**
     * 主页视图
     */
    //@UserFlag
    @ApiOperation(value = "主页逻辑块", notes = "主页逻辑块", httpMethod = "GET")
    @RequestMapping(value="/index",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String IndexView(String domainId,String userId) throws Exception {
        JSONObject j = new JSONObject();
        try {
            PbxExtsCriteria search = new PbxExtsCriteria();
            search.setDomainId(domainId);
            PageResultBean<List<PbxExts>> bean = new PageResultBean(this.pbxExtsService.findAll(search));
            //未读消息
            int countNotice = uccNoticeService.selectByFlagCount(userId);
            UccNoticeCriteria uccNoticeCriteriaSearch = new UccNoticeCriteria();
            uccNoticeCriteriaSearch.setNoticeType("1");
            uccNoticeCriteriaSearch.setUserId(userId);
            uccNoticeCriteriaSearch.setFlag("0");
            Page<UccNotice> pageUccNotice = uccNoticeService.findAll(uccNoticeCriteriaSearch);
            j.put("return_msg","success");
            j.put("return_code","success");
            j.put("countNotice",countNotice);
            j.put("noticeList",pageUccNotice.getContent());
            j.put("exts",bean.getData());
            return j.toString();
        } catch (Exception e) {
            System.out.println("查询主页逻辑块失败！");
            e.printStackTrace();
        }
        j.put("return_msg","error");
        j.put("return_code","error");
        return j.toString();
    }
    /**
     * 主页视图Plus
     */
    //@UserFlag
    @ApiOperation(value = "主页视图Plus", notes = "主页视图Plus", httpMethod = "GET")
    @RequestMapping(value = "/index1.html",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String IndexView1(String domainId,String userId,String DeptIdAndChildIds) throws Exception {
        JSONObject j = new JSONObject();
        try {
            j.put("return_msg","success");
            j.put("return_code","success");
            PbxExtsCriteria search = new PbxExtsCriteria();
            search.setDomainId(domainId);
            PageResultBean<List<PbxExts>> bean = new PageResultBean(this.pbxExtsService.findAll(search));
            j.put("exts",bean.getData());

            String deptIdAndChildId = DeptIdAndChildIds;
            if(deptIdAndChildId != null && deptIdAndChildId.length() > 0){
                deptIdAndChildId = deptIdAndChildId.replaceAll(",", "','");
            }
            String deptIds = "'" + deptIdAndChildId + "'";
            //通过部门ID查询需要的信息数量
            int wa = taskService.selectWaitAllCount(deptIds);
            int wt = taskService.selectWaitTodayCount(deptIds);
            int oa = taskService.selectOngoingAllCount(deptIds);
            int on = taskService.selectOngoingNoCount(deptIds);
            int cd = taskService.selectCompleteByDaysCount(deptIds);
            int ct = taskService.selectCompleteTodayCount(deptIds);
            j.put("wa",wa);
            j.put("wt",wt);
            j.put("oa",oa);
            j.put("on",on);
            j.put("cd",cd);
            j.put("ct",ct);
            //未读消息
            UccNoticeCriteria uccNoticeCriteriaSearch = new UccNoticeCriteria();
            uccNoticeCriteriaSearch.setNoticeType("1");
            uccNoticeCriteriaSearch.setUserId(userId);
            uccNoticeCriteriaSearch.setFlag("0");
            Page<UccNotice> pageUccNotice = uccNoticeService.findAll(uccNoticeCriteriaSearch);

            j.put("count",pageUccNotice.getTotalElements());
            j.put("countNotice",uccNoticeService.selectByFlagTypeCount(userId));
            j.put("noticeList",pageUccNotice.getContent());
            //
            UccNoticeCriteria searchSevenDay = new UccNoticeCriteria();
            //开始日期
            Calendar calendar = Calendar.getInstance();
            Date date = new Date(System.currentTimeMillis());
            calendar.setTime(date);
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            date = (Date) calendar.getTime();
            searchSevenDay.setNoticeTimeFrom(date);
            searchSevenDay.setUserId(userId);
            searchSevenDay.setNoticeType("0");
            Page<UccNotice> pageUccNotice1 = uccNoticeService.findAll(searchSevenDay);
            int countNoticeAndAffiche = uccNoticeService.selectByFlagCount(userId);
            j.put("noticeList1",pageUccNotice1.getContent());
            j.put("countNoticeAndAffiche",countNoticeAndAffiche);
            return j.toString();
        } catch (Exception e) {
            System.out.println("查询主页视图Plus失败！");
            e.printStackTrace();
        }
        j.put("return_msg","error");
        j.put("return_code","error");
        return j.toString();
    }
    /**
     * 监控中心
     */
    //@UserFlag(870)
    @ApiOperation(value = "监控中心", notes = "监控中心", httpMethod = "GET")
    @RequestMapping(value= "/monitor.html",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String MonitorView(String domainId)  throws Exception {
        JSONObject j = new JSONObject();
        try {
            j.put("return_msg","success");
            j.put("return_code","success");
            PbxExtsCriteria search = new PbxExtsCriteria();
            search.setDomainId(domainId);
            PageResultBean<List<PbxExts>> bean = new PageResultBean(this.pbxExtsService.findAll(search));
            j.put("exts",bean.getData());
            PbxGatewaysCriteria pbxGatewaysSearch = new PbxGatewaysCriteria();
            pbxGatewaysSearch.setDomainId(domainId);
            Page<PbxGateways> pagePbxGateways =  pbxGatewaysService.findAll(pbxGatewaysSearch);
            j.put("gws",pagePbxGateways.getContent());
            PbxQueuesCriteria PbxQueuessearch = new PbxQueuesCriteria();
            PbxQueuessearch.setDomainId(domainId);
            Page<PbxQueues> queues = pbxQueuesService.findAll(PbxQueuessearch);
          /*  ObjectMapper mapper = new ObjectMapper();
            for (PbxQueues pbxQueue : queues.getContent()) {
                if (pbxQueue.getNumbers() != null && pbxQueue.getNumbers().size()>0) {
                    try {
                        pbxQueue.setNumbersJson(mapper.writeValueAsString(pbxQueue.getNumbers()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage(), e);
                    }
                }
            }*/
          j.put("queues",queues.getContent());
          return j.toString();
        } catch (Exception e) {
            System.out.println("查询监控中心失败！");
            e.printStackTrace();
        }
        j.put("return_msg","error");
        j.put("return_code","error");
        return j.toString();
    }


    /***
     * 员工详情
     */
    //@UserFlag(870)
    @ApiOperation(value = "员工详情", notes = "员工详情", httpMethod = "GET")
    @RequestMapping(value = "/monitor/userInfo.html",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String taskDetail(String userId,String extNum) throws Exception {
        JSONObject j = new JSONObject();
        try {
            j.put("return_msg","success");
            j.put("return_code","success");
            UccUserCriteria search = new UccUserCriteria();
            search.setExtNum(extNum);
            Page<UccUsers> pageuser = uccUserService.findAll(search);
            j.put("bean",pageuser.getContent().get(0));
            List<UccDepts> uccDeptsList = uccDeptsService.selectByUserId(userId);
            j.put("depts",uccDeptsList);
            j.put("urs",uccUserService.findOne(userId));
            return j.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询员工详情失败！");
        }
        j.put("return_msg","error");
        j.put("return_code","error");
        return j.toString();
    }
    /**
     * 用户中心
     */
    //@UserFlag
    @ApiOperation(value = "用户中心", notes = "用户中心", httpMethod = "GET")
    @RequestMapping(value = "/user/center.html",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String userCenterView(String userId) throws Exception  {
        JSONObject j = new JSONObject();
        try {
            j.put("return_msg","success");
            j.put("return_code","success");
            LoginLogCriteria loginLogCriteria = new LoginLogCriteria();
            loginLogCriteria.setUserId(userId);
            loginLogService.findAll(loginLogCriteria).getContent();
            j.put("list",loginLogService.findAll(loginLogCriteria).getContent());
            return j.toString();
        } catch (Exception e) {
            System.out.println("查询用户中心失败！");
            e.printStackTrace();
        }
        j.put("return_msg","error");
        j.put("return_code","error");
        return j.toString();
    }
}
