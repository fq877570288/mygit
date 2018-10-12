package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccNoticeTraceCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeTrace;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccNoticeTraceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mk on 2017/10/13.
 */
@RestController
@RequestMapping(value = "/uccNoticeTrace")
public class UccNoticeTraceController {
    @Autowired
    private UccNoticeTraceService uccNoticeTraceService;

    @ApiOperation(value="根据查询条件获取通知公告状态列表", notes="根据查询条件获取通知公告状态列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<UccNoticeTrace>> findAll(@ModelAttribute UccNoticeTraceCriteria search){
        return new PageResultBean(this.uccNoticeTraceService.findAll(search));
    }
    
    @ApiOperation(value = "根据noticeTraceId查询uccNoticeTrace", notes = "根据noticeTraceId查询uccNoticeTrace")
    @RequestMapping(value = "/{noticeTraceId}", method= RequestMethod.GET)
    public ResultBean<UccNoticeTrace> findOne(@PathVariable String noticeTraceId){
        return new ResultBean<>(this.uccNoticeTraceService.findOne(noticeTraceId));
    }

    @ApiOperation(value = "根据noticeTraceId删除uccNoticeTrace", notes = "根据noticeTraceId删除uccNoticeTrace")
    @RequestMapping(value = "/{noticeTraceId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String noticeTraceId){
        return new ResultBean<>(this.uccNoticeTraceService.delete(noticeTraceId));


    }

    @ApiOperation(value = "创建uccNoticeTrace", notes = "创建uccNoticeTrace")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccNoticeTrace uccNoticeTrace) {
        boolean result = this.uccNoticeTraceService.save(uccNoticeTrace) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改uccNoticeTrace", notes = "修改uccNoticeTrace")
    @RequestMapping(value = "/{noticeTraceId}", method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String noticeTraceId, @RequestBody UccNoticeTrace uccNoticeTrace){
        boolean result = this.uccNoticeTraceService.save(uccNoticeTrace) != null;
        return new ResultBean<>(result);
    }
}
