package cn.cucsi.bsd.ucc.common.mapper;

import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.TaskRecordSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskRecordMapper {

	int insert(TaskRecord record);

	List<TaskRecord> selectAll(TaskRecordSearch search);

	int selectBySearchCount(TaskRecordSearch search);

	int deleteByTime(String recordTime);

	List<TaskRecord> selectBySearch(TaskRecordSearch search);

	TaskRecord selectById(String taskRecordId);

	/****
	 * 批量插入
	 */
	int insertGroup(Map<String, Object> map) throws Exception;
	
	/****
	 * 分页查询
	 */
	int selectAllCount(TaskRecordSearch search) throws Exception;

	/****
	 * 按时间删除归档流转表
	 */
	int deleteTaskRecordTransferByTime(String time) throws Exception;
	
}