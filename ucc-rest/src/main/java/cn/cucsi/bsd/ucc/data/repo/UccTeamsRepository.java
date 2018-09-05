package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.UccTeams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by tianyuwei on 17/10/10.
 */
public interface UccTeamsRepository extends PagingAndSortingRepository<UccTeams,String>,JpaSpecificationExecutor {
    Page<UccTeams> findAll(Pageable pageable);
    List<UccTeams> findAllByDomainId(String domainId);
    UccTeams save(UccTeams uccTeams);


}
