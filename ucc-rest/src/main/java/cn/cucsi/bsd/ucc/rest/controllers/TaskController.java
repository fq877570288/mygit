package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import cn.cucsi.bsd.ucc.service.PbxCdrsService;
import cn.cucsi.bsd.ucc.service.TaskService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 任务操作类(目前限于移动端用)
 * add by wangxiaoyu
 * 2018-08-30
 */
@Api(tags={"任务操作接口"})
@RestController
@RequestMapping(value = "/taskControl")
public class TaskController {
	
	private static Logger logger = Logger.getLogger(TaskController.class);
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private PbxCdrsService pbxCdrsService;

	/***
	 * 根据查询条件获取当前坐席任务外乎列表
     * add by wangxiaoyu
     * 2018-08-27
	 */
	@ApiOperation(value="根据查询条件获取当前坐席任务外呼列表", notes="根据查询条件获取当前坐席任务外呼列表")
	@RequestMapping(value = "/findByUserId", method= RequestMethod.POST)
	public Map<String,Object> findTasksByUserId(@RequestBody OngoingTaskCriteria ongoingTaskCriteria){

		Map<String,Object> taskDetailsMap = new HashMap<String,Object>();
		try {
			taskDetailsMap = taskService.selectDetailByUserId(ongoingTaskCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("根据查询条件获取当前坐席任务外呼列表发生异常！");
		}
		return taskDetailsMap;
	}

	/***
	 * 撤回任务
	 * add by wangxiaoyu
	 * 2018-08-30
	 */
	@ApiOperation(value="撤回任务", notes="撤回任务")
	@RequestMapping(value = "/taskBackByCriteria", method= RequestMethod.POST)
	public ResultBean_New<TaskTransfer> taskBackByCriteria(@RequestBody ShowTaskDetailCriteria showTaskDetailCriteria){
		return taskService.taskBack(showTaskDetailCriteria);
	}

	/***
	 * 查询客户任务详情
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	@ApiOperation(value="查询客户任务详情", notes="查询客户任务详情")
	@RequestMapping(value = "/showTaskDetail", method= RequestMethod.POST)
	public ResultBean_New<JSONObject> showTaskDetailByTaskDetailId(@RequestBody ShowTaskDetailCriteria showTaskDetailCriteria){
		return taskService.showTaskDetailByTaskDetailId(showTaskDetailCriteria);
	}

	/***
	 * 任务处理结果提交
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	@ApiOperation(value="任务处理结果提交", notes="任务处理结果提交")
	@RequestMapping(value = "/taskResultSubmit", method= RequestMethod.POST)
	public ResultBean_New<String> taskResultSubmit(@RequestBody TaskSubmitCriteria taskSubmitCriteria){
		return taskService.taskResultSubmit(taskSubmitCriteria);
	}

	/***
	 * 根据条件查询呼出记录
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	@ApiOperation(value="根据条件查询呼出记录", notes="根据条件查询呼出记录")
	@RequestMapping(value = "/selectCallNotesByCriteria", method= RequestMethod.POST)
	public PageResultBean_New<List<TaskTransfer>> selectCallNotesByCriteria(@RequestBody ShowTaskDetailCriteria showTaskDetailCriteria){

		PageResultBean_New<List<TaskTransfer>> pageResultBean_new = null;
		try {
			pageResultBean_new = taskService.selectCallNotesByCriteria(showTaskDetailCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("根据业务编码查询呼出记录发生异常！");
		}
		return pageResultBean_new;
	}

	/***
	 * 个人中心--获取当前坐席“在办”、“待办”、“本月办结”数量
	 * add by wangxiaoyu
	 * 2018-09-01
	 */
	@ApiOperation(value="获取当前坐席“在办”、“待办”、“本月办结”数量", notes="根据查询条件获取当前坐席“在办”、“待办”、“本月办结”数量")
	@RequestMapping(value = "/showTaskCountsByUserId", method= RequestMethod.POST)
	public ResultBean_New<JSONObject> showTaskCountsByUserId(@RequestBody OngoingTaskCriteria ongoingTaskCriteria){

		return taskService.showTaskCountsByUserId(ongoingTaskCriteria);
	}

	/***
	 * 根据条件查询通话记录列表（移动端查询通话记录）
	 * add by wangxiaoyu
	 * 2018-09-04
	 */
	@ApiOperation(value="根据条件查询通话记录列表", notes="根据条件查询通话记录列表")
	@RequestMapping(value = "/showListByTel", method= RequestMethod.POST)
	public PageResultBean_New<List<PbxCdrs>> showListByTel(@RequestBody PbxCdrsForAPPCriteria pbxCdrsForAPPCriteria){

		PageResultBean_New<List<PbxCdrs>> pageResultBean_new = null;
		try {
			pageResultBean_new = pbxCdrsService.showListByTel(pbxCdrsForAPPCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("根据条件查询通话记录列表发生异常！");
		}
		return pageResultBean_new;
	}

}
