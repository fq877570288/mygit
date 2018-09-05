package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.DomainWeekendException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by home on 2017/10/16.
 */
public interface DomainWeekendExceptionRepository extends PagingAndSortingRepository<DomainWeekendException,String>,JpaSpecificationExecutor {
    Page<DomainWeekendException> findAll(Pageable pageable);
}
