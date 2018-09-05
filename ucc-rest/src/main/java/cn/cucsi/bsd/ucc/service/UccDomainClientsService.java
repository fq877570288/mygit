package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccDomainClientsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomainClients;
import cn.cucsi.bsd.ucc.data.domain.UccDomainClientsPK;
import org.springframework.data.domain.Page;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public interface UccDomainClientsService {
    Page<UccDomainClients> findAll(UccDomainClientsCriteria criteria);
    UccDomainClients findOne(UccDomainClientsPK pk);
    UccDomainClients save(UccDomainClients uccDomainClients);
    Boolean delete(UccDomainClientsPK pk);
}
