package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import cn.cucsi.bsd.ucc.common.beans.AutoSearchTaskCriteria;
import cn.cucsi.bsd.ucc.common.beans.CustomFieldsSaveCriteria;
import cn.cucsi.bsd.ucc.common.beans.DataImportCriteria;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;

/****
 * 数据导入Controller
 * add by wangxiaoyu
 * 2018-09-11
 */
@Api(tags={"任务数据导入操作接口"})
@RestController
@RequestMapping(value = "/taskDataControl")
public class DataImportController {

	private static Logger logger = Logger.getLogger(DataImportController.class);
	@Autowired
	private DataImportService dataImportService;
	
	@Autowired
	private UserCustomFieldService userCustomFieldService;
	
	@Autowired
	private DataCustomfieldService dataCustomfieldService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private CreateTaskService createTaskService;
	
	/***
	 * Excel导入列
	 */
	public static int businessCodeCell = -1;
	public static int taskTypeNameCell = -1;
	public static int deptMeshNameCell = -1;
	public static int deptAreaNameCell = -1;
	public static int productTypeCell = -1;
	public static int phoneNumber3Cell = -1;
	public static int velocityCell = -1;
	public static int customfields1Cell = -1;
	public static int customfields2Cell = -1;
	public static int customfields3Cell = -1;
	public static int customfields4Cell = -1;
	public static int customfields5Cell = -1;
	public static int customfields6Cell = -1;
	public static int customfields7Cell = -1;
	public static int customfields8Cell = -1;
	public static int customfields9Cell = -1;
	public static int customfields10Cell = -1;
	public static int customfields11Cell = -1;
	public static int customfields12Cell = -1;
	public static int customfields13Cell = -1;
	public static int customfields14Cell = -1;
	public static int customfields15Cell = -1;
	public static int responsibleCell = -1;
	public static int netStopCell = -1;
	public static int packageNameCell = -1;
	public static int contractNameCell =  -1;
	public static int contractStartTimeCell = -1;
	public static int contractEndTimeCell = -1;
	public static int activationTimeCell = -1;
	public static int developmentDeptCell = -1;
	public static int phoneNumber1Cell = -1;
	public static int phoneNumber2Cell = -1;
	public static int contactsCell = -1;
	public static int userNameCell = -1;
	public static int tariffNameCell = -1;
	public static int installedAddressCell = -1;
	public static int statusCell = -1;
	public static int ponLogoCell = -1;

	/***
	 * 数据导入列表
	 * add by wangxiaoyu
	 * 2018-09-11
	 */
	@ApiOperation(value="数据导入列表", notes="数据导入列表")
	@RequestMapping(value = "/dataImportList", method= RequestMethod.POST)
	public Map<String,Object> dataImportList(@RequestBody DataImportCriteria dataImportCriteria,HttpSession session){

		Map<String,Object> dataImportMap = new HashMap<String,Object>();
		dataImportMap.put("msg","操作失败！");
		dataImportMap.put("code","-1");

		List<DataImport> list;
		List<DataCustomfield> dataCustomfieldList = null;
		
		String dataCustomfieldJson = "";
		String dataImportJson = "";
		String cfieldAll = "";
		
		JSONArray json = new JSONArray();
		JSONArray dataJson = new JSONArray();
		JSONArray jsonAll = new JSONArray();

		String userId = dataImportCriteria.getUserId()==null?"":dataImportCriteria.getUserId();
		try {
			dataImportCriteria.setImportPersonId(userId);
			dataImportCriteria.setup(dataImportService.selectBySearchCount(dataImportCriteria), dataImportCriteria.getShowLines());
			list = dataImportService.selectBySearch(dataImportCriteria);
            System.out.println("数据导入列表 list.size():::" + list.size());
			//model.addAttribute("list", list);
            dataImportMap.put("list", list);
			if(!MyUtils.isBlank(list)){
                for(DataImport dataImport : list){
                    JSONObject jo = new JSONObject();
                    jo.put("importBatch", dataImport.getImportBatch());
                    jo.put("importPersonId", dataImport.getImportPersonId());
                    jo.put("importTime", dataImport.getTrTime());
                    jo.put("businessCode", dataImport.getBusinessCode());
                    jo.put("taskTypeName", dataImport.getTaskTypeName());
                    jo.put("deptMeshName", dataImport.getDeptMeshName());
                    jo.put("deptAreaName", dataImport.getDeptAreaName());
                    jo.put("developmentDept", dataImport.getDevelopmentDept());
                    jo.put("productType", dataImport.getProductType());
                    jo.put("phoneNumber3", dataImport.getPhoneNumber3());
                    jo.put("velocity",dataImport.getVelocity());
                    jo.put("customfields1",dataImport.getCustomfields1());
                    jo.put("customfields2",dataImport.getCustomfields2());
                    jo.put("customfields3",dataImport.getCustomfields3());
                    jo.put("customfields4",dataImport.getCustomfields4());
                    jo.put("customfields5",dataImport.getCustomfields5());
                    jo.put("customfields6",dataImport.getCustomfields6());
                    jo.put("customfields7",dataImport.getCustomfields7());
                    jo.put("customfields8",dataImport.getCustomfields8());
                    jo.put("customfields9",dataImport.getCustomfields9());
                    jo.put("customfields10",dataImport.getCustomfields10());
                    jo.put("customfields11",dataImport.getCustomfields11());
                    jo.put("customfields12",dataImport.getCustomfields12());
                    jo.put("customfields13",dataImport.getCustomfields13());
                    jo.put("customfields14",dataImport.getCustomfields14());
                    jo.put("customfields15",dataImport.getCustomfields15());
                    jo.put("responsible",dataImport.getResponsible());
                    jo.put("netStop",dataImport.getNetStop());
                    jo.put("packageName",dataImport.getPackageName());
                    jo.put("contractName",dataImport.getContractName());
                    jo.put("contractStartTime",dataImport.getContractStartTime());
                    jo.put("contractEndTime",dataImport.getContractEndTime());
                    jo.put("activationTime",dataImport.getActivationTime());
                    jo.put("phoneNumber1",dataImport.getPhoneNumber1());
                    jo.put("phoneNumber2",dataImport.getPhoneNumber2());
                    jo.put("contacts",dataImport.getContacts());
                    jo.put("userName",dataImport.getUserName());
                    jo.put("tariffName",dataImport.getTariffName());
                    jo.put("installedAddress",dataImport.getInstalledAddress());
                    jo.put("status",dataImport.getStatus());
                    jo.put("ponLogo",dataImport.getPonLogo());
                    dataJson.add(jo);
                }
                dataImportJson = dataJson.toString().replaceAll("\"", "'");
                //model.addAttribute("dataImportJson", dataImportJson);
                dataImportMap.put("dataImportJson", dataImportJson);
            }
			dataCustomfieldList = (List<DataCustomfield>)session.getAttribute("DataCustomfields");
			//dataCustomfieldList = dataImportCriteria.getDataCustomfields();

			if(!MyUtils.isBlank(dataCustomfieldList)){
				for(DataCustomfield dataCustomfield : dataCustomfieldList){
					JSONObject jo = new JSONObject();
					jo.put("DATACUSTOMFIELDSID", dataCustomfield.getDataCustomfieldsId());
					jo.put("CUSTOMFIELDSNAME", dataCustomfield.getCustomfieldsName());
					json.add(jo);
				}
				dataCustomfieldJson = json.toString().replaceAll("\"", "'");
				if("ISO-8859-1".equals(getEncoding(dataCustomfieldJson))){
					dataCustomfieldJson = new String(dataCustomfieldJson.getBytes("iso8859-1"),"utf-8");
				}
			}
            List<DataCustomfield> fieldAll = dataCustomfieldService.selectAll();
            if(!MyUtils.isBlank(fieldAll)){
                for(DataCustomfield dataCustomfield : fieldAll){
                    JSONObject jo = new JSONObject();
                    jo.put("DATACUSTOMFIELDSID", dataCustomfield.getDataCustomfieldsId());
                    jo.put("CUSTOMFIELDSNAME", dataCustomfield.getCustomfieldsName());
                    jsonAll.add(jo);
                }
                cfieldAll = jsonAll.toString().replaceAll("\"", "'");

                //model.addAttribute("cfieldAll", cfieldAll);
                dataImportMap.put("cfieldAll", cfieldAll);
            }
			dataImportMap.put("dataCustomfieldJson", dataCustomfieldJson);
			dataImportMap.put("dataImportCriteria",dataImportCriteria);
			dataImportMap.put("msg","操作成功！");
			dataImportMap.put("code","0");
			return dataImportMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			System.out.println("数据导入列表时发生异常！");
			dataImportMap.put("msg","操作失败！");
			dataImportMap.put("code","-1");
			return dataImportMap;
		}
	}

