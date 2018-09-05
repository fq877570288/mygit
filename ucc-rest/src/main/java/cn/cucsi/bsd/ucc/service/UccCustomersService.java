package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccCustomersCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccToBlackCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import org.springframework.data.domain.Page;

/**
 * Created by tianyuwei on 2017/10/13.
 */

public interface UccCustomersService {

    Page<UccCustomers> findAll(UccCustomersCriteria criteria);
    UccCustomers findOne(String name);
    UccCustomers save(UccCustomers uccCustomers);
    Boolean delete(String name);
    /***
     * 拉黑客户
     * add by wangxiaoyu
     * 2018-08-24
     */
    int inBlackListByBusinessCode(UccToBlackCriteria uccToBlackCriteria) throws Exception;

    /***
     * 根据businessCode查询客户是否在黑名单
     * add by wangxiaoyu
     * 2018-08-31
     */
    int custmIsBlack(String businessCode);
}

