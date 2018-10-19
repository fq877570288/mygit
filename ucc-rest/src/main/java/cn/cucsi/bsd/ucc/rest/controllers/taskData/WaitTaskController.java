package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.common.beans.TaskReceiveCriteria;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccUserService;
import cn.cucsi.bsd.ucc.service.WaitTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api(tags={"待办任务操作接口"})
@RestController
@RequestMapping(value = "/waitTaskControl")
public class WaitTaskController {
	private static Logger logger = Logger.getLogger(WaitTaskController.class);

	@Autowired
	private WaitTaskService waitTaskService;
	@Autowired
	private UccUserService uccUserService;

	/***
	 * 待办任务列表查询
	 */
	@ApiOperation(value="待办任务列表查询", notes="待办任务列表查询")
	@RequestMapping(value = "/waitTaskList", method= RequestMethod.POST)
	public Map<String,Object> waitTaskList(@RequestBody TaskDetailSearch taskDetailSearch,HttpSession session){
		
		List<TaskDetail> list;
		List<String> taskDetailIdList = null;
		Map<String,Object> waitTaskListMap = new HashMap<String,Object>();
		waitTaskListMap.put("msg","操作失败！");
		waitTaskListMap.put("code","-1");
		try {
			session.setAttribute("taskDetailIdListForWait", null);
			//search.setUserId(Auth.getLoginUser(session).getId());
			String deptIdAndChildId = (String)session.getAttribute("DeptIdAndChildIds");
			System.out.println("待办任务列表查询 从session获取到的DeptIdAndChildIds:::" + deptIdAndChildId);
			taskDetailSearch.setRoperateDeptId(deptIdAndChildId);
			list = waitTaskService.selectWaitBySearch(taskDetailSearch);//待办任务列表查询
			taskDetailIdList = waitTaskService.selectWaitTaskDetailIdBySearch(taskDetailSearch);
			session.setAttribute("taskDetailIdListForWait", taskDetailIdList);
			//model.addAttribute("list", list);
			waitTaskListMap.put("list", list);
			//waitTaskListMap.put("taskDetailIdListForWait", taskDetailIdList);
			waitTaskListMap.put("taskDetailSearch",taskDetailSearch);
			waitTaskListMap.put("msg","操作成功！");
			waitTaskListMap.put("code","0");
			return waitTaskListMap;
		} catch (Exception e) {
			session.setAttribute("taskDetailIdListForWait", null);
			//waitTaskListMap.put("taskDetailIdListForWait", null);
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
            Map<String,Object> taskBackMap = waitTaskService.taskBack(taskTransfer, userId);
            if(taskBackMap.get("code").equals("-1")){
                taskBackSubmitMap.put("msg",taskBackMap.get("msg"));
                return taskBackSubmitMap;
            }
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
	public Map<String,Object> taskReceive(@RequestBody TaskReceiveCriteria taskReceiveCriteria,HttpSession session) {

		Map<String,Object> taskReceiveMap = new HashMap<String,Object>();
		taskReceiveMap.put("msg","操作失败！");
		taskReceiveMap.put("code","-1");

		String userId = taskReceiveCriteria.getUserId()==null?"":taskReceiveCriteria.getUserId();
		String domainId = taskReceiveCriteria.getDomainId()==null?"":taskReceiveCriteria.getDomainId();
		List<String> userIdList = taskReceiveCriteria.getUserIdList();
		List<String> taskDetailIdsList = taskReceiveCriteria.getTaskDetailIdsList();

		Map<String,Object> doTaskReceiveMap = new HashMap<String,Object>();
		try {
			//如果前端不传taskDetailIdsList(即不勾选)，那么就从session拿
			if(MyUtils.isBlank(taskDetailIdsList)){
				taskDetailIdsList = (List<String>)session.getAttribute("taskDetailIdListForWait");
			}
			if(MyUtils.isBlank(taskDetailIdsList)){
				taskReceiveMap.put("msg","操作失败！taskDetailIdListForWait为空！");
				System.out.println("任务接收时 前端或从session获取taskDetailIdListForWait为空");
				return taskReceiveMap;
			}
			if(!MyUtils.isBlank(userIdList)){
				//如果所传用户ID列表不为空，则把任务平均分配给这些用户
				List<List<String>> splitedTaskList = MyUtils.averageAssign(taskDetailIdsList,userIdList.size());
				List<String> splitedTaskListbatch = null;

				for (int i = 0; i < splitedTaskList.size(); i++) {
					splitedTaskListbatch = splitedTaskList.get(i);
					if(!MyUtils.isBlank(splitedTaskListbatch)){
						StringBuilder stringBuilder = new StringBuilder();
						for(String taskId: splitedTaskListbatch){
							stringBuilder = stringBuilder.append(taskId).append(",");
						}
						String taskIdsStr = stringBuilder.toString();
						if(taskIdsStr.endsWith(",")){
							taskIdsStr = taskIdsStr.substring(0, taskIdsStr.length()-1);
						}
						for (int j = 0; j < userIdList.size(); j++) {
							userId = userIdList.get(j);
							doTaskReceiveMap = waitTaskService.taskReceive(userId, taskIdsStr,domainId);
							if(doTaskReceiveMap.get("code").equals("-1")){
								taskReceiveMap.put("msg",doTaskReceiveMap.get("msg"));
								return taskReceiveMap;
							}
						}
					}
				}
			}else{
				//如果所传用户ID列表为空，则先查询该用户下同部门的所有用户ID，然后再平均分配任务
				List<UccUsers> userIdList2 = uccUserService.selectSameDeptUserIdByUserId(userId);
				if(!MyUtils.isBlank(userIdList2)){
					//把任务平均分配给这些用户
					List<List<String>> splitedTaskList2 = MyUtils.averageAssign(taskDetailIdsList,userIdList2.size());
					List<String> splitedTaskListbatch2 = null;

					if(!MyUtils.isBlank(splitedTaskList2)){
						for (int i = 0; i < splitedTaskList2.size(); i++) {
							splitedTaskListbatch2 = splitedTaskList2.get(i);
							if(!MyUtils.isBlank(splitedTaskListbatch2)){
								StringBuilder stringBuilder2 = new StringBuilder();
								for(String taskId: splitedTaskListbatch2){
									stringBuilder2 = stringBuilder2.append(taskId).append(",");
								}
								String taskIdsStr = stringBuilder2.toString();
								if(taskIdsStr.endsWith(",")){
									taskIdsStr = taskIdsStr.substring(0, taskIdsStr.length()-1);
								}
								for (int j = 0; j < userIdList2.size(); j++) {
									System.out.println("userIdList2:::" + userIdList2.get(j).getUserId());
									userId = userIdList2.get(j).getUserId();
									doTaskReceiveMap = waitTaskService.taskReceive(userId,taskIdsStr,domainId);
									System.out.println("doTaskReceiveMap:::" + doTaskReceiveMap.get("code"));
									System.out.println("doTaskReceiveMap:::" + doTaskReceiveMap.get("msg"));

									if(doTaskReceiveMap.get("code").equals("-1")){
										taskReceiveMap.put("msg",doTaskReceiveMap.get("msg"));
										return taskReceiveMap;
									}
								}
							}
						}
					}
				}
			}
			session.setAttribute("taskDetailIdListForWait", null);
			taskReceiveMap.put("msg","操作成功！");
			taskReceiveMap.put("code","0");
			return taskReceiveMap;
		} catch (Exception e) {
			session.setAttribute("taskDetailIdListForWait", null);
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return taskReceiveMap;
		}
	}
}
