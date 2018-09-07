package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccServersCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccSkillGroupCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccServers;
import cn.cucsi.bsd.ucc.data.domain.UccSkillGroup;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccServersService;
import cn.cucsi.bsd.ucc.service.UccSkillGroupService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/uccSkillGroup")
public class UccSkillGroupController {
    @Autowired
    UccSkillGroupService uccSkillGroupService;

    @ApiOperation(value="根据查询条件获取技能组表", notes="根据查询条件获取技能组表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<UccSkillGroup>> findAll(@RequestBody UccSkillGroupCriteria search){
        PageResultBean<List<UccSkillGroup>> list= new PageResultBean(this.uccSkillGroupService.findAll(search));
        return new PageResultBean(this.uccSkillGroupService.findAll(search));
    }

    @ApiOperation(value = "根据skillGroupId查询UccSkillGroup", notes = "根据skillGroupId查询UccSkillGroup")
    @RequestMapping(value = "/{skillGroupId}", method= RequestMethod.POST)
    public ResultBean<UccSkillGroup> findOne(@RequestBody String skillGroupId){
        return new ResultBean<>(this.uccSkillGroupService.findOne(skillGroupId));
    }

    @ApiOperation(value = "根据skillGroupId删除uccSkillGroup", notes = "根据skillGroupId删除uccSkillGroup")
    @RequestMapping(value = "/{skillGroupId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String skillGroupId){
        return new ResultBean<>(this.uccSkillGroupService.delete(skillGroupId));
    }

    @ApiOperation(value = "创建uccSkillGroup", notes = "创建uccSkillGroup")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody  UccSkillGroup uccSkillGroup) {
        boolean result=this.uccSkillGroupService.save(uccSkillGroup)!=null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccSkillGroup", notes = "修改UccSkillGroup")
    @RequestMapping(value = "/{skillGroupId}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String skillGroupId, @RequestBody  UccSkillGroup uccSkillGroup){
        boolean result=this.uccSkillGroupService.save(uccSkillGroup)!=null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "更新UccSkillGroup的状态", notes = "更新UccSkillGroup的状态")
    @RequestMapping(value = "/${skillGroupId}/${status}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> updateStatusById( @PathVariable String status,@PathVariable String skillGroupId){
        return new ResultBean<>(this.uccSkillGroupService.updateStatusById( status,skillGroupId));
    }

}
