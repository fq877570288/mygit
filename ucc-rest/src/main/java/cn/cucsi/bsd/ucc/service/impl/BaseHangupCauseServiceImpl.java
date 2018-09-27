package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.BaseHangupCauseCriteria;
import cn.cucsi.bsd.ucc.data.domain.BaseHangupCause;
import cn.cucsi.bsd.ucc.data.repo.BaseHangupCauseRepository;
import cn.cucsi.bsd.ucc.data.specs.BaseHangupCauseSpecs;
import cn.cucsi.bsd.ucc.service.BaseHangupCauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Created by home on 2017/10/16.
 */
@Service
public class BaseHangupCauseServiceImpl implements BaseHangupCauseService{

    @Autowired
    BaseHangupCauseRepository baseHangupCauseRepository;

    @Override
    public List<BaseHangupCause> findAll(BaseHangupCauseCriteria criteria) {
        //Sort sort = new Sort(Sort.Direction.DESC,"causeSort");
        //Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return baseHangupCauseRepository.findAll(BaseHangupCauseSpecs.createSpec(criteria));
    }

    @Override
    public BaseHangupCause findOne(String causeEn) {
        return baseHangupCauseRepository.findOne(causeEn);
    }

    @Override
    public BaseHangupCause save(BaseHangupCause baseHangupCause) {
        return baseHangupCauseRepository.save(baseHangupCause);
    }

    @Override
    public Boolean delete(String causeEn) {
        this.baseHangupCauseRepository.delete(causeEn);
        return true;
    }
}
