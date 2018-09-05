package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.UccPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by tianyuwei on 17/10/10.
 */
public interface UccPermissionsRepository extends PagingAndSortingRepository<UccPermissions,String>, JpaSpecificationExecutor {
    Page<UccPermissions> findAll(Pageable pageable);
}
