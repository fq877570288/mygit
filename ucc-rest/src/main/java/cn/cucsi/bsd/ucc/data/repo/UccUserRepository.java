package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UccUserRepository  extends PagingAndSortingRepository<UccUsers, String>, JpaSpecificationExecutor {
    UccUsers findByUserName(String userName);
    UccUsers save(UccUsers uccUsers);

}
