package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.SkillGroupUserCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUser;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUserPK;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/17.
 */
public interface SkillGroupUserService {
    Page<SkillGroupUser> findAll(SkillGroupUserCriteria criteria);
    SkillGroupUser findOne(SkillGroupUserPK pk);
    SkillGroupUser save(SkillGroupUser skillGroupUser);
    Boolean delete(SkillGroupUserPK pk);
    List<UccUsers> findAllOfNoPage( UccUserCriteria criteria);
    Boolean insert(String userId,String skillGroup,String createdPerson);
    List<SkillGroupUser>  findSkillGroupUsersByUserIdEquals(String userId );
    Boolean deleteByUserId(String userId);
    Boolean update(String userId, String skillGroup);


}
