package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public interface UccDeptsService {
    Page<UccDepts> findAll(UccDeptsCriteria criteria);
    UccDepts findOne(String deptId);
    UccDepts save(UccDepts uccCustomers);
    Boolean delete(String deptId);
    Page<UccDepts> findAllTree(UccDeptsCriteria criteria);
}
