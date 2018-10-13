package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.cucsi.bsd.ucc.common.beans.CustomFieldsSaveCriteria;
import cn.cucsi.bsd.ucc.common.beans.QueryImportBatchCriteria;
import cn.cucsi.bsd.ucc.common.beans.TaskRecordSearch;
import cn.cucsi.bsd.ucc.common.untils.Auth;
import cn.cucsi.bsd.ucc.common.untils.JxlExcelUtils;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.DataCustomfield;
import cn.cucsi.bsd.ucc.data.domain.ImportBatch;
import cn.cucsi.bsd.ucc.data.domain.TaskRecord;
import cn.cucsi.bsd.ucc.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * 数据导出Controller
 * add by wangxiaoyu
 * 2018-09-28
 */
@Api(tags={"任务数据导出操作接口"})
@RestController
@RequestMapping(value = "/dataExportController")
public class DataExportController {

	private static Logger logger = Logger.getLogger(DataExportController.class);

	@Autowired
	private TaskRecordService taskRecordService;
	@Autowired
	private UserCustomFieldService userCustomFieldService;
	@Autowired
	private DataCustomfieldService dataCustomfieldService;
	@Autowired
	private MonitorTaskService monitorTaskService;
	@Autowired
	private ImportBatchService importBatchService;

	HttpSession httpSession = null;
	
