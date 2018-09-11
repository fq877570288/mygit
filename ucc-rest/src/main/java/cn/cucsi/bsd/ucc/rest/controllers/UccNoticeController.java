package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.UccNotice;
import cn.cucsi.bsd.ucc.service.UccNoticeService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */
@Api(tags={"通知公告接口"})
@RestController
@RequestMapping(value = "/uccNotice")
public class UccNoticeController {
    @Autowired
    UccNoticeService uccNoticeService;

    @ApiOperation(value="根据查询条件获取通知公告列表", notes="根据查询条件获取通知公告列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public PageResultBean<List<UccNotice>> findAll(@RequestBody UccNoticeCriteria search) {
        return new PageResultBean(this.uccNoticeService.findAll(search));
    }

    @ApiOperation(value = "根据noticeId查询UccNotice", notes = "根据noticeId查询UccNotice")
    @RequestMapping(value = "/{noticeId}", method= RequestMethod.POST)
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

    /***
     * 根据查询条件获取通知列表--APP用
     * 备注：如果是未读的，点击查询后需要将其置为已读
     * add by wangxiaoyu
     * 2018-09-07
     */
    @ApiOperation(value="根据查询条件获取通知公告列表--APP用", notes="根据查询条件获取通知公告列表--APP用", httpMethod = "POST")
    @RequestMapping(value = "/findNoticeListByUserId", method = RequestMethod.POST)
    public PageResultBean_New<List<UccNotice>> findNoticeListByUserId(@RequestBody NoticeShowListCriteria noticeShowListCriteria) {
        return new PageResultBean_New(uccNoticeService.selectByUserId(noticeShowListCriteria));
    }

    /***
     * 查询通知详情--APP用
     * 备注：该接口目前仅是APP用。因为该类上面方法也有查看详情的，
     * 但是本接口还需要含判断“如果是未读的，点击查询后需要将其置为已读”，
     * springdata我不太熟，所以单独写了这个 见谅！
     * add by wangxiaoyu
     * 2018-09-07
     */
    @ApiOperation(value="查询通知详情", notes="查询通知详情")
    @RequestMapping(value = "/showNoticeDetail", method= RequestMethod.POST)
    public ResultBean_New<UccNotice> showNoticeDetailByNoticeId(@RequestBody ShowNoticeDetailCriteria showNoticeDetailCriteria){
        return uccNoticeService.showNoticeDetailByNoticeId(showNoticeDetailCriteria);
    }

}
