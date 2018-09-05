package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccDomainServersCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomainServers;
import cn.cucsi.bsd.ucc.data.domain.UccDomainServersPK;
import cn.cucsi.bsd.ucc.data.repo.UccDomainServersRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDomainClientsSpecs;
import cn.cucsi.bsd.ucc.data.specs.UccDomainServersSpecs;
import cn.cucsi.bsd.ucc.service.UccDomainServersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccDomainServersServiceImpl implements UccDomainServersService {
    @Autowired
    UccDomainServersRepository uccDomainServersRepository;

    @Override
    public Page<UccDomainServers> findAll(UccDomainServersCriteria criteria) {
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize());
        return uccDomainServersRepository.findAll(UccDomainServersSpecs.createSpec(criteria), pageable);
    }

    @Override
    public UccDomainServers findOne(UccDomainServersPK pk) {
        return uccDomainServersRepository.findOne(pk);
    }

    @Override
    public UccDomainServers save(UccDomainServers uccDomainClients) {
        return uccDomainServersRepository.save(uccDomainClients);
    }

    @Override
    public Boolean delete(UccDomainServersPK pk) {
        uccDomainServersRepository.delete(pk);
        return true;
    }
}
