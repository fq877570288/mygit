package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.PbxGateways;
import cn.cucsi.bsd.ucc.data.domain.PbxGwCalleeRewriteRules;
import cn.cucsi.bsd.ucc.data.domain.PbxGwNumbers;
import cn.cucsi.bsd.ucc.service.PbxGatewaysService;
import cn.cucsi.bsd.ucc.service.PbxGwCalleeRewriteRulesService;
import cn.cucsi.bsd.ucc.service.PbxGwNumbersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/PbxGateways")
@Transactional(rollbackFor = Exception.class)
public class PbxGatewaysController {

    @Autowired
    private PbxGatewaysService PbxGatewaysService;
    @Autowired
    private PbxGwCalleeRewriteRulesService PbxGwCalleeRewriteRulesService;
    @Autowired
    private cn.cucsi.bsd.ucc.service.PbxGwNumbersService PbxGwNumbersService;
    @ApiOperation(value="根据查询条件获取网关信息表", notes="根据查询条件获取网关信息表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public PageResultBean<List<PbxGateways>> findAll(@RequestBody PbxGatewaysCriteria search){
        return new PageResultBean(this.PbxGatewaysService.findAll(search));
    }
    @ApiOperation(value="不分页根据条件获取网关信息表", notes="不分页根据条件获取网关信息表", httpMethod = "POST")
    @RequestMapping(value = "/findAllOfNoPage", method= RequestMethod.POST)
    public ResultBean<List<PbxGateways>> findAllOfNoPage(@RequestBody PbxGatewaysCriteria search){
        return new ResultBean(this.PbxGatewaysService.findAllOfNoPage(search));
    }
    @ApiOperation(value = "根据gwId查询PbxGateways", notes = "根据gwId查询PbxGateways")
    @RequestMapping(value = "/{gwId}", method= RequestMethod.GET)
    public ResultBean<PbxGateways> findOne(@PathVariable String gwId){
        return new ResultBean<>(this.PbxGatewaysService.findOne(gwId));
    }
    @ApiOperation(value = "根据gwId删除PbxGateways", notes = "根据gwId删除PbxGateways")
    @RequestMapping(value = "/{gwId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String gwId){

        PbxGwCalleeRewriteRulesCriteria pbxGwCalleeRewriteRulesCriteria = new PbxGwCalleeRewriteRulesCriteria();
        pbxGwCalleeRewriteRulesCriteria.setGatewayId(gwId);
        List<PbxGwCalleeRewriteRules> pbxGwCalleeRewriteRulesList = PbxGwCalleeRewriteRulesService.findAllNoPage(pbxGwCalleeRewriteRulesCriteria);
        PbxGwCalleeRewriteRulesService.deleteManyByitselfList(pbxGwCalleeRewriteRulesList);
        PbxGwNumbersCriteria pbxGwNumbersCriteria = new PbxGwNumbersCriteria();
        pbxGwNumbersCriteria.setGatewayId(gwId);
        List<PbxGwNumbers> pbxGwNumberlist = PbxGwNumbersService.findAllOfNoPage(pbxGwNumbersCriteria);
        PbxGwNumbersService.deleteByitselfList(pbxGwNumberlist);
        PbxGateways gateway = this.PbxGatewaysService.findOne(gwId);
        gateway.setStatus(0);
        this.PbxGatewaysService.save(gateway);
        return new ResultBean<>(true);
    }
    @ApiOperation(value = "创建PbxGateways", notes = "创建PbxGateways")
    @RequestMapping(value = "" , method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxGateways PbxGateways) {
        String[] gwNumbersStr = PbxGateways.getGwNumbersStr();
        PbxGateways pbxGateway = this.PbxGatewaysService.save(PbxGateways);
        if(pbxGateway!=null && gwNumbersStr.length>0){//插入大号表(pbx_gw_numbers)信息
            for (String gwNumber: gwNumbersStr
                 ) {
                PbxGwNumbers gwNumbers = new PbxGwNumbers();
                gwNumbers.setCreatedTime(PbxGateways.getCreatedTime());
                gwNumbers.setGatewayId(pbxGateway.getGwId());
                gwNumbers.setGwNumber(gwNumber);
                PbxGwNumbersService.save(gwNumbers);
            }
        }
        //插入被叫规则表(pbx_gw_callee_rewite_rules)信息
        if(PbxGateways.getPbxGwCalleeRewriteRules()!=null && PbxGateways.getPbxGwCalleeRewriteRules().size()>0){
            Collection<PbxGwCalleeRewriteRules> pbxGwCalleeRewriteRules = PbxGateways.getPbxGwCalleeRewriteRules();
            for (PbxGwCalleeRewriteRules pbxGwCalleeRewriteRule:pbxGwCalleeRewriteRules
                 ) {
                pbxGwCalleeRewriteRule.setGatewayId(pbxGateway.getGwId());
                pbxGwCalleeRewriteRule.setCreatedTime(PbxGateways.getCreatedTime());
                PbxGwCalleeRewriteRulesService.save(pbxGwCalleeRewriteRule);
            }
        }
        return new ResultBean<>(true);
    }
    @ApiOperation(value = "修改PbxGateways", notes = "修改PbxGateways")
    @RequestMapping(value = "/{gwId}", method= RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String gwId, @RequestBody PbxGateways PbxGateways ){
        String[] gwNumbersStr = PbxGateways.getGwNumbersStr();
        Date nowDate = new Date();
        PbxGateways.setUpdatedTime(nowDate);
        PbxGateways pbxGateway = this.PbxGatewaysService.save(PbxGateways);
        if(PbxGateways.getPbxGwCalleeRewriteRules()!=null && PbxGateways.getPbxGwCalleeRewriteRules().size()>0) {
            //先删除 被叫规则表(pbx_gw_callee_rewite_rules)信息相应信息
            PbxGwCalleeRewriteRulesCriteria pbxGwCalleeRewriteRulesCriteria = new PbxGwCalleeRewriteRulesCriteria();
            pbxGwCalleeRewriteRulesCriteria.setGatewayId(pbxGateway.getGwId());
            List<PbxGwCalleeRewriteRules> pbxGwCalleeRewriteRulesList = PbxGwCalleeRewriteRulesService.findAllNoPage(pbxGwCalleeRewriteRulesCriteria);
            PbxGwCalleeRewriteRulesService.deleteManyByitselfList(pbxGwCalleeRewriteRulesList);

            Collection<PbxGwCalleeRewriteRules> pbxGwCalleeRewriteRules = PbxGateways.getPbxGwCalleeRewriteRules();
            for (PbxGwCalleeRewriteRules pbxGwCalleeRewriteRule : pbxGwCalleeRewriteRules
                    ) {
                pbxGwCalleeRewriteRule.setGatewayId(pbxGateway.getGwId());
                pbxGwCalleeRewriteRule.setCreatedTime(pbxGateway.getCreatedTime());
                pbxGwCalleeRewriteRule.setUpdatedTime(nowDate);
                PbxGwCalleeRewriteRulesService.save(pbxGwCalleeRewriteRule);
            }
        }
        if(pbxGateway!=null && gwNumbersStr.length>0) {//插入大号表(pbx_gw_numbers)信息
            //先删除 大号表(pbx_gw_numbers)信息相应信息
            PbxGwNumbersCriteria pbxGwNumbersCriteria = new PbxGwNumbersCriteria();
            pbxGwNumbersCriteria.setGatewayId(pbxGateway.getGwId());
            List<PbxGwNumbers> pbxGwNumberlist = PbxGwNumbersService.findAllOfNoPage(pbxGwNumbersCriteria);
            PbxGwNumbersService.deleteByitselfList(pbxGwNumberlist);
            for (String gwNumber: gwNumbersStr
                    ) {
                PbxGwNumbers gwNumbers = new PbxGwNumbers();
                gwNumbers.setCreatedTime(pbxGateway.getCreatedTime());
                gwNumbers.setUpdatedTime(nowDate);
                gwNumbers.setGatewayId(pbxGateway.getGwId());
                gwNumbers.setGwNumber(gwNumber);
                PbxGwNumbersService.save(gwNumbers);
            }
        }
        return new ResultBean<>(true);
    }

}
