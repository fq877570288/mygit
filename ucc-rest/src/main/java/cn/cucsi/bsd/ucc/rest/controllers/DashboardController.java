package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.CountBean;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.common.mapper.PbxCdrsMapper;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.ExtGroupExtsService;
import cn.cucsi.bsd.ucc.service.PbxCdrsService;
import cn.cucsi.bsd.ucc.service.UccUserService;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZSS on 2018/9/21 .
 */
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {
    /*****
     * 分机组、坐席员、分机号集合
     * add by ZSS
     * 2018-9-21
     */
    @Autowired
    private ExtGroupExtsService extGroupExtsService;
    @Autowired
    private UccUserService uccUserService;

    @Autowired
    private PbxCdrsMapper pbxCdrsMapper;

    @ApiOperation(value = "查询机组、坐席员、分机号集合", notes = "查询机组、坐席员、分机号集合")
    @RequestMapping(value = "/list", method =  RequestMethod.GET)
    public PageResultBean_New<List<PbxExtGroups>> list(String domainId){
        try {
            return this.extGroupExtsService.queryDashboardList(domainId);
        } catch (Exception e) {
            System.out.println("根据domainId查询查询机组、坐席员、分机号集合失败！");
            e.printStackTrace();
            return new PageResultBean_New<List<PbxExtGroups>>();
        }
    }
    @ApiOperation(value = "坐席总数、今日人工、今日IVR、示忙、示闲", notes = "坐席总数、今日人工、今日IVR、示忙、示闲")
    @RequestMapping(value = "/allNumbers", method = RequestMethod.GET,produces="application/json;charset=utf-8")
    public String allNumbers(String domainId){
        JsonObject j = new JsonObject();
        try {
            List<UccUsers> uccUsersList = new ArrayList<>();
            UccUserCriteria uccUserCriteria = new UccUserCriteria();
            if(domainId.equals("uccAdmin")){
                uccUsersList = uccUserService.querySeater(uccUserCriteria);
            }else {
                uccUserCriteria.setDomainId(domainId);
                uccUsersList = uccUserService.querySeater(uccUserCriteria);
            }

            //获取今日IVR
            CountBean ivrCount = pbxCdrsMapper.vivCount();
            CountBean queueCount = pbxCdrsMapper.queueCount();

            j.addProperty("return_msg","success");
            j.addProperty("return_code","success");
            j.addProperty("seatTotal",uccUsersList.size());//坐席总数
            j.addProperty("manTotal",queueCount.getCount());//今日人工
            j.addProperty("ivrTotal",ivrCount.getCount());//今日IVR
            j.addProperty("callingQueueTotal",0);//人工通话中
            j.addProperty("callingIvrTotal",0);//IVR通话中
            return j.toString();
        }catch (Exception e){
            System.out.println("根据domainId坐席总数、今日人工、今日IVR、示忙、示闲失败！");
            j.addProperty("return_msg","error");
            j.addProperty("return_code","error");
            return j.toString();
        }
    }
}
