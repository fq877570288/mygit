package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxIvrsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxIvrs;
import cn.cucsi.bsd.ucc.data.repo.PbxIvrsRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxIvrsSpecs;
import cn.cucsi.bsd.ucc.service.PbxIvrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
@Service(value = "PbxIvrsService")
public class PbxIvrsServiceImpl implements PbxIvrsService {
    @Autowired
    PbxIvrsRepository repository;
    @Override
    public Page<PbxIvrs> findAll(PbxIvrsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "domainId");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return repository.findAll(PbxIvrsSpecs.createSpec(criteria),pageable);
    }


    @Override
    public List<PbxIvrs> findAllOfNoPage(PbxIvrsCriteria criteria) {
        return repository.findAll(PbxIvrsSpecs.createSpec(criteria));
    }

    @Override
    public PbxIvrs findOne(String ivrId) {
        return repository.findOne(ivrId);
    }

    @Override
    public PbxIvrs save(PbxIvrs pbxIvrs) {
        return repository.save(pbxIvrs);
    }

    @Override
    public Boolean delete(String ivrId) {
        this.repository.delete(ivrId);
        return true;
    }

}