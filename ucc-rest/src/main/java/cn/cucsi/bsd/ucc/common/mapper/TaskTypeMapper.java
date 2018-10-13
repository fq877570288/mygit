package cn.cucsi.bsd.ucc.common.mapper;

import java.util.List;

import cn.cucsi.bsd.ucc.data.domain.TaskType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskTypeMapper {
    int deleteByPrimaryKey(String taskTypeId);

    int insert(TaskType record);

    int insertSelective(TaskType record);

    TaskType selectByPrimaryKey(String taskTypeId);

    int updateByPrimaryKeySelective(TaskType record);

    int updateByPrimaryKey(TaskType record);
	
	List<TaskType> selectAll(String ignoreNotask) throws Exception;
}