package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PermissionGroupsCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.PermissionGroups;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.PermissionGroupsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/13.
 */

@RestController
@RequestMapping(value = "/prmissionGroups")
public class PermissionGroupsController {

    @Autowired
    PermissionGroupsService permissionGroupsService;

    @ApiOperation(value="根据查询条件获取权限分组列表", notes="根据查询条件获取权限分组列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResultBean<List<PermissionGroups>> findAll(@ModelAttribute PermissionGroupsCriteria search) {
        return new ResultBean(this.permissionGroupsService.findAll(search));
    }

    @ApiOperation(value = "根据permissionGroupId查询PermissionGroups", notes = "根据permissionGroupId查询PermissionGroups")
    @RequestMapping(value = "/{permissionGroupId}", method= RequestMethod.GET)
    public ResultBean<PermissionGroups> findOne(@PathVariable String permissionGroupId){
        return new ResultBean<>(this.permissionGroupsService.findOne(permissionGroupId));
    }

    @ApiOperation(value = "根据permissionGroupId删除PermissionGroups", notes = "根据permissionGroupId删除PermissionGroups")
    @RequestMapping(value = "/{permissionGroupId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String permissionGroupId){
        return new ResultBean<>(this.permissionGroupsService.delete(permissionGroupId));
    }

    @ApiOperation(value = "修改PermissionGroups", notes = "修改PermissionGroups")
    @RequestMapping(value = "/{permissionGroupId}", method = RequestMethod.PUT)
    public ResultBean<PermissionGroups> save(@PathVariable String permissionGroupId, @RequestBody PermissionGroups permissionGroups){
        return new ResultBean<>(this.permissionGroupsService.save(permissionGroups));
    }

    @ApiOperation(value = "创建PermissionGroups", notes = "创建PermissionGroups")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PermissionGroups permissionGroups) {
        boolean result = this.permissionGroupsService.save(permissionGroups) != null;
        return new ResultBean<>(result);
    }

}
