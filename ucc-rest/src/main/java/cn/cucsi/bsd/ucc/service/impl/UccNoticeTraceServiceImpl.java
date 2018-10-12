package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccNoticeTraceCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeTrace;
import cn.cucsi.bsd.ucc.data.repo.UccNoticeTraceRepository;
import cn.cucsi.bsd.ucc.service.UccNoticeTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import cn.cucsi.bsd.ucc.data.specs.UccNoticeTraceSpecs;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by mk on 2017/10/13.
 */
@Service
public class UccNoticeTraceServiceImpl  implements UccNoticeTraceService{
    @Autowired
    private UccNoticeTraceRepository uccNoticeTraceRepository;

    @Override
    public Page<UccNoticeTrace> findAll(UccNoticeTraceCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccNoticeTraceRepository.findAll(UccNoticeTraceSpecs.createSpec(criteria),pageable);
    }
    @Override
    public List<UccNoticeTrace> findAllOne(UccNoticeTraceCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        //Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        //return uccNoticeTraceRepository.findAll(UccNoticeTraceSpecs.createSpec(criteria),pageable);
        return uccNoticeTraceRepository.findAll(UccNoticeTraceSpecs.createSpec(criteria),sort);
    }
    @Override
    public UccNoticeTrace findOne(String noticeTraceId) {
        return uccNoticeTraceRepository.findOne(noticeTraceId);
    }

    @Override
    public UccNoticeTrace save(UccNoticeTrace uccNoticeTrace) {
        Date dateTime = new Date();
        uccNoticeTrace.setCreatedTime(dateTime);
        uccNoticeTrace.setOperateTime(dateTime);
        return uccNoticeTraceRepository.save(uccNoticeTrace);
    }

    @Override
    public Boolean delete(String noticeTraceId) {
        this.uccNoticeTraceRepository.delete(noticeTraceId);
        return true;
    }
}