	/****
	 * 搜索可删除批次-补全
	 * add by wangxiaoyu
	 * 2018-09-11
	 */
	@ApiOperation(value="搜索可删除批次-补全", notes="搜索可删除批次-补全")
	@RequestMapping(value = "/autoSearchDeleteBatch", method= RequestMethod.POST)
	public String autoSearchDeleteBatch(@RequestBody AutoSearchTaskCriteria autoSearchTaskCriteria) {
		
		List<String> strList = new ArrayList<String>();
		String xmlStr = "";
		String userId = autoSearchTaskCriteria.getUserId()==null?"":autoSearchTaskCriteria.getUserId();
		String word = autoSearchTaskCriteria.getWord()==null?"":autoSearchTaskCriteria.getWord();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("batchFlag", ImportBatch.BATCHFLAGN); // 未生产任务数据
		try {
			if (word != ""){
				map.put("word", word);
			}
			strList = createTaskService.autoSearchTaskBatch(map);
			xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<words>";
			if (strList != null && strList.size() > 0) {
				for (int i = 0; i < strList.size(); i++) {
					xmlStr += "<word>" + strList.get(i) + "</word>";
				}
			}
			xmlStr += "</words>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return "";
		}
		return xmlStr;
	}
	/***
	 * 按批次删除数据
	 * add by wangxiaoyu
     * 2018-09-13
	 */
	@ApiOperation(value="按批次删除数据", notes="按批次删除数据")
	@RequestMapping(value="/deleteByBatch/{taskBatchCode}",method= RequestMethod.DELETE)
	public Map<String,Object> deleteByBatch(@PathVariable String taskBatchCode){

        Map<String,Object> deleteByBatchMap = new HashMap<String,Object>();
		//model.addAttribute("msg", "删除失败!");
        deleteByBatchMap.put("msg", "删除失败!");
        deleteByBatchMap.put("code", "-1");
		//model.addAttribute("data", "/data/dataImportList.html");
        deleteByBatchMap.put("data", "/dataImportList");
		if (taskBatchCode != null && taskBatchCode.length() > 0) {
			try {
				dataImportService.deleteByBatch(taskBatchCode);
				//model.addAttribute("data", "/data/dataImportList.html");
                deleteByBatchMap.put("data", "/dataImportList");
				//model.addAttribute("msg", "删除成功!");
                deleteByBatchMap.put("msg", "删除成功!");
				deleteByBatchMap.put("code", "0");
			} catch (Exception e) {
				e.printStackTrace();
    			logger.error(e.getMessage(), e);
				System.out.println("按批次删除数据发生异常！");
			}
		}else {
			//model.addAttribute("msg", "请选择要删除的数据批次!");
            deleteByBatchMap.put("msg", "请选择要删除的数据批次!");
		}
		return deleteByBatchMap;
	}

	/***
	 * 自定义显示字段
	 */
	@ApiOperation(value="自定义显示字段", notes="自定义显示字段")
	@RequestMapping(value="/cusfsSave",method= RequestMethod.POST)
	public Map<String,Object> customfieldsSave(@RequestBody CustomFieldsSaveCriteria customFieldsSaveCriteria){

		String customfieldNames = customFieldsSaveCriteria.getCustomfieldNames()==null?"":customFieldsSaveCriteria.getCustomfieldNames();
		String userId = customFieldsSaveCriteria.getUserId()==null?"":customFieldsSaveCriteria.getUserId();
		Map<String,Object> customFieldsSaveMap = new HashMap<String,Object>();
		customFieldsSaveMap.put("msg","操作失败！");
		customFieldsSaveMap.put("code","-1");
		try {
			int retcode = userCustomFieldService.saveUserCustomField(customfieldNames, userId);
			if(retcode<0){
				customFieldsSaveMap.put("message","保存失败！");
			}
			List<DataCustomfield> dataCustomfieldList = dataCustomfieldService.selectImportByUserID(userId);
			customFieldsSaveMap.put("DataCustomfields", dataCustomfieldList);
			customFieldsSaveMap.put("msg","自定义显示字段保存成功！");
			customFieldsSaveMap.put("code","0");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			customFieldsSaveMap.put("msg","自定义显示字段操作发生异常！");
			customFieldsSaveMap.put("code","-1");
		}
		return customFieldsSaveMap;
	}

