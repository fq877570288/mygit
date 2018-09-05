package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExtsPK;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
public interface ExtGroupExtsRepository  extends PagingAndSortingRepository<ExtGroupExts, ExtGroupExtsPK>, JpaSpecificationExecutor {

    @Query(value = "SELECT groupId FROM ExtGroupExts  group by groupId")
    String[] findAllExtsUnderGroupId();

    List<ExtGroupExts> findExtGroupExtsByGroupId(String groupId);

    List<ExtGroupExts> findExtGroupExtsByExtId(String ExtId);


   // List<ExtGroupExts> findpByGroupId(String groupId);
}