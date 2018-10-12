package cn.cucsi.bsd.ucc.service.impl;

import java.util.List;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.common.mapper.TaskDetailMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskTransferMapper;
import cn.cucsi.bsd.ucc.data.domain.Paging;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.service.MonitorTaskService;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MonitorTaskServiceImpl implements MonitorTaskService {
	
	@Autowired
	private TaskDetailMapper taskDetailMapper;
	@Autowired
	private UccDeptsService uccDeptsService;
	@Autowired
	private TaskTransferMapper taskTransferMapper;

	@Override
	@Transactional
	public List<TaskDetail> selectMonitorBySearch(TaskDetailSearch search) throws Exception {
		try {
			// 部门
			List<UccDepts> uccDeptsList = uccDeptsService.selectByUserId(search.getUserId());
			//search.setRoperateDeptId(uccDeptsList.get(0).getDeptId());
			//search.setDeptLevel(uccDeptsList.get(0).getDeptLevel().toString());
			// 分页查询
			search.setup(taskDetailMapper.selectMonitorBySearchSearchCount(search),search.getShowLines());
			return taskDetailMapper.selectMonitorBySearch(search);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TaskTransfer> selectTransferByTaskDetailId(String taskDetailId) throws Exception {
		return taskTransferMapper.selectTransferByTaskDetailId(taskDetailId);
	}

	@Override
	public List<String> selectTaskDetailIdByMonitorSearch(TaskDetailSearch search) throws Exception {
		return taskDetailMapper.selectTaskDetailIdByMonitorSearch(search);
	}

}
