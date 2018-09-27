package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.untils.AESUtil;
import cn.cucsi.bsd.ucc.common.untils.Auth;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.ServiceHelper;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.OngoingTaskService;
import cn.cucsi.bsd.ucc.service.UccCustomersService;
import cn.cucsi.bsd.ucc.service.UccUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"在办任务操作接口"})
@RestController
@RequestMapping(value = "/ongoingTaskController")
public class OngoingTaskController {
	
	private static Logger logger = Logger.getLogger(OngoingTaskController.class);
	
	@Autowired
	private OngoingTaskService ongoingTaskService;
	@Autowired
	private UccCustomersService uccCustomersService;
	@Autowired
	private UccUserService uccUserService;
	/*@Autowired
	private CommonService commonService;*/
	
	/*@Value("${aes.pwd}")
    public String aesPwd;*/
	public String aesPwd = "century_eggs-012";//临时写死
	
	/***
	 * 在办任务列表查询
	 */
	@ApiOperation(value="在办任务列表查询", notes="在办任务列表查询")
	@RequestMapping(value = "/ongoingTaskList", method= RequestMethod.POST)
	public Map<String,Object> ongoingTaskList(@RequestBody TaskDetailSearch taskDetailSearch){

		Map<String,Object> ongoingTaskListMap = new HashMap<String,Object>();
		ongoingTaskListMap.put("msg","操作失败！");
		ongoingTaskListMap.put("code","-1");

		List<UccCustomers> list = null;
		try {
			//search.setUserId(ShellProperties.Auth.getLoginUser(session).getId());
			list = ongoingTaskService.selectOngoingBySearch(taskDetailSearch);
			if(list != null && list.size() > 0){
				for(UccCustomers uccCustomers : list){
					uccCustomers.setEncryptBusinessCode(AESUtil.encrypt(uccCustomers.getBusinesscode(), this.aesPwd));
				}
			}
			//model.addAttribute("list", list);
			ongoingTaskListMap.put("list", list);
			ongoingTaskListMap.put("taskDetailSearch",taskDetailSearch);
			ongoingTaskListMap.put("msg","操作成功！");
			ongoingTaskListMap.put("code","0");

			return ongoingTaskListMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return ongoingTaskListMap;
		}
	}

