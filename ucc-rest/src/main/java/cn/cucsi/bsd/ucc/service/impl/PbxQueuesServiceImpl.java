package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.ExtGroupExtsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PbxQueueNumbersCriteria;
import cn.cucsi.bsd.ucc.common.beans.PbxQueuesCriteria;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.data.repo.ExtGroupExtsRepository;
import cn.cucsi.bsd.ucc.data.repo.PbxExtGroupsRepository;
import cn.cucsi.bsd.ucc.data.repo.PbxExtsRepository;
import cn.cucsi.bsd.ucc.data.repo.PbxQueuesRepository;
import cn.cucsi.bsd.ucc.data.specs.ExtGroupExtsSpecs;
import cn.cucsi.bsd.ucc.data.specs.PbxQueuesSpecs;
import cn.cucsi.bsd.ucc.service.PbxQueueNumbersService;
import cn.cucsi.bsd.ucc.service.PbxQueuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jjjjj on 2017-10-16.
 */
@Service(value = "PbxQueuesService")
public class PbxQueuesServiceImpl implements PbxQueuesService {
    @Autowired
    ExtGroupExtsRepository extgroupextsrepository;//分机组 分机 关联表
    @Autowired
    private PbxExtGroupsRepository pbxextgroupsrepository;
    @Autowired
    PbxExtsRepository pbxExtsRepository;
    @Autowired
    PbxQueuesRepository repository;

    @Override
    public Page<PbxQueues> findAll(PbxQueuesCriteria criteria) {

        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(),sort);
        return repository.findAll(PbxQueuesSpecs.createSpec(criteria),pageable);
    }

    @Override
    public List<PbxQueues> findAllOfNoPage(PbxQueuesCriteria criteria) {
        return repository.findAll(PbxQueuesSpecs.createSpec(criteria));
    }

    @Override
    public PbxQueues findOne(String queueId) {
        return repository.findOne(queueId);
    }

    @Override
    public PbxQueues save(PbxQueues pbxQueues) {
        PbxQueues queues =  repository.save(pbxQueues);
        return queues;
    }

    @Override
    public Boolean delete(String queueId) {
        this.repository.delete(queueId);
        return true;
    }

    @Override
    @Transactional
    public  Map<String,List<PbxExts>> findAllPbxExtGroups( PbxQueuesCriteria search) {
        String[] extGroupExtsIdList = extgroupextsrepository.findAllExtsUnderGroupId ();//查到了 所有的groupID
        Map<String,List<PbxExts>> extGroupExtsMap = new HashMap<String,List<PbxExts>>();
        for (String groupId:extGroupExtsIdList
             ) {
            PbxExtGroups group = pbxextgroupsrepository.findPbxExtGroupsByGroupIdEqualsAndAndDomainIdEquals(groupId ,search.getDomainId());
            List<PbxExts> list= new ArrayList<PbxExts>();
            if(group != null){
                List<ExtGroupExts> extGroupExts = extgroupextsrepository.findExtGroupExtsByGroupId(groupId);

                for (ExtGroupExts extgroupexts:extGroupExts
                        ) {
                    PbxExts exts  = pbxExtsRepository.findPbxExtsByExtIdEqualsAndDomainIdEquals(extgroupexts.getExtId(),search.getDomainId());
                    if(exts!=null){
                        list.add(exts);
                    }
                }
            }
            if(group!=null && list.size()>0){
                extGroupExtsMap.put(group.getGroupName()+"_"+group.getGroupId(),list);
            }

        }
        return extGroupExtsMap;
    }

}
