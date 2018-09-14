package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.TeamUsers;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeamUsersRepository  extends PagingAndSortingRepository<TeamUsers,String>,JpaSpecificationExecutor {
}
