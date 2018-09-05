package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxRecordsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxRecords;
import cn.cucsi.bsd.ucc.data.repo.PbxRecordsRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxRecordsSpecs;
import cn.cucsi.bsd.ucc.service.PbxRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by jjjjj on 2017-10-16.
 */
@Service
public class PbxRecordsServiceImpl implements PbxRecordsService {
    @Autowired
    PbxRecordsRepository repository;
    @Override
    public Page<PbxRecords> findAll(PbxRecordsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "domainId");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(),sort);
        return repository.findAll(PbxRecordsSpecs.createSpec(criteria),pageable);
    }

    @Override
    public PbxRecords findOne(String recordId) {
        return repository.findOne(recordId);
    }

    @Override
    public PbxRecords save(PbxRecords pbxRecords) {
        return repository.save(pbxRecords);
    }

    @Override
    public Boolean delete(String recordId) {
        this.repository.delete(recordId);
        return true;
    }
}
