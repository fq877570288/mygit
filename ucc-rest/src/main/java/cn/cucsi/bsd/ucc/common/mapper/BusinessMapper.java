package cn.cucsi.bsd.ucc.common.mapper;

import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.TaskTypeCriteria;
import cn.cucsi.bsd.ucc.data.domain.TaskType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Mapper
@Repository
public interface BusinessMapper {
	
	int insert(TaskType record);
	
	int deleteByPrimaryKey(String id);

	TaskType selectByPrimaryKey(String id);

	List<TaskType> selectAll(TaskTypeCriteria search);

	List<TaskType> selectAllBySearch(TaskTypeCriteria search);

	int selectBySearchCount(TaskTypeCriteria search);
	
	int deleteByGroup(Map<String, Object> map);
}