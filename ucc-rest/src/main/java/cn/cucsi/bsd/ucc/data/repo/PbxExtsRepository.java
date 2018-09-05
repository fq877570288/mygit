package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.PbxExts;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
public interface PbxExtsRepository extends PagingAndSortingRepository<PbxExts,String>, JpaSpecificationExecutor {
    PbxExts findPbxExtsByExtIdEqualsAndDomainIdEquals(String extId , String domainId);

    @Query(value="SELECT * FROM ucc.pbx_exts a WHERE a.domain_id= ?1 AND a.ext_id NOT IN (SELECT b.ext_id FROM ucc.user_ext b)",nativeQuery=true)
    List<PbxExts> findAllFreeExts(String domainId);



    @Query(value="SELECT * FROM ucc.pbx_exts a WHERE a.domain_id= ?1 AND a.ext_num = ?2",nativeQuery=true)
    List<PbxExts> findPbxExtsByExtNum(String domain_id,String ext_num);

    @Query(value="SELECT * FROM ucc.pbx_exts a WHERE a.domain_id= ?1 AND a.ext_num = ?2 AND a.ext_id != ?3",nativeQuery=true)
    List<PbxExts> findPbxExtsByExtNumAndExtIdNotEquals(String domain_id,String extNum,String extId);
}
