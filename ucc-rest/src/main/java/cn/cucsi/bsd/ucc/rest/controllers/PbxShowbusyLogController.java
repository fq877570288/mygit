package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxShowbusyLogCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxShowbusyLog;
import cn.cucsi.bsd.ucc.service.PbxShowbusyLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/PbxShowbusyLog")
public class PbxShowbusyLogController {

    @Autowired
    private PbxShowbusyLogService PbxShowbusyLogService;

    @ApiOperation(value="根据查询条件获取示忙记录表", notes="根据查询条件获取示忙记录表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<PbxShowbusyLog>> findAll(@ModelAttribute PbxShowbusyLogCriteria search){
        return new PageResultBean(this.PbxShowbusyLogService.findAll(search));
    }
    @ApiOperation(value = "根据showbusyLogId查询PbxShowbusyLog", notes = "根据showbusyLogId查询PbxShowbusyLog")
    @RequestMapping(value = "/{showbusyLogId}", method= RequestMethod.GET)
    public ResultBean<PbxShowbusyLog> findOne(@PathVariable String showbusyLogId){
        return new ResultBean<>(this.PbxShowbusyLogService.findOne(showbusyLogId));
    }
    @ApiOperation(value = "根据showbusyLogId删除PbxShowbusyLog", notes = "根据showbusyLogId删除PbxShowbusyLog")
    @RequestMapping(value = "/{showbusyLogId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String showbusyLogId){
        return new ResultBean<>(this.PbxShowbusyLogService.delete(showbusyLogId));
    }
    @ApiOperation(value = "创建PbxShowbusyLog", notes = "创建PbxShowbusyLog")
    @RequestMapping(value = "" , method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxShowbusyLog PbxShowbusyLog) {
        boolean result = this.PbxShowbusyLogService.save(PbxShowbusyLog) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxShowbusyLog", notes = "修改PbxShowbusyLog")
    @RequestMapping(value = "/{showbusyLogId}", method= RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String showbusyLogId,@RequestBody PbxShowbusyLog PbxShowbusyLog){

        boolean result = this.PbxShowbusyLogService.save(PbxShowbusyLog) != null;
        return new ResultBean<>(result);
    }

}
