package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.mapper.UccUsersMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
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

import java.util.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<String> selectSameDeptUserIdByUserId(String userId) throws Exception {
        return uccUsersMapper.selectSameDeptUserIdByUserId(userId);

    }
}
