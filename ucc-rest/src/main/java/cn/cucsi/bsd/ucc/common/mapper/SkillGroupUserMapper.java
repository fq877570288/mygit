package cn.cucsi.bsd.ucc.common.mapper;


import cn.cucsi.bsd.ucc.common.beans.SkillGroupUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SkillGroupUserMapper {
    int deleteByPrimaryKey(SkillGroupUser skillGroupUser);

    int insert(SkillGroupUser record);

    int insertSelective(SkillGroupUser record);

    List<SkillGroupUser> selectByPrimaryKey(SkillGroupUserCriteria skillGroupUserCriteria);

    int updateByPrimaryKeySelective(SkillGroupUser record);

    int updateByPrimaryKey(SkillGroupUser record);
}