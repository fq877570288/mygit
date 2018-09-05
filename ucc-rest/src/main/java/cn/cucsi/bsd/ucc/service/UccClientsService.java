package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccClientsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccClients;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/13.
 */
public interface UccClientsService {
    Page<UccClients> findAll(UccClientsCriteria search);
    List<UccClients> findAllOfNoPage(UccClientsCriteria search);
    UccClients findOne(String name);
    UccClients save(UccClients uccClients);
    Boolean delete(String name);
}
