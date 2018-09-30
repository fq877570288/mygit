package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.untils.ImgUtil;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.PbxExtsService;
import cn.cucsi.bsd.ucc.service.TeamUsersService;
import cn.cucsi.bsd.ucc.service.UccPermissionsService;
import cn.cucsi.bsd.ucc.service.UccUserService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/uccUser")
public class UccUserController  {

    @Autowired
    private UccUserService uccUserService;
    @Autowired
    private PbxExtsService PbxExtsService;
    @Autowired
    private TeamUsersService teamUsersService;
    @Autowired
    UccPermissionsService uccPermissionsService;

    @ApiOperation(value="根据查询条件获取用户列表", notes="根据查询条件获取用户列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public PageResultBean<List<UccUsers>> findAll(@RequestBody UccUserCriteria criteria){
        PbxExtsCriteria search = new PbxExtsCriteria();
        if(criteria.getExtNum()!=null&&!"".equals(criteria.getExtNum())){
            search.setExtNumVague(criteria.getExtNum());
            Page<PbxExts> extList = this.PbxExtsService.findAll(search);
            List<String> extId = new ArrayList<>();
            for (PbxExts pbxExts:extList) {
                extId.add(pbxExts.getExtId());
            }
            criteria.setExtId(extId);
        }
        PageResultBean<List<UccUsers>> list = new PageResultBean(this.uccUserService.findAll(criteria));
        return list;
    }

    @ApiOperation(value="根据类型查询所有用户列表", notes="根据类型查询所有用户列表", httpMethod = "GET")
    @RequestMapping(value = "/findAllByType/{type}", method= RequestMethod.GET)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public PageResultBean<List<UccUsers>> findAllByType(@PathVariable String type){
        PageResultBean<List<UccUsers>> list = new PageResultBean<>();
        if("team".equals(type)){
            UccUserCriteria criteria = new UccUserCriteria();
            list = new PageResultBean(this.teamUsersService.addFindAll(criteria));
            List<UccUsers> uccUsersList = list.getData();
            Integer totalElements = 0;
            List<UccUsers> resultUccUsersList = new ArrayList<>();
            for (UccUsers uccUsers : uccUsersList) {
                if(uccUsers.getTeamUsers().size()==0){
                    totalElements+=1;
                    resultUccUsersList.add(uccUsers);
                }
            }
            list.setTotalElements(totalElements);
            list.setData(resultUccUsersList);
        }
        return list;
    }

    @ApiOperation(value="根据查询条件获取用户", notes="根据查询条件获取用户", httpMethod = "POST")
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public  PageResultBean<List<UccUsers>> login(@RequestBody UccUserCriteria criteria, HttpServletRequest request, HttpServletResponse response) {

        List<UccUsers> list =this.uccUserService.findAllList(criteria);

        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy年MM月dd日");
        String sessionValue ="";
        for (int i=0;i<list.size();i++){
            String userId = list.get(i).getUserId();
            UccPermissionsCriteria search = new UccPermissionsCriteria();
            search.setUserId(userId);
            //search.setIsLeftMenu("0");
            List<UccPermissionsAndUser> uccPermissionsList = tree(search);
            list.get(i).setUccPermissions(uccPermissionsList);
            sessionValue=  list.get(i).getUserName()+"#"+ list.get(i).getPassword();
        }
        Cookie cookie = new Cookie("login",sessionValue);
        cookie.setMaxAge(900000);
        cookie.setPath("/");
        response.addCookie(cookie);
        cookie.setDomain("localhost:8000");

        Cookie[] cookies = request.getCookies();
        String login = "";
        for (Cookie cookie1 : cookies) {
            switch(cookie1.getName()){
                case "login":
                    login = cookie1.getValue();
                    break;
                default:
                    break;
            }
        }
        System.out.println(login);

        PageResultBean<List<UccUsers>> uccUsersList = new PageResultBean<>();
        uccUsersList.setData(list);

        HttpSession session = request.getSession();
         //将数据存储到session中
        session.setAttribute("login", sessionValue);
        if(list!=null&&list.size()!=0){
            session.setAttribute("uccUsers", list.get(0));
            uccUsersList.setMsg("登陆成功！");
        }else{
            uccUsersList.setMsg("用户名或密码不正确！");
            uccUsersList.setCode(ResultBean.FAIL);
        }
        return uccUsersList;
    }

