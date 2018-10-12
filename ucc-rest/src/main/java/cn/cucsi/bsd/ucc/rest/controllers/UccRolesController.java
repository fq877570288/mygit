package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccRolesCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccRoles;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccRolesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.cucsi.bsd.ucc.data.domain.RolesPermissions;
import cn.cucsi.bsd.ucc.service.RolesPermissionsService;

import java.util.Date;
import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/uccRoles")
public class UccRolesController {
    @Autowired
    UccRolesService uccRolesService;
    
    @Autowired
    private RolesPermissionsService rolesPermissionsService;

    @ApiOperation(value="根据查询条件获取角色列表", notes="根据查询条件获取角色列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<UccRoles>> findAll(@RequestBody UccRolesCriteria search){
        return new PageResultBean(this.uccRolesService.findAll(search));
    }

    @ApiOperation(value = "根据roleId查询UccRoles", notes = "根据roleId查询UccRoles")
    @RequestMapping(value = "/{roleId}", method= RequestMethod.GET)
    public ResultBean<UccRoles> findOne(@PathVariable String roleId){
        return new ResultBean<>(this.uccRolesService.findOne(roleId));
    }

    @ApiOperation(value = "根据roleId删除UccRoles", notes = "根据roleId删除UccRoles")
    @RequestMapping(value = "/{roleId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String roleId){
        return new ResultBean<>(this.uccRolesService.delete(roleId));
    }


    @ApiOperation(value = "创建UccRoles", notes = "创建UccRoles")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccRoles uccRoles) {
        UccRolesCriteria uccRolesCriteria = new UccRolesCriteria();
        uccRolesCriteria.setRoleName(uccRoles.getRoleName());
        List<UccRoles> uccRolesList = this.uccRolesService.findByRoleName(uccRolesCriteria);
        Boolean resultByName = uccRolesList.size()==0;
        if(!resultByName){
            return new ResultBean<>(ResultBean.FAIL,"角色名重复！",resultByName);
        }else {
            Date dateTime = new Date();
            uccRoles.setCreatedTime(dateTime);
            UccRoles queues = this.uccRolesService.save(uccRoles);
            boolean result =queues != null;
            if(result){
                String[] permissionsIds = uccRoles.getPermissions();
                result = false ;
                for (String permissionsId:permissionsIds) {
                    RolesPermissions  rolesPermissions = new RolesPermissions();
                    rolesPermissions.setPermissionId(permissionsId);
                    rolesPermissions.setRoleId(queues.getRoleId());
                    result = this.rolesPermissionsService.save(rolesPermissions)!=null;
                }
                return new ResultBean<>(result);
            }else{
                return new ResultBean<>(result);
            }
        }
    }

    @ApiOperation(value = "修改UccRoles", notes = "修改UccRoles")
    @RequestMapping(value = "/{roleId}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String roleId,@RequestBody UccRoles uccRoles){
        Date dateTime = new Date();
        uccRoles.setUpdatedTime(dateTime);
        UccRoles queues = this.uccRolesService.save(uccRoles);
        boolean result =queues != null;
        if(result){
            String[] permissionsIds = uccRoles.getPermissions();
            result = false ;
            for (String permissionsId:permissionsIds) {
                RolesPermissions  rolesPermissions = new RolesPermissions();
                rolesPermissions.setPermissionId(permissionsId);
                rolesPermissions.setRoleId(queues.getRoleId());
                result = this.rolesPermissionsService.save(rolesPermissions)!=null;
            }
            return new ResultBean<>(result);
        }else{
            return new ResultBean<>(result);
        }
    }

}
