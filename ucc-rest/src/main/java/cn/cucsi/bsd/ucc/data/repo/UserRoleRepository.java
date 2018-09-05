package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.UserRole;
import cn.cucsi.bsd.ucc.data.domain.UserRolePK;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by mk on 2017/10/16.
 */
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, UserRolePK>, JpaSpecificationExecutor {
}
