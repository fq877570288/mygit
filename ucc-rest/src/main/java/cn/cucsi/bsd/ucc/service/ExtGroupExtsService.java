package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.ExtGroupExtsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExtsPK;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
public interface ExtGroupExtsService {
    Page<ExtGroupExts> findAll(ExtGroupExtsCriteria ExtGroupExtsCriteria);
    ExtGroupExts findOne(ExtGroupExtsPK ExtGroupExtsPK);
    ExtGroupExts save(ExtGroupExts ExtGroupExts);
    Boolean delete(ExtGroupExtsPK ExtGroupExtsPK);
    PageResultBean_New<List<PbxExtGroups>> queryDashboardList(String domainId);
}
