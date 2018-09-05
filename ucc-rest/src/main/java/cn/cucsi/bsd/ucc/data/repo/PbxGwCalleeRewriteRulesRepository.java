package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.PbxGwCalleeRewriteRules;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxGwCalleeRewriteRulesRepository  extends PagingAndSortingRepository<PbxGwCalleeRewriteRules, String>, JpaSpecificationExecutor {
}
