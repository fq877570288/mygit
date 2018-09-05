package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.UccDomain;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UccDomainRepository extends PagingAndSortingRepository<UccDomain, String>,JpaSpecificationExecutor {
    @Modifying
    @Query("update UccDomain d set d.status = ?2 where d.domainId = ?1")
    Integer updateStatusById(String domainId, String status);
}
