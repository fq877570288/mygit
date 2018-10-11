package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccDomainCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomain;
import cn.cucsi.bsd.ucc.data.repo.UccDomainRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDomainSpecs;
import cn.cucsi.bsd.ucc.service.UccDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Transactional
@Service
public class UccDomainServiceImpl implements UccDomainService {
    @Autowired
    UccDomainRepository uccDomainRepository;

    @Override
    public Page<UccDomain> findAll(UccDomainCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(),sort);
        return uccDomainRepository.findAll(UccDomainSpecs.createSpec(criteria), pageable);

    }

    @Override
    public UccDomain findOne(String domainId) {
        return this.uccDomainRepository.findOne(domainId);
    }

    @Override
    public UccDomain save(UccDomain uccDomain) {
        return this.uccDomainRepository.save(uccDomain);
    }

    @Override
    public Boolean delete(String domainId) {
        this.uccDomainRepository.delete(domainId);
        return true;
    }

    @Override
    public Boolean updateStatusById(String domainId, String status) {
        Integer result = this.uccDomainRepository.updateStatusById(domainId, status);
        if(result == 0) return false;
        return true;
    }

    @Override
    public List<UccDomain> findAllOfNoPage(UccDomainCriteria search) {
        return uccDomainRepository.findAll(UccDomainSpecs.createSpec(search));
    }


}
