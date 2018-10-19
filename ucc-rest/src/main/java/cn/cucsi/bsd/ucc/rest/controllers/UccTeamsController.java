package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.UccTeams;
import cn.cucsi.bsd.ucc.service.UccTeamsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/uccTeams")
public class UccTeamsController {
    @Autowired
    UccTeamsService uccTeamsService;

    @ApiOperation(value="根据查询条件获取班组列表", notes="根据查询条件获取班组列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<UccTeams>> findAll(@RequestBody UccTeamsCriteria search){
        return new PageResultBean(this.uccTeamsService.findAll(search));
    }

    @ApiOperation(value="获取班组列表不带分页", notes="获取班组列表不带分页", httpMethod = "POST")
    @RequestMapping(value = "/findAllOfNoPage", method = RequestMethod.POST)
    public ResultBean<List<UccTeams>> findAllOfNoPage(@RequestBody UccTeamsCriteria search) {
        return new ResultBean(this.uccTeamsService.findAllOfNoPage(search));
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
        UccTeamsCriteria uccTeamsCriteria = new UccTeamsCriteria();
        uccTeamsCriteria.setTeamName(uccTeams.getTeamName());
        List<UccTeams> uccTeamsList = uccTeamsService.findAllOfNoPage(uccTeamsCriteria);
        Boolean resultByName = uccTeamsList.size()==0;
        if(!resultByName){
            return new ResultBean<>(ResultBean.FAIL,"班组名重复！",resultByName);
        }else {
            boolean result=this.uccTeamsService.save(uccTeams)!=null;
            return new ResultBean<>(result);
        }
    }

    @ApiOperation(value = "修改UccTeams", notes = "修改UccTeams")
    @RequestMapping(value = "/{teamId}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String teamId, @RequestBody UccTeams uccTeams){
        boolean result=this.uccTeamsService.save(uccTeams)!=null;
        return new ResultBean<>(result);
    }

}
