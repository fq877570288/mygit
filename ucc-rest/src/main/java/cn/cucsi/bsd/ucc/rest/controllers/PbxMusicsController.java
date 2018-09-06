package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxMusicsCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxMusics;
import cn.cucsi.bsd.ucc.service.PbxMusicsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/pbxMusics")
public class PbxMusicsController {

    @Autowired
    private PbxMusicsService PbxMusicsService;

    @ApiOperation(value = "根据查询条件获取音乐文件列表", notes = "根据查询条件获取音乐文件列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public PageResultBean<List<PbxMusics>> findAll(@RequestBody PbxMusicsCriteria search) {
        return new PageResultBean(this.PbxMusicsService.findAll(search));
    }


    @ApiOperation(value = "根据查询条件获取音乐文件列表", notes = "根据查询条件获取音乐文件列表", httpMethod = "GET")
    @RequestMapping(value = "/findAllOfNoPage", method = RequestMethod.GET)
    public ResultBean<List<PbxMusics>> findAllOfNoPage(@ModelAttribute PbxMusicsCriteria search) {
        return new ResultBean(this.PbxMusicsService.findAllOfNoPage(search));
    }

    @ApiOperation(value = "根据musicId查询PbxMusics", notes = "根据musicId查询PbxMusics")
    @RequestMapping(value = "/{musicId}", method = RequestMethod.GET)
    public ResultBean<PbxMusics> findOne(@PathVariable String musicId) {
        return new ResultBean<>(this.PbxMusicsService.findOne(musicId));
    }

    @ApiOperation(value = "根据musicId删除PbxMusics", notes = "根据musicId删除PbxMusics")
    @RequestMapping(value = "/{musicId}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String musicId) {
        return new ResultBean<>(this.PbxMusicsService.delete(musicId));
    }


    @ApiOperation(value = "根据musicId获取相应music文件", notes = "根据musicId获取相应music文件")
    @RequestMapping(value = "/getMusic/{musicId}")
    public void getMusic(HttpServletResponse rsp, @PathVariable String musicId) {

        //基础参数
        String filePath = "musicTest\\";
        File file = new File(filePath + musicId + ".mp3");
        InputStream inputStream = null;
        try {
            //定义响应头
            PbxMusics music = this.PbxMusicsService.findOne(musicId);
            rsp.setContentType(music.getContentType());
            rsp.setHeader("Content-Disposition", "attachment; filename=" + music.getMusicName());
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

    @ApiOperation(value = "创建PbxMusics", notes = "创建PbxMusics")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultBean<Boolean> create(PbxMusics PbxMusics, @RequestParam("file") MultipartFile file) {
        byte[] fileBox = null;
        if (file != null && file.getSize() > 0) {
            PbxMusics.setMusicName(file.getOriginalFilename());
            PbxMusics.setContentType(file.getContentType());
            try {
                fileBox = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boolean result = this.PbxMusicsService.save(fileBox, PbxMusics) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改PbxMusics", notes = "修改PbxMusics")
    @RequestMapping(value = "/{musicId}", method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String musicId, @RequestBody PbxMusics PbxMusics) {
        boolean result = this.PbxMusicsService.updateById(PbxMusics)!= null;
        return new ResultBean<>(result);
    }

}
