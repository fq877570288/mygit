package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.OcAcceptInfoCriteria;
import cn.cucsi.bsd.ucc.data.domain.OcAcceptInfo;

import java.util.List;

/**
 * Created by tnn on 2018/10/25.
 */
public interface OcAcceptInfoService {
    List<OcAcceptInfo> findAll(OcAcceptInfoCriteria ocAcceptInfoCriteria);
    int create(OcAcceptInfo ocAcceptInfo);
}
