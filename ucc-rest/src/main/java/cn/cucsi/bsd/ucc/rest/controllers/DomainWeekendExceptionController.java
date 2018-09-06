package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.DomainWeekendExceptionCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendException;
import cn.cucsi.bsd.ucc.service.DomainWeekendExceptionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by home on 2017/10/17.
 */
@RestController
@RequestMapping(value = "/domainWeekendException")
public class DomainWeekendExceptionController {

    @Autowired
    private DomainWeekendExceptionService domainWeekendExceptionService;
    @ApiOperation(value="根据查询条件获取班组工作日例外表", notes="根据查询条件获取班组工作日例外表", httpMethod = "POST")
    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public PageResultBean<List<DomainWeekendException>> findAll(@RequestBody DomainWeekendExceptionCriteria search){
        return new PageResultBean(this.domainWeekendExceptionService.findAll(search));
    }
    @ApiOperation(value = "根据exceptionId查询DomainWeekendException", notes = "根据exceptionId查询DomainWeekendException")
    @RequestMapping(value = "/{exceptionId}", method= RequestMethod.GET)
    public ResultBean<DomainWeekendException> findOne(@PathVariable String exceptionId){
        return new ResultBean(this.domainWeekendExceptionService.findOne(exceptionId));
    }
    @ApiOperation(value = "创建DomainWeekendException", notes = "创建DomainWeekendException")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody DomainWeekendException domainWeekendException){
        Boolean result = this.domainWeekendExceptionService.save(domainWeekendException) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改DomainWeekendException", notes = "修改DomainWeekendException")
    @RequestMapping(value = "/{exceptionId}", method = RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String exceptionId,@RequestBody DomainWeekendException domainWeekendException){
        Boolean result = this.domainWeekendExceptionService.save(domainWeekendException) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "根据exceptionId删除DomainWeekendException", notes = "根据exceptionId删除DomainWeekendException")
    @RequestMapping(value = "/{exceptionId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String exceptionId){
        return new ResultBean<>(this.domainWeekendExceptionService.delete(exceptionId));
    }
}
