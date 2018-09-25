package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.common.beans.TaskReceiveCriteria;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import cn.cucsi.bsd.ucc.service.WaitTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"待办任务操作接口"})
@RestController
@RequestMapping(value = "/waitTaskControl")
public class WaitTaskController {
	private static Logger logger = Logger.getLogger(WaitTaskController.class);

	@Autowired
	private WaitTaskService waitTaskService;

	/***
	 * 待办任务列表查询
	 */
	@ApiOperation(value="待办任务列表查询", notes="待办任务列表查询")
	@RequestMapping(value = "/waitTaskList", method= RequestMethod.POST)
	public Map<String,Object> waitTaskList(@RequestBody TaskDetailSearch taskDetailSearch){
		
		List<TaskDetail> list;
		List<String> taskDetailIdList = null;
		Map<String,Object> waitTaskListMap = new HashMap<String,Object>();
		waitTaskListMap.put("msg","操作失败！");
		waitTaskListMap.put("code","-1");
		try {
			//session.setAttribute("taskDetailIdListForWait", null);
			//search.setUserId(Auth.getLoginUser(session).getId());
			list = waitTaskService.selectWaitBySearch(taskDetailSearch);//待办任务列表查询
			taskDetailIdList = waitTaskService.selectWaitTaskDetailIdBySearch(taskDetailSearch);
			//session.setAttribute("taskDetailIdListForWait", taskDetailIdList);
			//model.addAttribute("list", list);
			waitTaskListMap.put("list", list);
			waitTaskListMap.put("taskDetailIdListForWait", taskDetailIdList);
			waitTaskListMap.put("taskDetailSearch",taskDetailSearch);
			waitTaskListMap.put("msg","操作成功！");
			waitTaskListMap.put("code","0");
			return waitTaskListMap;
		} catch (Exception e) {
			//session.setAttribute("taskDetailIdListForWait", null);
			waitTaskListMap.put("taskDetailIdListForWait", null);
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return waitTaskListMap;
		}
	}
	
	/***
	 * 跳转到任务回退页面
	 */
	@ApiOperation(value="跳转到任务回退页面", notes="跳转到任务回退页面")
	@RequestMapping(value = "/taskBack", method= RequestMethod.POST)
	public Map<String,Object> taskBack(@RequestBody TaskDetail taskDetail) {

		Map<String,Object> taskBacktMap = new HashMap<String,Object>();
		taskBacktMap.put("msg","操作失败！");
		taskBacktMap.put("code","-1");

		TaskTransfer taskTransfer = new TaskTransfer();
		try {
			taskTransfer.setTaskDetailId(taskDetail.getTaskDetailId());
			taskBacktMap.put("taskTransfer", taskTransfer);
			taskBacktMap.put("msg","操作成功！");
			taskBacktMap.put("code","0");
			return taskBacktMap;
		} catch (Exception e) {
			e.printStackTrace();
			return taskBacktMap;
		}
	}
	
	/***
	 * 任务回退
	 */
	@ApiOperation(value="任务回退", notes="任务回退")
	@RequestMapping(value = "/taskBack/submit/{userId}", method= RequestMethod.POST)
	public Map<String,Object> taskBackSubmit(@RequestBody TaskTransfer taskTransfer,@PathVariable String userId) {

		Map<String,Object> taskBackSubmitMap = new HashMap<String,Object>();
		taskBackSubmitMap.put("msg","操作失败！");
		taskBackSubmitMap.put("code","-1");
		taskBackSubmitMap.put("infourl", "/waitTaskList");
		try {
			waitTaskService.taskBack(taskTransfer, userId);
			taskBackSubmitMap.put("msg","操作成功！");
			taskBackSubmitMap.put("code","0");
			return taskBackSubmitMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return taskBackSubmitMap;
		}
	}
	
	/***
	 * 任务接收
	 */
	@ApiOperation(value="任务接收", notes="任务接收")
	@RequestMapping(value = "/taskReceive", method= RequestMethod.POST)
	public Map<String,Object> taskReceive(@RequestBody TaskReceiveCriteria taskReceiveCriteria) {

		Map<String,Object> taskReceiveMap = new HashMap<String,Object>();
		taskReceiveMap.put("msg","操作失败！");
		taskReceiveMap.put("code","-1");

		String userId = taskReceiveCriteria.getUserId()==null?"":taskReceiveCriteria.getUserId();
		String taskDetailIds = taskReceiveCriteria.getTaskDetailIds()==null?"":taskReceiveCriteria.getTaskDetailIds();
		List<String> idList = taskReceiveCriteria.getTaskDetailIdListForWait();
		/*ObjectMapper mapper = new ObjectMapper();
		String json = null;
		String message = "操作失败！";*/
		Map<String,Object> doTaskReceiveMap = new HashMap<String,Object>();
		try {
			if(taskDetailIds==null || taskDetailIds.isEmpty()){
				//List<String> idList = (List<String>)session.getAttribute("taskDetailIdListForWait");
				if(!MyUtils.isBlank(idList)){
					StringBuilder sb = new StringBuilder();
					for(String id: idList){
						sb = sb.append(id).append(",");
					}
					String idsStr = sb.toString();
					if(idsStr.endsWith(",")){
						idsStr = idsStr.substring(0, idsStr.length()-1);
					}
					if(!idsStr.isEmpty()){
						doTaskReceiveMap = waitTaskService.taskReceive(userId, idsStr);
						if(doTaskReceiveMap.get("code").equals("-1")){
							taskReceiveMap.put("msg",doTaskReceiveMap.get("msg"));
							return taskReceiveMap;
						}
					}
				}
			}else{
				doTaskReceiveMap = waitTaskService.taskReceive(userId, taskDetailIds);
				if(doTaskReceiveMap.get("code").equals("-1")){
					taskReceiveMap.put("msg",doTaskReceiveMap.get("msg"));
					return taskReceiveMap;
				}
			}
			/*session.setAttribute("taskDetailIdListForWait", null);
			message = "操作成功！";
			json = mapper.writeValueAsString(message);*/
			taskReceiveMap.put("msg","操作成功！");
			taskReceiveMap.put("code","0");
			taskReceiveMap.put("taskDetailIdListForWait", null);
			return taskReceiveMap;
		} catch (Exception e) {
			//session.setAttribute("taskDetailIdListForWait", null);
			taskReceiveMap.put("taskDetailIdListForWait", null);
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return taskReceiveMap;
		}
	}
}
