package cn.cucsi.bsd.ucc.service;

import java.util.List;
import java.util.Map;

public interface CreateTaskService {
	
	/***
	 * 新建任务
	 */
    void createNewTask(String barchs, String userId, String oldTaskBatch) throws Exception;
    
    /***
	 * 替换现有任务
	 */
    void createOldTask(String barchs, String userId, String oldTaskBatch) throws Exception;

	List<String> autoSearchTaskBatch(Map<String, Object> map) throws Exception;
}
