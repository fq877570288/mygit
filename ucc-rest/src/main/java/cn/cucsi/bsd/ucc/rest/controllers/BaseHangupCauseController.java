package cn.cucsi.bsd.ucc.rest.controllers;


import cn.cucsi.bsd.ucc.common.beans.BaseHangupCauseCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.BaseHangupCause;
import cn.cucsi.bsd.ucc.service.BaseHangupCauseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import java.util.List;

/**
 * Created by home on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/baseHangupCause")

public class BaseHangupCauseController {

    @Autowired
    private BaseHangupCauseService baseHangupCauseService;
    @ApiOperation(value="根据查询条件获取挂机原因参数表", notes="根据查询条件获取挂机原因参数表", httpMethod = "GET")
    @RequestMapping(value = "/findAll",method= RequestMethod.GET)
    public ResultBean<List<BaseHangupCause>> findAll( @ModelAttribute BaseHangupCauseCriteria search){
        return new ResultBean(this.baseHangupCauseService.findAll(search));
    }
    @ApiOperation(value="根据causeEn查询BaseHangupCause", notes="根据causeEn查询BaseHangupCause")
    @RequestMapping(value = "/{causeEn}", method= RequestMethod.GET)
    public ResultBean<BaseHangupCause> findOne(@PathVariable String causeEn){
        return new ResultBean<>(this.baseHangupCauseService.findOne(causeEn));
    }
    @ApiOperation(value="根据causeEn删除BaseHangupCause", notes="根据causeEn删除BaseHangupCause")
    @RequestMapping(value = "/{causeEn}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String causeEn){
        return new ResultBean<>(this.baseHangupCauseService.delete(causeEn));
    }
    @ApiOperation(value="创建BaseHangupCause", notes="创建BaseHangupCause")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody BaseHangupCause baseHangupCause){
        Boolean result = this.baseHangupCauseService.save(baseHangupCause) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value="修改BaseHangupCause", notes="修改BaseHangupCause")
    @RequestMapping(value = "/{causeEn}", method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String causeEn,@RequestBody BaseHangupCause baseHangupCause){
        Boolean result = this.baseHangupCauseService.save(baseHangupCause) != null;
        return new ResultBean<>(result);
    }

}
