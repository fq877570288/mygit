package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.RolesPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by tianyuwei on 17/10/11.
 */
public interface RolesPermissionsRepository extends PagingAndSortingRepository<RolesPermissions,String> ,JpaSpecificationExecutor {
//    Page<PermissionGroups> findAll(Pageable pageable);
//    PermissionGroups save(PermissionGroups permissionGroups);
}