    @ApiOperation(value="根据查询条件获取用户", notes="根据查询条件获取用户", httpMethod = "POST")
    @RequestMapping(value = "/logout", method= RequestMethod.GET)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public  ResultBean<Boolean> logout( HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("login");
        session.removeAttribute("uccUsers");
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie: cookies){
            if("login".equals(cookie.getName())){
                cookie.setValue("");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        String login = "";
        for (Cookie cookie : cookies) {
            switch(cookie.getName()){
                case "login":
                    login = cookie.getValue();
                    break;
                default:
                    break;
            }
        }

        boolean result = session.getAttribute("login")==null
                &&session.getAttribute("uccUsers")==null
                &&"".equals(login);
        if(result){
            return new ResultBean<>(ResultBean.SUCCESS,"登出成功！",result);
        }
        return new ResultBean<>(ResultBean.FAIL,"登出失败！",result);
    }

    @ApiOperation(value = "根据userId查询UccUsers", notes = "根据userId查询UccUsers")
    @RequestMapping(value = "/{userId}", method= RequestMethod.GET)
    public ResultBean<UccUsers> findOne(@PathVariable String userId){
        return new ResultBean<>(this.uccUserService.findOne(userId));
    }

    @ApiOperation(value = "根据userId删除UccUsers", notes = "根据userId删除UccUsers")
    @RequestMapping(value = "/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String userId){
        return new ResultBean<>(this.uccUserService.delete(userId));
    }

    @ApiOperation(value = "根据userId批量删除UccUsers", notes = "根据userId批量删除UccUsers")
    @RequestMapping(value = "multiDelete/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> multiDelete(@PathVariable String userId){
        return new ResultBean<>(this.uccUserService.multiDelete(userId));
    }

    @ApiOperation(value = "创建UccUsers", notes = "创建UccUsers")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccUsers uccUsers) {
        uccUsers.setCreatedTime(new Date());
        this.uccUserService.saveMiddleTable(uccUsers);
        boolean result = this.uccUserService.save(uccUsers) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccUsers", notes = "保存UccUsers")
    //@ApiImplicitParam(name = "uccUsers", value = "用户entity", required = true, dataType = "UccUsers")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String userId,@RequestBody UccUsers uccUsers){
        UccUsers targetUser = this.uccUserService.findOne(uccUsers.getUserId());
        UpdateUtil.copyNullProperties(targetUser,uccUsers);
        uccUsers.setUpdatedTime(new Date());
        this.uccUserService.saveMiddleTable(uccUsers);
        boolean result = this.uccUserService.save(uccUsers) != null;
        return new ResultBean<>(result);
    }

    /***
     * 根据用户名、密码获取用户列表（APP登录用）
     * 此方法临时用，后期需要做补充
     * add by wangxiaoyu
     * 2018-09-10
     */
    @ApiOperation(value="根据用户名、密码获取用户列表（APP登录用）", notes="根据用户名、密码获取用户列表（APP登录用）", httpMethod = "POST")
    @RequestMapping(value = "/userLoginForAPP", method= RequestMethod.POST)
    public ResultBean_New<UccUsers> userLoginForAPP(@RequestBody UserLoginForAPPCriteria userLoginForAPPCriteria){
        return uccUserService.userLoginForAPP(userLoginForAPPCriteria);
    }

    /**
     * 根据部门获取所有的人员列表
     * @return
     */
    @RequestMapping(value = "/findAllByDept", method= RequestMethod.GET)
    public ResultBean_New<List<UccUserByDept>> userListByDept() {
        UserDeptCriteria userDeptCriteria = new UserDeptCriteria();
        userDeptCriteria.setDeptId("4028e38165ea6d7f0165ea951c290000");
        userDeptCriteria.setDomainId("domain1");
        userDeptCriteria.setDeptName("部门666");

        UserDeptCriteria userDeptCriteria1 = new UserDeptCriteria();
        userDeptCriteria1.setDeptId("402881e565ea2d280165ea782e3c0000");
        userDeptCriteria1.setDomainId("domain1");
        userDeptCriteria1.setDeptName("部门555");

        List<UserDeptCriteria> userDeptCriterias = new ArrayList<>();
        userDeptCriterias.add(userDeptCriteria);
        userDeptCriterias.add(userDeptCriteria1);

        return uccUserService.userListByDept(userDeptCriterias);
    }

