package cn.cucsi.bsd.ucc.service;

import java.util.List;
import java.util.Map;

import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;

public interface WaitTaskService {

	/***
	 * 待办任务列表查询
	 */
	List<TaskDetail> selectWaitBySearch(TaskDetailSearch search) throws Exception;

	/***
	 * 待办任务列表查询
	 */
	List<String> selectWaitTaskDetailIdBySearch(TaskDetailSearch search) throws Exception;
	
	/***
	 * 任务回退
	 */
	void taskBack(TaskTransfer taskTransfer, String userId) throws Exception;

	/***
	 * 任务接收
	 */
	Map<String,Object> taskReceive(String userId, String taskDetailIds) throws Exception;
}
