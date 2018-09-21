package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccNoticeFileCriteria;
import cn.cucsi.bsd.ucc.common.mapper.UccNoticeFileMapper;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeFile;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;

import cn.cucsi.bsd.ucc.data.repo.UccNoticeFileRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDeptsSpecs;
import cn.cucsi.bsd.ucc.data.specs.UccNoticeFileSpecs;
import cn.cucsi.bsd.ucc.service.UccNoticeFileService;
import cn.cucsi.bsd.ucc.service.SystemConfigService;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccNoticeFileServiceImpl implements UccNoticeFileService{
    @Autowired
    UccNoticeFileRepository uccNoticeFileRepository;
    @Autowired
    private UccNoticeFileMapper uccNoticeFileMapper;
    @Autowired
    private SystemConfigService systemConfigService;
     
    @Override
    public Page<UccNoticeFile> findAll(UccNoticeFileCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "uploadTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccNoticeFileRepository.findAll(UccNoticeFileSpecs.createSpec(criteria), pageable);
    }
    @Override
    public List<UccNoticeFile> findAllOne(UccNoticeFileCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "uploadTime");
        //Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccNoticeFileRepository.findAll(UccNoticeFileSpecs.createSpec(criteria),sort);
    }
    @Override
    public UccNoticeFile findOne(String noticeFileId) {
        return uccNoticeFileRepository.findOne(noticeFileId);
    }
    @Override
    public List<UccNoticeFile> findFileList(String noticeFileId) {
        return uccNoticeFileMapper.findFileList(noticeFileId);
    }

    @Override
    public UccNoticeFile save(byte[] fileBox,UccNoticeFile uccNoticeFile) {
        SystemConfig systemConfig  = systemConfigService.findOne("noticeFilePath");
        UccNoticeFile uccNoticeFilere = uccNoticeFileRepository.save(uccNoticeFile);
        if(fileBox!=null && fileBox.length>0){
            String filePath = systemConfig.getValue() + "\\" + uccNoticeFilere.getNoticeFileId() + "\\";
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            try {
                FileOutputStream out = new FileOutputStream(filePath  + uccNoticeFilere.getFileName());
                out.write(fileBox);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
                
        return uccNoticeFilere;
    }
    @Override
    public UccNoticeFile save(UccNoticeFile uccNoticeFile) {
        return uccNoticeFileRepository.save(uccNoticeFile);
    }

    @Override
    public Boolean delete(String noticeFileId) {
        SystemConfig systemConfig  = systemConfigService.findOne("noticeFilePath");
        String filePath = systemConfig.getValue() + "\\" + noticeFileId;
        File dir=new File(filePath);//路径
        Boolean bol= deleteDir(dir);
        uccNoticeFileRepository.delete(noticeFileId);
        return true;
    }
    private static Boolean deleteDir(File dir) {
      if (dir.isDirectory()) {
          String[] children = dir.list();
          for (int i=0; i<children.length; i++) {
              Boolean success = deleteDir(new File(dir, children[i]));
              if (!success) {
                  return false;
              }
          }
      }
      // 目录此时为空，可以删除
      return dir.delete();
    }
}
