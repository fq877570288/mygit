package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxGwNumbersCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxGwNumbers;
import cn.cucsi.bsd.ucc.data.repo.PbxGwNumbersRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxGwNumbersSpecs;
import cn.cucsi.bsd.ucc.service.PbxGwNumbersService;
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
@Service(value = "PbxGwNumbersService")
public class PbxGwNumbersServiceImpl implements PbxGwNumbersService {
    @Autowired
    PbxGwNumbersRepository repository;
    @Override
    public Page<PbxGwNumbers> findAll(PbxGwNumbersCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "gatewayId");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return repository.findAll(PbxGwNumbersSpecs.createSpec(criteria),pageable);
    }

    @Override
    public PbxGwNumbers findOne(String gwId) {
        return repository.findOne(gwId);
    }

    @Override
    public PbxGwNumbers save(PbxGwNumbers pbxGwNumbers) {
        return repository.save(pbxGwNumbers);
    }

    @Override
    public Boolean delete(String gwId) {
        this.repository.delete(gwId);
        return true;
    }

    @Override
    public Boolean deleteByitselfList(List<PbxGwNumbers> list) {
        this.repository.delete(list);
        return true;
    }

    @Override
    public List<PbxGwNumbers> findAllOfNoPage(PbxGwNumbersCriteria criteria) {
        return repository.findAll(PbxGwNumbersSpecs.createSpec(criteria));
    }
}
