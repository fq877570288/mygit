package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.AutoSearchTaskCriteria;
import cn.cucsi.bsd.ucc.common.beans.CreateTaskCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.DoCreateTaskCriteria;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.ImportBatch;
import cn.cucsi.bsd.ucc.data.domain.Task;
import cn.cucsi.bsd.ucc.service.CreateTaskService;
import cn.cucsi.bsd.ucc.service.ImportBatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"任务生成操作接口"})
@RestController
@RequestMapping(value = "/createTaskControl")
public class CreateTaskController {

	private static Logger logger = Logger.getLogger(CreateTaskController.class);
	@Autowired
	private ImportBatchService importBatchervice;
	
	@Autowired
	private CreateTaskService createTaskService;

	/***
	 * 生成任务 导入数据批次列表(列表展示用)
	 * add by wangxiaoyu
	 * 2018-09-13
	 */
	@ApiOperation(value="生成任务", notes="生成任务")
	@RequestMapping(value = "/createTaskList", method= RequestMethod.POST)
	public PageResultBean_New<List<ImportBatch>> createTaskList(@RequestBody CreateTaskCriteria createTaskCriteria){

		Page pageInfo = PageHelper.startPage(createTaskCriteria.getPageNum(), createTaskCriteria.getPageSize());
		List<ImportBatch> importBatchlist = null;
		
		ImportBatch importBatch = new ImportBatch();
		String userId = createTaskCriteria.getUserId()==null?"":createTaskCriteria.getUserId();
		importBatch.setImportPersonId(userId);
		importBatch.setBatchFlag("0");
		try {
			importBatchlist = importBatchervice.selectAllByBatchFlag(importBatch);
			//model.addAttribute("list", list);
			PageResultBean_New<List<ImportBatch>> pageResultBean_new = new PageResultBean_New(pageInfo);
			pageResultBean_new.setList(importBatchlist);
			return pageResultBean_new;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/***
	 * 创建任务
	 * add by wangxiaoyu
	 * 2018-09-14
	 */
	@ApiOperation(value="创建任务", notes="创建任务")
	@RequestMapping(value="/doCreateTask",method= RequestMethod.POST)
	public String customfieldsSave(@RequestBody DoCreateTaskCriteria doCreateTaskCriteria) {

		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		String message = "创建任务失败！";

		String createMode = doCreateTaskCriteria.getCreateMode()==null?"":doCreateTaskCriteria.getCreateMode();
		String barchs = doCreateTaskCriteria.getBarchs()==null?"":doCreateTaskCriteria.getBarchs();
		String userId = doCreateTaskCriteria.getUserId()==null?"":doCreateTaskCriteria.getUserId();
		String oldTaskBatch = doCreateTaskCriteria.getOldTaskBatch()==null?"":doCreateTaskCriteria.getOldTaskBatch();
		try {
			if(MyUtils.isBlank(createMode)||MyUtils.isBlank(barchs)||MyUtils.isBlank(userId)||MyUtils.isBlank(oldTaskBatch)){
				message = "入参不能为空！";
				json = mapper.writeValueAsString(message);
				return json;
			}
			if(Task.CREATENEW.equals(createMode)){
				// 新建任务
				createTaskService.createNewTask(barchs, userId, null);
			}else if(Task.CREATEOLD.equals(createMode)){
				//替换现有任务
				createTaskService.createOldTask(barchs, userId, oldTaskBatch);
			}
			message = "任务生成完成，请到[分派任务]页面进行任务分派操作！";
			json = mapper.writeValueAsString(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			System.out.println("生成任务时发生异常！");
		}
		return json;
	}
	
	/****
	 * 搜索可替换批次-补全
	 * add by wangxiaoyu
	 * 2018-09-13
	 */
	@ApiOperation(value="搜索可替换批次-补全", notes="搜索可替换批次-补全")
	@RequestMapping(value="/autoSearchTaskBatch",method= RequestMethod.POST)
	public String autoSearchTaskBatch(@RequestBody AutoSearchTaskCriteria autoSearchTaskCriteria) {
		
		List<String> strList = new ArrayList<String>();
		String xmlStr = "";
		String word = autoSearchTaskCriteria.getWord()==null?"":autoSearchTaskCriteria.getWord();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", autoSearchTaskCriteria.getUserId());
		map.put("batchFlag", ImportBatch.BATCHFLAGY); // 已生产任务数据
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
}