	/***
	 * 导入
	 */
	@ApiOperation(value="导入", notes="导入")
	@RequestMapping(value="/upload",method= RequestMethod.POST)
	public Map<String,Object> uploadFile(@RequestParam("files") MultipartFile file,@RequestParam("userId")String userId,@RequestParam("domainId")String domainId, HttpSession httpSession) {

		System.out.println("导入接口获取到的参数userId：：：" + userId);
		System.out.println("导入接口获取到的参数domainId：：：" + domainId);
		Map<String,Object> customFieldsSaveMap = new HashMap<String,Object>();
		//model.addAttribute("msg", "上传失败!");
		//model.addAttribute("data", "/data/dataImportList.html");
		customFieldsSaveMap.put("msg", "上传失败!");
		customFieldsSaveMap.put("code", "-1");
		customFieldsSaveMap.put("data", "/dataImportList");
		if (file != null && file.getSize() > 0) {
			try {
				List<DataImport> dataImportList = null;
				Map<String,Object> dataImportMap = null;
				// 获取文件后缀 
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				
				if(DataImport.XLS.equals(suffix)){
					dataImportMap = readExcelXls(file.getInputStream(),httpSession,userId,domainId);
					if(!MyUtils.isBlank(dataImportMap)){
						if(dataImportMap.get("code").equals("0")){
							if(!MyUtils.isBlank(dataImportMap.get("list"))){
								dataImportList = (List<DataImport>)dataImportMap.get("list");
							}
						}
					}
				}else if(DataImport.XLSX.equals(suffix)){
					dataImportMap = readExcelXlsx(file.getInputStream(),httpSession,userId,domainId);
					if(!MyUtils.isBlank(dataImportMap)){
						if(dataImportMap.get("code").equals("0")){
							if(!MyUtils.isBlank(dataImportMap.get("list"))){
								dataImportList = (List<DataImport>)dataImportMap.get("list");
							}
						}
					}
				}
				// 数据校验
				DataImport dataImport = null;
				String taskType = "";
				String deptIdAndChildIds = (String)httpSession.getAttribute("DeptIdAndChildIds");

				/*if(deptIdAndChildIds==null || deptIdAndChildIds.isEmpty()){
					//model.addAttribute("msg", "您所属的部门为空，不能完成导入操作，请联系系统管理员!");
					customFieldsSaveMap.put("msg", "您所属的部门为空，不能完成导入操作，请联系系统管理员!");
					return customFieldsSaveMap;
				}*/
				for(int i=0; i<dataImportList.size(); i++){
					taskType = dataImportList.get(0).getTaskTypeName();
					dataImport = dataImportList.get(i);
					int rownumber = i+3;
					if(dataImport.getTaskTypeName() == null || "".equals(dataImport.getTaskTypeName())){
						//model.addAttribute("msg", "第"+rownumber+"行：任务类型不可空!");
						//return "info.view";
						customFieldsSaveMap.put("msg", "第"+rownumber+"行：任务类型不可空!");
						return customFieldsSaveMap;
					}else if(!taskType.equals(dataImport.getTaskTypeName())){
						//model.addAttribute("msg", "导入任务类型请保持一致!");
						//return "info.view";
						customFieldsSaveMap.put("msg", "导入任务类型请保持一致!");
						return customFieldsSaveMap;
					}
					if(dataImport.getDeptMeshName() == null || "".equals(dataImport.getDeptMeshName())){
						//model.addAttribute("msg", "第"+rownumber+"行：名称不可空!");
						//return "info.view";
						customFieldsSaveMap.put("msg", "第"+rownumber+"行：名称不可空!");
						return customFieldsSaveMap;
					}
					if(dataImport.getDeptAreaName() == null || "".equals(dataImport.getDeptAreaName())){
						//model.addAttribute("msg", "第"+rownumber+"行：包区名称不可空!");
						//return "info.view";
						customFieldsSaveMap.put("msg", "第"+rownumber+"行：包区名称不可空!");
						return customFieldsSaveMap;
					}
					if(dataImport.getBusinessCode() == null || "".equals(dataImport.getBusinessCode())){
						//model.addAttribute("msg", "第"+rownumber+"行：业务号码不可空!");
						//return "info.view";
						customFieldsSaveMap.put("msg", "第"+rownumber+"行：业务号码不可空!");
						return customFieldsSaveMap;
					}
				}
				if(!checkTaskType(taskType,domainId)){
					//model.addAttribute("msg", "任务类型不存在，请检查您的任务类型!");
					//return "info.view";
					customFieldsSaveMap.put("msg", "任务类型不存在，请检查您的任务类型!");
					return customFieldsSaveMap;
				}
				dataImportService.insertGroup(dataImportList);
				//model.addAttribute("data", "/data/dataImportList.html");
				//model.addAttribute("msg", "本次成功导入数据："+dataImportList.size()+"条!");
				customFieldsSaveMap.put("data", "/dataImportList");
				customFieldsSaveMap.put("code", "0");
				customFieldsSaveMap.put("msg", "本次成功导入数据："+dataImportList.size()+"条!");
			} catch (Exception e) {
				e.printStackTrace();
    			logger.error(e.getMessage(), e);
			}
		}
		return customFieldsSaveMap;
	}
	
	/***
	 * 校验任务类型
	 * add by wangxiaoyu
	 */
	private boolean checkTaskType(String taskTypeName,String domainId) throws Exception{
		TaskType taskType = businessService.selectByNameAndDomainIdInCache(taskTypeName,domainId);
		if(taskType != null && taskType.getTaskTypeId() != null && !"".equals(taskType.getTaskTypeId())){
			return true;
		}
		return false;
	}

