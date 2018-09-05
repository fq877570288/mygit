package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.BaseHangupCause;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by home on 2017/10/16.
 */
public interface BaseHangupCauseRepository extends PagingAndSortingRepository<BaseHangupCause,String>,JpaSpecificationExecutor {
    Page<BaseHangupCause> findAll(Pageable pageable);
}
