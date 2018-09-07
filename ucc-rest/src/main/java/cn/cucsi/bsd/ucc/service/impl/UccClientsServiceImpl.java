package cn.cucsi.bsd.ucc.service.impl;


import cn.cucsi.bsd.ucc.common.beans.UccClientsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccClients;
import cn.cucsi.bsd.ucc.data.repo.UccClientsRepository;
import cn.cucsi.bsd.ucc.data.specs.UccClientsSpecs;
import cn.cucsi.bsd.ucc.service.UccClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/13.
 */

@Service
public class UccClientsServiceImpl implements UccClientsService{
    @Autowired
    private UccClientsRepository uccClientsRepository;

    @Override
    public Page<UccClients> findAll(UccClientsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccClientsRepository.findAll(UccClientsSpecs.createSpec(criteria), pageable);
    }

    @Override
    public List<UccClients> findAllOfNoPage(UccClientsCriteria search) {
        return uccClientsRepository.findAll(UccClientsSpecs.createSpec(search));
    }

    @Override
    public UccClients findOne(String name) {
        return this.uccClientsRepository.findOne(name);
    }

    @Override
    public UccClients save(UccClients uccClients) {
        return this.uccClientsRepository.save(uccClients);
    }

    @Override
    public Boolean delete(String name) {
        this.uccClientsRepository.delete(name);
        return true;
    }

    @Override
    public Boolean deleteAll() {
        this.uccClientsRepository.deleteAll();
        return true;
    }
}
