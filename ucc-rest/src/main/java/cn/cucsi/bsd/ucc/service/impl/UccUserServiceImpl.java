package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.mapper.UccUsersMapper;
import cn.cucsi.bsd.ucc.common.mapper.UserRoleMapper;
import cn.cucsi.bsd.ucc.common.untils.ImgUtil;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.data.repo.UccUserRepository;
import cn.cucsi.bsd.ucc.service.UccUserService;
import cn.cucsi.bsd.ucc.data.specs.UccUserSpecs;
import cn.cucsi.bsd.ucc.service.UserDeptService;
import cn.cucsi.bsd.ucc.service.UserExtService;
import cn.cucsi.bsd.ucc.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Encoder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

@Service
public class UccUserServiceImpl implements UccUserService{

    @Autowired
    private UccUserRepository uccUserRepository;
    @Autowired
    private UserDeptService userDeptService;
    @Autowired
    private UserExtService userExtService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UccUsersMapper uccUsersMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    private static Logger logger = Logger.getLogger(UccUserServiceImpl.class);

    @Override
    public Page<UccUsers> findAll(UccUserCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccUserRepository.findAll(UccUserSpecs.createSpec(criteria), pageable);
    }

    @Override
    public List<UccUsers> findAllList(UccUserCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "updatedTime");
        return uccUserRepository.findAll(UccUserSpecs.createSpec(criteria));
    }

    @Override
    public List<UccUsers> loginList(UccUserCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "updatedTime");
        return uccUserRepository.findAll(UccUserSpecs.loginCreateSpec(criteria));
    }

    @Override
    public UccUsers findOne(String userId) {
        return this.uccUserRepository.findOne(userId);
    }

    @Override
    public UccUsers save(UccUsers uccUsers) {
        return this.uccUserRepository.save(uccUsers);
    }

    @Override
    public UccUsers saveAll(UccUsers uccUsers,UccUsers targetUser) {
        String userId;
        UccUsers uccUsersD = new UccUsers();
        Collection<UccDepts> depts = uccUsers.getDepts();
        Collection<UccRoles> roles = uccUsers.getRoles();
        Date currTime = new Date();
        uccUsers.setLastLoginTime(currTime);
        uccUsers.setLastOperateTime(currTime);
        uccUsers.setLockTime(currTime);
        uccUsers.setRegTime(currTime);
        if(uccUsers.getUserId() == null || uccUsers.getUserId() == ""){
            uccUsersD =  this.uccUserRepository.save(uccUsers);
        }
        else {
            userId = uccUsers.getUserId();
            UpdateUtil.copyNullProperties(targetUser,uccUsers);
            boolean flag = this.uccUsersMapper.updateByPrimaryKeySelective(uccUsers)>0;
            if(flag){
                uccUsersD = uccUsers;
            }
            if(targetUser.getUserDepts()!=null&&targetUser.getUserDepts().size()>0){
                userDeptService.deleteByUserId(userId);
            }
            if(targetUser.getUserRoles()!=null&&targetUser.getUserRoles().size()>0&&roles!=null&&roles.size()>0) {
                userRoleService.deleteByUserId(userId);
            }
            if(depts!=null&&depts.size()>0){
                for (UccDepts dept : depts) {
                    if(dept!=null){
                        UserDeptKey userDept = new UserDeptKey();
                        userDept.setUserId(userId);
                        userDept.setDeptId(dept.getDeptId());
                        userDeptService.insert(userDept);
                    }
                }
            }
            if(roles!=null&&roles.size()>0){
                for (UccRoles role : roles) {
                    if(role!=null){
                        UserRole userRole = new UserRole();
                        userRole.setUserId(userId);
                        userRole.setRoleId(role.getRoleId());
                        userRoleMapper.insert(userRole);
                    }
                }
            }
        }
        return uccUsersD;
    }

    @Override
    public Boolean delete(String userId) {
        Boolean result = false;
        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
        userRoleCriteria.setUserId(userId);
        List<UserRole> roles = userRoleService.findAll(userRoleCriteria);
        for (UserRole role : roles) {
            UserRolePK userRolePK = new UserRolePK();
            userRolePK.setUserId(userId);
            userRolePK.setRoleId(role.getRoleId());
            userRoleService.delete(userRolePK);
        }
        UserDeptCriteria userDeptCriteria = new UserDeptCriteria();
        userDeptCriteria.setUserId(userId);
        Page<UserDept> userDepts = userDeptService.findAll(userDeptCriteria);
        for (UserDept userDept : userDepts) {
            UserDeptPK userDeptPK = new UserDeptPK();
            userDeptPK.setUserId(userId);
            userDeptPK.setDeptId(userDept.getDeptId());
            userDeptService.delete(userDeptPK);
        }
        return result;
    }


    @Override
    @Transactional
    public Boolean multiDelete(String userIds) {
        if(userIds.contains(",")){
            String[] ids = userIds.split(",");
            if(ids.length > 0){
                for(int i = 0; i < ids.length; i++){
                    this.uccUserRepository.delete(ids[i]);
                }
            }
        }
        else{
            this.uccUserRepository.delete(userIds);
        }

        return true;
    }

    /**
     * 查询坐席数
     * */
    @Override
    public List<UccUsers> querySeater(UccUserCriteria uccUserCriteria) {
        return uccUsersMapper.querySeater(uccUserCriteria);
    }

    /***
     * 根据用户名、密码获取用户列表（APP登录用）
     * add by wangxiaoyu
     * 2018-09-10
     */
    @Override
    public ResultBean_New<UccUsers> userLoginForAPP(UserLoginForAPPCriteria userLoginForAPPCriteria){
        ResultBean_New<UccUsers> resultBean = new ResultBean_New<>();
        //初始化赋值
        resultBean.setReturnMsg("操作失败！");
        resultBean.setReturnCode(ResultBean_New.FAIL);
        if(MyUtils.isBlank(userLoginForAPPCriteria.getUserName())|| MyUtils.isBlank(userLoginForAPPCriteria.getPassword())){
            resultBean.setReturnMsg("用户名或密码为空！");
            return resultBean;
        }
        UccUsers uccUsers = null;
        try {
            uccUsers = uccUsersMapper.userLoginForAPP(userLoginForAPPCriteria);
            if(MyUtils.isBlank(uccUsers)){
                resultBean.setReturnMsg("用户名或密码错误！");
                return resultBean;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setReturnMsg("APP根据用户名、密码登录出现异常！");
            return resultBean;
        }
        resultBean.setData(uccUsers);
        resultBean.setReturnMsg("登录成功！");
        return resultBean;
    }
    @Override
    public void saveMiddleTable(UccUsers uccUsers){
        String userId = uccUsers.getUserId();
        //清空对应的部门关联信息后添加新的对应信息
        if(uccUsers.getUserDepts() != null){
            Collection<UserDept> depts = uccUsers.getUserDepts();
            Iterator dept = depts.iterator();

            while(dept.hasNext()){
                UserDept ud = (UserDept) dept.next();
                ud.setUserId(userId);
//                ud.setCreatedTime(currTime);
//                ud.setUpdatedTime(currTime);
//                ud.setUpdatedPerson(uccUsers.getUpdatedPerson());

                userDeptService.save(ud);
            }
        }

        //清空角色保存新的角色信息
        if(uccUsers.getUserRoles() != null){
            Collection<UserRole> roles = uccUsers.getUserRoles();
            Iterator role = roles.iterator();
            while(role.hasNext()){
                UserRole ur = (UserRole) role.next();
                ur.setUserId(userId);
//                ur.setCreatedTime(currTime);
//                ur.setUpdatedTime(currTime);
//                ur.setUpdatedPerson(uccUsers.getUpdatedPerson());

                userRoleService.save(ur);
            }
        }
    }

    @Override
    public void cusfsSave(Map<String, Object> map) throws Exception {
        uccUsersMapper.cusfsSave(map);
    }

    @Override
    public UccUsers selectByPrimaryKey(String userId) throws Exception {
        return uccUsersMapper.selectByPrimaryKey(userId);
    }



    /**
     * 根据部门信息查询用户列表
     * @param userDeptCriterias
     * @return
     */
    public ResultBean_New<List<UccUserByDept>> userListByDept(List<UserDeptCriteria> userDeptCriterias) {
        ResultBean_New<List<UccUserByDept>> resultBean = new ResultBean_New<>();

        if (userDeptCriterias != null && userDeptCriterias.size() > 0) {
            List<UccUserByDept> uccUserByDeptList = new ArrayList<>();
            for (int size = 0; size < userDeptCriterias.size(); size++) {
                UserDeptCriteria userDeptCriteria = userDeptCriterias.get(size);

                UccUserByDept uccUserByDept = new UccUserByDept();
                uccUserByDept.setDeptId(userDeptCriteria.getDeptId());
                uccUserByDept.setDeptName(userDeptCriteria.getDeptName());
                List<UccUsers> uccUsers = uccUsersMapper.selectByDept(userDeptCriteria);

                if (uccUsers != null && uccUsers.size() > 0) {
                    List<UccUsers> users = new ArrayList<>();
                    for (int i = 0; i < uccUsers.size(); i++) {
                        UccUsers user = new UccUsers();
                        user.setUserId(uccUsers.get(i).getUserId());
                        user.setUserName(uccUsers.get(i).getUserName());
                        user.setNickName(uccUsers.get(i).getNickName());
                        users.add(user);
                    }
                    uccUserByDept.setUsers(users);
                }
                uccUserByDeptList.add(uccUserByDept);
            }
            resultBean.setData(uccUserByDeptList);
        }

        return resultBean;
    }

    @Override
    public List<UccUsers> selectSameDeptUserIdByUserId(String userId) throws Exception {
        return uccUsersMapper.selectSameDeptUserIdByUserId(userId);

    }

    /**
     * 获取验证码图片和ID
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login/verification/image",method= RequestMethod.GET,
            produces="application/json;charset=utf-8")
    public ResultBean<VerificationCode> VerificationImg()
            throws IOException {
        ImgUtil imgUtil = new ImgUtil();
        BufferedImage image = imgUtil.getImage();
        String text = imgUtil.getText();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        VerificationCode alidateCodeBean = new VerificationCode();
        ResultBean resultBean = new ResultBean();
        String ImgId = "";
        imgUtil.output(image, outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Img = encoder.encode(outputStream.toByteArray());
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        ImgId = uuidGenerator.generate();
        alidateCodeBean.setImgId(ImgId);
        alidateCodeBean.setImgBase64(base64Img);
        alidateCodeBean.setText(text);
        resultBean.setData(alidateCodeBean);
        logger.info("验证码内容："+text);
        logger.info("验证码ID："+ImgId);
        return resultBean;

    }
}
