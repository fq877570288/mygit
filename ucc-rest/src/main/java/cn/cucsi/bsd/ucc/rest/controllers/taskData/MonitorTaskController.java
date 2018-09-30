package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import cn.cucsi.bsd.ucc.common.beans.MonitorCompleteCriteria;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import cn.cucsi.bsd.ucc.service.CompleteTaskService;
import cn.cucsi.bsd.ucc.service.MonitorTaskService;
import cn.cucsi.bsd.ucc.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"任务监控操作接口"})
@RestController
@RequestMapping(value = "/monitorTaskController")
public class MonitorTaskController {
	
	private static Logger logger = Logger.getLogger(MonitorTaskController.class);

	@Autowired
	private MonitorTaskService monitorTaskService;
	@Autowired
	private CompleteTaskService completeTaskService;
	@Autowired
	private TaskService taskDetailService;

	/***
	 * 任务监控列表查询
	 * add by wangxiaoyu
	 * 2018-09-27
	 */
	@ApiOperation(value="任务监控列表查询", notes="任务监控列表查询")
	@RequestMapping(value = "/monitorTaskList", method= RequestMethod.POST)
	public Map<String,Object> monitorTaskList(@RequestBody TaskDetailSearch taskDetailSearch, HttpSession session){

		Map<String,Object> monitorTaskListMap = new HashMap<String,Object>();
		monitorTaskListMap.put("msg","任务监控列表查询操作失败！");
		monitorTaskListMap.put("code","-1");

		List<TaskDetail> list = null;
		try {
			String deptIdAndChildIds = (String)session.getAttribute("DeptIdAndChildIds");
			/*//此处为临时加的
			if(MyUtils.isBlank(deptIdAndChildIds)){
				deptIdAndChildIds = "'8ac086e665f4746e0165f4a5e9cf0000'";
			}*/
			//taskDetailSearch.setUserId(Auth.getLoginUser(session).getUserId());
			taskDetailSearch.setUserId(taskDetailSearch.getUserId());
			taskDetailSearch.setDeptIdAndChildIds(deptIdAndChildIds);

			list = monitorTaskService.selectMonitorBySearch(taskDetailSearch);

			monitorTaskListMap.put("list", list);
			monitorTaskListMap.put("taskDetailSearch",taskDetailSearch);
			monitorTaskListMap.put("msg","任务监控列表查询操作成功！");
			monitorTaskListMap.put("code","0");
			return monitorTaskListMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return monitorTaskListMap;
		}
	}
	
	/***
	 * 跳转到任务详情页面
	 * add by wangxiaoyu
	 * 2018-09-27
	 */
	@ApiOperation(value="跳转到任务详情页面", notes="跳转到任务详情页面")
	@RequestMapping(value = "/taskDetail{taskDetailId}", method= RequestMethod.GET)
	public Map<String,Object> taskDetail(@PathVariable String taskDetailId) {

		Map<String,Object> taskDetailMap = new HashMap<String,Object>();
		taskDetailMap.put("msg","跳转到任务详情页面失败！");
		taskDetailMap.put("code","-1");
		try {
			TaskDetail taskDetail = taskDetailService.selectByPrimaryKeyForWEB(taskDetailId);
			taskDetailMap.put("taskDetail", taskDetail);
			taskDetailMap.put("msg","任务监控列表查询操作成功！");
			taskDetailMap.put("code","0");
			return taskDetailMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return taskDetailMap;
		}
	}
	
	/***
	 * 跳转到流转明细页面
	 * add by wangxiaoyu
	 * 2018-09-27
	 */
	@ApiOperation(value="跳转到流转明细页面", notes="跳转到流转明细页面")
	@RequestMapping(value = "/taskTransfer{taskDetailId}", method= RequestMethod.GET)
	public Map<String,Object> taskTransfer(@PathVariable String taskDetailId) {

		Map<String,Object> taskTransferMap = new HashMap<String,Object>();
		taskTransferMap.put("msg","跳转到流转明细页面失败！");
		taskTransferMap.put("code","-1");

		List<TaskTransfer> taskTransferList = null;
		try {
			taskTransferList = monitorTaskService.selectTransferByTaskDetailId(taskDetailId);
			taskTransferMap.put("taskTransferList", taskTransferList);
			taskTransferMap.put("msg","跳转到流转明细页面成功！");
			taskTransferMap.put("code","0");
			return taskTransferMap;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return taskTransferMap;
		}
	}

	/***
	 * 批量结束任务（分派者结束，外呼结果是6）
	 * add by wangxiaoyu
	 * 2018-09-27
	 */
	@ApiOperation(value="批量结束任务", notes="批量结束任务分派者结束，外呼结果是6）")
	@RequestMapping(value = "/monitorComplete", method= RequestMethod.POST)
	public Map<String,Object> monitorComplete(@RequestBody MonitorCompleteCriteria monitorCompleteCriteria) {

		String completeMode = monitorCompleteCriteria.getCompleteMode()==null?"":monitorCompleteCriteria.getCompleteMode();
		String batchs = monitorCompleteCriteria.getBatchs()==null?"":monitorCompleteCriteria.getBatchs();
		String importBatch = monitorCompleteCriteria.getImportBatch()==null?"":monitorCompleteCriteria.getImportBatch();
		String userId = monitorCompleteCriteria.getUserId()==null?"":monitorCompleteCriteria.getUserId();

		Map<String,Object> monitorCompleteMap = new HashMap<String,Object>();
		monitorCompleteMap.put("msg","批量结束任务操作失败！");
		monitorCompleteMap.put("code","-1");

		TaskDetailSearch search = new TaskDetailSearch();
		search.setUserId(userId);
		search.setCompleteMode(completeMode);

		if("A".equals(completeMode)){
			search.setTaskDetailIds(batchs);
			search.setTaskDetailIdInList(Arrays.asList(batchs.split(",")));
		}else if("B".equals(completeMode)){
			search.setImportBatch(importBatch);
		}else{
			logger.error("任务批量完成操作错误，既不是按照所选任务又不是按照批次，而是 completeMode = "+ completeMode);
			monitorCompleteMap.put("msg","任务批量完成操作错误，既不是按照所选任务又不是按照批次，而是 completeMode = "+ completeMode);
			return monitorCompleteMap;
		}
		try {
			if(search.getTaskDetailIdInList() !=null && !search.getTaskDetailIdInList().isEmpty()
					|| search.getImportBatch() != null && !search.getImportBatch().isEmpty()){
				completeTaskService.updateTaskStatusByIds(search);
			}
			monitorCompleteMap.put("msg","批量结束任务操作成功！");
			monitorCompleteMap.put("code","0");
			return monitorCompleteMap;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return monitorCompleteMap;
		}
	}
}