	/****
	 * 读取Excel.xls内容 Map<String,List<DataImport>>
	 * add by wangxiaoyu
	 * 2018-09-18
	 */
	public static Map<String,Object> readExcelXls(InputStream stream, HttpSession session,String userID,String domainId) throws BiffException, IOException {

		//String domainId = (String)session.getAttribute("domainId");
		//String userID = Auth.getLoginUser(session).getUserId().toString();

		Map<String,Object> readExcelXlsMap = new HashMap<String,Object>();
		readExcelXlsMap.put("code", "-1");
		readExcelXlsMap.put("msg", "上传失败!");

		List<DataImport> list = null;
		try {
			// 创建一个list 用来存储读取的内容
			list = new ArrayList<DataImport>();
			// 数据导入实体类
			DataImport dataImport = null;
			// key：字段名  value：列编码
			HashMap<String, Integer> importMap = new HashMap<String, Integer>();

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String TIME = new String(df.format(date));

			String eventId = "";
			if(!MyUtils.isBlank(userID)){
                // 导入批次  规则：yyyyMMddHHmmss+操作员ID+十位随机码
                eventId = TIME+userID+createRandomCode(1);
            }else{
                System.out.println("读取Excel.xls内容,session获取userID为空！");
				readExcelXlsMap.put("msg","读取Excel.xls内容,session获取userID为空！");
                return readExcelXlsMap;
            }
			// 主键
			UUIDGenerator generator = new UUIDGenerator();
			//导入时间
			Timestamp importTime = new Timestamp(System.currentTimeMillis());

			HSSFWorkbook hwb=new HSSFWorkbook(stream);
			// 第一个标签
			HSSFSheet sheet=hwb.getSheetAt(0);
			// 总行数
			int rowcount = sheet.getLastRowNum();
			// 总列数
			int cellcount = sheet.getRow(0).getPhysicalNumberOfCells();
			// 获得表头对应实体类字段名
			for(int i=0; i<cellcount; i++){
                // 第一行  表头
                HSSFRow row = sheet.getRow(0);
                String cell = row.getCell(i).toString();
                if(!StringUtils.isNumeric(cell)){
					readExcelXlsMap.put("msg", "第1行，第"+(i+1)+"列必须为数字，但实际值是："+cell+ "，请修正！");
					return readExcelXlsMap;
                    //throw new NumberFormatException("已经处理的数字格式化异常！！内容为： "+"第1行，第"+(i+1)+"列必须为数字，但实际值是："+cell+ "，请修正！");
                }
                Integer cellCode = Integer.parseInt(cell.trim());
                String cellName = getCellName(cellCode);
                importMap.put(cellName, i);
            }
			// 获取列编码
			getColumnCode(importMap);
			// 初始化单元格
			HSSFCell businessCode = null;
			HSSFCell taskTypeName = null;
			HSSFCell deptMeshName = null;
			HSSFCell deptAreaName = null;
			HSSFCell productType = null;
			HSSFCell phoneNumber3 = null;
			HSSFCell velocity = null;
			HSSFCell customfields1 = null;
			HSSFCell customfields2 = null;
			HSSFCell customfields3 = null;
			HSSFCell customfields4 = null;
			HSSFCell customfields5 = null;
			HSSFCell customfields6 = null;
			HSSFCell customfields7 = null;
			HSSFCell customfields8 = null;
			HSSFCell customfields9 = null;
			HSSFCell customfields10 = null;
			HSSFCell customfields11 = null;
			HSSFCell customfields12 = null;
			HSSFCell customfields13 = null;
			HSSFCell customfields14 = null;
			HSSFCell customfields15 = null;
			HSSFCell responsible = null;
			HSSFCell netStop = null;
			HSSFCell packageName = null;
			HSSFCell contractName = null;
			HSSFCell contractStartTime = null;
			HSSFCell contractEndTime = null;
			HSSFCell activationTime = null;
			HSSFCell developmentDept = null;
			HSSFCell phoneNumber1 = null;
			HSSFCell phoneNumber2 = null;
			HSSFCell contacts = null;
			HSSFCell userName = null;
			HSSFCell tariffName = null;
			HSSFCell installedAddress = null;
			HSSFCell status = null;
			HSSFCell ponLogo = null;
			// 行数(表头的目录不需要，从3开始)
			for (int i = 2; i <= rowcount; i++) {
                dataImport = new DataImport();
				dataImport.setDomainId(domainId);
                // 行
                HSSFRow row = sheet.getRow(i);
                // 列编码大于0 获取单元格内容 存入DataImport实体类
                if(businessCodeCell >= 0){
                    businessCode = row.getCell(businessCodeCell);
                    dataImport.setBusinessCode(getColumnXls(businessCode));
                }
                if(taskTypeNameCell >= 0){
                    taskTypeName = row.getCell(taskTypeNameCell);
                    dataImport.setTaskTypeName(getColumnXls(taskTypeName));
                }
                if(deptMeshNameCell >= 0){
                    deptMeshName = row.getCell(deptMeshNameCell);
                    dataImport.setDeptMeshName(getColumnXls(deptMeshName));
                }
                if(deptAreaNameCell >= 0){
                    deptAreaName = row.getCell(deptAreaNameCell);
                    dataImport.setDeptAreaName(getColumnXls(deptAreaName));
                }
                if(productTypeCell >= 0){
                    productType = row.getCell(productTypeCell);
                    dataImport.setProductType(getColumnXls(productType));
                }
                if(phoneNumber3Cell >= 0){
                    phoneNumber3 = row.getCell(phoneNumber3Cell);
                    dataImport.setPhoneNumber3(getColumnXls(phoneNumber3));
                }
                if(phoneNumber2Cell >= 0){
                    phoneNumber2 = row.getCell(phoneNumber2Cell);
                    dataImport.setPhoneNumber2(getColumnXls(phoneNumber2));
                }
                if(phoneNumber1Cell >= 0){
                    phoneNumber1 = row.getCell(phoneNumber1Cell);
                    dataImport.setPhoneNumber1(getColumnXls(phoneNumber1));
                }
                if(velocityCell >= 0){
                    velocity = row.getCell(velocityCell);
                    dataImport.setVelocity(getColumnXls(velocity));
                }
                if(responsibleCell >= 0){
                    responsible = row.getCell(responsibleCell);
                    dataImport.setResponsible(getColumnXls(responsible));
                }
                if(netStopCell >= 0){
                    netStop = row.getCell(netStopCell);
                    dataImport.setNetStop(getColumnXls(netStop));
                }
                if(packageNameCell >= 0){
                    packageName = row.getCell(packageNameCell);
                    dataImport.setPackageName(getColumnXls(packageName));
                }
                if(contractNameCell >= 0){
                    contractName = row.getCell(contractNameCell);
                    dataImport.setContractName(getColumnXls(contractName));
                }
                if(contractStartTimeCell >= 0){
                    contractStartTime = row.getCell(contractStartTimeCell);
                    dataImport.setContractStartTime(getColumnXls(contractStartTime));
                }
                if(contractEndTimeCell >= 0){
                    contractEndTime = row.getCell(contractEndTimeCell);
                    dataImport.setContractEndTime(getColumnXls(contractEndTime));
                }
                if(activationTimeCell >= 0){
                    activationTime = row.getCell(activationTimeCell);
                    dataImport.setActivationTime(getColumnXls(activationTime));
                }
                if(developmentDeptCell >= 0){
                    developmentDept = row.getCell(developmentDeptCell);
                    dataImport.setDevelopmentDept(getColumnXls(developmentDept));
                }
                if(contactsCell >= 0){
                    contacts = row.getCell(contactsCell);
                    dataImport.setContacts(getColumnXls(contacts));
                }
                if(userNameCell >= 0){
                    userName = row.getCell(userNameCell);
                    dataImport.setUserName(getColumnXls(userName));
                }
                if(tariffNameCell >= 0){
                    tariffName = row.getCell(tariffNameCell);
                    dataImport.setTariffName(getColumnXls(tariffName));
                }
                if(installedAddressCell >= 0){
                    installedAddress = row.getCell(installedAddressCell);
                    dataImport.setInstalledAddress(getColumnXls(installedAddress));
                }
                if(statusCell >= 0){
                    status = row.getCell(statusCell);
                    dataImport.setStatus(getColumnXls(status));
                }
                if(ponLogoCell >= 0){
                    ponLogo = row.getCell(ponLogoCell);
                    dataImport.setPonLogo(getColumnXls(ponLogo));
                }
                if(customfields1Cell >= 0){
                    customfields1 = row.getCell(customfields1Cell);
                    dataImport.setCustomfields1(getColumnXls(customfields1));
                }
                if(customfields2Cell >= 0){
                    customfields2 = row.getCell(customfields2Cell);
                    dataImport.setCustomfields2(getColumnXls(customfields2));
                }
                if(customfields3Cell >= 0){
                    customfields3 = row.getCell(customfields3Cell);
                    dataImport.setCustomfields3(getColumnXls(customfields3));
                }
                if(customfields4Cell >= 0){
                    customfields4 = row.getCell(customfields4Cell);
                    dataImport.setCustomfields4(getColumnXls(customfields4));
                }
                if(customfields5Cell >= 0){
                    customfields5 = row.getCell(customfields5Cell);
                    dataImport.setCustomfields5(getColumnXls(customfields5));
                }
                if(customfields6Cell >= 0){
                    customfields6 = row.getCell(customfields6Cell);
                    dataImport.setCustomfields6(getColumnXls(customfields6));
                }
                if(customfields7Cell >= 0){
                    customfields7 = row.getCell(customfields7Cell);
                    dataImport.setCustomfields7(getColumnXls(customfields7));
                }
                if(customfields8Cell >= 0){
                    customfields8 = row.getCell(customfields8Cell);
                    dataImport.setCustomfields8(getColumnXls(customfields8));
                }
                if(customfields9Cell >= 0){
                    customfields9 = row.getCell(customfields9Cell);
                    dataImport.setCustomfields9(getColumnXls(customfields9));
                }
                if(customfields10Cell >= 0){
                    customfields10 = row.getCell(customfields10Cell);
                    dataImport.setCustomfields10(getColumnXls(customfields10));
                }
                if(customfields11Cell >= 0){
                    customfields11 = row.getCell(customfields11Cell);
                    dataImport.setCustomfields11(getColumnXls(customfields11));
                }
                if(customfields12Cell >= 0){
                    customfields12 = row.getCell(customfields12Cell);
                    dataImport.setCustomfields12(getColumnXls(customfields12));
                }
                if(customfields13Cell >= 0){
                    customfields13 = row.getCell(customfields13Cell);
                    dataImport.setCustomfields13(getColumnXls(customfields13));
                }
                if(customfields14Cell >= 0){
                    customfields14 = row.getCell(customfields14Cell);
                    dataImport.setCustomfields14(getColumnXls(customfields14));
                }
                if(customfields15Cell >= 0){
                    customfields15 = row.getCell(customfields15Cell);
                    dataImport.setCustomfields15(getColumnXls(customfields15));
                }
                dataImport.setImportBatch(eventId);
                dataImport.setImportPersonId(userID);
                dataImport.setImportTime(importTime);

                String uuid = generator.generate();
                dataImport.setDataImportId(uuid);
                list.add(dataImport);
            }
			readExcelXlsMap.put("list",list);
			readExcelXlsMap.put("code","0");
			readExcelXlsMap.put("msg","上传成功！");
			return readExcelXlsMap;
		} catch (Exception e) {
			e.printStackTrace();
			return readExcelXlsMap;
		}
	}
	