	/***
	 * 数据列表
	 */
	@ApiOperation(value="数据列表", notes="数据列表")
	@RequestMapping(value = "/dataExportList", method= RequestMethod.POST)
	public Map<String,Object> dataExportList(@RequestBody TaskRecordSearch taskRecordSearch, HttpSession session){

		Map<String,Object> dataExportListMap = new HashMap<String,Object>();
		dataExportListMap.put("msg","操作失败！");
		dataExportListMap.put("code","-1");

		httpSession = session;
		
		List<TaskRecord> list = null;
		List<DataCustomfield> dataCustomfieldList = null;
		String dataCustomfieldJson = "";
		String TaskRecordJson = "";
		String cfieldAll = "";
		JSONArray json = new JSONArray();
		JSONArray dataJson = new JSONArray();
		JSONArray jsonAll = new JSONArray();
		try {
			// 分页查询
			taskRecordSearch.setup(taskRecordService.selectAllCount(taskRecordSearch),taskRecordSearch.getShowLines());
			list = taskRecordService.selectAll(taskRecordSearch);
			dataExportListMap.put("list", list);
			//model.addAttribute("list", list);
			for(TaskRecord TaskRecord : list){
				JSONObject jo = new JSONObject();
				jo.put("importBatch", TaskRecord.getImportBatch());
	            jo.put("importPersonId", TaskRecord.getImportPersonName());
	            jo.put("importTime", TaskRecord.getTrTime());
	            jo.put("businessCode", TaskRecord.getBusinessCode());
	            jo.put("taskTypeName", TaskRecord.getTaskTypeName());
	            jo.put("deptMeshName", TaskRecord.getDeptMeshName());
	            jo.put("deptAreaName", TaskRecord.getDeptAreaName());
	            jo.put("developmentDeptName", TaskRecord.getDevelopmentDeptName());
	            jo.put("productType", TaskRecord.getProductType());
	            jo.put("phoneNumber", TaskRecord.getPhoneNumber());
	            jo.put("velocity",TaskRecord.getVelocity());
	            jo.put("customfields1",TaskRecord.getCustomfields1());
	            jo.put("customfields2",TaskRecord.getCustomfields2());
	            jo.put("customfields3",TaskRecord.getCustomfields3());
	            jo.put("customfields4",TaskRecord.getCustomfields4());
	            jo.put("customfields5",TaskRecord.getCustomfields5());
	            jo.put("customfields6",TaskRecord.getCustomfields6());
	            jo.put("customfields7",TaskRecord.getCustomfields7());
	            jo.put("customfields8",TaskRecord.getCustomfields8());
	            jo.put("customfields9",TaskRecord.getCustomfields9());
	            jo.put("customfields10",TaskRecord.getCustomfields10());
	            jo.put("customfields11",TaskRecord.getCustomfields11());
	            jo.put("customfields12",TaskRecord.getCustomfields12());
	            jo.put("customfields13",TaskRecord.getCustomfields13());
	            jo.put("customfields14",TaskRecord.getCustomfields14());
	            jo.put("customfields15",TaskRecord.getCustomfields15());
	            jo.put("responsible",TaskRecord.getResponsible());
	            jo.put("netStop",TaskRecord.getNetStop());
	            jo.put("packageName",TaskRecord.getPackageName());
	            jo.put("contractName",TaskRecord.getContractName());
	            jo.put("contractStartTime",TaskRecord.getContractStartTime());
	            jo.put("contractEndTime",TaskRecord.getContractEndTime());
	            jo.put("activationTime",TaskRecord.getActivationTime());
	            jo.put("phoneNumber1",TaskRecord.getPhoneNumber1());
	            jo.put("phoneNumber2",TaskRecord.getPhoneNumber2());
	            jo.put("contacts",TaskRecord.getContacts());
	            jo.put("userName",TaskRecord.getUserName());
	            jo.put("tariffName",TaskRecord.getTariffName());
	            jo.put("installedAddress",TaskRecord.getInstalledAddress());
	            jo.put("status",TaskRecord.getStatus());
	            jo.put("ponLogo",TaskRecord.getPonLogo());
	            jo.put("recordTime",TaskRecord.getReTime());
	            jo.put("operatorId",TaskRecord.getOperatorName());
	            jo.put("operatorDept",TaskRecord.getOperatorDName());
	            jo.put("callMemo",TaskRecord.getCallMemo());
	            jo.put("callResult",TaskRecord.getCallResultName());
	            jo.put("taskCode",TaskRecord.getTaskCode());
	            jo.put("totalTime",TaskRecord.getTotalTime());
	            jo.put("hangupCauseStr",TaskRecord.getHangupCauseStr());
	            
	            dataJson.put(jo);
			}
			TaskRecordJson = dataJson.toString().replaceAll("\"", "'");
			//model.addAttribute("dataImportJson", TaskRecordJson);
			dataExportListMap.put("dataImportJson", TaskRecordJson);

			dataCustomfieldList = (List<DataCustomfield>) session.getAttribute("DataCustomExportfields");
			if(!MyUtils.isBlank(dataCustomfieldList)){
				for(DataCustomfield dataCustomfield : dataCustomfieldList){
					JSONObject jo = new JSONObject();
					jo.put("DATACUSTOMFIELDSID", dataCustomfield.getDataCustomfieldsId());
					jo.put("CUSTOMFIELDSNAME", dataCustomfield.getCustomfieldsName());
					json.put(jo);
				}
				dataCustomfieldJson = json.toString().replaceAll("\"", "'");
				if("ISO-8859-1".equals(getEncoding(dataCustomfieldJson))){
					dataCustomfieldJson = new String(dataCustomfieldJson.getBytes("iso8859-1"),"utf-8");
				}
			}else {
				//dataExportListMap.put("msg","session中不存在DataCustomExportfields！");
				//return dataExportListMap;
			}
			List<DataCustomfield> fieldAll = dataCustomfieldService.selectAllExport();
			if(MyUtils.isBlank(fieldAll)){
				dataExportListMap.put("msg","查询导出全部为空或操作失败！");
				return dataExportListMap;
			}
			for(DataCustomfield dataCustomfield : fieldAll){
				JSONObject jo = new JSONObject();
	            jo.put("DATACUSTOMFIELDSID", dataCustomfield.getDataCustomfieldsId());
	            jo.put("CUSTOMFIELDSNAME", dataCustomfield.getCustomfieldsName());
	            jsonAll.put(jo);
			}
			cfieldAll = jsonAll.toString().replaceAll("\"", "'");
			dataExportListMap.put("cfieldAll", cfieldAll);
			dataExportListMap.put("taskRecordSearch", taskRecordSearch);
			dataExportListMap.put("dataCustomfieldJson", dataCustomfieldJson);
			//dataExportListMap.put("userId", Auth.getLoginUser(session).getUserId());
			dataExportListMap.put("userId", taskRecordSearch.getUserId()==null?"":taskRecordSearch.getUserId());
			dataExportListMap.put("msg","操作成功！");
			dataExportListMap.put("code","0");
			return dataExportListMap;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return dataExportListMap;
		}
	}
	
