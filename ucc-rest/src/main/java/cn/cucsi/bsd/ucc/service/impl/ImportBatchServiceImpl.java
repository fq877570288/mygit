package cn.cucsi.bsd.ucc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import cn.cucsi.bsd.ucc.common.mapper.ImportBatchMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskDetailMapper;
import cn.cucsi.bsd.ucc.data.domain.ImportBatch;
import cn.cucsi.bsd.ucc.service.ImportBatchService;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportBatchServiceImpl implements ImportBatchService {

	@Autowired
	private ImportBatchMapper importBatchMapper;
	@Autowired
	private TaskDetailMapper taskDetailMapper;
	@Autowired
	private UccDeptsService uccDeptsService;

	@Override
	public String queryImportBatch(String taskTypeName, String recent) throws Exception {
		Map<String, Object> condition = new HashMap<>();
//		condition.put("batchFlag", "2");
		condition.put("taskTypeName", taskTypeName);
		condition.put("recent", recent);
		List<ImportBatch> importBatchList = importBatchMapper.selectBySearch(condition);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(importBatchList);
		return json;
	}

	@Override
	public List<String> selectRecentImportBatch() throws Exception{

//		Map<String, Object> barchsMap = new HashMap<>();
//		barchsMap.put("batchFlag", "2");
		return this.importBatchMapper.selectForTaskJob();
	}

	@Override
	public List<ImportBatch> selectBySearch(Map<String, Object> barchsMap) throws Exception{
		return this.importBatchMapper.selectBySearch(barchsMap);
	}

	@Override
	public List<ImportBatch> selectAll(ImportBatch importBatch) throws Exception {
		return importBatchMapper.selectAll(importBatch);
	}

	@Override
	public List<ImportBatch> selectAllByBatchFlag(ImportBatch importBatch) throws Exception {
		return importBatchMapper.selectAllByBatchFlag(importBatch);
	}

	@Override
	public List<ImportBatch> selectAllocationAllByBatchFlag(ImportBatch importBatch, HttpSession session)
			throws Exception {

		/*TaskDetailSearch search = new TaskDetailSearch();
		List<TaskDetail> taskDetailList = null;

		search.setUserId(Auth.getLoginUser(session).getId());
		String deptIdAndChildId = (String)session.getAttribute("DeptIdAndChildIds");
		if(deptIdAndChildId != null && !"".equals(deptIdAndChildId)){
			String [] deptIdAndChildIds = deptIdAndChildId.split(",");
			if(deptIdAndChildIds.length > 1){
				// 部门
				List<UccDepts> UccDeptsList = uccDeptsService.selectByUserId(search.getUserId());
				// 增加权限控制  登录人是中心权限 查询流转操作为0的数据  登录人是网格或包区权限查询流转操作为1的数据
				if(UccDeptsList != null && UccDeptsList.size() > 0){
					if(UccDeptsList.get(0).getDeptLevel() <= 1){
						search.setTaskStatus("0");
						search.setImportPersonId(search.getUserId().toString());
					}else {
						search.setTaskStatus("1");
					}
				}
				search.setRoperateDeptId(UccDeptsList.get(0).getDeptId().toString());
				taskDetailList = taskDetailMapper.selectImportBatchsBySearch(search);
			}else {
				search.setAllLines(0);
			}
		}
		String importBatchs = "";
		if(taskDetailList != null && taskDetailList.size() > 0){
			for(TaskDetail taskDetail : taskDetailList){
				importBatchs += taskDetail.getImportBatch() + "','";
			}
		}
		if(!"".equals(importBatchs)){
			importBatchs = "'" + importBatchs.substring(0, importBatchs.lastIndexOf(","));
			importBatch.setImportBatchs(importBatchs);
		}*/
		return importBatchMapper.selectAllByBatchFlag(importBatch);
	}

}