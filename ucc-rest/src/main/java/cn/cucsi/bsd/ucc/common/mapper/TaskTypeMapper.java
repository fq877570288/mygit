package cn.cucsi.bsd.ucc.common.mapper;

import java.util.List;

import cn.cucsi.bsd.ucc.common.beans.TaskTypeForMybatisCriteria;
import cn.cucsi.bsd.ucc.data.domain.TaskType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
	
	List<TaskType> selectAll(@Param("ignoreNotask")String ignoreNotask,@Param("domainId")String domainId) throws Exception;

	List<TaskType> selectByPage(TaskTypeForMybatisCriteria taskTypeForMybatisCriteria);

    int selectByName(String name);

    List<TaskType> selectAllTaskTypeByToDoTask(String domainId);
}