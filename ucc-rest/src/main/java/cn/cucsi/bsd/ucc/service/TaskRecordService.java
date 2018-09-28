package cn.cucsi.bsd.ucc.service;

import java.util.List;
import cn.cucsi.bsd.ucc.common.beans.TaskRecordSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskRecord;
import cn.cucsi.bsd.ucc.data.domain.TaskTransferRecord;

/****
 * 数据导出业务层 （copy from 哈分项目）
 * add by wangxiaoyu
 * 2018-09-28
 */
public interface TaskRecordService {
	
	int insert(TaskRecord record) throws Exception;

	List<TaskRecord> selectAll(TaskRecordSearch search) throws Exception;

	int selectBySearchCount(TaskRecordSearch search) throws Exception;
	
	int deleteByTime(String time)throws Exception;
	
    List<TaskRecord> selectBySearch(TaskRecordSearch search)throws Exception;
    
    TaskRecord selectById(String taskRecordId)throws Exception;
    
    /****
	 * 分页查询
	 */
	int selectAllCount(TaskRecordSearch search) throws Exception;
	
	/****
	 * 按时间删除归档流转表
	 */
	int deleteTaskRecordTransferByTime(String time)throws Exception;

	/****
	 * 查询任务流转
	 */
	List<TaskTransferRecord> selectTransferRecordByTaskRecordId(String taskRecordId) throws Exception;
    
}
