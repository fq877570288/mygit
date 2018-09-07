package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccClientsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccClients;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccClientsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/13.
 */

@RestController
@RequestMapping(value = "/uccClients")
public class UccClientsController {
    @Autowired
    UccClientsService uccClientsService;

    @ApiOperation(value="根据查询条件获取客户端列表", notes="根据查询条件获取客户端列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public PageResultBean<List<UccClients>> findAll(@RequestBody UccClientsCriteria search) {
        return new PageResultBean(this.uccClientsService.findAll(search));
    }
    @ApiOperation(value="根据查询条件获取客户端列表", notes="根据查询条件获取客户端列表", httpMethod = "POST")
    @RequestMapping(value = "/findAllOfNoPage", method = RequestMethod.POST)
    public ResultBean<List<UccClients>> findAllOfNoPage(@RequestBody UccClientsCriteria search) {
        return new ResultBean(this.uccClientsService.findAllOfNoPage(search));
    }

    @ApiOperation(value = "根据name查询UccClients", notes = "根据name查询UccClients")
    @RequestMapping(value = "/{name}", method= RequestMethod.GET)
    public ResultBean<UccClients> findOne(@PathVariable String name){
        return new ResultBean<>(this.uccClientsService.findOne(name));
    }

    @ApiOperation(value = "根据name删除UccClients", notes = "根据name删除UccClients")
    @RequestMapping(value = "/{name}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String name){
        return new ResultBean<>(this.uccClientsService.delete(name));
    }

    @ApiOperation(value = "创建UccClients", notes = "创建UccClients")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccClients uccClients) {
        boolean result = this.uccClientsService.save(uccClients) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccClients", notes = "修改UccClients")
    @RequestMapping(value = "/{name}",method =  RequestMethod.PUT)
    public ResultBean<UccClients> save(@PathVariable String name, @RequestBody UccClients uccClients){
        return new ResultBean<>(this.uccClientsService.save(uccClients));
    }

    @ApiOperation(value = "删除全部UccClients", notes = "删除全部UccClients")
    @RequestMapping(value = "/deleteAll",method =  RequestMethod.DELETE)
    public ResultBean<Boolean> deleteAll(){
        return new ResultBean<>(this.uccClientsService.deleteAll());
    }
}
