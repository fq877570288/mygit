package cn.cucsi.bsd.ucc.service;

import java.util.List;
import java.util.Map;

import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;

public interface AllocationTaskService {
	
	/***
	 * 默认分派
	 */
	Map<String,Object> allocationTask(String userId, String alloc, String barchs, String endDate,String deptIds,List<String> dispatchDept) throws Exception;

    /***
	 * 修改任务组织结构
	 */
	void editTaskDept(String meshID, String areaID, String developmentID, String taskDetailIds) throws Exception;

	/***
	 * 分派任务列表查询
	 */
	List<TaskDetail> selectAllocationBySearch(TaskDetailSearch search) throws Exception;

	/**
	 * 查询所有要分派的TaskDetailId，返回给controller
	 */
	List<String> selectTaskDetailIdBySearch(TaskDetailSearch search) throws Exception;
}
