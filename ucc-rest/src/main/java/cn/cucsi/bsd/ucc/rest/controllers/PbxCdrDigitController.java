package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrDigitCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrDigit;
import cn.cucsi.bsd.ucc.service.PbxCdrDigitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/pbxCdrDigit")
public class PbxCdrDigitController {

    @Autowired
    private PbxCdrDigitService PbxCdrDigitService;
    @ApiOperation(value="根据查询条件获取详单IVR按键表", notes="根据查询条件获取详单IVR按键表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<PbxCdrDigit>> findAll(@ModelAttribute PbxCdrDigitCriteria PbxCdrDigitCriteria){
        return new PageResultBean(this.PbxCdrDigitService.findAll(PbxCdrDigitCriteria));
    }
    @ApiOperation(value = "根据cdrDigitId查询PbxCdrDigit", notes = "根据cdrDigitId查询PbxCdrDigit")
    @RequestMapping(value = "/{cdrDigitId}", method= RequestMethod.GET)
    public ResultBean<PbxCdrDigit> findOne(@PathVariable String cdrDigitId){
        return new ResultBean<>(this.PbxCdrDigitService.findOne(cdrDigitId));
    }
    @ApiOperation(value = "根据cdrDigitId删除PbxCdrDigit", notes = "根据cdrDigitId删除PbxCdrDigit")
    @RequestMapping(value = "/{cdrDigitId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String cdrDigitId){
        return new ResultBean<>(this.PbxCdrDigitService.delete(cdrDigitId));
    }
    @ApiOperation(value = "创建PbxCdrDigit", notes = "创建PbxCdrDigit")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxCdrDigit PbxCdrDigit) {
        boolean result = this.PbxCdrDigitService.save(PbxCdrDigit) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxCdrDigit", notes = "修改PbxCdrDigit")
    @RequestMapping(value = "/{cdrDigitId}", method =  RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String cdrDigitId,@RequestBody PbxCdrDigit PbxCdrDigit) {
        boolean result = this.PbxCdrDigitService.save(PbxCdrDigit) != null;
        return new ResultBean<>(result);
    }
}
