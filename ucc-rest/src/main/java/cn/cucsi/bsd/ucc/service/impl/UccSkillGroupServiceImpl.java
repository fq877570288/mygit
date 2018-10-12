package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccSkillGroupCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccSkillGroup;
import cn.cucsi.bsd.ucc.data.repo.UccSkillGroupRepository;
import cn.cucsi.bsd.ucc.data.specs.UccSkillGroupSpecs;
import cn.cucsi.bsd.ucc.service.UccSkillGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by mk on 2017/10/16.
 */
@Transactional
@Service
public class UccSkillGroupServiceImpl implements UccSkillGroupService {
    @Autowired
    UccSkillGroupRepository uccSkillGroupRepository;
    @Override
    public Page<UccSkillGroup> findAll(UccSkillGroupCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "skillGroupName");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccSkillGroupRepository.findAll(UccSkillGroupSpecs.createSpec(criteria),pageable);
    }

    @Override
    public Page<UccSkillGroup> findAllTree(UccSkillGroupCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "skillGroupName");
        Pageable pageable = new PageRequest(0, 999999, sort);
        return uccSkillGroupRepository.findAll(UccSkillGroupSpecs.createSpec(criteria),pageable);
    }

    @Override
    public UccSkillGroup findOne(String skillGroupId) {
        return uccSkillGroupRepository.findOne(skillGroupId);
    }

    @Override
    public UccSkillGroup save(UccSkillGroup uccSkillGroup) {
        return uccSkillGroupRepository.save(uccSkillGroup);
    }

    @Override
    public Boolean delete(String skillGroupId) {
        this.uccSkillGroupRepository.delete(skillGroupId);
        return true;
    }

    @Override
    public Boolean updateStatusById(String status, String skillGroupId) {
        Integer result = this.uccSkillGroupRepository.updateStatusById(status,skillGroupId);
        if(result == 0) return false;
        return true;
    }
}
