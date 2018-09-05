package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.SkillGroupUser;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUserPK;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/17.
 */
public interface SkillGroupUserRepository extends
        PagingAndSortingRepository<SkillGroupUser,SkillGroupUserPK>,JpaSpecificationExecutor{
        List<SkillGroupUser> findSkillGroupUsersByUserIdEquals(String userId);

}