	/****
	 * 获取字符串编码格式
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	/***
	 * 导出数据
	 */
	@ApiOperation(value="导出数据", notes="导出数据")
	@RequestMapping(value = "/export", method= RequestMethod.POST)
	public Map<String,Object> Export(@RequestBody TaskRecordSearch taskRecordSearch , HttpSession session, HttpServletResponse response){

		Map<String,Object> exportMap = new HashMap<String,Object>();
		exportMap.put("msg","导出数据操作失败！");
		exportMap.put("code","-1");

		List<TaskRecord> list = null;
		exportMap.put("infourl", "/dataExportList");
		try {
			taskRecordSearch.setShowLines(0);
			list = taskRecordService.selectAll(taskRecordSearch);
			if(list==null || list.isEmpty()){
				TaskRecord _tr = new TaskRecord();
				list.add(_tr);
			}
			List<DataCustomfield> dataCustomfieldList = (List<DataCustomfield>) session.getAttribute("DataCustomExportfields");
			if(MyUtils.isBlank(dataCustomfieldList)){
				exportMap.put("msg","session中DataCustomExportfields为空！");
				return exportMap;
			}
			String[] keys = new String[dataCustomfieldList.size()];
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i< dataCustomfieldList.size();i++){
				if(sb!=null){
					sb.append(",");
				}
				if(dataCustomfieldList.get(i).getDataCustomfieldsId().equals("recordTime")){
					sb.append("reTime");
				}else if(dataCustomfieldList.get(i).getDataCustomfieldsId().equals("importTime")){
					sb.append("trTime");
				}else if(dataCustomfieldList.get(i).getDataCustomfieldsId().equals("operatorDept")){
					sb.append("operatorDName");
				}else if(dataCustomfieldList.get(i).getDataCustomfieldsId().equals("importPersonId")){
					sb.append("importPersonName");
				}else if(dataCustomfieldList.get(i).getDataCustomfieldsId().equals("operatorId")){
					sb.append("operatorName");
				}else if(dataCustomfieldList.get(i).getDataCustomfieldsId().equals("callResult")){
					sb.append("callResultName");
				}
				else{
					sb.append(dataCustomfieldList.get(i).getDataCustomfieldsId());
				}
				keys[i]=dataCustomfieldList.get(i).getCustomfieldsName();
				
			}
			String tmarray = sb.toString() ;
		    //导出列表
		    JxlExcelUtils.exportexcle(response, "列表", list, "work", keys,TaskRecord.class,null,tmarray);
			exportMap.put("msg","导出操作成功！");
			exportMap.put("code","0");
			exportMap.put("taskRecordSearch","taskRecordSearch");
			exportMap.put("userId", Auth.getLoginUser(session).getUserId());
			return exportMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			if(e instanceof RowsExceededException){
				exportMap.put("msg","导出数据量过大！");
			}
			return exportMap;
		}
	}

	/***
	 * 自定义显示字段
	 */
	@ApiOperation(value="自定义显示字段", notes="自定义显示字段")
	@RequestMapping(value="/cusfsExSave",method= RequestMethod.POST)
	public Map<String,Object> customExfieldsSave(@RequestBody CustomFieldsSaveCriteria customFieldsSaveCriteria) {

		Map<String,Object> customExfieldsSaveMap = new HashMap<String,Object>();
		customExfieldsSaveMap.put("msg","操作失败！");
		customExfieldsSaveMap.put("code","-1");

		String customfieldNames = customFieldsSaveCriteria.getCustomfieldNames()==null?"":customFieldsSaveCriteria.getCustomfieldNames();
		String userId = customFieldsSaveCriteria.getUserId()==null?"":customFieldsSaveCriteria.getUserId();
		try {
			userCustomFieldService.saveUserCustomExportField(customfieldNames, userId);
			List<DataCustomfield> dataCustomfieldList = dataCustomfieldService.selectExportByUserID(userId);
			httpSession.setAttribute("DataCustomExportfields", dataCustomfieldList);

			customExfieldsSaveMap.put("msg","自定义显示字段保存成功！");
			customExfieldsSaveMap.put("code","0");
			return customExfieldsSaveMap;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return customExfieldsSaveMap;
		}
	}
	
	/***
	 * 任务查询导出数据
	 */
	@ApiOperation(value="任务查询导出数据", notes="任务查询导出数据")
	@RequestMapping(value="/exportTask",method= RequestMethod.POST)
	public Map<String,Object> ExportTask(@RequestBody TaskRecordSearch taskRecordSearch,HttpSession session,HttpServletResponse response){

		Map<String,Object> customExfieldsSaveMap = new HashMap<String,Object>();
		customExfieldsSaveMap.put("msg","操作失败！");
		customExfieldsSaveMap.put("code","-1");
		customExfieldsSaveMap.put("infourl", "/searchTaskList");
		List<TaskRecord> list = null;
		try {
			taskRecordSearch.setShowLines(0); //不分页
			list = taskRecordService.selectAll(taskRecordSearch);
			String[] keys = new String[]{"任务编号","任务类型","任务办理结果","业务号码","联系人","联系电话","任务受理人","任务受理部门","任务受理时间"};
			String tmarray ="taskCode,taskTypeName,callResultName,businessCode,userName,defultPhone,nickName,operatorDName,transferTime" ;
			//导出列表
			JxlExcelUtils.exportexcle(response, "列表", list, "work", keys,TaskRecord.class,null,tmarray);
			customExfieldsSaveMap.put("msg","导出成功！");
			customExfieldsSaveMap.put("code","0");
			customExfieldsSaveMap.put("userId", Auth.getLoginUser(session).getUserId());
			customExfieldsSaveMap.put("taskRecordSearch","taskRecordSearch！");
			return customExfieldsSaveMap;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return customExfieldsSaveMap;
		}
	}

	/***
	 * 查询导入批次
	 */
	@ApiOperation(value="查询导入批次", notes="查询导入批次")
	@RequestMapping(value = "/queryImportBatch",method = RequestMethod.POST)
	public Map<String,Object> queryImportBatch(@RequestBody QueryImportBatchCriteria queryImportBatchCriteria) throws Exception {

		String taskTypeName = queryImportBatchCriteria.getTaskTypeName() == null?"":queryImportBatchCriteria.getTaskTypeName();
		String recent = queryImportBatchCriteria.getRecent() == null?"":queryImportBatchCriteria.getRecent();

		Map<String,Object> queryImportBatchMap = new HashMap<String,Object>();
		queryImportBatchMap.put("msg","操作失败！");
		queryImportBatchMap.put("code","-1");

		List<ImportBatch> list = new ArrayList<ImportBatch>();
		try {
			list = importBatchService.queryImportBatch(taskTypeName, recent);
			queryImportBatchMap.put("resultValue",list);
			queryImportBatchMap.put("msg","操作成功！");
			queryImportBatchMap.put("code","0");
			return queryImportBatchMap;
		} catch (Exception e) {
			e.printStackTrace();
			return queryImportBatchMap;
		}
	}
}
