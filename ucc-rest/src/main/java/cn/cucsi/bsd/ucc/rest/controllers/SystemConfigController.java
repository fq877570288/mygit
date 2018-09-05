package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PermissionGroupsCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.SystemConfigCriteria;
import cn.cucsi.bsd.ucc.data.domain.PermissionGroups;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.SystemConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/13.
 */

@RestController
@RequestMapping(value = "/systemConfig")
public class SystemConfigController {
    @Autowired
    SystemConfigService systemConfigService;

    @ApiOperation(value="根据查询条件获取系统配置列表", notes="根据查询条件获取系统配置列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public PageResultBean<List<SystemConfig>> findAll(@ModelAttribute SystemConfigCriteria search) {
        return new PageResultBean(this.systemConfigService.findAll(search));
    }

    @ApiOperation(value = "根据name查询SystemConfig", notes = "根据name查询SystemConfig")
    @RequestMapping(value = "/{name}", method= RequestMethod.GET)
    public ResultBean<SystemConfig> findOne(@PathVariable String name){
        return new ResultBean<>(this.systemConfigService.findOne(name));
    }

    @ApiOperation(value = "根据name删除SystemConfig", notes = "根据name删除SystemConfig")
    @RequestMapping(value = "/{name}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String name){
        return new ResultBean<>(this.systemConfigService.delete(name));
    }

    @ApiOperation(value = "创建SystemConfig", notes = "创建SystemConfig")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody SystemConfig systemConfig) {
        boolean result = this.systemConfigService.save(systemConfig) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改SystemConfig", notes = "修改SystemConfig")
    @RequestMapping(value = "/{name}",method =  RequestMethod.PUT)
    public ResultBean<SystemConfig> save(@PathVariable String name, @RequestBody SystemConfig systemConfig){
        return new ResultBean<>(this.systemConfigService.save(systemConfig));
    }
}
