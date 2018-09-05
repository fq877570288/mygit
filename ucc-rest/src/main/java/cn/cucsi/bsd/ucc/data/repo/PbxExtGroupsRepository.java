package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
public interface PbxExtGroupsRepository   extends PagingAndSortingRepository<PbxExtGroups, String>, JpaSpecificationExecutor {
    @Modifying
    @Transactional
    @Query("update PbxExtGroups pbx set pbx.status = ?2 where pbx.groupId = ?1")
    Integer updateStatusById(String groupId, String status);


    PbxExtGroups  findPbxExtGroupsByGroupIdEqualsAndAndDomainIdEquals(String groupId , String domainId);

    @Query(value="select * from pbx_ext_groups a where a.group_id in (select b.group_id from ucc.ext_group_exts b where b.ext_id = ?1)",nativeQuery=true)
    List<PbxExtGroups> findpbxExtGroupsByExtId(String ext_id);
}