	/****
	 * 读取Excel.xlsx内容
	 * add by wangxiaoyu
	 */
	public static Map<String,Object> readExcelXlsx(InputStream stream, HttpSession session,String userID,String domainId) throws BiffException, IOException {

		//String domainId = (String)session.getAttribute("domainId");
		//String userID = Auth.getLoginUser(session).getUserId().toString();

		Map<String,Object> readExcelXlsxMap = new HashMap<String,Object>();
		readExcelXlsxMap.put("code","-1");
		readExcelXlsxMap.put("msg","上传失败！");
		List<DataImport> list = null;
		try {
			// 创建一个list 用来存储读取的内容
			list = new ArrayList<DataImport>();
			// 数据导入实体类
			DataImport dataImport = null;
			// key：字段名  value：列编码
			HashMap<String, Integer> importMap = new HashMap<String, Integer>();

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String TIME = new String(df.format(date));

			String eventId = "";
			if(!MyUtils.isBlank(userID)){
			// 导入批次  规则：yyyyMMddHHmmss+操作员ID+十位随机码
				eventId = TIME+createRandomCode(1);
			}else{
				System.out.println("读取Excel.xlsx内容,session获取userID为空！");
				readExcelXlsxMap.put("msg","读取Excel.xlsx内容,session获取userID为空！");
				return readExcelXlsxMap;
			}
			// 主键
			UUIDGenerator generator = new UUIDGenerator();
			//导入时间
			Timestamp importTime = new Timestamp(System.currentTimeMillis());

			XSSFWorkbook xwb=new XSSFWorkbook(stream);
			// 第一个标签
			XSSFSheet sheet=xwb.getSheetAt(0);
			// 总行数
			int rowcount = sheet.getLastRowNum();
			// 总列数
			int cellcount = sheet.getRow(0).getPhysicalNumberOfCells();
			// 获得表头对应实体类字段名
			for(int i=0; i<cellcount; i++){
                // 第一行  表头
                XSSFRow row = sheet.getRow(0);
                String cell = row.getCell(i) == null? "": row.getCell(i).toString();
                if(cell==null || cell.trim().isEmpty() || !StringUtils.isNumeric(cell)){
					readExcelXlsxMap.put("msg", "第1行，第"+(i+1)+"列必须为数字，但实际值是："+cell+ "，请修正！");
					return readExcelXlsxMap;
                    //throw new NumberFormatException("已经处理的数字格式化异常！！内容为： "+"第1行，第"+(i+1)+"列必须为数字，但实际值是："+cell+ "，请修正！");
                }
                Integer cellCode = Integer.parseInt(cell.trim());
                String cellName = getCellName(cellCode);
                importMap.put(cellName, i);
            }
			// 获取列编码
			getColumnCode(importMap);
			// 初始化单元格
			XSSFCell businessCode = null;
			XSSFCell taskTypeName = null;
			XSSFCell deptMeshName = null;
			XSSFCell deptAreaName = null;
			XSSFCell productType = null;
			XSSFCell phoneNumber3 = null;
			XSSFCell velocity = null;
			XSSFCell customfields1 = null;
			XSSFCell customfields2 = null;
			XSSFCell customfields3 = null;
			XSSFCell customfields4 = null;
			XSSFCell customfields5 = null;
			XSSFCell customfields6 = null;
			XSSFCell customfields7 = null;
			XSSFCell customfields8 = null;
			XSSFCell customfields9 = null;
			XSSFCell customfields10 = null;
			XSSFCell customfields11 = null;
			XSSFCell customfields12 = null;
			XSSFCell customfields13 = null;
			XSSFCell customfields14 = null;
			XSSFCell customfields15 = null;
			XSSFCell responsible = null;
			XSSFCell netStop = null;
			XSSFCell packageName = null;
			XSSFCell contractName = null;
			XSSFCell contractStartTime = null;
			XSSFCell contractEndTime = null;
			XSSFCell activationTime = null;
			XSSFCell developmentDept = null;
			XSSFCell phoneNumber1 = null;
			XSSFCell phoneNumber2 = null;
			XSSFCell contacts = null;
			XSSFCell userName = null;
			XSSFCell tariffName = null;
			XSSFCell installedAddress = null;
			XSSFCell status = null;
			XSSFCell ponLogo = null;

			// 行数(表头的目录不需要，从3开始)
			for (int i = 2; i <= rowcount; i++) {
                dataImport = new DataImport();
				dataImport.setDomainId(domainId);
                // 行
                XSSFRow row = sheet.getRow(i);
                // 列编码大于0 获取单元格内容 存入DataImport实体类
                if(row==null){
					readExcelXlsxMap.put("msg", "第"+(i+1)+"行，整行为空，请修正！");
                    return readExcelXlsxMap;
					//throw new NumberFormatException("已经处理的EXCEL数据导入异常！！内容为： "+"第"+(i+1)+"行，整行为空，请修正！");
                }
                if(businessCodeCell >= 0){
                    businessCode = row.getCell(businessCodeCell);
                    dataImport.setBusinessCode(getColumnXlsx(businessCode));
                }
                if(taskTypeNameCell >= 0){
                    taskTypeName = row.getCell(taskTypeNameCell);
                    dataImport.setTaskTypeName(getColumnXlsx(taskTypeName));
                }
                if(deptMeshNameCell >= 0){
                    deptMeshName = row.getCell(deptMeshNameCell);
                    dataImport.setDeptMeshName(getColumnXlsx(deptMeshName));
                }
                if(deptAreaNameCell >= 0){
                    deptAreaName = row.getCell(deptAreaNameCell);
                    dataImport.setDeptAreaName(getColumnXlsx(deptAreaName));
                }
                if(productTypeCell >= 0){
                    productType = row.getCell(productTypeCell);
                    dataImport.setProductType(getColumnXlsx(productType));
                }
                if(phoneNumber3Cell >= 0){
                    phoneNumber3 = row.getCell(phoneNumber3Cell);
                    dataImport.setPhoneNumber3(getColumnXlsx(phoneNumber3));
                }
                if(phoneNumber2Cell >= 0){
                    phoneNumber2 = row.getCell(phoneNumber2Cell);
                    dataImport.setPhoneNumber2(getColumnXlsx(phoneNumber2));
                }
                if(phoneNumber1Cell >= 0){
                    phoneNumber1 = row.getCell(phoneNumber1Cell);
                    dataImport.setPhoneNumber1(getColumnXlsx(phoneNumber1));
                }
                if(velocityCell >= 0){
                    velocity = row.getCell(velocityCell);
                    dataImport.setVelocity(getColumnXlsx(velocity));
                }
                if(responsibleCell >= 0){
                    responsible = row.getCell(responsibleCell);
                    dataImport.setResponsible(getColumnXlsx(responsible));
                }
                if(netStopCell >= 0){
                    netStop = row.getCell(netStopCell);
                    dataImport.setNetStop(getColumnXlsx(netStop));
                }
                if(packageNameCell >= 0){
                    packageName = row.getCell(packageNameCell);
                    dataImport.setPackageName(getColumnXlsx(packageName));
                }
                if(contractNameCell >= 0){
                    contractName = row.getCell(contractNameCell);
                    dataImport.setContractName(getColumnXlsx(contractName));
                }
                if(contractStartTimeCell >= 0){
                    contractStartTime = row.getCell(contractStartTimeCell);
                    dataImport.setContractStartTime(getColumnXlsx(contractStartTime));
                }
                if(contractEndTimeCell >= 0){
                    contractEndTime = row.getCell(contractEndTimeCell);
                    dataImport.setContractEndTime(getColumnXlsx(contractEndTime));
                }
                if(activationTimeCell >= 0){
                    activationTime = row.getCell(activationTimeCell);
                    dataImport.setActivationTime(getColumnXlsx(activationTime));
                }
                if(developmentDeptCell >= 0){
                    developmentDept = row.getCell(developmentDeptCell);
                    dataImport.setDevelopmentDept(getColumnXlsx(developmentDept));
                }
                if(contactsCell >= 0){
                    contacts = row.getCell(contactsCell);
                    dataImport.setContacts(getColumnXlsx(contacts));
                }
                if(userNameCell >= 0){
                    userName = row.getCell(userNameCell);
                    dataImport.setUserName(getColumnXlsx(userName));
                }
                if(tariffNameCell >= 0){
                    tariffName = row.getCell(tariffNameCell);
                    dataImport.setTariffName(getColumnXlsx(tariffName));
                }
                if(installedAddressCell >= 0){
                    installedAddress = row.getCell(installedAddressCell);
                    dataImport.setInstalledAddress(getColumnXlsx(installedAddress));
                }
                if(statusCell >= 0){
                    status = row.getCell(statusCell);
                    dataImport.setStatus(getColumnXlsx(status));
                }
                if(ponLogoCell >= 0){
                    ponLogo = row.getCell(ponLogoCell);
                    dataImport.setPonLogo(getColumnXlsx(ponLogo));
                }
                if(customfields1Cell >= 0){
                    customfields1 = row.getCell(customfields1Cell);
                    dataImport.setCustomfields1(getColumnXlsx(customfields1));
                }
                if(customfields2Cell >= 0){
                    customfields2 = row.getCell(customfields2Cell);
                    dataImport.setCustomfields2(getColumnXlsx(customfields2));
                }
                if(customfields3Cell >= 0){
                    customfields3 = row.getCell(customfields3Cell);
                    dataImport.setCustomfields3(getColumnXlsx(customfields3));
                }
                if(customfields4Cell >= 0){
                    customfields4 = row.getCell(customfields4Cell);
                    dataImport.setCustomfields4(getColumnXlsx(customfields4));
                }
                if(customfields5Cell >= 0){
                    customfields5 = row.getCell(customfields5Cell);
                    dataImport.setCustomfields5(getColumnXlsx(customfields5));
                }
                if(customfields6Cell >= 0){
                    customfields6 = row.getCell(customfields6Cell);
                    dataImport.setCustomfields6(getColumnXlsx(customfields6));
                }
                if(customfields7Cell >= 0){
                    customfields7 = row.getCell(customfields7Cell);
                    dataImport.setCustomfields7(getColumnXlsx(customfields7));
                }
                if(customfields8Cell >= 0){
                    customfields8 = row.getCell(customfields8Cell);
                    dataImport.setCustomfields8(getColumnXlsx(customfields8));
                }
                if(customfields9Cell >= 0){
                    customfields9 = row.getCell(customfields9Cell);
                    dataImport.setCustomfields9(getColumnXlsx(customfields9));
                }
                if(customfields10Cell >= 0){
                    customfields10 = row.getCell(customfields10Cell);
                    dataImport.setCustomfields10(getColumnXlsx(customfields10));
                }
                if(customfields11Cell >= 0){
                    customfields11 = row.getCell(customfields11Cell);
                    dataImport.setCustomfields11(getColumnXlsx(customfields11));
                }
                if(customfields12Cell >= 0){
                    customfields12 = row.getCell(customfields12Cell);
                    dataImport.setCustomfields12(getColumnXlsx(customfields12));
                }
                if(customfields13Cell >= 0){
                    customfields13 = row.getCell(customfields13Cell);
                    dataImport.setCustomfields13(getColumnXlsx(customfields13));
                }
                if(customfields14Cell >= 0){
                    customfields14 = row.getCell(customfields14Cell);
                    dataImport.setCustomfields14(getColumnXlsx(customfields14));
                }
                if(customfields15Cell >= 0){
                    customfields15 = row.getCell(customfields15Cell);
                    dataImport.setCustomfields15(getColumnXlsx(customfields15));
                }
                dataImport.setImportBatch(eventId);
                dataImport.setImportPersonId(userID);
                dataImport.setImportTime(importTime);

                String uuid = generator.generate();
                dataImport.setDataImportId(uuid);

                list.add(dataImport);
            }
			readExcelXlsxMap.put("list",list);
			readExcelXlsxMap.put("code","0");
			readExcelXlsxMap.put("msg","上传成功！");
			return readExcelXlsxMap;
		} catch (Exception e) {
			e.printStackTrace();
			return readExcelXlsxMap;
		}
	}
	
