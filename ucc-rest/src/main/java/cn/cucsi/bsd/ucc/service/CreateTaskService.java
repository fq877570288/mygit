package cn.cucsi.bsd.ucc.service;

import java.util.List;
import java.util.Map;

public interface CreateTaskService {
	
	/***
	 * 新建任务
	 */
	Map<String,Object> createNewTask(String barchs, String userId, String oldTaskBatch) throws Exception;
    
    /***
	 * 替换现有任务
	 */
	List<String> autoSearchTaskBatch(Map<String, Object> map) throws Exception;

	Map<String,Object> createOldTask(String barchs, String userId, String oldTaskBatch) throws Exception;

	/***
	 * 查询所有批次号
	 */
	List<String> selectAllImportBatch() throws Exception;
}
