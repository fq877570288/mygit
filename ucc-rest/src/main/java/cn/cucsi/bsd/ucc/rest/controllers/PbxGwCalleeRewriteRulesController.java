package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxGwCalleeRewriteRulesCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxGwCalleeRewriteRules;
import cn.cucsi.bsd.ucc.service.PbxGwCalleeRewriteRulesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/PbxGwCalleeRewriteRules")
public class PbxGwCalleeRewriteRulesController {

    @Autowired
    private PbxGwCalleeRewriteRulesService PbxGwCalleeRewriteRulesService;

    @ApiOperation(value="根据查询条件获取被叫重写规则表", notes="根据查询条件获取被叫重写规则表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<PbxGwCalleeRewriteRules>> findAll(@ModelAttribute PbxGwCalleeRewriteRulesCriteria search){
        return new PageResultBean(this.PbxGwCalleeRewriteRulesService.findAll(search));
    }
    @ApiOperation(value = "根据rewriteId查询PbxGwCalleeRewriteRules", notes = "根据rewriteId查询PbxGwCalleeRewriteRules")
    @RequestMapping(value = "/{rewriteId}", method= RequestMethod.GET)
    public ResultBean<PbxGwCalleeRewriteRules> findOne(@PathVariable String rewriteId){
        return new ResultBean<>(this.PbxGwCalleeRewriteRulesService.findOne(rewriteId));
    }
    @ApiOperation(value = "根据rewriteId删除PbxGwCalleeRewriteRules", notes = "根据rewriteId删除PbxGwCalleeRewriteRules")
    @RequestMapping(value = "/{rewriteId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String rewriteId){
        return new ResultBean<>(this.PbxGwCalleeRewriteRulesService.delete(rewriteId));
    }
    @ApiOperation(value = "创建PbxGwCalleeRewriteRules", notes = "创建PbxGwCalleeRewriteRules")
    @RequestMapping(value = "" , method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxGwCalleeRewriteRules PbxGwCalleeRewriteRules) {
        boolean result = this.PbxGwCalleeRewriteRulesService.save(PbxGwCalleeRewriteRules) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxGwCalleeRewriteRules", notes = "修改PbxGwCalleeRewriteRules")
    @RequestMapping(value = "/{rewriteId}", method= RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String rewriteId,@RequestBody PbxGwCalleeRewriteRules PbxGwCalleeRewriteRules){

        boolean result = this.PbxGwCalleeRewriteRulesService.save(PbxGwCalleeRewriteRules) != null;
        return new ResultBean<>(result);
    }

}
