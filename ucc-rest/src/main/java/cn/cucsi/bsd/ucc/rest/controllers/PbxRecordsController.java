package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxRecordsCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxRecords;
import cn.cucsi.bsd.ucc.service.PbxRecordsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
@RestController
@RequestMapping(value = "/PbxRecords")
public class PbxRecordsController {
    @Autowired
    PbxRecordsService pbxRecordsService;


    @ApiOperation(value="根据查询条件获取录音记录表", notes="根据查询条件获取录音记录表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<PbxRecords>> findAll(@ModelAttribute PbxRecordsCriteria search){
        return new PageResultBean(this.pbxRecordsService.findAll(search));
    }

    @ApiOperation(value = "根据recordId查询PbxRecords", notes = "根据recordId查询PbxRecords")
    @RequestMapping(value = "/{recordId}", method= RequestMethod.GET)
    public ResultBean<PbxRecords> findOne(@PathVariable String recordId){
        return new ResultBean<>(this.pbxRecordsService.findOne(recordId));
    }
    @ApiOperation(value = "根据recordId删除PbxRecords", notes = "根据recordId删除PbxRecords")
    @RequestMapping(value = "/{recordId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String recordId){
        return new ResultBean<Boolean>(this.pbxRecordsService.delete(recordId));
    }

    @ApiOperation(value = "修改PbxRecords", notes = "修改PbxRecords")
    @RequestMapping(value = "/{recordId}", method = RequestMethod.PUT)
    public ResultBean<PbxRecords> save(@PathVariable String recordId, @RequestBody PbxRecords pbxRecords){
        return new ResultBean<PbxRecords>(this.pbxRecordsService.save(pbxRecords));
    }
}