	/***
	 * 跳转到任务外呼页面
	 */
	@ApiOperation(value="跳转到任务外呼页面", notes="跳转到任务外呼页面")
	@RequestMapping(value = "/taskOutCall", method= RequestMethod.POST)
	public Map<String,Object> taskOutCall(@RequestBody TaskOutCallCriteria taskOutCallCriteria,HttpSession session) {

		Map<String,Object> taskOutCallMap = new HashMap<String,Object>();
		taskOutCallMap.put("msg","操作失败！");
		taskOutCallMap.put("code","-1");

		String businessCode = taskOutCallCriteria.getBusinessCode()==null?"": taskOutCallCriteria.getBusinessCode();
		String domainId = taskOutCallCriteria.getDomainId()==null?"": taskOutCallCriteria.getDomainId();
		String userId = taskOutCallCriteria.getUserId()==null?"": taskOutCallCriteria.getUserId();

		UccCustomers uccCustomers = null;
		List<TaskDetail> taskDetailList = null;
		List<TaskTransfer> taskTransferList = null;
		String taskDetailIds = "";
		int taskDetailCount = 0;
		String [] customfieldNames = null;
		String customfields = "";
		try {
			businessCode = AESUtil.decrypt(businessCode, this.aesPwd);
			// 根据businessCode 查询客户基本信息
			uccCustomers = uccCustomersService.selectByBusinessCode(businessCode,domainId);
			// 查询任务列表
			taskDetailList = ongoingTaskService.selectDetailByBusinessCode(businessCode,domainId);
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
			UccUsers user = uccUserService.selectByPrimaryKey(userId);
			
			if(user.getCustomfieldnames() != null && !"".equals(user.getCustomfieldnames())){
				customfieldNames = user.getCustomfieldnames().split(",");
				customfields = user.getCustomfieldnames();
			}
			//model.addAttribute("userMobile", user.getTel());
			taskOutCallMap.put("userMobile", user.getTel());
			
			// 查询短信模板
			/*SystemConfig systemConfig = commonService.selectSystemConfigByName("templateSMS");
			if(systemConfig != null && systemConfig.getValue() != null){
				String [] templateSMS = systemConfig.getValue().split("#");
				model.addAttribute("templateSMSBefore", templateSMS[0]);
				if(templateSMS.length > 2){
					model.addAttribute("templateSMSAfter", templateSMS[2]);
				}
			}*/
			// 脱敏/加密处理
			if(uccCustomers != null){
				boolean isDesensitize = Auth.UserFlagCanDo(session, 1507);
				if(uccCustomers.getPhone() != null && !"".equals(uccCustomers.getPhone())){
					if(isDesensitize){
						uccCustomers.setDesensitizePhone(ServiceHelper.desensitizeMobilePhone(uccCustomers.getPhone()));
					}else{
						uccCustomers.setDesensitizePhone(uccCustomers.getPhone());
					}

					uccCustomers.setEncryptPhone(AESUtil.encrypt(uccCustomers.getPhone(), this.aesPwd));
				}
				if(uccCustomers.getPhone2() != null && !"".equals(uccCustomers.getPhone2())){
					if(isDesensitize) {
						uccCustomers.setDesensitizePhone2(ServiceHelper.desensitizeMobilePhone(uccCustomers.getPhone2()));
					}else{
						uccCustomers.setDesensitizePhone2(uccCustomers.getPhone2());
					}
					uccCustomers.setEncryptPhone2(AESUtil.encrypt(uccCustomers.getPhone2(), this.aesPwd));
				}
				if(uccCustomers.getPhone3() != null && !"".equals(uccCustomers.getPhone3())){
					if(isDesensitize) {
						uccCustomers.setDesensitizePhone3(ServiceHelper.desensitizeMobilePhone(uccCustomers.getPhone3()));
					}else{
						uccCustomers.setDesensitizePhone3(uccCustomers.getPhone3());
					}
					uccCustomers.setEncryptPhone3(AESUtil.encrypt(uccCustomers.getPhone3(), this.aesPwd));
				}
				if(uccCustomers.getDefultPhone() != null && !"".equals(uccCustomers.getDefultPhone())){
					if(isDesensitize) {
						uccCustomers.setDesensitizeDefultPhone(ServiceHelper.desensitizeMobilePhone(uccCustomers.getDefultPhone()));
					}else{
						uccCustomers.setDesensitizeDefultPhone(uccCustomers.getDefultPhone());
					}
					uccCustomers.setEncryptDefultPhone(AESUtil.encrypt(uccCustomers.getDefultPhone(), this.aesPwd));
				}
				if(uccCustomers.getChangephone1() != null && !"".equals(uccCustomers.getChangephone1())){
					if(isDesensitize) {
						uccCustomers.setDesensitizeChangePhone1(ServiceHelper.desensitizeMobilePhone(uccCustomers.getChangephone1()));
					}else{
						uccCustomers.setDesensitizeChangePhone1(uccCustomers.getChangephone1());
					}
					uccCustomers.setEncryptChangePhone1(AESUtil.encrypt(uccCustomers.getChangephone1(), this.aesPwd));
				}
				if(uccCustomers.getChangephone2() != null && !"".equals(uccCustomers.getChangephone2())){
					if(isDesensitize) {
						uccCustomers.setDesensitizeChangePhone2(ServiceHelper.desensitizeMobilePhone(uccCustomers.getChangephone2()));
					}else{
						uccCustomers.setDesensitizeChangePhone2(uccCustomers.getChangephone2());
					}
					uccCustomers.setEncryptChangePhone2(AESUtil.encrypt(uccCustomers.getChangephone2(), this.aesPwd));
				}
				if(uccCustomers.getChangephone3() != null && !"".equals(uccCustomers.getChangephone3())){
					if(isDesensitize) {
						uccCustomers.setDesensitizeChangePhone3(ServiceHelper.desensitizeMobilePhone(uccCustomers.getChangephone3()));
					}else{
						uccCustomers.setDesensitizeChangePhone3(uccCustomers.getChangephone3());
					}
					uccCustomers.setEncryptChangePhone3(AESUtil.encrypt(uccCustomers.getChangephone3(), this.aesPwd));
				}
			}
			taskOutCallMap.put("userMobile", user.getTel());
			taskOutCallMap.put("uccCustomers", uccCustomers);
			taskOutCallMap.put("taskDetailIds", taskDetailIds);
			taskOutCallMap.put("taskDetailList", taskDetailList);
			taskOutCallMap.put("taskTransferList", taskTransferList);
			taskOutCallMap.put("taskDetailCount", taskDetailCount);
			taskOutCallMap.put("customfieldNames", customfieldNames);
			taskOutCallMap.put("customfields", customfields);
			taskOutCallMap.put("msg","操作成功！");
			taskOutCallMap.put("code","0");
			return taskOutCallMap;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return taskOutCallMap;
		}
	}

