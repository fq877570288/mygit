package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccNoticeFileCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeFile;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccNoticeFileService;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@RestController
@RequestMapping(value = "/uccNoticeFile")
public class UccNoticeFileController {
    @Autowired
    UccNoticeFileService uccNoticeFileService;

    @ApiOperation(value="根据查询条件获取通知公告附件列表", notes="根据查询条件获取通知公告附件列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public PageResultBean<List<UccNoticeFile>> findAll(@ModelAttribute UccNoticeFileCriteria search) {
        return new PageResultBean(this.uccNoticeFileService.findAll(search));
    }

    @ApiOperation(value = "根据noticeFileId查询UccNoticeFile", notes = "根据noticeFileId查询UccNoticeFile")
    @RequestMapping(value = "/{noticeFileId}", method= RequestMethod.GET)
    public ResultBean<UccNoticeFile> findOne(@PathVariable String noticeFileId){
        return new ResultBean<>(this.uccNoticeFileService.findOne(noticeFileId));
    }

    @ApiOperation(value = "根据noticeFileId删除UccNoticeFile", notes = "根据noticeFileId删除UccNoticeFile")
    @RequestMapping(value = "/{noticeFileId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String noticeFileId){
        return new ResultBean<>(this.uccNoticeFileService.delete(noticeFileId));
    }

    @ApiOperation(value = "创建UccNoticeFile", notes = "创建UccNoticeFile")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccNoticeFile uccNoticeFile, MultipartFile file) {
        
        byte[] fileBox = null;
        if (file != null && file.getSize() > 0) {
            uccNoticeFile.setFileName(file.getOriginalFilename());
            uccNoticeFile.setFileOrder(1);
            try {
                fileBox = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boolean result = this.uccNoticeFileService.save(fileBox, uccNoticeFile) != null;
        return new ResultBean<>(result);
        
        
        
        //boolean result = this.uccNoticeFileService.save(uccNoticeFile) != null;
        //return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改UccNoticeFile", notes = "修改UccNoticeFile")
    @RequestMapping(value = "/{noticeFileId}",method =  RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String noticeFileId, @RequestBody UccNoticeFile uccNoticeFile){
        boolean result = this.uccNoticeFileService.updateById(uccNoticeFile)!= null;
        return new ResultBean<>(result);
    }
}
