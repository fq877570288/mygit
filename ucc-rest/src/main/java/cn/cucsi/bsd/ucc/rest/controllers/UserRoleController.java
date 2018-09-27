package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.common.beans.UserRoleCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.data.domain.UserRole;
import cn.cucsi.bsd.ucc.data.domain.UserRolePK;
import cn.cucsi.bsd.ucc.service.UserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value="根据查询条件获取用户角色表", notes="根据查询条件获取用户角色表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public ResultBean<List<UserRole>> findAll(@RequestBody  UserRoleCriteria search){
        return new ResultBean(this.userRoleService.findAll(search));
    }
    @ApiOperation(value = "根据userId、roleId查询UserRole", notes = "根据userId、roleId查询UserRole")
    @RequestMapping(value = "/{userId}/{roleId}", method= RequestMethod.POST)
    public ResultBean<UserRole> findOne(@RequestBody String userId,@PathVariable String roleId){
        UserRolePK userRolePK=new UserRolePK();
        userRolePK.setUserId(userId);
        userRolePK.setRoleId(roleId);
        return new ResultBean<>(this.userRoleService.findOne(userRolePK));
    }
    @ApiOperation(value = "根据userId、roleId删除UserRole", notes = "根据userId、roleId删除UserRole")
    @RequestMapping(value = "/{userId}/{roleId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String userId,@PathVariable String roleId){
        UserRolePK userRolePK=new UserRolePK();
        userRolePK.setUserId(userId);
        userRolePK.setRoleId(roleId);
        return new ResultBean<>(this.userRoleService.delete(userRolePK));
    }
    @ApiOperation(value = "创建UserRole", notes = "创建UserRole")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UserRole userRole) {
        boolean result=this.userRoleService.save(userRole)!=null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改UserRole", notes = "修改UserRole")
    @RequestMapping(value = "/{userId}/{roleId}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String userId,@PathVariable String roleId, @RequestBody UserRole userRole){
        boolean result=this.userRoleService.save(userRole)!=null;
        return new ResultBean<>(result);
    }
}
