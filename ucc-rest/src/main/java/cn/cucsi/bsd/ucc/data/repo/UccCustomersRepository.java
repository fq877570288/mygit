package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by tianyuwei on 17/10/11.
 */
public interface UccCustomersRepository  extends PagingAndSortingRepository<UccCustomers,String>, JpaSpecificationExecutor {
    Page<UccCustomers> findAll(Pageable pageable);

    /***
     * 拉黑客户
     * add by wangxiaoyu
     * 2018-08-24
     */
    @Modifying
    @Transactional
    @Query(value ="update ucc_customers uc set uc.type = ?1 , " +
            "uc.updated_user_id = ?2 , " +
            "uc.updated_time = ?3 ," +
            "uc.pull_black_reason = ?4 " +
            "where uc.cust_id = ?5", nativeQuery = true)
    int inBlackListByBusinessCode(Integer type, String userId,Date updatedTime,String pullBlackReason,String custId);

    /***
     * 根据条件查询客户是否在黑名单
     * add by wangxiaoyu
     * 2018-08-31
     */
    @Query(value =" SELECT a.type from ucc_customers as a where a.businesscode = ?1 AND a.domain_id = ?2",nativeQuery = true)
    int checkCustmIsBlack(String businesscode,String domainId);

}