	public static String createRandomCode(int ln) {
		String pwd = "";
		for (int i = 0; i != ln; i++) {
			pwd += (int) (Math.random() * 1000);
		}
		return pwd;
	}
	
	/****
	 * 列编码--字段名 对照
	 * add by wangxiaoyu
	 */
	public static String getCellName(int cellCode) {
		String cellName = "";
		
		switch (cellCode) {
		case 1:
			cellName = DataImport.TASKTYPENAME;
			break;
		case 2:
			cellName = DataImport.DEPTMESHNAME;
			break;
		case 3:
			cellName = DataImport.DEPTAREANAME;
			break;
		case 4:
			cellName = DataImport.BUSINESSCODE;
			break;
		case 5:
			cellName = DataImport.USERNAME;
			break;
		case 6:
			cellName = DataImport.PRODUCTTYPE;
			break;
		case 7:
			cellName = DataImport.TARIFFNAME;
			break;
		case 8:
			cellName = DataImport.INSTALLEDADDRESS;
			break;
		case 9:
			cellName = DataImport.STATUS;
			break;
		case 10:
			cellName = DataImport.CONTACTS;
			break;
		case 11:
			cellName = DataImport.PHONENUMBER1;
			break;
		case 12:
			cellName = DataImport.PHONENUMBER2;
			break;
		case 13:
			cellName = DataImport.PHONENUMBER3;
			break;
		case 14:
			cellName = DataImport.PONLOGO;
			break;
		case 15:
			cellName = DataImport.VELOCITY;
			break;
		case 16:
			cellName = DataImport.CUSTOMFIELDS1;
			break;
		case 17:
			cellName = DataImport.CUSTOMFIELDS2;
			break;
		case 18:
			cellName = DataImport.CUSTOMFIELDS3;
			break;
		case 19:
			cellName = DataImport.CUSTOMFIELDS4;
			break;
		case 20:
			cellName = DataImport.CUSTOMFIELDS5;
			break;
		case 21:
			cellName = DataImport.CUSTOMFIELDS6;
			break;
		case 22:
			cellName = DataImport.CUSTOMFIELDS7;
			break;
		case 23:
			cellName = DataImport.CUSTOMFIELDS8;
			break;
		case 24:
			cellName = DataImport.CUSTOMFIELDS9;
			break;
		case 25:
			cellName = DataImport.CUSTOMFIELDS10;
			break;
		case 26:
			cellName = DataImport.CUSTOMFIELDS11;
			break;
		case 27:
			cellName = DataImport.CUSTOMFIELDS12;
			break;
		case 28:
			cellName = DataImport.CUSTOMFIELDS13;
			break;
		case 29:
			cellName = DataImport.CUSTOMFIELDS14;
			break;
		case 30:
			cellName = DataImport.CUSTOMFIELDS15;
			break;
		case 31:
			cellName = DataImport.RESPONSIBLE;
			break;
		case 32:
			cellName = DataImport.NETSTOP;
			break;
		case 33:
			cellName = DataImport.PACKAGENAME;
			break;
		case 34:
			cellName = DataImport.CONTRACTNAME;
			break;
		case 35:
			cellName = DataImport.CONTRACTSTARTTIME;
			break;
		case 36:
			cellName = DataImport.CONTRACTENDTIME;
			break;
		case 37:
			cellName = DataImport.ACTIVATIONTIME;
			break;
		case 38:
			cellName = DataImport.DEVELOPMENTDEPT;
			break;

		default:
			break;
		}
		return cellName;
	}
	
