package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccNoticeCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNotice;
import cn.cucsi.bsd.ucc.service.UccNoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@RestController
@RequestMapping(value = "/uccNotice")
public class UccNoticeController {
    @Autowired
    UccNoticeService uccNoticeService;

    @ApiOperation(value="根据查询条件获取通知公告列表", notes="根据查询条件获取通知公告列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public PageResultBean<List<UccNotice>> findAll(@ModelAttribute UccNoticeCriteria search) {
        return new PageResultBean(this.uccNoticeService.findAll(search));
    }

    @ApiOperation(value = "根据noticeId查询UccNotice", notes = "根据noticeId查询UccNotice")
    @RequestMapping(value = "/{noticeId}", method= RequestMethod.GET)
    public ResultBean<UccNotice> findOne(@PathVariable String noticeId){
        return new ResultBean<>(this.uccNoticeService.findOne(noticeId));
    }

    @ApiOperation(value = "根据noticeId删除UccNotice", notes = "根据noticeId删除UccNotice")
    @RequestMapping(value = "/{noticeId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String noticeId){
        return new ResultBean<>(this.uccNoticeService.delete(noticeId));
    }

    @ApiOperation(value = "创建UccNotice", notes = "创建UccNotice")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccNotice uccNotice) {
        boolean result = this.uccNoticeService.save(uccNotice) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccNotice", notes = "修改UccNotice")
    @RequestMapping(value = "/{noticeId}", method =  RequestMethod.PUT)
    public ResultBean<UccNotice> save(@PathVariable String noticeId, @RequestBody UccNotice uccNotice){
        return new ResultBean<>(this.uccNoticeService.save(uccNotice));
    }
}