	/***
	 * 保存任务明细
	 */
	@ApiOperation(value="保存任务明细", notes="保存任务明细")
	@RequestMapping(value = "/ongoing/saveDetail", method= RequestMethod.POST)
	public Map<String,Object> saveDetail(@RequestBody SaveDetailCriteria saveDetailCriteria) {

		String callinfo = saveDetailCriteria.getCallinfo()==null?"":saveDetailCriteria.getCallinfo();
		String cdrId = saveDetailCriteria.getCdrId()==null?"":saveDetailCriteria.getCdrId();
		String userId = saveDetailCriteria.getUserId()==null?"":saveDetailCriteria.getUserId();
		String domainId = saveDetailCriteria.getDomainId()==null?"":saveDetailCriteria.getDomainId();

		Map<String,Object> saveDetailMap = new HashMap<String,Object>();
		saveDetailMap.put("msg","保存失败！");
		saveDetailMap.put("code","-1");
		try {
            Map<String,Object> doSaveDetailMap = ongoingTaskService.saveDetail(callinfo, userId, cdrId,domainId);
            if(doSaveDetailMap.get("code").equals("-1")){
                saveDetailMap.put("msg",doSaveDetailMap.get("msg"));
                return saveDetailMap;
            }
            saveDetailMap.put("msg","保存成功！");
			saveDetailMap.put("code","0");
			return saveDetailMap;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return saveDetailMap;
		}
	}

	/**
	 * 保存并继续
	 */
    @ApiOperation(value="保存并继续", notes="保存并继续")
    @RequestMapping(value = "/ongoing/saveAndGoonDetail", method= RequestMethod.POST)
	public Map<String,Object> saveAndGoonDetail(@RequestBody SaveAndGoonDetailCriteria saveAndGoonDetailCriteria, HttpSession session) {

        Map<String,Object> saveDetailMap = new HashMap<String,Object>();
        saveDetailMap.put("msg","保存并继续操作失败！");
        saveDetailMap.put("code","-1");

        String callinfo = saveAndGoonDetailCriteria.getCallinfo()==null?"":saveAndGoonDetailCriteria.getCallinfo();
        String userId = saveAndGoonDetailCriteria.getUserId()==null?"":saveAndGoonDetailCriteria.getUserId();
        String taskTypeId = saveAndGoonDetailCriteria.getTaskTypeId()==null?"":saveAndGoonDetailCriteria.getTaskTypeId();
        String cdrId = saveAndGoonDetailCriteria.getCdrId()==null?"":saveAndGoonDetailCriteria.getCdrId();
        String businessCode = saveAndGoonDetailCriteria.getBusinessCode()==null?"":saveAndGoonDetailCriteria.getBusinessCode();
        String domainId = saveAndGoonDetailCriteria.getDomainId()==null?"":saveAndGoonDetailCriteria.getDomainId();

		String businesscode = "";
		session.setAttribute("selectTaskTypeId", taskTypeId);
		try {
			businesscode = ongoingTaskService.saveAndGoonDetail(callinfo, userId, taskTypeId, cdrId, businessCode,domainId);
			if(businesscode != null && !"".equals(businesscode)){
				businesscode = AESUtil.encrypt(businesscode, this.aesPwd);
                saveDetailMap.put("businesscode",businesscode);
			}
            saveDetailMap.put("msg","操作成功！");
            saveDetailMap.put("code","0");

            return saveDetailMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
            return saveDetailMap;
		}
	}
	
	/***
	 * 保存变更号码
	 */
	@ApiOperation(value="保存变更号码", notes="保存变更号码")
	@RequestMapping(value = "/ongoing/saveChangePhone", method= RequestMethod.POST)
	public Map<String,Object> saveChangePhone(@RequestBody SaveChangePhoneCriteria saveChangePhoneCriteria) {

		Map<String,Object> saveChangePhoneMap = new HashMap<String,Object>();
		saveChangePhoneMap.put("msg","保存变更号码操作失败！");
		saveChangePhoneMap.put("code","-1");

		String domainId = saveChangePhoneCriteria.getDomainId()==null?"":saveChangePhoneCriteria.getDomainId(); //租户ID
		String businessCode = saveChangePhoneCriteria.getBusinessCode()==null?"":saveChangePhoneCriteria.getBusinessCode(); //业务编码
		String changePhone = saveChangePhoneCriteria.getChangePhone()==null?"":saveChangePhoneCriteria.getChangePhone();
		String fPhone = saveChangePhoneCriteria.getfPhone()==null?"":saveChangePhoneCriteria.getfPhone();

		if(MyUtils.isBlank(domainId)||MyUtils.isBlank(businessCode)||MyUtils.isBlank(changePhone)||MyUtils.isBlank(fPhone)){
			saveChangePhoneMap.put("msg","入参存在为空字段！");
			return saveChangePhoneMap;
		}
		try {
			Map<String,Object> doSaveChangePhoneMap = ongoingTaskService.saveChangePhone(changePhone, fPhone, businessCode, domainId);
			if(doSaveChangePhoneMap.get("code").equals("-1")){
				saveChangePhoneMap.put("msg",doSaveChangePhoneMap.get("msg"));
				return saveChangePhoneMap;
			}
			saveChangePhoneMap.put("msg","操作成功！");
			saveChangePhoneMap.put("code","0");

			return saveChangePhoneMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return saveChangePhoneMap;
		}
	}

