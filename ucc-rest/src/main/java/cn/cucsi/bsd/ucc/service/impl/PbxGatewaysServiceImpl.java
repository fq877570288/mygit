package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxGatewaysCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxGateways;
import cn.cucsi.bsd.ucc.data.repo.PbxGatewaysRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxGatewaysSpecs;
import cn.cucsi.bsd.ucc.service.PbxGatewaysService;
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
@Service(value = "PbxGatewaysService")
public class PbxGatewaysServiceImpl implements PbxGatewaysService {
    @Autowired
    PbxGatewaysRepository repository;

    @Override
    public Page<PbxGateways> findAll(PbxGatewaysCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return repository.findAll(PbxGatewaysSpecs.createSpec(criteria),pageable);
    }

    @Override
    public List<PbxGateways> findAllOfNoPage(PbxGatewaysCriteria criteria) {
        return repository.findAll(PbxGatewaysSpecs.createSpec(criteria));
    }

    @Override
    public PbxGateways findOne(String gwId) {
        return repository.findOne(gwId);
    }

    @Override
    public PbxGateways save(PbxGateways pbxGateways) {
        return repository.save(pbxGateways);
    }

    @Override
    public Boolean delete(String gwId) {
        this.repository.delete(gwId);
        return true;
    }
}
