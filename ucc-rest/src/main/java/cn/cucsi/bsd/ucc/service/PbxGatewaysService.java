package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxGatewaysCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxGateways;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxGatewaysService {
    Page<PbxGateways> findAll(PbxGatewaysCriteria criteria);
    List<PbxGateways> findAllOfNoPage(PbxGatewaysCriteria criteria);
    PbxGateways findOne(String gwId);
    PbxGateways save(PbxGateways pbxGateways);
    Boolean delete(String gwId);
}
