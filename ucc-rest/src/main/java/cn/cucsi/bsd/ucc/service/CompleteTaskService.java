package cn.cucsi.bsd.ucc.service;

import java.util.List;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;

public interface CompleteTaskService {

	/***
	 * 办结任务列表查询
	 */
	List<TaskDetail> selectCompleteBySearch(TaskDetailSearch search) throws Exception;

	/****
	 * 超过任务截止日期的任务状态更改为已办结
	 */
	int updateTaskStatusByEndDate() throws Exception;

	int updateTaskStatusByIds(TaskDetailSearch search) throws Exception;

}
