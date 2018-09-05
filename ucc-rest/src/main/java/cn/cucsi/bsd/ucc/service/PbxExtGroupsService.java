package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxExtGroupsCriteria;

import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
public interface PbxExtGroupsService {
    Page<PbxExtGroups> findAll(PbxExtGroupsCriteria search);
    List<PbxExtGroups> findAllGroups(PbxExtGroupsCriteria search);
    PbxExtGroups findOne(String userId);
    PbxExtGroups save(PbxExtGroups uccUsers);
    Boolean delete(String userId);

    List<PbxExtGroups> findGroupsByExtId(String ExtId);
    Boolean updateStatusById(String groupId, String status);

}
