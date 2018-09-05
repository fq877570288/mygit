package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.SystemConfigCriteria;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import cn.cucsi.bsd.ucc.data.repo.SystemConfigRepository;
import cn.cucsi.bsd.ucc.data.specs.SystemConfigSpecs;
import cn.cucsi.bsd.ucc.data.specs.UccUserSpecs;
import cn.cucsi.bsd.ucc.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by tianyuwei on 2017/10/13.
 */
@Service
public class SystemConfigSerciceImpl implements SystemConfigService {
    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @Override
    public Page<SystemConfig> findAll(SystemConfigCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return systemConfigRepository.findAll(SystemConfigSpecs.createSpec(criteria), pageable);
    }

    @Override
    public SystemConfig findOne(String name) {
        return this.systemConfigRepository.findOne(name);
    }

    @Override
    public SystemConfig save(SystemConfig systemConfig) {
        return this.systemConfigRepository.save(systemConfig);
    }

    @Override
    public Boolean delete(String name) {
        this.systemConfigRepository.delete(name);
        return true;
    }
}
