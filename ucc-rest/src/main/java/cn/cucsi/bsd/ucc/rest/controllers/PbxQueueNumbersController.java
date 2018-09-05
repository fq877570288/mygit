package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxQueueNumbersCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxQueueNumbers;
import cn.cucsi.bsd.ucc.data.domain.PbxQueueNumbersPK;
import cn.cucsi.bsd.ucc.service.PbxQueueNumbersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/PbxQueueNumbers")
public class PbxQueueNumbersController {

    @Autowired
    private PbxQueueNumbersService PbxQueueNumbersService;

    @ApiOperation(value="根据查询条件获取队列与分机号对应关系列表", notes="根据查询条件获取队列与分机号对应关系列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public ResultBean<List<PbxQueueNumbers>> findAll(@ModelAttribute PbxQueueNumbersCriteria search){
        return new ResultBean<>(this.PbxQueueNumbersService.findAll(search));
    }
    @ApiOperation(value = "根据queueId、extId查询PbxQueueNumbers", notes = "根据queueId、extId查询PbxQueueNumbers")
    @RequestMapping(value = "/{queueId}/{extId}", method= RequestMethod.GET)
    public ResultBean<PbxQueueNumbers> findOne(@PathVariable String queueId,@PathVariable String extId){
        PbxQueueNumbersPK pk = new PbxQueueNumbersPK();
        pk.setExtId(extId);
        pk.setQueueId(queueId);
        return new ResultBean<>(this.PbxQueueNumbersService.findOne(pk));
    }
    @ApiOperation(value = "根据queueId、extId删除PbxQueueNumbers", notes = "根据queueId、extId删除PbxQueueNumbers")
    @RequestMapping(value = "/{queueId}/{extId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String queueId,@PathVariable String extId){
        PbxQueueNumbersPK pk = new PbxQueueNumbersPK();
        pk.setExtId(extId);
        pk.setQueueId(queueId);
        return new ResultBean<>(this.PbxQueueNumbersService.delete(pk));
    }
    @ApiOperation(value = "创建PbxQueueNumbers", notes = "创建PbxQueueNumbers")
    @RequestMapping(value = "" , method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxQueueNumbers PbxQueueNumbers) {
        boolean result = this.PbxQueueNumbersService.save(PbxQueueNumbers) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "删除PbxQueueNumbers", notes = "删除PbxQueueNumbers")
    @RequestMapping(value = "", method= RequestMethod.PUT)
    public ResultBean<Boolean> save(@RequestBody PbxQueueNumbers PbxQueueNumbers){

        boolean result = this.PbxQueueNumbersService.save(PbxQueueNumbers) != null;
        return new ResultBean<>(result);
    }

}
