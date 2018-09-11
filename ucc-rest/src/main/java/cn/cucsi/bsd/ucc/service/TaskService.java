package cn.cucsi.bsd.ucc.service;

import java.util.List;

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
	PageResultBean_New<List<TaskDetail>> selectDetailByUserId(OngoingTaskCriteria ongoingTaskCriteria);

	/***
	 * 任务回退
	 * add by wangxiaoyu
	 * 2018-08-30
	 */
	ResultBean_New<TaskTransfer> taskBack(TaskBackCriteria taskBackCriteria);

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
}