    /**
     * 获取图片验证码
     * @return
     */
    @RequestMapping(value = "/login/verification/image",method= RequestMethod.GET,
            produces="application/json;charset=utf-8")
    public ResultBean<VerificationCode> VerificationImg(HttpSession httpSession) throws IOException {
        ResultBean<VerificationCode> resultBean=uccUserService.VerificationImg();
        VerificationCode verificationCode=new VerificationCode();
        verificationCode=resultBean.getData();
        httpSession.setAttribute("verificationCodeMark",resultBean.getData().getText());
        httpSession.setAttribute("verificationImageMark",resultBean.getData().getImgId());
        //verificationCode.setText("");
        resultBean.setData(verificationCode);
//        httpSession.setMaxInactiveInterval(60);//设置验证码有效时间
        return resultBean;
    }
    /**
     * 验证验证码是否正确
     * @param httpSession
     * @param verificationCode
     * @param imageCode
     * @return
     */
    @RequestMapping(value = "/login/verification/{verificationCode}/image/{imageCode}",method= RequestMethod.GET,
            produces="application/json;charset=utf-8")
    public ResultBean<VerificationCode> verification(HttpSession httpSession,
                                                     @PathVariable String verificationCode,
                                                     @PathVariable String imageCode){


        if(httpSession.getAttribute("verificationImageMark")!=null&&httpSession.getAttribute("verificationImageMark").toString().equals(imageCode))
        {
            httpSession.removeAttribute("verificationImageMark");
            if(httpSession.getAttribute("verificationCodeMark")!=null&&(httpSession.getAttribute("verificationCodeMark").toString()).equalsIgnoreCase(verificationCode))
            {
                httpSession.removeAttribute("verificationCodeMark");
                return new ResultBean<>();
            }
            else
            {
                httpSession.removeAttribute("verificationCodeMark");
                return new ResultBean<>(ResultBean.FAIL,"验证失败");
            }
        }
        else
        {
            //验证失败，直接删除
            httpSession.removeAttribute("verificationImageMark");
            httpSession.removeAttribute("verificationCodeMark");
            return new ResultBean<>(ResultBean.FAIL,"验证失败");
        }

    }



    public List<UccPermissionsAndUser> tree(UccPermissionsCriteria search){
        PageResultBean<List<UccPermissions>> uccPermissionsList = new PageResultBean(this.uccPermissionsService.findAllTree(search));
        List<UccPermissions> list = uccPermissionsList.getData();
        List<UccPermissions> pIdList = new ArrayList<>();
        List<UccPermissionsAndUser> uccPermissionsAndUserList = new ArrayList<>();
        if(list!=null&&list.size()!=0) {
            //父ID
            for (UccPermissions uccPermissions : list) {
                if (uccPermissions.getMpid() == null) {
                    pIdList.add(uccPermissions);
                }
            }
            //往父ID中添加子节点
            if(pIdList!=null&&pIdList.size()!=0) {
                for (UccPermissions pId : pIdList) {
                    List<UccPermissions> childNodeList = new ArrayList<>();
                    for (UccPermissions uccPermissions : list) {
                        if(pId.getPermissionId().equals(uccPermissions.getMpid())){
                            childNodeList.add(uccPermissions);
                        }
                    }
                    pId.setUccPermissions(childNodeList);
                }
                for (UccPermissions uccPermissions : pIdList) {
                    UccPermissionsAndUser uccPermissionsAndUser = new UccPermissionsAndUser();
                    uccPermissionsAndUser = copyProperties(uccPermissions,uccPermissionsAndUser);
                    uccPermissionsAndUserList.add(uccPermissionsAndUser);
                }
                return uccPermissionsAndUserList;
            }
        }

        uccPermissionsList = new PageResultBean(this.uccPermissionsService.findAllTree(search));
        list = uccPermissionsList.getData();
        if(list!=null&&list.size()!=0) {
            for (UccPermissions uccPermissions : list) {
                UccPermissionsAndUser uccPermissionsAndUser = new UccPermissionsAndUser();
                uccPermissionsAndUser = copyProperties(uccPermissions,uccPermissionsAndUser);
                uccPermissionsAndUserList.add(uccPermissionsAndUser);
            }
        }
        return uccPermissionsAndUserList;
    }

    private UccPermissionsAndUser copyProperties(UccPermissions uccPermissions,UccPermissionsAndUser uccPermissionsAndUser) {
        uccPermissionsAndUser.setName(uccPermissions.getPermissionName());
        uccPermissionsAndUser.setIcon(uccPermissions.getIcon());
        uccPermissionsAndUser.setPath(uccPermissions.getRoute());
        List<UccPermissions> uccPermissionsList = uccPermissions.getUccPermissions();
        List<UccPermissionsAndUserSecound> uccPermissionsAndUserList = new ArrayList<>();
        if(uccPermissionsList!=null&&uccPermissionsList.size()!=0){
            for (UccPermissions permissions : uccPermissionsList) {
                UccPermissionsAndUserSecound permissionsAndUser = new UccPermissionsAndUserSecound();
                permissionsAndUser.setName(permissions.getPermissionName());
                permissionsAndUser.setIcon(permissions.getIcon());
                permissionsAndUser.setPath(permissions.getRoute());
                uccPermissionsAndUserList.add(permissionsAndUser);
            }
        }
        uccPermissionsAndUser.setChildren(uccPermissionsAndUserList);
        return uccPermissionsAndUser;
    }

}
