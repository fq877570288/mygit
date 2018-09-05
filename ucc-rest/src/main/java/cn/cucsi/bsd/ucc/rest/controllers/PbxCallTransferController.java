package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxCallTransferCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxCallTransfer;
import cn.cucsi.bsd.ucc.service.PbxCallTransferService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/pbxCallTransfer")
public class PbxCallTransferController {

    @Autowired
    private PbxCallTransferService pbxCallTransferService;
    @ApiOperation(value="根据查询条件获取呼转表", notes="根据查询条件获取呼转表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<PbxCallTransfer>> findAll(@ModelAttribute PbxCallTransferCriteria pbxCallTransferCriteria){
        return new PageResultBean(this.pbxCallTransferService.findAll(pbxCallTransferCriteria));
    }
    @ApiOperation(value = "根据extId查询PbxCallTransfer", notes = "根据extId查询PbxCallTransfer")
    @RequestMapping(value = "/{extId}", method= RequestMethod.GET)
    public ResultBean<PbxCallTransfer> findOne(@PathVariable String extId){
        return new ResultBean<>(this.pbxCallTransferService.findOne(extId));
    }
    @ApiOperation(value = "根据extId删除PbxCallTransfer", notes = "根据extId删除PbxCallTransfer")
    @RequestMapping(value = "/{extId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String extId){
        return new ResultBean<>(this.pbxCallTransferService.delete(extId));
    }
    @ApiOperation(value = "创建PbxCallTransfer", notes = "创建PbxCallTransfer")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxCallTransfer pbxCallTransfer) {
        boolean result = this.pbxCallTransferService.save(pbxCallTransfer) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxCallTransfer", notes = "修改PbxCallTransfer")
    @RequestMapping(value = "/{extId}", method =  RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String extId,@RequestBody PbxCallTransfer pbxCallTransfer) {
        boolean result = this.pbxCallTransferService.save(pbxCallTransfer) != null;
        return new ResultBean<>(result);
    }
}
