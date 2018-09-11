package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccNoticeFileCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeFile;
import cn.cucsi.bsd.ucc.data.repo.UccNoticeFileRepository;
import cn.cucsi.bsd.ucc.data.specs.UccNoticeFileSpecs;
import cn.cucsi.bsd.ucc.service.UccNoticeFileService;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import cn.cucsi.bsd.ucc.service.SystemConfigService;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccNoticeFileServiceImpl implements UccNoticeFileService{
    @Autowired
    UccNoticeFileRepository uccNoticeFileRepository;
    SystemConfigService systemConfigService;
SystemConfig systemConfig;
    @Override
    public Page<UccNoticeFile> findAll(UccNoticeFileCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "uploadTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccNoticeFileRepository.findAll(UccNoticeFileSpecs.createSpec(criteria), pageable);
    }

    @Override
    public UccNoticeFile findOne(String noticeFileId) {
        return uccNoticeFileRepository.findOne(noticeFileId);
    }

    @Override
    public UccNoticeFile save(byte[] fileBox,UccNoticeFile uccNoticeFile) {
        UccNoticeFile save= uccNoticeFileRepository.save(uccNoticeFile);
        if(fileBox!=null && fileBox.length>0){
            systemConfig = systemConfigService.findOne("noticeFilePath");
            String filePath = systemConfig.getValue();
            File targetFile = new File(filePath);
            
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            try {
                FileOutputStream out = new FileOutputStream(filePath+save.getNoticeFileId()+uccNoticeFile.getFileName());
                out.write(fileBox);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return save;        
    }
 
    @Override
    public Boolean delete(String noticeFileId) {
        UccNoticeFile uccNoticeFile = this.findOne(noticeFileId);
        systemConfig = systemConfigService.findOne("noticeFilePath");
        String filePath = systemConfig.getValue();
        File file = new File(filePath+noticeFileId+uccNoticeFile.getFileName());
        if(file.exists()){
            file.delete();
        }
        uccNoticeFileRepository.delete(noticeFileId);
        return true;
        
    }
    @Override
    public UccNoticeFile updateById(UccNoticeFile uccNoticeFile) {

  //      return repository.updateById( PbxMusics.getDescription(),PbxMusics.getUpdatedPerson(),PbxMusics.getUpdatedTime(),PbxMusics.getMusicId());
        return uccNoticeFileRepository.save(uccNoticeFile);
    }
}
