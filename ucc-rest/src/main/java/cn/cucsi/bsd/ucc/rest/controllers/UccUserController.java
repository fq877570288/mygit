package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.untils.ChatLogin;
import cn.cucsi.bsd.ucc.common.untils.ImgUtil;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.*;
import cn.cucsi.bsd.ucc.service.impl.UccDeptsServiceImpl;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UccDeptsServiceImpl uccDeptsServiceImpl;
    @Autowired
    private UserExtService userExtService;
    @Autowired
    private DataCustomfieldService dataCustomfieldService;

    @ApiOperation(value="根据查询条件获取用户列表", notes="根据查询条件获取用户列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public PageResultBean<List<UccUsers>> findAll(@RequestBody UccUserCriteria criteria){
        PbxExtsCriteria search = new PbxExtsCriteria();
        search.setDomainId(criteria.getDomainId());
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
        if(list!=null&&list.getData().size()>0){
            for (UccUsers uccUsers : list.getData() ) {
                String userId = uccUsers.getUserId();
                List<UserExt> extList = userExtService.selectByUserId(userId);
                uccUsers.setExt(extList);
            }
        }
        return list;
    }

    @ApiOperation(value="根据类型查询所有用户列表", notes="根据类型查询所有用户列表", httpMethod = "POST")
    @RequestMapping(value = "/findAllByType/{type}", method= RequestMethod.POST)
    public ResultBean<List<UccUsers>> findAllByType(@PathVariable String type,@RequestBody UccUserCriteria uccUserCriteria){
        ResultBean<List<UccUsers>> resultBean = new ResultBean<>();
        if("team".equals(type)){
            uccUserCriteria.setUserStatus("0");//ucc_users表中状态标识0是有效，1是无效(不知道谁设定的)
            List<UccUsers> uccUsersList = teamUsersService.addFindAll(uccUserCriteria);
            resultBean.setData(uccUsersList);
        }
        return resultBean;
    }

    @ApiOperation(value="验证用户名是否重复", notes="验证用户名是否重复", httpMethod = "GET")
    @RequestMapping(value = "/validate/{userName}", method= RequestMethod.GET)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public  ResultBean<Boolean> validate(@PathVariable String userName) {
        UccUserCriteria criteria = new UccUserCriteria();
        criteria.setUserName(userName);
        List<UccUsers> list = this.uccUserService.loginList(criteria);
        Boolean result = list.size()==0;
        if(result){
            return new ResultBean<>(ResultBean.SUCCESS,"用户名不重复！",result);
        }
        return new ResultBean<>(ResultBean.FAIL,"用户名重复！",result);
    }

    @ApiOperation(value="根据查询条件获取用户", notes="根据查询条件获取用户", httpMethod = "POST")
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    @JsonView(JSONView.UccUserWithDeptAndRoleAndExt.class)
    public  PageResultBean<List<UccUsers>> login(@RequestBody UccUserCriteria criteria, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UccUsers> list =this.uccUserService.loginList(criteria);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(-1);//设置单位为秒，设置为-1永不过期

        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy年MM月dd日");
        String sessionValue ="";
        for (int i=0;i<list.size();i++){
            String userId = list.get(i).getUserId();
            List<UserExt> extList = userExtService.selectByUserId(userId);
            list.get(i).setExt(extList);
            UccPermissionsCriteria search = new UccPermissionsCriteria();
            search.setUserId(userId);
            //search.setIsLeftMenu("0");
            List<UccPermissionsAndUser> uccPermissionsList = tree(search);
            String fistPath = "";
            if(uccPermissionsList!=null&&uccPermissionsList.size()>0){
                fistPath += uccPermissionsList.get(0).getPath();
                if(uccPermissionsList.get(0).getChildren()!=null&&uccPermissionsList.get(0).getChildren().size()>0){
                    String path = uccPermissionsList.get(0).getChildren().get(0).getPath();
                    if(!path.startsWith("/")){
                        path = "/"+path;
                    }
                    fistPath += path;
                }
            }
            list.get(i).setFistPath(fistPath);
            list.get(i).setUccPermissions(uccPermissionsList);
            sessionValue=  list.get(i).getUserName()+"#"+ list.get(i).getPassword();

            session.setAttribute("uccUsers", list.get(i));
            session.setAttribute("LoginUser", list.get(i));
            Collection<UccDepts> deptList = list.get(i).getDepts();
            /*String DeptIdAndChildIds = "";
            int j = 0;
            for (UccDepts uccDepts : deptList) {
                DeptIdAndChildIds += "'"+uccDepts.getDeptId()+"'";
                if(i<deptList.size()-1){
                    DeptIdAndChildIds += ",";
                }
                j++;
            }
            session.setAttribute("DeptIdAndChildIds", DeptIdAndChildIds);*/
            List<DataCustomfield> dataCustomfields = dataCustomfieldService.selectImportByUserID(list.get(i).getUserId().toString());
            session.setAttribute("dataCustomfields",dataCustomfields);
            List<DataCustomfield> dataCustomfieldExportList = dataCustomfieldService.selectExportByUserID(list.get(i).getUserId().toString());
            session.setAttribute("dataCustomfieldExportList",dataCustomfieldExportList);
            List<UccDepts> uccDeptsList = new ArrayList<UccDepts>(deptList);
            List<UccDepts> udList = uccDeptsServiceImpl.queryChildrenByDepts(list.get(i).getDomainId(),uccDeptsList);
            session.setAttribute("DeptIdList", udList);
            String deptIdAndChildIds  = udList.parallelStream().map(uccDepts-> "'"+uccDepts.getDeptId()+"'").collect(Collectors.joining(","));
            session.setAttribute("DeptIdAndChildIds", deptIdAndChildIds);
            String deptIds  = deptList.parallelStream().map(uccDepts->"'"+ uccDepts.getDeptId()+"'").collect(Collectors.joining(","));
            session.setAttribute("DeptIds", deptIds);
            ChatLogin chatLogin = new ChatLogin();
            JSONObject re = chatLogin.login(request, new ObjectMapper(), redisTemplate);
            list.get(i).setResult(re);
        }
        Cookie cookie = new Cookie("login",sessionValue);
        cookie.setMaxAge(900000);
        cookie.setPath("/");
        response.addCookie(cookie);
        cookie.setDomain("localhost:8000");


        PageResultBean<List<UccUsers>> uccUsersList = new PageResultBean<>();
        uccUsersList.setData(list);

         //将数据存储到session中
        session.setAttribute("login", sessionValue);
        if(list!=null&&list.size()!=0){
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
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("login");
            session.removeAttribute("uccUsers");
            session.removeAttribute("LoginUser");
            Cookie[] cookies=request.getCookies();
            if(cookies!=null&&cookies.length>0){
                for(Cookie cookie: cookies){
                    if("login".equals(cookie.getName())){
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("cookie:"+e);
        }
        return new ResultBean<>(ResultBean.SUCCESS,"登出成功！",true);
    }

    @ApiOperation(value = "根据userId查询UccUsers", notes = "根据userId查询UccUsers")
    @RequestMapping(value = "/{userId}", method= RequestMethod.GET)
    public ResultBean<UccUsers> findOne(@PathVariable String userId){
        UccUsers uccUsers = this.uccUserService.findOne(userId);
        List<UserExt> extList = userExtService.selectByUserId(userId);
        if(uccUsers!=null){
            uccUsers.setExt(extList);
        }
        return new ResultBean<>(uccUsers);
    }

    @ApiOperation(value = "根据userId删除UccUsers", notes = "根据userId删除UccUsers")
    @RequestMapping(value = "/delete", method= RequestMethod.POST,produces="application/json;charset=utf-8")
    public ResultBean<Boolean> delete(@RequestBody UccUsers uccUsers){
        int userStatus = uccUsers.getUserStatus();
        uccUsers = this.uccUserService.findOne(uccUsers.getUserId());
        uccUsers.setUserStatus(userStatus);
        return new ResultBean<>(this.uccUserService.save(uccUsers)!=null);
        //return new ResultBean<>(this.uccUserService.delete(userId));
    }

    @ApiOperation(value = "根据userId批量删除UccUsers", notes = "根据userId批量删除UccUsers")
    @RequestMapping(value = "multiDelete/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> multiDelete(@PathVariable String userId){
        return new ResultBean<>(this.uccUserService.multiDelete(userId));
    }

    @ApiOperation(value = "创建UccUsers", notes = "创建UccUsers")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    @ResponseBody
    public ResultBean<Boolean> create(@RequestBody UccUsers uccUsers, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UccUsers sessionUser = (UccUsers) session.getAttribute("uccUsers");
        if(sessionUser!=null){
            if(sessionUser.getNickName()!=null&&!"".equals(sessionUser.getNickName())){
                uccUsers.setCreatedNickName(sessionUser.getNickName());
            }
            if(sessionUser.getUserName()!=null&&!"".equals(sessionUser.getUserName())){
                uccUsers.setCreatedUserName(sessionUser.getUserName());
            }
            if(sessionUser.getUserId()!=null&&!"".equals(sessionUser.getUserId())){
                uccUsers.setCreatedUserId(sessionUser.getUserId());
            }
        }
        uccUsers.setCreatedTime(new Date());
        uccUsers.setPassword("123456");
        uccUsers.setUserStatus(0);
        boolean result = this.uccUserService.saveAll(uccUsers,new UccUsers()) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccUsers", notes = "保存UccUsers")
    //@ApiImplicitParam(name = "uccUsers", value = "用户entity", required = true, dataType = "UccUsers")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResultBean<Boolean> save(@PathVariable String userId,@RequestBody UccUsers uccUsers,HttpServletRequest request){
        UccUsers targetUser = this.uccUserService.findOne(uccUsers.getUserId());
        HttpSession session = request.getSession();
        UccUsers sessionUser = (UccUsers) session.getAttribute("uccUsers");
        if(sessionUser!=null) {
            if (sessionUser.getNickName() != null && !"".equals(sessionUser.getNickName())) {
                uccUsers.setUpdatedNickName(sessionUser.getNickName());
            }
            if (sessionUser.getUserName() != null && !"".equals(sessionUser.getUserName())) {
                uccUsers.setUpdatedUserName(sessionUser.getUserName());
            }
            if (sessionUser.getUserId() != null && !"".equals(sessionUser.getUserId())) {
                uccUsers.setUpdatedUserId(sessionUser.getUserId());
            }
        }
        uccUsers.setUpdatedTime(new Date());
        boolean result = this.uccUserService.saveAll(uccUsers,targetUser) != null;
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
    @RequestMapping(value = "/findAllByDept", method= RequestMethod.POST)
    public ResultBean_New<List<UccUserByDept>> userListByDept( HttpServletRequest request) {
        HttpSession session = request.getSession();
        UccUsers user = (UccUsers) session.getAttribute("uccUsers");
        Collection<UccDepts> uccDepts = user.getDepts();
        List<UserDeptCriteria> userDeptCriterias = new ArrayList<>();
        for (UccDepts uccDept : uccDepts) {
            UserDeptCriteria userDeptCriteria = new UserDeptCriteria();
            userDeptCriteria.setDeptId(uccDept.getDeptId());
            userDeptCriteria.setDomainId(uccDept.getDomainId());
            userDeptCriteria.setDeptName(uccDept.getDeptName());
            userDeptCriterias.add(userDeptCriteria);
        }

        /*UserDeptCriteria userDeptCriteria = new UserDeptCriteria();
        userDeptCriteria.setDeptId("4028e38165ea6d7f0165ea951c290000");
        userDeptCriteria.setDomainId("domain1");
        userDeptCriteria.setDeptName("部门666");

        UserDeptCriteria userDeptCriteria1 = new UserDeptCriteria();
        userDeptCriteria1.setDeptId("402881e565ea2d280165ea782e3c0000");
        userDeptCriteria1.setDomainId("domain1");
        userDeptCriteria1.setDeptName("部门555");

        List<UserDeptCriteria> userDeptCriterias = new ArrayList<>();
        userDeptCriterias.add(userDeptCriteria);
        userDeptCriterias.add(userDeptCriteria1);*/

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
        uccPermissionsAndUser.setPermissionId(uccPermissions.getPermissionId());
        uccPermissionsAndUser.setName(uccPermissions.getPermissionName());
        uccPermissionsAndUser.setIcon(uccPermissions.getIcon());
        uccPermissionsAndUser.setPath(uccPermissions.getRoute());
        uccPermissionsAndUser.setIsLeftMenu(uccPermissions.getIsLeftMenu());
        List<UccPermissions> uccPermissionsList = uccPermissions.getUccPermissions();
        List<UccPermissionsAndUserSecound> uccPermissionsAndUserList = new ArrayList<>();
        if(uccPermissionsList!=null&&uccPermissionsList.size()!=0){
            for (UccPermissions permissions : uccPermissionsList) {
                UccPermissionsAndUserSecound permissionsAndUser = new UccPermissionsAndUserSecound();
                permissionsAndUser.setPermissionId(permissions.getPermissionId());
                permissionsAndUser.setName(permissions.getPermissionName());
                permissionsAndUser.setIcon(permissions.getIcon());
                permissionsAndUser.setPath(permissions.getRoute());
                permissionsAndUser.setIsLeftMenu(permissions.getIsLeftMenu());
                uccPermissionsAndUserList.add(permissionsAndUser);
            }
        }
        uccPermissionsAndUser.setChildren(uccPermissionsAndUserList);
        return uccPermissionsAndUser;
    }


    /**
     * 分机分配
     * @return
     */
    @RequestMapping(value="/pbx/setext", method=RequestMethod.POST,
            produces="application/json;charset=utf-8")
    @ResponseBody
    public ResultBean<Boolean> pbxSetExt(@RequestBody UccUsers uccUsers) {
        List<UserExt> userExtList = uccUsers.getExt();
        Boolean result = false;
        try {
            int insertResult=0;
            UserExt userExt = new UserExt();
            userExt.setUserId(uccUsers.getUserId());
            List<UserExt> queryList=userExtService.findByUserId(userExt);
            if(queryList.size()>0){
                int delResult = userExtService.del(userExt);
                if(delResult>0){
                    for (UserExt userEx : userExtList) {
                        insertResult= userExtService.insert(userEx);
                    }
                    if(insertResult!=0||userExtList==null||userExtList.size()==0){
                        result=true;
                    }
                }
            }else {
                for (UserExt userEx : userExtList) {
                    insertResult= userExtService.insert(userEx);
                }
                if(insertResult!=0||userExtList==null||userExtList.size()==0){
                    result=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result){
            return new ResultBean<>(ResultBean.SUCCESS,"分配成功！",result);
        }
        return new ResultBean<>(ResultBean.FAIL,"分配失败！",result);
    }

    @ResponseBody
    @RequestMapping(value = "/chat/login", produces = "application/json;charset=UTF-8", method=RequestMethod.GET)
    public JSONObject login(HttpServletRequest request, ObjectMapper mapper) {
        ChatLogin chatLogin = new ChatLogin();
        JSONObject re = chatLogin.login(request, mapper, redisTemplate);
        return re;
    }
}
