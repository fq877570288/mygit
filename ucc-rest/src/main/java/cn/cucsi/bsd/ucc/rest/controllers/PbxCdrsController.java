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
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

/**
 * Created by Song on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/pbxCdrs")
public class PbxCdrsController {

    @Autowired
    private PbxCdrsService PbxCdrsService;
    @Autowired
    private PbxRecordsService pbxRecordsService;
    @Autowired
    private SystemConfigService systemConfigService;

        
    @ApiOperation(value="根据查询条件获取通话详单表", notes="根据查询条件获取通话详单表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<PbxCdrs>> findAll(@RequestBody PbxCdrsCriteria pbxCdrsCriteria){
        return new PageResultBean(this.PbxCdrsService.findAll(pbxCdrsCriteria));
        //return new PageResultBean(this.PbxExtsService.findAll(search));
    }
    @ApiOperation(value = "根据cdrId查询PbxCdrs", notes = "根据cdrId查询PbxCdrs")
    @RequestMapping(value = "/{cdrId}", method= RequestMethod.GET)
    public ResultBean<PbxCdrs> findOne(@PathVariable String cdrId){
        return new ResultBean<>(this.PbxCdrsService.findOne(cdrId));
    }
    @ApiOperation(value = "根据cdrId删除PbxCdrs", notes = "根据cdrId删除PbxCdrs")
    @RequestMapping(value = "/{cdrId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String cdrId){
        for(String strid : cdrId.split(","))
        {
            this.PbxCdrsService.delete(strid);
        }
        return new ResultBean(true);
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
    @RequestMapping(value = "/{cdrId}/record",method= RequestMethod.POST)
    public void getRecording(HttpServletResponse rsp, @PathVariable String cdrId){
        PbxRecords record = this.PbxCdrsService.findOne(cdrId).getPbxRecords1();
        System.out.println(record.getRecordName());  
     
        //PbxRecords record = this.pbxRecordsService.findOne(recordid1);
        InputStream inputStream = null;
        //定义响应头
        rsp.setContentType(record.getContentType());
        rsp.setHeader("Content-Disposition", "attachment; filename=" + record.getRecordName());
        /*if(record.getType().equals("1"))
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
        {*/
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
        //}
        
    }
    @ApiOperation(value = "根据查询条件获取导出文件", notes = "根据查询条件获取导出文件")
    @RequestMapping(value = "/findAllExport",method= RequestMethod.POST)
    public void exportCdrs(HttpServletResponse rsp,@RequestBody PbxCdrsCriteria pbxCdrsCriteria) throws Exception {
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
                System.out.println(row.getCreatedTime());   
                export.writeRow(bean);
                //bean = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e.getMessage(), e);
        }finally{
            export.close();		//关闭�?
        }
    }
    @ApiOperation(value = "根据cdrId获取录音文件,逗号分隔", notes = "根据cdrId获取录音文件,逗号分隔")
    @RequestMapping(value = "/{cdrIds}/downloadzip",method= RequestMethod.POST)
    public void downloadFiles(HttpServletResponse rsp,@PathVariable String cdrIds)
    {
        try {
            List<File> files = new ArrayList<File>();
            for (String cdrId : cdrIds.split(",")) {
                PbxRecords record = this.PbxCdrsService.findOne(cdrId).getPbxRecords1();
                //String fileName = record.getRecordName();
                //InputStream inputStream = null;
                //定义响应头
                //rsp.setContentType(record.getContentType());
                
                   
                //if(record.getType().equals("1"))
                //{
                     /*File file = new File(filePath + "\\" + record.getRecordName());
                    files.add(record.getContent());
                   FileOutputStream f = new FileOutputStream;
                    */
                //}
                //else if(record.getType().equals("2"))
                //{
                    String filePath = systemConfigService.findOne("recordPath").getValue();
                    File file = new File(filePath + "\\" + record.getRecordName());
                    if(file==null|| !file.exists()){
                        //logger.error("要下载的文件不存在，忽略这个文件，文件名： "+fileQualifiedName);
                    }else{
                        files.add(file);
                    }
                //}
            }
            
            UUIDGenerator generatorNot = new UUIDGenerator();
            String taskTransferUuidNot = generatorNot.generate();
            String fileName = taskTransferUuidNot + ".zip";
                //rsp.setCharacterEncoding("utf-8");
            rsp.setContentType("application/zip;charset=utf-8");
            rsp.setHeader("Content-Disposition", "attachment; filename=" + fileName); 
            //rsp.setHeader("Content-Disposition", "attachment; filename=aa.zip");
            String filePath = systemConfigService.findOne("recordPath").getValue();
            File zipFile = new File(filePath + "\\" + fileName);
            zipFiles(files, zipFile);
            File file = new File(filePath + "\\" + fileName);
            //读取文件到ouputStream
            FileInputStream inputStream = new FileInputStream(file);
            int tempbyte;
            byte[] musicFile = new byte[4096];
            OutputStream os = rsp.getOutputStream();
            while ((tempbyte = inputStream.read(musicFile)) != -1) {
                os.write(musicFile, 0, tempbyte);
            }
             //rsp.flushBuffer();
            os.close();
            File filede = new File(filePath + "\\" + fileName);
            if(filede.exists()){
                filede.delete();
            }
	}
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void zipFiles(List<File> srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (int i = 0; i < srcFiles.size(); i++) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(srcFiles.get(i));
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(srcFiles.get(i).getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            //fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @ApiOperation(value = "根据cdrId获取录音文件,逗号分隔", notes = "根据cdrId获取录音文件,逗号分隔")
    @RequestMapping(value = "/{cdrIds}/downloadzip",method= RequestMethod.POST)
    public void downloadFiles(HttpServletResponse rsp,@PathVariable String cdrIds)
    {
        try {
            List<File> files = new ArrayList<File>();
            for (String cdrId : cdrIds.split(",")) {
                PbxRecords record = this.PbxCdrsService.findOne(cdrId).getPbxRecords1();
                //String fileName = record.getRecordName();
                //InputStream inputStream = null;
                //定义响应头
                //rsp.setContentType(record.getContentType());
                
                   
                /*if(record.getType().equals("1"))
                {
                     /*File file = new File(filePath + "\\" + record.getRecordName());
                    files.add(record.getContent());
                   FileOutputStream f = new FileOutputStream;
                    */
                //}
                //else if(record.getType().equals("2"))
                //{
                    String filePath = systemConfigService.findOne("recordPath").getValue();
                    File file = new File(filePath + "\\" + record.getRecordName());
                     System.out.println(filePath + "\\" + record.getRecordName());
                    if(file==null|| !file.exists()){
                        //logger.error("要下载的文件不存在，忽略这个文件，文件名： "+fileQualifiedName);
                    }else{
                        files.add(file);
                    }
                //}
            }
            
            UUIDGenerator generatorNot = new UUIDGenerator();
            String taskTransferUuidNot = generatorNot.generate();
            String fileName = taskTransferUuidNot + ".zip";
                //rsp.setCharacterEncoding("utf-8");
                //rsp.setContentType("application/zip;charset=utf-8");
                rsp.setHeader("Content-Disposition", "attachment; filename=" + fileName); 
            //rsp.setHeader("Content-Disposition", "attachment; filename=aa.zip");
            String filePath = systemConfigService.findOne("recordPath").getValue();
            File zipFile = new File(filePath + "\\" + fileName);
            zipFiles(files, zipFile);
           
            
            
            
         /*   
            InputStream inputStream = null;
        //定义响应头
        rsp.setContentType("application/zip;charset=utf-8");
        rsp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
         // File file = new File(filePath + "\\" + record.getRecordName());
            try {
                //读取文件到ouputStream
                inputStream = new FileInputStream(zipFile);
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
            */
            
            
            
            
            File file = new File(filePath + "\\" + fileName);
            //读取文件到ouputStream
            FileInputStream inputStream = new FileInputStream(file);
            int tempbyte;
            byte[] musicFile = new byte[1024];
            OutputStream os = rsp.getOutputStream();
            while ((tempbyte = inputStream.read(musicFile)) != -1) {
                os.write(musicFile, 0, tempbyte);
            }
             //rsp.flushBuffer();
            os.close();
            File filede = new File(filePath + "\\" + fileName);
            if(filede.exists()){
                filede.delete();
            }

	}
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void zipFiles(List<File> srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (int i = 0; i < srcFiles.size(); i++) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(srcFiles.get(i));
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(srcFiles.get(i).getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                //OutputStream os = rsp.getOutputStream();
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            //fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
