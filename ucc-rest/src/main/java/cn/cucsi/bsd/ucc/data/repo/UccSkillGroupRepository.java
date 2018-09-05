package cn.cucsi.bsd.ucc.data.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by tianyuwei on 17/10/10.
 */
public interface UccSkillGroupRepository extends PagingAndSortingRepository<cn.cucsi.bsd.ucc.data.domain.UccSkillGroup,String>,JpaSpecificationExecutor {
    Page<cn.cucsi.bsd.ucc.data.domain.UccSkillGroup> findAll(Pageable pageable);

    @Modifying
    @Query("update UccSkillGroup d set d.status = ?1  where d.skillGroupId = ?2")
    Integer updateStatusById(String status,String skillGroupId);
}
