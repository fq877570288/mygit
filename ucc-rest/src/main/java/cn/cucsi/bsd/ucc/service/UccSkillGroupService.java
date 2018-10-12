package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccSkillGroupCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccSkillGroup;
import org.springframework.data.domain.Page;

/**
 * Created by mk on 2017/10/16.
 */
public interface UccSkillGroupService {
    Page<UccSkillGroup> findAll(UccSkillGroupCriteria search);
    Page<UccSkillGroup> findAllTree(UccSkillGroupCriteria search);
    UccSkillGroup findOne(String skillGroupId );
    UccSkillGroup save(UccSkillGroup uccSkillGroup);
    Boolean delete(String skillGroupId);
    Boolean updateStatusById( String status,String skillGroupId);
}
