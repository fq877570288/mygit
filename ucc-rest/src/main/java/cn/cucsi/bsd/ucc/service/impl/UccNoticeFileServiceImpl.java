package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccNoticeFileCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeFile;
import cn.cucsi.bsd.ucc.data.repo.UccNoticeFileRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDeptsSpecs;
import cn.cucsi.bsd.ucc.data.specs.UccNoticeFileSpecs;
import cn.cucsi.bsd.ucc.service.UccNoticeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccNoticeFileServiceImpl implements UccNoticeFileService{
    @Autowired
    UccNoticeFileRepository uccNoticeFileRepository;

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
    public UccNoticeFile save(UccNoticeFile uccNoticeFile) {
        return uccNoticeFileRepository.save(uccNoticeFile);
    }

    @Override
    public Boolean delete(String noticeFileId) {
        uccNoticeFileRepository.delete(noticeFileId);
        return true;
    }
}
