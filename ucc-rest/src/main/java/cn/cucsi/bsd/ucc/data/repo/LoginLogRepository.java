package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.LoginLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Song on 2017/10/16.
 */
public interface LoginLogRepository extends PagingAndSortingRepository<LoginLog, String>, JpaSpecificationExecutor {
}
