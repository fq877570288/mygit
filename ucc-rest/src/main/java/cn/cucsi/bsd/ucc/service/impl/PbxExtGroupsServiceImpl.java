package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxExtGroupsCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import cn.cucsi.bsd.ucc.data.repo.ExtGroupExtsRepository;
import cn.cucsi.bsd.ucc.data.repo.PbxExtGroupsRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxExtGroupsSpecs;
import cn.cucsi.bsd.ucc.service.PbxExtGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@Service(value = "pbxextGroupsService")
@Transactional
public class PbxExtGroupsServiceImpl implements PbxExtGroupsService {
    @Autowired
    private PbxExtGroupsRepository pbxextgroupsrepository;
    @Autowired
    private ExtGroupExtsRepository ExtGroupExtsRepository;


    @Override
    public Page<PbxExtGroups> findAll(PbxExtGroupsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "updatedTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return pbxextgroupsrepository.findAll(PbxExtGroupsSpecs.createSpec(criteria), pageable);
    }

    @Override
    public List<PbxExtGroups> findAllGroups(PbxExtGroupsCriteria search) {
        return pbxextgroupsrepository.findAll(PbxExtGroupsSpecs.createSpec(search));
    }

    @Override
    public PbxExtGroups findOne(String groupId) {
        return pbxextgroupsrepository.findOne(groupId);
    }

    @Override
    public PbxExtGroups save(PbxExtGroups pbxextgroups) {

        return pbxextgroupsrepository.save(pbxextgroups);
    }

    @Override
    public Boolean delete(String groupId) {
        this.pbxextgroupsrepository  .delete(groupId);
        return true;
    }

    @Override
    public List<PbxExtGroups> findGroupsByExtId(String ExtId) {
        List<ExtGroupExts> extGroupExts = this.ExtGroupExtsRepository.findExtGroupExtsByExtId(ExtId);
        List<PbxExtGroups> extGroups = new ArrayList<PbxExtGroups>();
        for (ExtGroupExts extGroupExt: extGroupExts
             ) {
            PbxExtGroups extGroup = this.pbxextgroupsrepository.findOne(extGroupExt.getGroupId());
            extGroups.add(extGroup);
        }

        return extGroups;
    }

    @Override
    public Boolean updateStatusById(String groupId, String status) {
        Integer result = this.pbxextgroupsrepository.updateStatusById(groupId, status);
        if(result == 0) return false;
        return true;
    }
}
