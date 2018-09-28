package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.TaskRecordSearch;
import cn.cucsi.bsd.ucc.common.mapper.TaskRecordMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskTransferRecordMapper;
import cn.cucsi.bsd.ucc.data.domain.TaskRecord;
import cn.cucsi.bsd.ucc.data.domain.TaskTransferRecord;
import cn.cucsi.bsd.ucc.service.TaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/****
 * 数据导出业务层 （copy from 哈分项目）
 * add by wangxiaoyu
 * 2018-09-28
 */
@Service
public class TaskRecordServiceImpl implements TaskRecordService {

	@Autowired
	private TaskRecordMapper taskRecordMapper;
	
	@Autowired
	private TaskTransferRecordMapper taskTransferRecordMapper;

	@Override
	public int insert(TaskRecord record) throws Exception {
		// TODO Auto-generated method stub
		return taskRecordMapper.insert(record);
	}

	@Override
	public List<TaskRecord> selectAll(TaskRecordSearch search) throws Exception {
		// TODO Auto-generated method stub
		return taskRecordMapper.selectAll(search);
	}

	@Override
	public int selectBySearchCount(TaskRecordSearch search) throws Exception {
		// TODO Auto-generated method stub
		return taskRecordMapper.selectBySearchCount(search);
	}

	@Override
	public int deleteByTime(String time) throws Exception {
		// TODO Auto-generated method stub
		return taskRecordMapper.deleteByTime(time);
	}

	@Override
	public List<TaskRecord> selectBySearch(TaskRecordSearch search) throws Exception {
		// TODO Auto-generated method stub
		return taskRecordMapper.selectBySearch(search);
	}

	@Override
	public TaskRecord selectById(String taskRecordId) throws Exception {
		// TODO Auto-generated method stub
		return taskRecordMapper.selectById(taskRecordId);
	}

	@Override
	public int selectAllCount(TaskRecordSearch search) throws Exception {
		return taskRecordMapper.selectAllCount(search);
	}

	@Override
	public int deleteTaskRecordTransferByTime(String time) throws Exception {
		return taskRecordMapper.deleteTaskRecordTransferByTime(time);
	}

	@Override
	public List<TaskTransferRecord> selectTransferRecordByTaskRecordId(String taskRecordId) throws Exception {
		return taskTransferRecordMapper.selectTransferRecordByTaskRecordId(taskRecordId);
	}

}
