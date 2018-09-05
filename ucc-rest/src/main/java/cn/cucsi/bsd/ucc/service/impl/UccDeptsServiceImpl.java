package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.repo.UccDeptsRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDeptsSpecs;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccDeptsServiceImpl implements UccDeptsService {
    @Autowired
    UccDeptsRepository uccDeptsRepository;

    @Override
    public Page<UccDepts> findAll(UccDeptsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "deptCreateTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccDeptsRepository.findAll(UccDeptsSpecs.createSpec(criteria), pageable);
    }

    @Override
    public UccDepts findOne(String deptId) {
        return this.uccDeptsRepository.findOne(deptId);
    }

    @Override
    public UccDepts save(UccDepts uccCustomers) {
        return this.uccDeptsRepository.save(uccCustomers);
    }

    @Override
    public Boolean delete(String deptId) {
        this.uccDeptsRepository.delete(deptId);
        return true;
    }

    @Override
    public Page<UccDepts> findAllTree(UccDeptsCriteria criteria) {
        Pageable pageable = new PageRequest(0, 9999);
        Sort sort = new Sort(Sort.Direction.ASC, "deptLevel");
        return uccDeptsRepository.findAll(UccDeptsSpecs.createSpec(criteria), pageable);
    }
}
