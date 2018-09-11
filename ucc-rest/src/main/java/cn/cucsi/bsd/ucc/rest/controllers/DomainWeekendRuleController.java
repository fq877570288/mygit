package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.DomainWeekendRuleCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRule;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRulePK;
import cn.cucsi.bsd.ucc.service.DomainWeekendRuleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by home on 2017/10/17.
 */
@RestController
@RequestMapping(value = "/domainWeekendRule")
public class DomainWeekendRuleController {

    @Autowired
    private DomainWeekendRuleService domainWeekendRuleService;
    @ApiOperation(value="根据查询条件获取班组工作日规则表", notes="根据查询条件获取班组工作日规则表", httpMethod = "POST")
    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public PageResultBean<List<DomainWeekendRule>> findAll(@RequestBody DomainWeekendRuleCriteria domainWeekendRuleCriteria){
        return new PageResultBean<>(this.domainWeekendRuleService.findAll(domainWeekendRuleCriteria));
    }
    @ApiOperation(value = "根据domainId、teamId查询DomainWeekendRule", notes = "根据domainId、teamId查询DomainWeekendRule")
    @RequestMapping(value = "/{domainId}/{teamId}", method= RequestMethod.GET)
    public ResultBean<DomainWeekendRule> findOne(@PathVariable String domainId,@PathVariable String teamId){
        DomainWeekendRulePK domainWeekendRulePK = new DomainWeekendRulePK();
        domainWeekendRulePK.setDomainId(domainId);
        domainWeekendRulePK.setTeamId(teamId);
        return new ResultBean<>(this.domainWeekendRuleService.findOne(domainWeekendRulePK));
    }
    @ApiOperation(value = "创建DomainWeekendRule", notes = "创建DomainWeekendRule")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody DomainWeekendRule domainWeekendRule){
        Boolean result = this.domainWeekendRuleService.save(domainWeekendRule) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改DomainWeekendRule", notes = "修改DomainWeekendRule")
    @RequestMapping(value = "/{weekendRule}", method = RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String weekendRule,@RequestBody DomainWeekendRule domainWeekendRule){
        Boolean result = this.domainWeekendRuleService.save(domainWeekendRule) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "根据domainId、teamId删除DomainWeekendRule", notes = "根据domainId、teamId删除DomainWeekendRule")
    @RequestMapping(value = "/{domainId}/{teamId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String domainId,@PathVariable String teamId){
        DomainWeekendRulePK domainWeekendRulePK = new DomainWeekendRulePK();
        domainWeekendRulePK.setDomainId(domainId);
        domainWeekendRulePK.setTeamId(teamId);
        return new ResultBean<>(this.domainWeekendRuleService.delete(domainWeekendRulePK));
    }
}
