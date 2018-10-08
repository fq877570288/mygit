package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.ImportBatch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.service.AllocationTaskService;
import cn.cucsi.bsd.ucc.service.ImportBatchService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api(tags={"任务分配、数据调拨操作接口"})
@RestController
@RequestMapping(value = "/taskDataControl")
public class AllocationTaskController {
	
	private static Logger logger = Logger.getLogger(AllocationTaskController.class);

	@Autowired
	private AllocationTaskService allocationTaskService;
	@Autowired
	private ImportBatchService importBatchervice;
	
	/***
	 * 分配任务列表查询
	 * add by wangxiaoyu
	 * 2018-09-21
	 */
	@ApiOperation(value="分配任务列表查询", notes="分配任务列表查询")
	@RequestMapping(value = "/allocationTaskList", method= RequestMethod.POST)
	public PageResultBean_New<List<ImportBatch>> allocationTaskList(@RequestBody AllocationTaskCriteria allocationTaskCriteria){

		Page pageInfo = PageHelper.startPage(allocationTaskCriteria.getPageNum(), allocationTaskCriteria.getPageSize());
		List<ImportBatch> importBatchlist = null;

		String userId = allocationTaskCriteria.getUserId()==null?"":allocationTaskCriteria.getUserId();
		ImportBatch importBatch = new ImportBatch();
		importBatch.setImportPersonId(userId);
		importBatch.setBatchFlag("1");
		PageResultBean_New<List<ImportBatch>> pageResultBean_new = null;
		try {
			importBatchlist = importBatchervice.selectAllocationAllByBatchFlag(importBatch,allocationTaskCriteria);
			System.out.println("importBatchlist:::" + importBatchlist.size());
			//model.addAttribute("importBatchlist", importBatchlist);
			pageResultBean_new = new PageResultBean_New(pageInfo);
			pageResultBean_new.setList(importBatchlist);
			pageResultBean_new.setReturnMsg("查询成功！");
			pageResultBean_new.setReturnCode(PageResultBean_New.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			pageResultBean_new.setReturnMsg("查询失败！");
			pageResultBean_new.setReturnCode(PageResultBean_New.FAIL);
		}
		return pageResultBean_new;
	}

	/***
	 * 分派任务
	 * add by wangxiaoyu
	 * 2018-09-21
	 */
	@ApiOperation(value="分派任务", notes="分派任务")
	@RequestMapping(value = "/allocationTask",method = RequestMethod.POST)
	public ResultBean_New<String> allocationTask(@RequestBody DoAllocationTaskCriteria doAllocationTaskCriteria) {

		String alloc = doAllocationTaskCriteria.getAlloc()==null?"":doAllocationTaskCriteria.getAlloc();
		String userId = doAllocationTaskCriteria.getUserId()==null?"":doAllocationTaskCriteria.getUserId();
		String barchs = doAllocationTaskCriteria.getBarchs()==null?"":doAllocationTaskCriteria.getBarchs();
		String endDate = doAllocationTaskCriteria.getEndDate()==null?"":doAllocationTaskCriteria.getEndDate();

		ResultBean_New<String> resultBean = new ResultBean_New<>();
		//初始化赋值
		resultBean.setReturnMsg("任务分派失败！");
		resultBean.setReturnCode(ResultBean_New.FAIL);
		Map<String,Object> allocationTaskMap = new HashMap<String,Object>();
		try {
			allocationTaskMap = allocationTaskService.allocationTask(userId, alloc, barchs, endDate);
			if(allocationTaskMap.get("code").equals("-1")){
				resultBean.setReturnMsg((String)allocationTaskMap.get("msg"));
				return resultBean;
			}
			resultBean.setReturnMsg("任务分派完成！");
			resultBean.setReturnCode(ResultBean_New.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return resultBean;
	}

	/***
	 * 数据调拨页面(条件搜索查询)
	 * add by wangxiaoyu
	 * 2018-09-21
	 */
	@ApiOperation(value="数据调拨--条件搜索查询", notes="数据调拨--条件搜索查询")
	@RequestMapping(value = "/editDeptList",method = RequestMethod.POST)
	public Map<String,Object> editDeptList(@RequestBody TaskDetailSearch taskDetailSearch,HttpSession session){

		Map<String,Object> editDeptListMap = new HashMap<String,Object>();
		editDeptListMap.put("msg","操作失败！");
		editDeptListMap.put("code","-1");

		List<TaskDetail> list = null;
		List<String> taskDetailIdList = null;
		try {
			session.setAttribute("taskDetailIdListForEditDeptList", null);
			//search.setUserId(Auth.getLoginUser(session).getId());
			String deptIdAndChildId = (String)session.getAttribute("DeptIdAndChildIds");
			//此处为临时加的
			/*if(MyUtils.isBlank(deptIdAndChildId)){
				deptIdAndChildId = "'40287d8165fb1e530165fb1e53900001','40287d8165fb1e530165fb2870120010'";
			}*/

			//String deptIdAndChildId = taskDetailSearch.getDeptIdAndChildIds()==null?"":taskDetailSearch.getDeptIdAndChildIds();
			if(deptIdAndChildId != null && !"".equals(deptIdAndChildId)){
				String [] deptIdAndChildIds = deptIdAndChildId.split(",");
				if(deptIdAndChildIds.length > 1){
					list = allocationTaskService.selectAllocationBySearch(taskDetailSearch);
					taskDetailIdList = this.allocationTaskService.selectTaskDetailIdBySearch(taskDetailSearch);
					System.out.println("数据调拨页面(条件搜索查询) taskDetailIdList:::" + taskDetailIdList);
					session.setAttribute("taskDetailIdListForEditDeptList", taskDetailIdList);
					//editDeptListMap.put("taskDetailIdListForEditDeptList", taskDetailIdList);
				}else {
					session.setAttribute("taskDetailIdListForEditDeptList", null);
					taskDetailSearch.setAllLines(0);
				}
			}else{
				session.setAttribute("taskDetailIdListForEditDeptList", null);
			}
			//model.addAttribute("list", list);
			editDeptListMap.put("list", list);
			editDeptListMap.put("taskNumberStart", taskDetailSearch.getTaskNumberStart());
			editDeptListMap.put("taskNumberEnd", taskDetailSearch.getTaskNumberEnd());
			editDeptListMap.put("userId", taskDetailSearch.getUserId());
			editDeptListMap.put("taskDetailSearch",taskDetailSearch);
			editDeptListMap.put("msg","操作成功！");
			editDeptListMap.put("code","0");

			return editDeptListMap;
		} catch (Exception e) {
			session.setAttribute("taskDetailIdListForEditDeptList", null);
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return editDeptListMap;
		}
	}
	
	/***
	 * 数据调拨--任务组织机构修改
	 * add by wangxiaoyu
	 * 2018-09-21
	 */
	@ApiOperation(value="数据调拨--任务组织机构修改", notes="数据调拨--任务组织机构修改")
	@RequestMapping(value = "/editTaskDept",method = RequestMethod.POST)
	public Map<String,Object> editTaskDept(@RequestBody AllocationSearch allocationSearch,HttpSession session) {

		Map<String,Object> editDeptListMap = new HashMap<String,Object>();
		editDeptListMap.put("msg","操作失败！");
		editDeptListMap.put("code","-1");

		String meshID = allocationSearch.getMeshID()==null?"":allocationSearch.getMeshID();
		String areaID = allocationSearch.getAreaID()==null?"":allocationSearch.getAreaID();
		String developmentID = allocationSearch.getDevelopmentID()==null?"":allocationSearch.getDevelopmentID();
		String taskDetailIds = allocationSearch.getTaskDetailIds()==null?"":allocationSearch.getTaskDetailIds();
		Integer taskNumberStart = allocationSearch.getTaskNumberStart()==null?0:allocationSearch.getTaskNumberStart();
		Integer taskNumberEnd = allocationSearch.getTaskNumberEnd()==null?0:allocationSearch.getTaskNumberEnd();
		//List<String> idList = allocationSearch.getTaskDetailIdListForEditDeptList();

		/*ObjectMapper mapper = new ObjectMapper();
		String json = null;
		String message = "数据调拨失败！";*/
		try {
			if(taskDetailIds==null || taskDetailIds.isEmpty()){
				List<String> idList = (List<String>)session.getAttribute("taskDetailIdListForEditDeptList");
				//临时测试用
				/*if(MyUtils.isBlank(idList)){
					idList.add("40287d8165fb1e530165fb226ce3000d");
					idList.add("40287d8165fb1e530165fb226ce3000b");
					idList.add("40287d8165fb1e530165fb2870120013");
					idList.add("40287d8165fb1e530165fb287012000f");
					idList.add("2");
				}*/
				if(!MyUtils.isBlank(idList)){
					Integer _taskNumberStart = 1;
					Integer _taskNumberEnd = idList.size();
					if(taskNumberStart != null && taskNumberStart >= 1){
						_taskNumberStart = taskNumberStart;
					}
					if(taskNumberEnd != null && taskNumberEnd >= 1 && taskNumberEnd < _taskNumberEnd){
						_taskNumberEnd = taskNumberEnd;
					}
					String idsStr = Joiner.on(",").join(idList.subList(_taskNumberStart-1, _taskNumberEnd));
					if(!idsStr.isEmpty()){
						allocationTaskService.editTaskDept(meshID, areaID, developmentID, idsStr);
					}
				}
			}else{
				allocationTaskService.editTaskDept(meshID, areaID, developmentID, taskDetailIds);
			}
			session.setAttribute("taskDetailIdListForEditDeptList", null);

			//message = "数据调拨完成！";
			//json = mapper.writeValueAsString(message);
			//model.addAttribute("taskNumberStart", taskNumberStart);
			//model.addAttribute("taskNumberEnd", taskNumberEnd);

			editDeptListMap.put("taskNumberStart", taskNumberStart);
			editDeptListMap.put("taskNumberEnd", taskNumberEnd);
			editDeptListMap.put("msg", "数据调拨完成！");
			editDeptListMap.put("code","0");
		} catch (Exception e) {
			session.setAttribute("taskDetailIdListForEditDeptList", null);
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return editDeptListMap;
	}
}
