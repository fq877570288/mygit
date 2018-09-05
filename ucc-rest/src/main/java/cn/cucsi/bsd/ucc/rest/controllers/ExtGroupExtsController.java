package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.ExtGroupExtsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExtsPK;
import cn.cucsi.bsd.ucc.service.ExtGroupExtsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/extGroupExts")
public class ExtGroupExtsController {


    @Autowired
    private ExtGroupExtsService ExtGroupExtsService;
    @ApiOperation(value="根据查询条件获取号码群组与分机关联表", notes="根据查询条件获取号码群组与分机关联表", httpMethod = "GET")
    @RequestMapping(value = "/findAll",method= RequestMethod.GET)
    public PageResultBean<List<ExtGroupExts>> findAll(@ModelAttribute ExtGroupExtsCriteria ExtGroupExtsCriteria){
        return new PageResultBean(this.ExtGroupExtsService.findAll(ExtGroupExtsCriteria));
    }
    @ApiOperation(value = "根据groupId、extId查询ExtGroupExts", notes = "根据groupId、extId查询ExtGroupExts")
    @RequestMapping(value = "/{groupId}/{extId}", method= RequestMethod.GET)
    public ResultBean<ExtGroupExts> findOne(@PathVariable String groupId,@PathVariable String extId){
        ExtGroupExtsPK extGroupExtsPK = new ExtGroupExtsPK();
        extGroupExtsPK.setExtId(extId);
        extGroupExtsPK.setGroupId(groupId);
        return new ResultBean<>(this.ExtGroupExtsService.findOne(extGroupExtsPK));
    }
    @ApiOperation(value = "根据groupId、extId删除ExtGroupExts", notes = "根据groupId、extId删除ExtGroupExts")
    @RequestMapping(value = "/{groupId}/{extId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String groupId,@PathVariable String extId){
        ExtGroupExtsPK extGroupExtsPK = new ExtGroupExtsPK();
        extGroupExtsPK.setExtId(extId);
        extGroupExtsPK.setGroupId(groupId);
        return new ResultBean<>(this.ExtGroupExtsService.delete(extGroupExtsPK));
    }
    @ApiOperation(value = "创建ExtGroupExts", notes = "创建ExtGroupExts")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody ExtGroupExts ExtGroupExts) {
        boolean result = this.ExtGroupExtsService.save(ExtGroupExts) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改ExtGroupExts", notes = "修改ExtGroupExts")
    @RequestMapping(value = "/{groupId}", method =  RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String groupId,@RequestBody ExtGroupExts ExtGroupExts) {
        boolean result = this.ExtGroupExtsService.save(ExtGroupExts) != null;
        return new ResultBean<>(result);
    }
}
