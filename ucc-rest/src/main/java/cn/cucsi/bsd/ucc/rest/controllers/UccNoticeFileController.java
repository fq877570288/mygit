package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccNoticeFileCriteria;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeFile;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import org.springframework.format.annotation.DateTimeFormat;
import cn.cucsi.bsd.ucc.service.SystemConfigService;
import cn.cucsi.bsd.ucc.service.UccNoticeFileService;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@RestController
@RequestMapping(value = "/uccNoticeFile")
public class UccNoticeFileController {
    @Autowired
    UccNoticeFileService uccNoticeFileService;
    @Autowired
    private SystemConfigService systemConfigService;

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
    @ApiOperation(value = "根据noticeId查询UccNotice", notes = "根据noticeId查询UccNotice")
    @RequestMapping(value = "/{noticeId}", method= RequestMethod.POST)
    public List<UccNoticeFile> findFileList(@PathVariable String noticeId){
        return  this.uccNoticeFileService.findFileList(noticeId);
    }
    @ApiOperation(value = "根据noticeFileId删除UccNoticeFile", notes = "根据noticeFileId删除UccNoticeFile")
    @RequestMapping(value = "/{noticeFileId}{fileName}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String noticeFileId,@PathVariable String fileName){
        //return new ResultBean<>(this.uccNoticeFileService.delete(noticeFileId));
        //UccNoticeFile uccNoticeFile = this.uccNoticeFileService.delete(noticeFileId);
        SystemConfig systemConfig  = systemConfigService.findOne("noticeFilePath");
        String filePath = systemConfig.getValue() + "\\" + noticeFileId + "\\";
        
        //String filePath = "musicTest\\";
        File file = new File(filePath + fileName);
        if(file.exists()){
            file.delete();
        }
        //this.repository.delete(musicId);
        return  new ResultBean<>(this.uccNoticeFileService.delete(noticeFileId));
    }

    @ApiOperation(value = "创建UccNoticeFile", notes = "创建UccNoticeFile")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<UccNoticeFile> create(@RequestBody UccNoticeFile uccNoticeFile,@RequestParam("file") MultipartFile file) {
        byte[] fileBox = null;
        if (file != null && file.getSize() > 0) {
            uccNoticeFile.setFileName(file.getOriginalFilename());
            Date dateTime = new Date();
            uccNoticeFile.setCreatedTime(dateTime);
            try {
                fileBox = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResultBean<>(this.uccNoticeFileService.save(fileBox, uccNoticeFile));
    }

    @ApiOperation(value = "修改UccNoticeFile", notes = "修改UccNoticeFile")
    @RequestMapping(value = "/{noticeFileId}",method =  RequestMethod.POST)
    public ResultBean<UccNoticeFile> save(@PathVariable String noticeFileId, @RequestBody UccNoticeFile uccNoticeFile){
        Date dateTime = new Date();
        uccNoticeFile.setUpdatedTime(dateTime);
        return new ResultBean<>(this.uccNoticeFileService.save(uccNoticeFile));
    }
    @ApiOperation(value = "根据noticeFileId、fileName获取相应附件", notes = "根据noticeFileId、fileName获取相应附件")
    @RequestMapping(value = "/{noticeFileId}/download")
    public void getFlie(HttpServletResponse rsp, @PathVariable String noticeFileId) {
        SystemConfig systemConfig  = systemConfigService.findOne("noticeFilePath");
        String filePath = systemConfig.getValue() + "\\" + noticeFileId + "\\";
        
        UccNoticeFile uccNoticeFile = this.uccNoticeFileService.findOne(noticeFileId);
        System.out.println(filePath + uccNoticeFile.getFileName());
        File file = new File(filePath + uccNoticeFile.getFileName());
        InputStream inputStream = null;
        try {
            rsp.setContentType(uccNoticeFile.getContentType());
            rsp.setHeader("Content-Disposition", "attachment; filename=" + uccNoticeFile.getFileName());
            //读取文件到ouputStream
            inputStream = new FileInputStream(file);
            int tempbyte;
            byte[] musicFile = new byte[1024];
            OutputStream os = rsp.getOutputStream();
            
            while ((tempbyte = inputStream.read(musicFile)) != -1) {
                os.write(musicFile, 0, tempbyte);
            }
            os.close();
        } catch (Exception e) {
            // e.printStackTrace();
            // TODO 处理connect reset
        }
    }
}
