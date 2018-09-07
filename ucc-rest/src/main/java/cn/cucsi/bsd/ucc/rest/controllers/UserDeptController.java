package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;

import cn.cucsi.bsd.ucc.common.beans.UserDeptCriteria;

import cn.cucsi.bsd.ucc.data.domain.UserDept;
import cn.cucsi.bsd.ucc.data.domain.UserDeptPK;

import cn.cucsi.bsd.ucc.service.UserDeptService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/userDept")
public class UserDeptController {
    @Autowired
    private UserDeptService userDeptService;
    @ApiOperation(value="根据查询条件获取用户归属部门关系表", notes="根据查询条件获取用户归属部门关系表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<UserDept>> findAll(@RequestBody UserDeptCriteria search){
        return new PageResultBean(this.userDeptService.findAll(search));
    }
    @ApiOperation(value = "根据userId、deptId查询UserDept", notes = "根据userId、deptId查询UserDept")
    @RequestMapping(value = "/{userId}/{deptId}", method= RequestMethod.POST)
    public ResultBean<UserDept> findOne(@RequestBody String userId,@PathVariable String deptId){
        UserDeptPK userDeptPK=new UserDeptPK();
        userDeptPK.setUserId(userId);
        userDeptPK.setDeptId(deptId);
        return new ResultBean<>(this.userDeptService.findOne(userDeptPK));
    }
    @ApiOperation(value = "根据userId、deptId删除UserDept", notes = "根据userId、deptId删除UserDept")
    @RequestMapping(value = "/{userId}/{deptId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String userId,@PathVariable String deptId){
        UserDeptPK userDeptPK=new UserDeptPK();
        userDeptPK.setUserId(userId);
        userDeptPK.setDeptId(deptId);
        return new ResultBean<>(this.userDeptService.delete(userDeptPK));
    }
    @ApiOperation(value = "创建UserDept", notes = "创建UserDept")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UserDept userDept) {
        boolean result=this.userDeptService.save(userDept)!=null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改UserDept", notes = "修改UserDept")
    @RequestMapping(value = "/{UserDeptPK}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable UserDeptPK userDeptPK, @RequestBody UserDept userDept){
        boolean result=this.userDeptService.save(userDept)!=null;
        return new ResultBean<>(result);
    }
}
