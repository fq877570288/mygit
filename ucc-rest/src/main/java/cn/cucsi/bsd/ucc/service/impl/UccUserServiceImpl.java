package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.data.repo.UccDeptsRepository;
import cn.cucsi.bsd.ucc.data.repo.UccUserRepository;
import cn.cucsi.bsd.ucc.data.repo.UserDeptRepository;
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

import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    @Override
    public Page<UccUsers> findAll(UccUserCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "updatedTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccUserRepository.findAll(UccUserSpecs.createSpec(criteria), pageable);
    }

    @Override
    public List<UccUsers> findAllList(UccUserCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "updatedTime");
        return uccUserRepository.findAll(UccUserSpecs.createSpec(criteria));
    }


    @Override
    public UccUsers findOne(String userId) {
        return this.uccUserRepository.findOne(userId);
    }

    @Override
    @Transactional
    public UccUsers save(UccUsers uccUsers) {
        String userId;
        Date currTime = new Date();
        uccUsers.setLastLoginTime(currTime);
        uccUsers.setLastOperateTime(currTime);
        uccUsers.setLockTime(currTime);
        uccUsers.setRegTime(currTime);
        UccUsers uccUsersD = new UccUsers();

        if(uccUsers.getUserId() == null || uccUsers.getUserId() == ""){
            uccUsersD =  this.uccUserRepository.save(uccUsers);
            userId = uccUsersD.getUserId();
        }
        else {
            userId = uccUsers.getUserId();
        }

        //清空对应的部门关联信息后添加新的对应信息
        if(uccUsers.getUserDepts() != null){
            UserDeptCriteria userDeptCriteria = new UserDeptCriteria();
            userDeptCriteria.setUserId(userId);
            userDeptCriteria.setPage(0);
            userDeptCriteria.setSize(999);
            Page<UserDept> list = userDeptService.findAll(userDeptCriteria);
            if(list.getContent() != null && list.getContent().size() > 0){
                List<UserDept> userDepts = list.getContent();
                for(int i = 0; i < userDepts.size(); i++){
                    UserDeptPK userDeptPK = new UserDeptPK();
                    userDeptPK.setUserId(userId);
                    userDeptPK.setDeptId(userDepts.get(i).getDeptId());
                    userDeptService.delete(userDeptPK);
                }
            }
            Collection<UserDept> depts = uccUsers.getUserDepts();
            java.util.Iterator dept = depts.iterator();

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
            UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
            userRoleCriteria.setUserId(userId);
            List<UserRole> list = userRoleService.findAll(userRoleCriteria);
            for(int i = 0; i < list.size(); i++){
                UserRolePK userRolePK = new UserRolePK();
                userRolePK.setUserId(userId);
                userRolePK.setRoleId(list.get(i).getRoleId());
                userRoleService.delete(userRolePK);
            }

            Collection<UserRole> roles = uccUsers.getUserRoles();
            java.util.Iterator role = roles.iterator();
            while(role.hasNext()){
                UserRole ur = (UserRole) role.next();
                ur.setUserId(userId);
//                ur.setCreatedTime(currTime);
//                ur.setUpdatedTime(currTime);
//                ur.setUpdatedPerson(uccUsers.getUpdatedPerson());

                userRoleService.save(ur);
            }
        }

        //添加或修改分机号码关联信息
        if(uccUsers.getUserExt() != null){
            UserExt userExt = new UserExt();
            String extId = uccUsers.getUserExt().getExtId();

            userExt.setUserId(userId);
            userExt.setExtId(extId);
            userExt.setCreatedTime(currTime);
            userExt.setUpdatedTime(currTime);
//            userExt.setUpdatedPerson(uccUsers.getUpdatedPerson());

            userExtService.save(userExt);
        }

        if(uccUsers.getUserId() == null || uccUsers.getUserId() == ""){
            return uccUsersD;
        }
        else{
            return this.uccUserRepository.save(uccUsers);
        }
        //return this.uccUserRepository.save(uccUsers);

    }

    @Override
    public Boolean delete(String userId) {
        this.uccUserRepository.delete(userId);
        return true;
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
        if(MyUtils.isBlank(userLoginForAPPCriteria.getUserName())||MyUtils.isBlank(userLoginForAPPCriteria.getPassword())){
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
}
