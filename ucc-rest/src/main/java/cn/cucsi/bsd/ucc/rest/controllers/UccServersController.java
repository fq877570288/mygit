package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccServersCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccServers;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccServersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/uccServers")
public class UccServersController {
    @Autowired
    UccServersService uccServersService;


    @ApiOperation(value="根据查询条件获取服务端地址信息表", notes="根据查询条件获取服务端地址信息表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<UccServers>> findAll(@RequestBody UccServersCriteria search){
        return new PageResultBean(this.uccServersService.findAll(search));
    }

    @ApiOperation(value = "根据serverName查询UccServers", notes = "根据serverName查询UccServers")
    @RequestMapping(value = "/{serverName}", method= RequestMethod.GET)
    public ResultBean<UccServers> findOne(@PathVariable String serverName){
        return new ResultBean<>(this.uccServersService.findOne(serverName));
    }

    @ApiOperation(value = "根据serverName删除UccServers", notes = "根据serverName删除UccServers")
    @RequestMapping(value = "/{serverName}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String serverName){
        return new ResultBean<>(this.uccServersService.delete(serverName));
    }

    @ApiOperation(value = "创建UccServers", notes = "创建UccServers")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody  UccServers uccServers) {
        boolean result=this.uccServersService.save(uccServers)!=null;
        return new ResultBean<>(result);
    }


    @ApiOperation(value = "修改UccServers", notes = "修改UccServers")
    @RequestMapping(value = "/{serverName}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String serverName, @RequestBody UccServers uccServers){
        boolean result=this.uccServersService.save(uccServers)!=null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "删除全部UccServers", notes = "删除全部UccServers")
    @RequestMapping(value = "/deleteAll",method = RequestMethod.DELETE)
    public ResultBean<Boolean> deleteAll(){
        return new ResultBean<>(this.uccServersService.deleteAll());
    }
}
