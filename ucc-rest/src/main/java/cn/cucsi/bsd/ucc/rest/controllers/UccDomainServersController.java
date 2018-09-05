package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccDomainClientsCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccDomainServersCriteria;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.UccDomainClientsService;
import cn.cucsi.bsd.ucc.service.UccDomainServersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@RestController
@RequestMapping(value = "/uccDomainServers")
public class UccDomainServersController {
    @Autowired
    UccDomainServersService uccDomainServersService;

    @ApiOperation(value="根据查询条件获取服务域对应列表", notes="根据查询条件获取服务域对应列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public PageResultBean<List<UccDomainServers>> findAll(@ModelAttribute UccDomainServersCriteria search) {
        return new PageResultBean(this.uccDomainServersService.findAll(search));
    }

    @ApiOperation(value = "根据UccDomainServersPK查询UccDomainServers", notes = "根据UccDomainServersPK查询UccDomainServers")
    @RequestMapping(value = "/{serverName}/{domainId}", method= RequestMethod.GET)
    public ResultBean<UccDomainServers> findOne(@PathVariable String serverName, @PathVariable String domainId){
        UccDomainServersPK pk = new UccDomainServersPK();
        pk.setDomainId(domainId);
        pk.setServerName(serverName);

        return new ResultBean<>(this.uccDomainServersService.findOne(pk));
    }

    @ApiOperation(value = "根据UccDomainServersPK删除UccDomainServers", notes = "根据UccDomainServersPK删除UccDomainServers")
    @RequestMapping(value = "/{serverName}/{domainId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String serverName, @PathVariable String domainId){
        UccDomainServersPK pk = new UccDomainServersPK();
        pk.setDomainId(domainId);
        pk.setServerName(serverName);

        return new ResultBean<>(this.uccDomainServersService.delete(pk));
    }

    @ApiOperation(value = "创建UccDomainServers", notes = "创建UccDomainServers")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccDomainServers uccDomainServers) {
        boolean result = this.uccDomainServersService.save(uccDomainServers) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccDomainServers", notes = "修改UccDomainServers")
    @RequestMapping(value = "/{serverName}/{domainId}", method =  RequestMethod.PUT)
    public ResultBean<UccDomainServers> save(@PathVariable String serverName, @PathVariable String domainId, @RequestBody UccDomainServers uccDomainServers){
        UccDomainServersPK pk = new UccDomainServersPK();
        pk.setDomainId(domainId);
        pk.setServerName(serverName);

        return new ResultBean<>(this.uccDomainServersService.save(uccDomainServers));
    }
}
