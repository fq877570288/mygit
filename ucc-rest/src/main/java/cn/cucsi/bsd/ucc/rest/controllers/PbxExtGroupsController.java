package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxExtGroupsCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import cn.cucsi.bsd.ucc.service.PbxExtGroupsService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/PbxExtGroups")
public class PbxExtGroupsController {

    @Autowired
    private PbxExtGroupsService pbxextGroupsService;


    @ApiOperation(value="根据查询条件获取号码群组表", notes="根据查询条件获取号码群组表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    @JsonView(JSONView.PbxPbxExtGroupsWithUser.class)
    public PageResultBean<List<PbxExtGroups>> findAll(@ModelAttribute PbxExtGroupsCriteria search){
        return new PageResultBean(this.pbxextGroupsService.findAll(search));
    }
    @ApiOperation(value="根据分机号id获取分机组列表", notes="根据查询条件获取分机表", httpMethod = "GET")
    @RequestMapping(value = "/queryExtGroupByextId", method= RequestMethod.GET)
    public ResultBean<List<PbxExtGroups>> queryExtGroupByextId(String extId){
        return new ResultBean(this.pbxextGroupsService.findGroupsByExtId(extId));
    }
    @ApiOperation(value="获取所有群组表", notes="获取所有号码群组表", httpMethod = "GET")
    @RequestMapping(value = "/findAllGroups", method= RequestMethod.GET)
    public ResultBean<List<PbxExtGroups>> findAllGroups(@ModelAttribute PbxExtGroupsCriteria search){
        return new ResultBean(this.pbxextGroupsService.findAllGroups(search));
    }
    @ApiOperation(value = "根据groupId查询PbxExtGroups", notes = "根据groupId查询PbxExtGroups")
    @RequestMapping(value = "/{groupId}", method= RequestMethod.GET)
    public ResultBean<PbxExtGroups> findOne(@PathVariable String groupId){
        return new ResultBean<>(this.pbxextGroupsService.findOne(groupId));
    }
    @ApiOperation(value = "根据groupId删除PbxExtGroups", notes = "根据groupId删除PbxExtGroups")
    @RequestMapping(value = "/{groupId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String groupId){
        return new ResultBean<>(this.pbxextGroupsService.delete(groupId));
    }
    @ApiOperation(value = "创建PbxExtGroups", notes = "创建PbxExtGroups")
    @RequestMapping(value = "" , method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxExtGroups pbxextgroups) {
        pbxextgroups.setCreatedTime(new Date());
        boolean result = this.pbxextGroupsService.save(pbxextgroups) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxExtGroups", notes = "修改PbxExtGroups")
    @RequestMapping(value = "/{groupId}", method= RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String groupId,@RequestBody PbxExtGroups pbxextgroups){
        pbxextgroups.setUpdatedTime(new Date());
        boolean result = this.pbxextGroupsService.save(pbxextgroups) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "更新PbxExtGroups的状态", notes = "更新PbxExtGroups的状态")
    @RequestMapping(value = "/${groupId}/${status}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> updateStatusById(@PathVariable String groupId, @PathVariable String status){
        return new ResultBean<>(this.pbxextGroupsService.updateStatusById(groupId, status));
    }



}
