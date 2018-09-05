package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxQueueNumbersCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxQueueNumbers;
import cn.cucsi.bsd.ucc.data.domain.PbxQueueNumbersPK;
import cn.cucsi.bsd.ucc.data.repo.PbxQueueNumbersRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxQueueNumbersSpecs;
import cn.cucsi.bsd.ucc.service.PbxQueueNumbersService;
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
@Service(value = "PbxQueueNumbersService")
public class PbxQueueNumbersServiceImpl implements PbxQueueNumbersService {
    @Autowired
    PbxQueueNumbersRepository repository;
    @Override
    public List<PbxQueueNumbers> findAll(PbxQueueNumbersCriteria criteria) {
        return repository.findAll(PbxQueueNumbersSpecs.createSpec(criteria));
    }

    @Override
    public PbxQueueNumbers findOne(PbxQueueNumbersPK pbxQueueNumbersPK) {
        return repository.findOne(pbxQueueNumbersPK);
    }


    @Override
    public PbxQueueNumbers save(PbxQueueNumbers pbxQueueNumbers) {
        return repository.save(pbxQueueNumbers);
    }

    @Override
    public Boolean delete(PbxQueueNumbersPK pbxQueueNumbersPK) {
        repository.delete(pbxQueueNumbersPK);
        return null;
    }
}
