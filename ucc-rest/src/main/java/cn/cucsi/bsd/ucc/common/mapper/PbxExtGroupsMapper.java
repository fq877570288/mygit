package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PbxExtGroupsMapper {
    List<PbxExtGroups> queryDashboardList(String domainId);
    List<PbxExtGroups> AllPbxExtList(String domainId);
}