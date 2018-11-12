package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxQueuesCriteria;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import cn.cucsi.bsd.ucc.data.domain.PbxExts;
import cn.cucsi.bsd.ucc.data.domain.PbxQueues;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxQueuesService {
    Page<PbxQueues> findAll(PbxQueuesCriteria criteria);
    PbxQueues findOne(String queueId);
    PbxQueues save(PbxQueues PbxQueues);
    Boolean delete(String queueId);
    Map<String,List<PbxExts>> findAllPbxExtGroups(PbxQueuesCriteria search);
    List<PbxQueues> findAllOfNoPage(PbxQueuesCriteria search);
    List<PbxQueues> findQueueList(PbxQueuesCriteria criteria);
}
