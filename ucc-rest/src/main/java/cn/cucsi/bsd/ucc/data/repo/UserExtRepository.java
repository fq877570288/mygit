package cn.cucsi.bsd.ucc.data.repo;


import cn.cucsi.bsd.ucc.data.domain.UserExt;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by mk on 2017/10/16.
 */
public interface UserExtRepository extends PagingAndSortingRepository<UserExt, String>, JpaSpecificationExecutor {
}
