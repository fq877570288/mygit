package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxCallTransferCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.untils.PbxReload;
import cn.cucsi.bsd.ucc.common.untils.ZooKeeperUtils;
import cn.cucsi.bsd.ucc.data.domain.PbxCallTransfer;
import cn.cucsi.bsd.ucc.service.PbxCallTransferService;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
    @Autowired
    private ZooKeeperUtils zk;

    @ApiOperation(value="根据查询条件获取呼转表", notes="根据查询条件获取呼转表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<PbxCallTransfer>> findAll(@RequestBody PbxCallTransferCriteria pbxCallTransferCriteria){
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
        boolean result = this.pbxCallTransferService.delete(extId);
        if(result){
            PbxCallTransfer pbxCallTransfer = new PbxCallTransfer();
            pbxCallTransfer.setExtId(extId);
            PbxReload.reloadCallTransfer(pbxCallTransfer, "delete", zk);
        }
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "创建PbxCallTransfer", notes = "创建PbxCallTransfer")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxCallTransfer pbxCallTransfer) {
        Date dateTime = new Date();
        pbxCallTransfer.setCreatedTime(dateTime);
        String extIds = pbxCallTransfer.getExtId();
        boolean result = false;
        for(String extId : extIds.split(","))
        {
            pbxCallTransfer.setExtId(extId);
            result = this.pbxCallTransferService.save(pbxCallTransfer) != null;
            if(result){
                PbxReload.reloadCallTransfer(pbxCallTransfer, "replace", zk);
            }
            
        }
       return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxCallTransfer", notes = "修改PbxCallTransfer")
    @RequestMapping(value = "/{extId}", method =  RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String extId,@RequestBody PbxCallTransfer pbxCallTransfer) {
        Date dateTime = new Date();
        pbxCallTransfer.setUpdatedTime(dateTime);
        boolean result = this.pbxCallTransferService.save(pbxCallTransfer) != null;
        if(result){
            PbxReload.reloadCallTransfer(pbxCallTransfer, "update", zk);
        }
        return new ResultBean<>(result);
    }
}
