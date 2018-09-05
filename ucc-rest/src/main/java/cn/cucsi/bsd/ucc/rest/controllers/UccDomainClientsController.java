package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccDomainClientsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomainClients;
import cn.cucsi.bsd.ucc.data.domain.UccDomainClientsPK;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccDomainClientsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@RestController
@RequestMapping(value = "/uccDomainClients")
public class UccDomainClientsController {
    @Autowired
    UccDomainClientsService uccDomainClientsService;

    @ApiOperation(value="根据查询条件获取客户端和域对应列表", notes="根据查询条件获取客户端和域对应列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public PageResultBean<List<UccDomainClients>> findAll(@ModelAttribute UccDomainClientsCriteria search) {
        Page<UccDomainClients> docmain =this.uccDomainClientsService.findAll(search);
        return new PageResultBean(this.uccDomainClientsService.findAll(search));
    }

    @ApiOperation(value = "根据UccDomainClientsPK查询UccDomainClients", notes = "根据UccDomainClientsPK查询UccDomainClients")
    @RequestMapping(value = "/{name}/{domainId}", method= RequestMethod.GET)
    public ResultBean<UccDomainClients> findOne(@PathVariable String name, @PathVariable String domainId){
        UccDomainClientsPK pk = new UccDomainClientsPK();
        pk.setDomainId(domainId);
        pk.setName(name);
        return new ResultBean<>(this.uccDomainClientsService.findOne(pk));
    }

    @ApiOperation(value = "根据UccDomainClientsPK删除UccDomainClients", notes = "根据UccDomainClientsPK删除UccDomainClients")
    @RequestMapping(value = "/{name}/{domainId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String name, @PathVariable String domainId){
        UccDomainClientsPK pk = new UccDomainClientsPK();
        pk.setDomainId(domainId);
        pk.setName(name);
        return new ResultBean<>(this.uccDomainClientsService.delete(pk));
    }

    @ApiOperation(value = "创建UccDomainClients", notes = "创建UccDomainClients")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccDomainClients uccDomainClients) {
        boolean result = this.uccDomainClientsService.save(uccDomainClients) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccDomainClients", notes = "修改UccDomainClients")
    @RequestMapping(value = "/{name}/{domainId}", method =  RequestMethod.PUT)
    public ResultBean<UccDomainClients> save(@PathVariable String name, @PathVariable String domainId, @RequestBody UccDomainClients uccDomainClients){
        UccDomainClientsPK pk = new UccDomainClientsPK();
        pk.setDomainId(domainId);
        pk.setName(name);

        return new ResultBean<>(this.uccDomainClientsService.save(uccDomainClients));
    }
}
