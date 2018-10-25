package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.OcAcceptInfoCriteria;
import cn.cucsi.bsd.ucc.common.mapper.OcAcceptInfoMapper;
import cn.cucsi.bsd.ucc.data.domain.OcAcceptInfo;
import cn.cucsi.bsd.ucc.service.OcAcceptInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tnn on 2018/10/25.
 */
@Service
public class OcAcceptInfoServiceImpl implements OcAcceptInfoService {
    @Autowired
    private OcAcceptInfoMapper ocAcceptInfoMapper;
    @Override
    public List<OcAcceptInfo> findAll(OcAcceptInfoCriteria ocAcceptInfoCriteria) {
        return ocAcceptInfoMapper.findAll(ocAcceptInfoCriteria);
    }

    @Override
    public int create(OcAcceptInfo ocAcceptInfo) {
        return ocAcceptInfoMapper.create(ocAcceptInfo);
    }
}
