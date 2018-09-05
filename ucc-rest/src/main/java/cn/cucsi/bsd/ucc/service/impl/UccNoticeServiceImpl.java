package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccNoticeCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNotice;
import cn.cucsi.bsd.ucc.data.repo.UccNoticeRepository;
import cn.cucsi.bsd.ucc.data.specs.UccNoticeFileSpecs;
import cn.cucsi.bsd.ucc.data.specs.UccNoticeSpecs;
import cn.cucsi.bsd.ucc.service.UccNoticeService;
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
public class UccNoticeServiceImpl implements UccNoticeService{

    @Autowired
    UccNoticeRepository uccNoticeRepository;

    @Override
    public Page<UccNotice> findAll(UccNoticeCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "startDate");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccNoticeRepository.findAll(UccNoticeSpecs.createSpec(criteria), pageable);
    }

    @Override
    public UccNotice findOne(String noticeId) {
        return uccNoticeRepository.findOne(noticeId);
    }

    @Override
    public UccNotice save(UccNotice uccNotice) {
        return uccNoticeRepository.save(uccNotice);
    }

    @Override
    public Boolean delete(String noticeId) {
        uccNoticeRepository.delete(noticeId);
        return true;
    }
}
