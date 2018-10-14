package cn.cucsi.bsd.ucc.service;


import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccDomainCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomain;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public interface UccDomainService {
    Page<UccDomain> findAll(UccDomainCriteria criteria);
    UccDomain findOne(String domainId);
    UccDomain save(UccDomain uccDomain);
    Boolean delete(String domainId);
    Boolean updateStatusById(String domainId, String status);

    List<UccDomain> findAllOfNoPage(UccDomainCriteria criteria);

    ResultBean<Object> createDomin(UccDomain uccDomain);

}
