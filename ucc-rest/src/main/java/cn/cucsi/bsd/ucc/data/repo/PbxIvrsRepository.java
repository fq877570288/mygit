package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.PbxIvrs;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxIvrsRepository  extends PagingAndSortingRepository<PbxIvrs, String>, JpaSpecificationExecutor {
}
