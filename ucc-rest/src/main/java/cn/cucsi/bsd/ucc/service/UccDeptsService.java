package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Map;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public interface UccDeptsService {

    Page<UccDepts> findAll(UccDeptsCriteria criteria);
    UccDepts findOne(String deptId);
    UccDepts save(UccDepts uccCustomers);
    Boolean delete(String deptId);
    Page<UccDepts> findAllTree(UccDeptsCriteria criteria);

    List<UccDepts> selectByUserId(String userId) throws Exception;

    int deleteByPrimaryKey(String deptId) throws Exception;

    int insert(UccDepts record) throws Exception;

    UccDepts selectByPrimaryKey(String deptId) throws Exception;

    List<UccDepts> selectAll() throws Exception;

    List<String> selectDidsByUserId(String userId) throws Exception;

    int updateByPrimaryKey(UccDepts record) throws Exception;

    int updateSimpleByPrimaryKey(UccDepts record) throws Exception;

    int selectSubCountsByPrimaryKey(String deptId) throws Exception;

    int clearUsersDeptsByDeptId(String deptId) throws Exception;

    int clearUsersDeptsByUserId(String userId) throws Exception;

    int insertUserDepts(String userId, String[] depts) throws Exception;

    UccDepts selectByNameInCache(String deptNameList)
            throws Exception;

    List<UccDepts> formatDept(List<UccDepts> list);

    UccDepts selectByIdInCache(String deptId) throws Exception;

    void putDeptToCache(UccDepts uccDepts) throws Exception;

    String selectMaxDeptID() throws Exception;

    int insertGroup(Map<String, Object> UccDeptsNewmap) throws Exception;

    List<UccDepts> selectDeptsByUserId(String userId);

    String selectDeptIdAndChildIds(String userId) throws Exception;

    List<UccDepts> selectMesh(UccDepts dept);

    List<UccDepts> selectAllInCache(UccDeptsCriteria search) throws Exception;
}
