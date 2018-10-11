package cn.cucsi.bsd.ucc.service.impl;

import java.sql.Timestamp;
import java.util.*;

import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.common.mapper.TaskDetailMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskTransferMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import cn.cucsi.bsd.ucc.service.UccUserService;
import cn.cucsi.bsd.ucc.service.WaitTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WaitTaskServiceImpl implements WaitTaskService {
	
	@Autowired
	private TaskDetailMapper taskDetailMapper;
	@Autowired
	private UccDeptsService uccDeptsService;
	@Autowired
	private TaskTransferMapper taskTransferMapper;
	@Autowired
	private UccUserService uccUserService;

	@Override
	@Transactional
	public List<TaskDetail> selectWaitBySearch(TaskDetailSearch taskDetailSearch) throws Exception {

		//String userId = taskDetailSearch.getUserId()==null?"":taskDetailSearch.getUserId();
		try {
			// 部门
			//List<UccDepts> uccDeptsList = uccDeptsService.selectByUserId(userId);
			//if(!MyUtils.isBlank(uccDeptsList)){
				//taskDetailSearch.setRoperateDeptId(uccDeptsList.get(0).getDeptId().toString());
				//System.out.println("部门id:::" + uccDeptsList.get(0).getDeptId().toString());
				taskDetailSearch.setTaskStatus("1");
				// 分页查询
				taskDetailSearch.setup(taskDetailMapper.selectwaitBySearchCount(taskDetailSearch),taskDetailSearch.getShowLines());
				return taskDetailMapper.selectwaitBySearch(taskDetailSearch);
			/*}else{
				System.out.println("根据用户ID查询不到部门信息！");
				return null;
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("待办任务列表查询时发生异常！");
			return null;
		}
	}
	
	@Override
	@Transactional
	public List<String> selectWaitTaskDetailIdBySearch(TaskDetailSearch taskDetailSearch) throws Exception {

		//String userId = taskDetailSearch.getUserId()==null?"":taskDetailSearch.getUserId();
		// 部门
		//List<UccDepts> uccDeptsList = uccDeptsService.selectByUserId(userId);

		taskDetailSearch.setTaskStatus("1");
		//taskDetailSearch.setRoperateDeptId(uccDeptsList.get(0).getDeptId().toString());
		
		return taskDetailMapper.selectWaitTaskDetailIdBySearch(taskDetailSearch);
	}
	
	@Override
	@Transactional
	public Map<String,Object> taskBack(TaskTransfer taskTransfer, String userId) throws Exception {

        Map<String,Object> taskBackMap = new HashMap<String,Object>();
        taskBackMap.put("msg", "任务回退失败!");
        taskBackMap.put("code", "-1");
        try {
            // 主键
            UUIDGenerator generator = new UUIDGenerator();
            // 流转时间
            Timestamp transferTime = new Timestamp(System.currentTimeMillis());
            String taskTransferUuid = generator.generate();
            UccUsers uccUsers = uccUserService.findOne(userId);
            if(MyUtils.isBlank(uccUsers)){
                taskBackMap.put("msg", "任务回退时 根据userId:::"+userId+"查询用户信息为空!");
                return taskBackMap;
            }
            String domainId = uccUsers.getDomainId()==null?"":uccUsers.getDomainId();
            if(MyUtils.isBlank(domainId)){
                taskBackMap.put("msg", "任务回退时 用户userId:::"+userId+"查询租户ID为空!");
                return taskBackMap;
            }
			// 部门
			//List<UccDepts> uccDeptsList = uccDeptsService.selectByUserId(userId);
			//String backDept = uccDeptsList.get(0).getDeptPid().toString();
			TaskDetailSearch taskDetailSearch = new TaskDetailSearch();
			taskDetailSearch.setUserId(userId);
			taskDetailSearch.setTaskDetailId(taskTransfer.getTaskDetailId()==null?"":taskTransfer.getTaskDetailId());
			// 部门
			String backDept = taskDetailMapper.selDeptByUserIdFromTaskDetail(taskDetailSearch);

			taskTransfer.setRoperateDeptId(backDept);
			taskTransfer.setTaskTransferId(taskTransferUuid);
			taskTransfer.setTransferStatus("5"); //流转状态   0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退
			taskTransfer.setTransfeRoperate(TaskTransfer.BACK); //流转操作  0:创建、1：分派、2：接收、3：回退
			taskTransfer.setTransferTime(transferTime); //流转时间
			taskTransfer.setOperatorId(userId); //操作员
			taskTransfer.setOperatorDept(backDept);
            taskTransfer.setDomainId(domainId);

			taskTransferMapper.insert(taskTransfer);
			taskDetailMapper.updateTaskStatus(taskTransfer);

            taskBackMap.put("code", "0");
            taskBackMap.put("msg", "任务回退操作成功!");
            return taskBackMap;
		} catch (Exception e) {
			e.printStackTrace();
            return taskBackMap;
		}
	}

	@Override
	@Transactional
	public Map<String,Object> taskReceive(String userId, String taskDetailIds,String domainId) throws Exception {

		Map<String,Object> doTaskReceiveMap = new HashMap<String,Object>();
		doTaskReceiveMap.put("msg", "任务接收操作失败!");
		doTaskReceiveMap.put("code", "-1");
		try {
			String [] detailIds = taskDetailIds.split(",");
			if(MyUtils.isBlank(detailIds)){
				doTaskReceiveMap.put("msg", "任务接收时 任务明细ID为空!");
				return doTaskReceiveMap;
			}
			List<String> detailIdsList = Arrays.asList(detailIds);
			List<TaskTransfer> taskTransferList = new ArrayList<TaskTransfer>();
			TaskTransfer taskTransfer = null;
			// 主键
			UUIDGenerator generator = new UUIDGenerator();
			// 流转时间
			Timestamp transferTime = new Timestamp(System.currentTimeMillis());
			TaskDetailSearch taskDetailSearch = new TaskDetailSearch();
			taskDetailSearch.setUserId(userId);
			taskDetailSearch.setTaskDetailIdInList(detailIdsList);
			// 部门
			String deptID = taskDetailMapper.selDeptByUserIdFromTaskDetail(taskDetailSearch);
			/*List<UccDepts> uccDeptsList = uccDeptsService.selectByUserId(userId);
			String deptID = uccDeptsList.get(0).getDeptId().toString();*/

			for(String id : detailIds){
                taskTransfer = new TaskTransfer();
                String taskTransferUuid = generator.generate();
                taskTransfer.setRoperateDeptId(deptID);
                taskTransfer.setTaskDetailId(id);
                taskTransfer.setTaskTransferId(taskTransferUuid);
                taskTransfer.setTransferStatus("2"); //流转状态    0:未分派、1：未接收、2：待办、3：在办
                taskTransfer.setTransfeRoperate(TaskTransfer.RECEIVE); //流转操作  0:创建、1：分派、2：接收、3：回退
                taskTransfer.setTransferTime(transferTime); //流转时间
                taskTransfer.setOperatorId(userId); //操作员
                taskTransfer.setOperatorDept(deptID);
                taskTransfer.setRoperatePersonId(userId);//受理员
                taskTransfer.setRoperateDeptId(deptID);//受理部门
                taskTransfer.setDomainId(domainId);

                taskTransferList.add(taskTransfer);
            }
			// 插入任务流转表
			try {
				Map<String, Object> taskTransfermap = new HashMap<String, Object>();
				taskTransfermap.put("list", taskTransferList);
				taskTransferMapper.insertGroup(taskTransfermap);
			} catch (Exception e) {
				e.printStackTrace();
				doTaskReceiveMap.put("msg", "任务接收时 插入任务流转表发生异常!");
				return doTaskReceiveMap;
			}
			// 更新任务状态
			try {
				for(TaskTransfer transfer : taskTransferList){
					System.out.println("更新任务状态 任务ID：：：" + transfer.getTaskDetailId());
					System.out.println("更新任务状态 任务状态：：：" + transfer.getTransferStatus());
                    taskDetailMapper.updateTaskStatus(transfer);
                }
			} catch (Exception e) {
				e.printStackTrace();
				doTaskReceiveMap.put("msg", "任务接收时 更新任务状态发生异常!");
				return doTaskReceiveMap;
			}
			doTaskReceiveMap.put("code", "0");
			doTaskReceiveMap.put("msg", "任务接收操作成功!");
			return doTaskReceiveMap;
		} catch (Exception e) {
			e.printStackTrace();
			return doTaskReceiveMap;
		}
	}
}
