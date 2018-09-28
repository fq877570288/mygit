package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import java.util.List;

public interface MonitorTaskService {
	
	/***
	 * 任务监控列表查询
	 */
	List<TaskDetail> selectMonitorBySearch(TaskDetailSearch search) throws Exception;

	/***
	 * 主键查询流转明细
	 */
	List<TaskTransfer> selectTransferByTaskDetailId(String taskDetailId)throws Exception;

	List<String> selectTaskDetailIdByMonitorSearch(TaskDetailSearch search) throws Exception;
}