	/****
	 * 获取列编码
	 * add by wangxiaoyu
	 */
	public static void getColumnCode(HashMap<String, Integer> importMap){
		
		businessCodeCell = importMap.get(DataImport.BUSINESSCODE) == null ? -1 : importMap.get(DataImport.BUSINESSCODE);                                                   
		taskTypeNameCell = importMap.get(DataImport.TASKTYPENAME) == null ? -1 : importMap.get(DataImport.TASKTYPENAME);                                                   
		deptMeshNameCell = importMap.get(DataImport.DEPTMESHNAME) == null ? -1 : importMap.get(DataImport.DEPTMESHNAME);                                                   
		deptAreaNameCell = importMap.get(DataImport.DEPTAREANAME) == null ? -1 : importMap.get(DataImport.DEPTAREANAME);                                                   
		productTypeCell = importMap.get(DataImport.PRODUCTTYPE) == null ? -1 : importMap.get(DataImport.PRODUCTTYPE);                                                      
		phoneNumber3Cell = importMap.get(DataImport.PHONENUMBER3) == null ? -1 : importMap.get(DataImport.PHONENUMBER3);                                                   
		velocityCell = importMap.get(DataImport.VELOCITY) == null ? -1 : importMap.get(DataImport.VELOCITY);                                                               
		customfields1Cell = importMap.get(DataImport.CUSTOMFIELDS1) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS1);                                                
		customfields2Cell = importMap.get(DataImport.CUSTOMFIELDS2) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS2);                                                
		customfields3Cell = importMap.get(DataImport.CUSTOMFIELDS3) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS3);                                                
		customfields4Cell = importMap.get(DataImport.CUSTOMFIELDS4) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS4);                                                
		customfields5Cell = importMap.get(DataImport.CUSTOMFIELDS5) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS5);                                                
		customfields6Cell = importMap.get(DataImport.CUSTOMFIELDS6) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS6);                                                
		customfields7Cell = importMap.get(DataImport.CUSTOMFIELDS7) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS7);                                                
		customfields8Cell = importMap.get(DataImport.CUSTOMFIELDS8) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS8);                                                
		customfields9Cell = importMap.get(DataImport.CUSTOMFIELDS9) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS9);                                                
		customfields10Cell = importMap.get(DataImport.CUSTOMFIELDS10) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS10);                                             
		customfields11Cell = importMap.get(DataImport.CUSTOMFIELDS11) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS11);                                             
		customfields12Cell = importMap.get(DataImport.CUSTOMFIELDS12) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS12);                                             
		customfields13Cell = importMap.get(DataImport.CUSTOMFIELDS13) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS13);                                             
		customfields14Cell = importMap.get(DataImport.CUSTOMFIELDS14) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS14);                                             
		customfields15Cell = importMap.get(DataImport.CUSTOMFIELDS15) == null ? -1 : importMap.get(DataImport.CUSTOMFIELDS15);                                             
		responsibleCell = importMap.get(DataImport.RESPONSIBLE) == null ? -1 : importMap.get(DataImport.RESPONSIBLE);                                                      
		netStopCell = importMap.get(DataImport.NETSTOP) == null ? -1 : importMap.get(DataImport.NETSTOP);                                                                  
		packageNameCell = importMap.get(DataImport.PACKAGENAME) == null ? -1 : importMap.get(DataImport.PACKAGENAME);                                                      
		contractNameCell = importMap.get(DataImport.CONTRACTNAME) == null ? -1 : importMap.get(DataImport.CONTRACTNAME);                                                   
		contractStartTimeCell = importMap.get(DataImport.CONTRACTSTARTTIME) == null ? -1 : importMap.get(DataImport.CONTRACTSTARTTIME);                                    
		contractEndTimeCell = importMap.get(DataImport.CONTRACTENDTIME) == null ? -1 : importMap.get(DataImport.CONTRACTENDTIME);                                          
		activationTimeCell = importMap.get(DataImport.ACTIVATIONTIME) == null ? -1 : importMap.get(DataImport.ACTIVATIONTIME);                                             
		developmentDeptCell = importMap.get(DataImport.DEVELOPMENTDEPT) == null ? -1 : importMap.get(DataImport.DEVELOPMENTDEPT);                                          
		phoneNumber1Cell = importMap.get(DataImport.PHONENUMBER1) == null ? -1 : importMap.get(DataImport.PHONENUMBER1);                                                   
		phoneNumber2Cell = importMap.get(DataImport.PHONENUMBER2) == null ? -1 : importMap.get(DataImport.PHONENUMBER2);                                                   
		contactsCell = importMap.get(DataImport.CONTACTS) == null ? -1 : importMap.get(DataImport.CONTACTS);                                                               
		userNameCell = importMap.get(DataImport.USERNAME) == null ? -1 : importMap.get(DataImport.USERNAME);                                                               
		tariffNameCell = importMap.get(DataImport.TARIFFNAME) == null ? -1 : importMap.get(DataImport.TARIFFNAME);                                                         
		installedAddressCell = importMap.get(DataImport.INSTALLEDADDRESS) == null ? -1 : importMap.get(DataImport.INSTALLEDADDRESS);                                       
		statusCell = importMap.get(DataImport.STATUS) == null ? -1 : importMap.get(DataImport.STATUS);                                                          
		ponLogoCell = importMap.get(DataImport.PONLOGO) == null ? -1 : importMap.get(DataImport.PONLOGO);          
	}
	
	/****
	 * 获取字符串编码格式
	 * add by wangxiaoyu
	 */
	public static String getEncoding(String str) {
		String encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode)))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode)))) {
				String s = encode;
				return s;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode)))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode)))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}
	
	public static String getColumnXlsx(XSSFCell colu) throws UnsupportedEncodingException  {
		if(colu != null){
			colu.setCellType(Cell.CELL_TYPE_STRING);
		}
		return colu == null ? "" : colu.getStringCellValue().trim();
	}
	
	public static String getColumnXls(HSSFCell colu) throws UnsupportedEncodingException  {
		if(colu != null){
			colu.setCellType(Cell.CELL_TYPE_STRING);
		}
		return colu == null ? "" : colu.getStringCellValue().trim();
	}
	
	public static String gbToUtf8(String str) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			String s = str.substring(i, i + 1);
			if (s.charAt(0) > 0x80) {
				byte[] bytes = s.getBytes("Unicode");
				String binaryStr = "";
				for (int j = 2; j < bytes.length; j += 2) {
					// the first byte
					String hexStr = getHexString(bytes[j + 1]);
					String binStr = getBinaryString(Integer.valueOf(hexStr, 16));
					binaryStr += binStr;
					// the second byte
					hexStr = getHexString(bytes[j]);
					binStr = getBinaryString(Integer.valueOf(hexStr, 16));
					binaryStr += binStr;
				}
				// convert unicode to utf-8
				String s1 = "1110" + binaryStr.substring(0, 4);
				String s2 = "10" + binaryStr.substring(4, 10);
				String s3 = "10" + binaryStr.substring(10, 16);
				byte[] bs = new byte[3];
				bs[0] = Integer.valueOf(s1, 2).byteValue();
				bs[1] = Integer.valueOf(s2, 2).byteValue();
				bs[2] = Integer.valueOf(s3, 2).byteValue();
				String ss = new String(bs, "UTF-8");
				sb.append(ss);
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}
	
	private static String getHexString(byte b) {   
        String hexStr = Integer.toHexString(b);   
        int m = hexStr.length();   
        if (m < 2) {   
            hexStr = "0" + hexStr;   
        } else {   
            hexStr = hexStr.substring(m - 2);   
        }   
        return hexStr;   
    }   
  
    private static String getBinaryString(int i) {   
        String binaryStr = Integer.toBinaryString(i);   
        int length = binaryStr.length();   
        for (int l = 0; l < 8 - length; l++) {   
            binaryStr = "0" + binaryStr;   
        }   
        return binaryStr;   
    }
}

