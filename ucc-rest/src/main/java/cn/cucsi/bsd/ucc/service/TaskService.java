package cn.cucsi.bsd.ucc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import com.alibaba.fastjson.JSONObject;

public interface TaskService {

	/***
	 * 根据查询条件获取当前坐席任务外乎列表
	 * add by wangxiaoyu
	 * 2018-08-27
	 */
	Map<String,Object> selectDetailByUserId(OngoingTaskCriteria ongoingTaskCriteria);

	/***
	 * 任务回退
	 * add by wangxiaoyu
	 * 2018-08-30
	 */
	ResultBean_New<TaskTransfer> taskBack(ShowTaskDetailCriteria showTaskDetailCriteria);

	/***
	 * 查询客户任务详情
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	ResultBean_New<JSONObject> showTaskDetailByTaskDetailId(ShowTaskDetailCriteria showTaskDetailCriteria);

	/***
	 * 任务处理结果提交
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	ResultBean_New<String> taskResultSubmit(TaskSubmitCriteria taskSubmitCriteria);

	/***
	 * 根据条件查询呼出记录
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	PageResultBean_New<List<TaskTransfer>> selectCallNotesByCriteria(ShowTaskDetailCriteria showTaskDetailCriteria);

	/***
	 * 个人中心--获取当前坐席“在办”、“待办”、“本月办结”数量
	 * add by wangxiaoyu
	 * 2018-09-01
	 */
	ResultBean_New<JSONObject> showTaskCountsByUserId(OngoingTaskCriteria ongoingTaskCriteria);
	/***
	 *  待办任务总量 （移植自outcall）
	 *  add by zss
	 *  2018-09-26
	 */
	int selectWaitAllCount(String deptIds);
	/***
	 *  今日新增待办任务量 （移植自outcall）
	 *  add by zss
	 *  2018-09-26
	 */
	int selectWaitTodayCount(String deptIds);
	/****
	 * 在办任务总量 （移植自outcall）
	 *  add by zss
	 *  2018-09-26
	 */
	int selectOngoingAllCount(String deptIds);
	/****
	 * 办理过但是没办完的任务数量 （移植自outcall）
	 *  add by zss
	 *  2018-09-26
	 */
	int selectOngoingNoCount(String deptIds);
	/****
	 * 本月内办结任务数量 （移植自outcall）
	 *  add by zss
	 *  2018-09-26
	 */
	int selectCompleteByDaysCount(String deptIds);
	/****
	 * 今日办结任务数量
	 *  add by zss
	 *  2018-09-26
	 */
	int selectCompleteTodayCount(String deptIds);

	/***
	 * 分页查询
	 */
	int selectBySearchCount(TaskDetailSearch search) throws Exception;

	List<TaskDetail> selectBySearch(TaskDetailSearch search) throws Exception;

	TaskDetail selectByPrimaryKeyForWEB(String taskDetailId) throws Exception;

	//视图
	int queryCompleteTask(Date date, String deptIds) throws Exception;

	int queryECall(Date date, String deptIds)  throws Exception;

	int queryACall(Date date, String deptIds) throws Exception;

}
