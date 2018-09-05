package cn.cucsi.bsd.ucc.service;


import cn.cucsi.bsd.ucc.common.beans.UccDomainServersCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomainServers;
import cn.cucsi.bsd.ucc.data.domain.UccDomainServersPK;
import org.springframework.data.domain.Page;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public interface UccDomainServersService {
    Page<UccDomainServers> findAll(UccDomainServersCriteria criteria);
    UccDomainServers findOne(UccDomainServersPK pk);
    UccDomainServers save(UccDomainServers uccDomainClients);
    Boolean delete(UccDomainServersPK pk);
}
