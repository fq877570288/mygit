package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UccDeptsMapper {

    int clearUsersDeptsByDeptId(String deptId);
    
    int clearUsersDeptsByUserId(String userId);
    
    int insertUserDepts(Map<String, Object> map);
    
    int deleteByPrimaryKey(String deptId);

    int insert(UccDepts record);

    UccDepts selectByPrimaryKey(String deptId);
    
    List<UccDepts> selectAll();
    
    List<UccDepts> selectByUserId(String userId);

    List<UccDepts> selectDidsByUserId(@Param("userId")String userId, @Param("domainId")String domainId);
    
    int updateByPrimaryKey(UccDepts record);
    
    int updateSimpleByPrimaryKey(UccDepts record);
    
    int selectSubCountsByPrimaryKey(String deptId);
    
    String selectMaxDeptID();

	int insertGroup(Map<String, Object> UccDeptsNewmap);
	
	/***
	 * 条件查询
	 */
	List<UccDepts> selectBySearch(UccDeptsCriteria search);

	List<UccDepts> selectDeptsByUserId(String userId);

	List<UccDepts> selectDeptIdAndChildIds(UccDeptsCriteria search);

	List<UccDepts> selectMesh(UccDepts dept);

	List<UccDepts> selectChildren(String deptIdList);
	
}