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
 * 任务操作类
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
	 * 在办任务列表查询
	 * Add by bli 2016-07-26
	 * @param model
	 * @param search
	 * @param session
	 * @return
	 */
	/*@UserFlag(1503)
	@RequestMapping(value="/taskData/ongoingTaskList.html",produces = "text/html;charset=UTF-8")
	public String ongoingTaskList(Model model,TaskDetailSearch search, HttpSession session){
		
		List<Customer> list;
		try {
			search.setUserId(Auth.getLoginUser(session).getId());
			list = ongoingTaskService.selectOngoingBySearch(search);
			if(list != null && list.size() > 0){
				for(Customer customer : list){
					customer.setEncryptBusinessCode(AESUtil.encrypt(customer.getBusinessCode(), this.aesPwd));
				}
			}
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		model.addAttribute("userId", Auth.getLoginUser(session).getId());
		model.addAttribute("js_list", new String[] { "page/taskTypeSelect.js","page/ongoingTask.js"});
		model.addAttribute("search", search);//分页查询信息
		
		return "taskData/ongoingTaskList.view";
	}*/
	
	/***
	 * 跳转到任务外呼页面
	 * 
	 * Add by bli 2016-07-25
	 * @param model
	 * @param businessCode
	 * @param session
	 * @return
	 */
	/*//@UserFlag(1503)
	@RequestMapping("/taskData/taskOutCall.html")
	public String taskOutCall(Model model, HttpSession session,String businessCode) {

		businessCode = AESUtil.decrypt(businessCode, this.aesPwd);
		
		Customer customer = null;
		List<TaskDetail> taskDetailList = null;
		List<TaskTransfer> taskTransferList = null;
		String taskDetailIds = "";
		int taskDetailCount = 0;
		String [] customfieldNames = null;
		String customfields = "";
		
		try {
			// 根据businessCode 查询客户基本信息
			customer = customerService.selectByBusinessCode(businessCode);
			// 查询任务列表
			taskDetailList = ongoingTaskService.selectDetailByBusinessCode(businessCode);
			String selectTaskTypeId = "";
			
			if(taskDetailList != null && taskDetailList.size() > 0){
				taskDetailCount = taskDetailList.size();
				selectTaskTypeId = taskDetailList.get(0).getTaskTypeId();

				for(TaskDetail taskd : taskDetailList){
					taskDetailIds += taskd.getTaskDetailId() + ",";
				}
			}
			if(taskDetailIds != null && !"".equals(taskDetailIds)){
				taskDetailIds = taskDetailIds.substring(0, taskDetailIds.lastIndexOf(","));
				// 查询businessCode呼出记录
				taskTransferList = ongoingTaskService.selectCallNotesByDetailIds(taskDetailIds);
			}
			NgtUser user = ngtUserService.selectByPrimaryKey(Auth.getLoginUser(session).getId());
			
			if(user.getCustomfieldNames() != null && !"".equals(user.getCustomfieldNames())){
				customfieldNames = user.getCustomfieldNames().split(",");
				customfields = user.getCustomfieldNames();
			}
			model.addAttribute("userMobile", user.getTel());
			
			// 查询短信模板
			SystemConfig systemConfig = commonService.selectSystemConfigByName("templateSMS");
			if(systemConfig != null && systemConfig.getValue() != null){
				String [] templateSMS = systemConfig.getValue().split("#");
				model.addAttribute("templateSMSBefore", templateSMS[0]);
				if(templateSMS.length > 2){
					model.addAttribute("templateSMSAfter", templateSMS[2]);
				}
			}
			// 脱敏/加密处理
			if(customer != null){
				boolean isDesensitize = Auth.UserFlagCanDo(session, 1507);
				if(customer.getPhone() != null && !"".equals(customer.getPhone())){
					if(isDesensitize){
						customer.setDesensitizePhone(ServiceHelper.desensitizeMobilePhone(customer.getPhone()));
					}else{
						customer.setDesensitizePhone(customer.getPhone());
					}
					customer.setEncryptPhone(AESUtil.encrypt(customer.getPhone(), this.aesPwd));
				}
				if(customer.getPhone2() != null && !"".equals(customer.getPhone2())){
					if(isDesensitize) {
						customer.setDesensitizePhone2(ServiceHelper.desensitizeMobilePhone(customer.getPhone2()));
					}else{
						customer.setDesensitizePhone2(customer.getPhone2());
					}
					customer.setEncryptPhone2(AESUtil.encrypt(customer.getPhone2(), this.aesPwd));
				}
				if(customer.getPhone3() != null && !"".equals(customer.getPhone3())){
					if(isDesensitize) {
						customer.setDesensitizePhone3(ServiceHelper.desensitizeMobilePhone(customer.getPhone3()));
					}else{
						customer.setDesensitizePhone3(customer.getPhone3());
					}
					customer.setEncryptPhone3(AESUtil.encrypt(customer.getPhone3(), this.aesPwd));
				}
				if(customer.getDefultPhone() != null && !"".equals(customer.getDefultPhone())){
					if(isDesensitize) {
						customer.setDesensitizeDefultPhone(ServiceHelper.desensitizeMobilePhone(customer.getDefultPhone()));
					}else{
						customer.setDesensitizeDefultPhone(customer.getDefultPhone());
					}
					customer.setEncryptDefultPhone(AESUtil.encrypt(customer.getDefultPhone(), this.aesPwd));
				}
				if(customer.getChangePhone1() != null && !"".equals(customer.getChangePhone1())){
					if(isDesensitize) {
						customer.setDesensitizeChangePhone1(ServiceHelper.desensitizeMobilePhone(customer.getChangePhone1()));
					}else{
						customer.setDesensitizeChangePhone1(customer.getChangePhone1());
					}
					customer.setEncryptChangePhone1(AESUtil.encrypt(customer.getChangePhone1(), this.aesPwd));
				}
				if(customer.getChangePhone2() != null && !"".equals(customer.getChangePhone2())){
					if(isDesensitize) {
						customer.setDesensitizeChangePhone2(ServiceHelper.desensitizeMobilePhone(customer.getChangePhone2()));
					}else{
						customer.setDesensitizeChangePhone2(customer.getChangePhone2());
					}
					customer.setEncryptChangePhone2(AESUtil.encrypt(customer.getChangePhone2(), this.aesPwd));
				}
				if(customer.getChangePhone3() != null && !"".equals(customer.getChangePhone3())){
					if(isDesensitize) {
						customer.setDesensitizeChangePhone3(ServiceHelper.desensitizeMobilePhone(customer.getChangePhone3()));
					}else{
						customer.setDesensitizeChangePhone3(customer.getChangePhone3());
					}
					customer.setEncryptChangePhone3(AESUtil.encrypt(customer.getChangePhone3(), this.aesPwd));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		model.addAttribute("userId", Auth.getLoginUser(session).getId());
		model.addAttribute("customer", customer);
		model.addAttribute("taskDetailIds", taskDetailIds);
		model.addAttribute("taskDetailList", taskDetailList);
		model.addAttribute("taskTransferList", taskTransferList);
		model.addAttribute("taskDetailCount", taskDetailCount);
		model.addAttribute("customfieldNames", customfieldNames);
		model.addAttribute("customfields", customfields);
		model.addAttribute("js_list", new String[] { "page/taskTypeSelect.js", "page/taskOutCall.js", "chat/taskOutcall.js"});
		return "taskData/taskOutCall.view";
	}*/

	/***
	 * 保存任务明细
	 * Add by bli 2016-07-27
	 * @param callinfo
	 * @param cdrId
	 * @param userId
	 * @return
	 */
	/*@UserFlag(1503)
	@ResponseBody
	@RequestMapping(value="/taskData/ongoing/saveDetail.html",produces = "text/html;charset=UTF-8")
	public String saveDetail(String callinfo, String cdrId, String userId) {
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		String message = "保存失败！";
		try {
			ongoingTaskService.saveDetail(callinfo, userId, cdrId);
			message = "保存完成！";
			json = mapper.writeValueAsString(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return json;
	}*/

	/***
	 * 查询客户黑名单信息
	 */
	/*@UserFlag(1506)
	@ResponseBody
	@RequestMapping(value="/taskData/ongoing/custmIsBlack.html",produces = "text/html;charset=UTF-8")
	public String custmIsBlack(String businessCode) {
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		String message = "N";
		try {
			String blackreason = ongoingTaskService.custmIsBlack(businessCode);
			if(blackreason != null && !"".equals(blackreason)){
				message = "该客户已被加入黑名单，拉黑原因：" + blackreason;
			}
			json = mapper.writeValueAsString(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return json;
	}*/

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