	/***
	 * 保存用户自定义显示字段
	 */
	@ApiOperation(value="保存用户自定义显示字段", notes="保存用户自定义显示字段")
	@RequestMapping(value = "/ongoing/cusfsSave", method= RequestMethod.POST)
	public Map<String,Object> cusfsSave(@RequestBody CusfsSaveCriteria cusfsSaveCriteria) {

		Map<String,Object> saveChangePhoneMap = new HashMap<String,Object>();
		saveChangePhoneMap.put("msg","保存用户自定义显示字段操作失败！");
		saveChangePhoneMap.put("code","-1");

		String userId = cusfsSaveCriteria.getUserId()==null?"":cusfsSaveCriteria.getUserId();
		String customfieldNames = cusfsSaveCriteria.getCustomfieldNames()==null?"":cusfsSaveCriteria.getCustomfieldNames();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", new Long(userId));
			map.put("customfieldNames", customfieldNames);
			ongoingTaskService.cusfsSave(map);

			saveChangePhoneMap.put("msg","保存用户自定义显示字段操作成功！");
			saveChangePhoneMap.put("code","0");
			return saveChangePhoneMap;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return saveChangePhoneMap;
		}
	}
	
	/***
	 * 查询客户黑名单信息
	 */
	@ApiOperation(value="查询客户黑名单信息", notes="查询客户黑名单信息")
	@RequestMapping(value = "/ongoing/custmIsBlack", method= RequestMethod.POST)
	public Map<String,Object> custmIsBlack(@RequestBody CustmIsBlackCriteria custmIsBlackCriteria) {

		String businessCode = custmIsBlackCriteria.getBusinessCode()==null?"":custmIsBlackCriteria.getBusinessCode();
		String domainId = custmIsBlackCriteria.getDomainId()==null?"":custmIsBlackCriteria.getDomainId();
		Map<String,Object> saveChangePhoneMap = new HashMap<String,Object>();
		saveChangePhoneMap.put("msg","查询客户黑名单信息操作失败！");
		saveChangePhoneMap.put("code","-1");

		String message = "N";
		try {
			String blackreason = ongoingTaskService.custmIsBlack(businessCode,domainId);
			if(blackreason != null && !"".equals(blackreason)){
				message = "该客户已被加入黑名单，拉黑原因：" + blackreason;
			}
			saveChangePhoneMap.put("msg",message);
			saveChangePhoneMap.put("code","0");
			return saveChangePhoneMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			saveChangePhoneMap.put("msg","查询客户黑名单信息操作失败！");
			return saveChangePhoneMap;
		}
	}

	/***
	 * 拉黑客户--WEB端用
	 *//*
	@ApiOperation(value="拉黑客户", notes="拉黑客户--WEB端用")
	@RequestMapping(value = "ongoing/inBlackList", method= RequestMethod.POST)
	public Map<String,Object> inBlackList(@RequestBody UccToBlackCriteria uccToBlackCriteria) {

		Map<String,Object> saveChangePhoneMap = new HashMap<String,Object>();
		saveChangePhoneMap.put("msg","拉黑客户操作失败！");
		saveChangePhoneMap.put("code","-1");

		String businessCode = uccToBlackCriteria.getBusinessCode()==null?"":uccToBlackCriteria.getBusinessCode();
		String userId = uccToBlackCriteria.getUserId()==null?"":uccToBlackCriteria.getUserId();
		String pullBlackReason = uccToBlackCriteria.getPullBlackReason()==null?"":uccToBlackCriteria.getPullBlackReason();
		try {
			UccCustomers customer = new UccCustomers();
			customer.setUpdatedUserId(userId);//UccCustomers表里没有userId这个字段，存在updatedUserId里
			customer.setType(7); //拉黑
			customer.setPullBlackReason(pullBlackReason);
			customer.setCreatedTime(new Date());
			customer.setBusinesscode(businessCode);

			int retCode = uccCustomersService.inBlackListByBusinessCodeWEB(customer);
			System.out.println("拉黑客户--WEB端 retCode:::" + retCode);
			if(retCode>=0){
				saveChangePhoneMap.put("msg","拉黑客户操作成功！");
				saveChangePhoneMap.put("code","0");
				return saveChangePhoneMap;
			}else {
				return saveChangePhoneMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return saveChangePhoneMap;
		}
	}*/
}
