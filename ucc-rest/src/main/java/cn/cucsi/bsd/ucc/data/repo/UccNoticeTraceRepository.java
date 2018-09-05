package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.UccNoticeTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by tianyuwei on 17/10/10.
 */
public interface UccNoticeTraceRepository extends PagingAndSortingRepository<UccNoticeTrace,String>, JpaSpecificationExecutor {
//    Page<UccNoticeTrace> findAll(Pageable pageable);
//    UccNoticeTrace save(UccNoticeTrace uccUsers);
}
