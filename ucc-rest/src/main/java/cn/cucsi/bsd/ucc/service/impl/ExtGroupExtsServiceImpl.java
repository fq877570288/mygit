package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.ExtGroupExtsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.mapper.PbxExtGroupsMapper;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExtsPK;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import cn.cucsi.bsd.ucc.data.repo.ExtGroupExtsRepository;
import cn.cucsi.bsd.ucc.data.specs.ExtGroupExtsSpecs;
import cn.cucsi.bsd.ucc.service.ExtGroupExtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
@Service(value = "ExtGroupExtsService")
public class ExtGroupExtsServiceImpl implements ExtGroupExtsService {


    @Autowired
    private ExtGroupExtsRepository ExtGroupExtsRepository;
    @Autowired
    private PbxExtGroupsMapper pbxExtsMapper;
    @Override
    public Page<ExtGroupExts> findAll(ExtGroupExtsCriteria ExtGroupExtsCriteria) {
        Pageable pageable = new PageRequest(ExtGroupExtsCriteria.getPage(), ExtGroupExtsCriteria.getSize());
        return ExtGroupExtsRepository.findAll(ExtGroupExtsSpecs.createSpec(ExtGroupExtsCriteria), pageable);
    }

    @Override
    public ExtGroupExts findOne(ExtGroupExtsPK extGroupExtsPK) {
        return this.ExtGroupExtsRepository.findOne(extGroupExtsPK);
    }

    @Override
    public ExtGroupExts save(ExtGroupExts ExtGroupExts) {
        return this.ExtGroupExtsRepository.save(ExtGroupExts);
    }

    @Override
    public Boolean delete(ExtGroupExtsPK extGroupExtsPK) {
        this.ExtGroupExtsRepository.delete(extGroupExtsPK);
        return true;
    }

    @Override
    public PageResultBean_New<List<PbxExtGroups>> queryDashboardList(String domainId) {
        PageResultBean_New<List<PbxExtGroups>> bean = new PageResultBean_New<List<PbxExtGroups>>();
        bean.setList(pbxExtsMapper.queryDashboardList(domainId));
        return bean;
    }

}
