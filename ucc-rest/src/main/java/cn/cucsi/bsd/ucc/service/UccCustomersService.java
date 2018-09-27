package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.UccBlackListCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccCustomersCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccToBlackCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import org.springframework.data.domain.Page;

import java.util.List;

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
     * 拉黑客户--WEB
     * add by wangxiaoyu
     * 2018-09-27
     */
    //int inBlackListByBusinessCodeWEB(UccCustomers customer) throws Exception;

    /***
     * 根据条件查询客户是否在黑名单
     * add by wangxiaoyu
     * 2018-08-31
     */
    int custmIsBlack(String businesscode,String domainId);
    /***
     * 根据条件查询黑名单列表
     * add by zss
     * 2018-09-6
     */
    PageResultBean_New<List<UccCustomers>> findBlackList(UccBlackListCriteria uccBlackListCriteria);
    /***
     * 根据条件修改客户信息
     * add by zss
     * 2018-09-13
     */
    UccCustomers update(UccCustomers uccCustomers);

    UccCustomers selectByBusinessCode(String businessCode,String domainId) throws Exception;
}

