package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.PbxCdrsExcelCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
import cn.cucsi.bsd.ucc.data.domain.PbxRecords;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import cn.cucsi.bsd.ucc.service.PbxRecordsService;
import cn.cucsi.bsd.ucc.service.PbxCdrsService;
import cn.cucsi.bsd.ucc.service.SystemConfigService;
import cn.cucsi.bsd.ucc.common.untils.ExportUtil;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Song on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/pbxCdrs")
public class PbxCdrsController {

    @Autowired
    private PbxCdrsService PbxCdrsService;
    private PbxRecordsService pbxRecordsService;
    private SystemConfigService systemConfigService;

        
    @ApiOperation(value="根据查询条件获取通话详单表", notes="根据查询条件获取通话详单表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean_New<List<PbxCdrs>> findAll(@RequestBody PbxCdrsCriteria pbxCdrsCriteria){
        return new PageResultBean_New(this.PbxCdrsService.findAll(pbxCdrsCriteria));
    }
    @ApiOperation(value = "根据cdrId查询PbxCdrs", notes = "根据cdrId查询PbxCdrs")
    @RequestMapping(value = "/{cdrId}", method= RequestMethod.GET)
    public ResultBean<PbxCdrs> findOne(@PathVariable String cdrId){
        return new ResultBean<>(this.PbxCdrsService.findOne(cdrId));
    }
    @ApiOperation(value = "根据cdrId删除PbxCdrs", notes = "根据cdrId删除PbxCdrs")
    @RequestMapping(value = "/{cdrId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String cdrId){
        return new ResultBean<>(this.PbxCdrsService.delete(cdrId));
    }
    @ApiOperation(value = "创建PbxCdrs", notes = "创建PbxCdrs")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxCdrs pbxCdrs) {
        boolean result = this.PbxCdrsService.save(pbxCdrs) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxCdrs", notes = "修改PbxCdrs")
    @RequestMapping(value = "/{cdrId}", method =  RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String cdrId,@RequestBody PbxCdrs pbxCdrs) {
        boolean result = this.PbxCdrsService.save(pbxCdrs) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "根据cdrId获取录音文件", notes = "根据cdrId获取录音文件")
    @RequestMapping(value = "/getRecording/{cdrId}")
    public void getRecording(HttpServletResponse rsp, @PathVariable String cdrId){
        String recordid1 = this.PbxCdrsService.findOne(cdrId).getRecordid1();
        if(recordid1.isEmpty())
        {
            return;
        }
        PbxRecords record = this.pbxRecordsService.findOne(recordid1);
        InputStream inputStream = null;
        //定义响应头
        rsp.setContentType(record.getContentType());
        rsp.setHeader("Content-Disposition", "attachment; filename=" + record.getRecordName());
        if(record.getType().equals("1"))
        {
            try {
                OutputStream os = rsp.getOutputStream();
                os.write(record.getContent());
                os.close();
            } catch (Exception e) {
                // e.printStackTrace();
                // TODO 处理connect reset
            }
        }
        else if(record.getType().equals("2"))
        {
            String filePath = systemConfigService.findOne("recordPath").getValue();
            File file = new File(filePath + "\\" + record.getRecordName());
            try {
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
    
    @ApiOperation(value = "根据cdrId获取录音文件", notes = "根据cdrId获取录音文件")
    @RequestMapping(value = "/exportCdrs")
    public void exportCdrs(HttpServletResponse rsp,@RequestBody PbxCdrsCriteria pbxCdrsCriteria, String sheetName) throws Exception {
        ExportUtil export = new ExportUtil(rsp,"通话记录", "通话记录1", PbxCdrsExcelCriteria.class);	//实例化一个jxlExport
        List<PbxCdrs> list = this.PbxCdrsService.findAllExcel(pbxCdrsCriteria);
        PbxCdrsExcelCriteria bean = null;
   
        try {
            for(PbxCdrs row : list){		//写入数据
                bean = new PbxCdrsExcelCriteria();		//创建导出bean,并设置对应属�?
                
                bean.setAnsweredTime(row.getAnsweredTime());
                bean.setCallTime(row.getCallTime());
                bean.setCreatedTime(row.getCreatedTime());
                bean.setFirstCallee(row.getFirstCallee());
                bean.setFirstCaller(row.getFirstCaller());
                bean.setHangupCause(row.getHangupCause());
                bean.setHangupTime(row.getHangupTime());
                bean.setTotalTime(row.getTotalTime());
                        
                export.writeRow(bean);
                bean = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e.getMessage(), e);
        }finally{
            export.close();		//关闭�?
        }
    }

}
