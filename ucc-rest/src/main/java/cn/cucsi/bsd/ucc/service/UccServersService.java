package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccServersCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccServers;
import org.springframework.data.domain.Page;

/**
 * Created by mk on 2017/10/16.
 */
public interface UccServersService {
    Page<UccServers> findAll(UccServersCriteria search);
    UccServers findOne(String serverName );
    UccServers save(UccServers uccServers);
    Boolean delete(String serverName);
}
