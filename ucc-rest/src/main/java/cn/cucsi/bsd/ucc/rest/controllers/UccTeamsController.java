package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccSkillGroupCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccTeamsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccSkillGroup;
import cn.cucsi.bsd.ucc.data.domain.UccTeams;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccSkillGroupService;
import cn.cucsi.bsd.ucc.service.UccTeamsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/uccTeams")
public class UccTeamsController {
    @Autowired
    UccTeamsService uccTeamsService;

    @ApiOperation(value="根据查询条件获取班组列表", notes="根据查询条件获取班组列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<UccTeams>> findAll(@ModelAttribute UccTeamsCriteria search){
        return new PageResultBean(this.uccTeamsService.findAll(search));
    }

    @ApiOperation(value = "根据teamId查询UccTeams", notes = "根据teamId查询UccTeams")
    @RequestMapping(value = "/{teamId}", method= RequestMethod.GET)
    public ResultBean<UccTeams> findOne(@PathVariable String teamId){
        return new ResultBean<>(this.uccTeamsService.findOne(teamId));
    }

    @ApiOperation(value = "根据teamId删除UccTeams", notes = "根据teamId删除UccTeams")
    @RequestMapping(value = "/{teamId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String teamId){

        return new ResultBean<>(this.uccTeamsService.delete(teamId));
    }

    @ApiOperation(value = "创建UccTeams", notes = "创建UccTeams")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccTeams uccTeams) {
        boolean result=this.uccTeamsService.save(uccTeams)!=null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccTeams", notes = "修改UccTeams")
    @RequestMapping(value = "/{teamId}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String teamId, @RequestBody UccTeams uccTeams){
        boolean result=this.uccTeamsService.save(uccTeams)!=null;
        return new ResultBean<>(result);
    }

}
