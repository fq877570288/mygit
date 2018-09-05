package cn.cucsi.bsd.ucc.data.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by tianyuwei on 17/10/10.
 */
public interface UccServersRepository extends PagingAndSortingRepository<cn.cucsi.bsd.ucc.data.domain.UccServers,String>,JpaSpecificationExecutor {
    Page<cn.cucsi.bsd.ucc.data.domain.UccServers> findAll(Pageable pageable);
}
