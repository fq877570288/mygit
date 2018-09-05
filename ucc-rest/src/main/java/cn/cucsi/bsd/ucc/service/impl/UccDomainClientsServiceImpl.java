package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccDomainClientsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomainClients;
import cn.cucsi.bsd.ucc.data.domain.UccDomainClientsPK;
import cn.cucsi.bsd.ucc.data.repo.UccDomainClientsRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDomainClientsSpecs;
import cn.cucsi.bsd.ucc.service.UccDomainClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccDomainClientsServiceImpl implements UccDomainClientsService {
    @Autowired
    UccDomainClientsRepository uccDomainClientsRepository;

    @Override
    public Page<UccDomainClients> findAll(UccDomainClientsCriteria criteria) {
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize());
        return uccDomainClientsRepository.findAll(UccDomainClientsSpecs.createSpec(criteria), pageable);
    }

    @Override
    public UccDomainClients findOne(UccDomainClientsPK pk) {
        return uccDomainClientsRepository.findOne(pk);
    }

    @Override
    public UccDomainClients save(UccDomainClients uccDomainClients) {
        return uccDomainClientsRepository.save(uccDomainClients);
    }

    @Override
    public Boolean delete(UccDomainClientsPK pk) {
        uccDomainClientsRepository.delete(pk);
        return true;
    }
}
