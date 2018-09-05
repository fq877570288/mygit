package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
import cn.cucsi.bsd.ucc.service.PbxCdrsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/pbxCdrs")
public class PbxCdrsController {

    @Autowired
    private PbxCdrsService PbxCdrsService;

    @ApiOperation(value="根据查询条件获取通话详单表", notes="根据查询条件获取通话详单表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<PbxCdrs>> findAll(@RequestBody PbxCdrsCriteria PbxCdrsCriteria){
        return new PageResultBean(this.PbxCdrsService.findAll(PbxCdrsCriteria));
    }
    @ApiOperation(value = "根据cdrId查询PbxCdrs", notes = "根据cdrId查询PbxCdrs")
    @RequestMapping(value = "/{cdrId}", method= RequestMethod.GET)
    public ResultBean<PbxCdrs> findOne(@PathVariable String cdrId){
        return new ResultBean<>(this.PbxCdrsService.findOne(cdrId));
    }
    @ApiOperation(value = "根据cdrId删除PbxCdrs", notes = "根据cdrId删除PbxCdrs")
    @RequestMapping(value = "/{cdrId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String cdrId){
        return new ResultBean<>(this.PbxCdrsService.delete(cdrId));
    }
    @ApiOperation(value = "创建PbxCdrs", notes = "创建PbxCdrs")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxCdrs pbxCdrs) {
        boolean result = this.PbxCdrsService.save(pbxCdrs) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxCdrs", notes = "修改PbxCdrs")
    @RequestMapping(value = "/{cdrId}", method =  RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String cdrId,@RequestBody PbxCdrs pbxCdrs) {
        boolean result = this.PbxCdrsService.save(pbxCdrs) != null;
        return new ResultBean<>(result);
    }


}
