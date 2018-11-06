package cn.cucsi.bsd.ucc.service;

import java.util.List;
import cn.cucsi.bsd.ucc.common.beans.TaskTypeCriteria;
import cn.cucsi.bsd.ucc.data.domain.TaskType;

/**
 * 
 */
public interface BusinessService {
	
	int insert(TaskType record) throws Exception;
	
	int deleteByPrimaryKey(String id) throws Exception;

	TaskType selectByPrimaryKey(String id) throws Exception;

	List<TaskType> selectAll(TaskTypeCriteria search) throws Exception;

	int selectBySearchCount(TaskTypeCriteria search) throws Exception;
	
	int deleteByGroup(String[] ids) throws Exception;

	void putTaskTypeToCache(TaskType taskType) throws Exception;

	TaskType selectByNameInCache(String taskTypeName) throws Exception;

	TaskType selectByNameAndDomainIdInCache(String taskTypeName,String domainId) throws Exception;

	TaskType selectByIdInCache(String taskTypeId) throws Exception;

	String getJson() throws Exception;
}
