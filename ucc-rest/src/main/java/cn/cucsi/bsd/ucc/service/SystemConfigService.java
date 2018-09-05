package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.SystemConfigCriteria;
import cn.cucsi.bsd.ucc.data.domain.PermissionGroups;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import org.springframework.data.domain.Page;

/**
 * Created by tianyuwei on 2017/10/13.
 */
public interface SystemConfigService {
    Page<SystemConfig> findAll(SystemConfigCriteria search);
    SystemConfig findOne(String name);
    SystemConfig save(SystemConfig systemConfig);
    Boolean delete(String name);
}
