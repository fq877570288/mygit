package cn.cucsi.bsd.ucc.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.mapper.TaskDetailMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskTransferMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import cn.cucsi.bsd.ucc.data.repo.UccCustomersRepository;
import cn.cucsi.bsd.ucc.service.TaskService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    private static SimpleDateFormat sdfyyyyMM = new SimpleDateFormat("yyyy-MM");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    //private static Logger logger = Logger.getLogger(OngoingTaskServiceImpl.class);

    @Autowired
	private TaskDetailMapper taskDetailMapper;

	@Autowired
	private TaskTransferMapper taskTransferMapper;

	@Autowired
	private UccCustomersRepository uccCustomersRepository;
	
	/***
	 * 根据查询条件获取当前坐席任务外乎列表
	 * add by wangxiaoyu
	 * 2018-08-27
	 */
	@Override
	public PageResultBean_New<List<TaskDetail>> selectDetailByUserId(OngoingTaskCriteria ongoingTaskCriteria){

		Page pageInfo = PageHelper.startPage(ongoingTaskCriteria.getPageNum(), ongoingTaskCriteria.getPageSize());

		List<TaskDetail> informationList = null;

		String keyWords = ongoingTaskCriteria.getKeyWords()== null ? "":ongoingTaskCriteria.getKeyWords();
		if(!MyUtils.isBlank(keyWords)){//判空
			if(MyUtils.isNumeric(keyWords)){//判断是否全为数字
				ongoingTaskCriteria.setCheckFlag("0");//为全数字
			}else{
				ongoingTaskCriteria.setCheckFlag("1");
			}
		}
		try {
			informationList = taskDetailMapper.selectDetailByUserId(ongoingTaskCriteria);
            String taskCountsFlag = ongoingTaskCriteria.getTaskCountsFlag()==null?"":ongoingTaskCriteria.getTaskCountsFlag();
			if(taskCountsFlag.equals("2")){
                Date now = new Date();
                String dateStr = sdf.format(now);
                ongoingTaskCriteria.setDateStart(sdfyyyyMM.format(now)+"-01 00:00:00");
                ongoingTaskCriteria.setDateEnd(dateStr+" 23:59:59");
            }
            if(!MyUtils.isBlank(taskCountsFlag)){

                String taskStatus = "";// 任务状态 0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退
                for (TaskDetail taskDetail:informationList){
                    taskStatus = taskDetail.getTaskStatus()==null?"":taskDetail.getTaskStatus();
                    if(taskStatus.equals("0")){
                        taskDetail.setTaskStatus("未分派");
                    }else if(taskStatus.equals("1")){
                        taskDetail.setTaskStatus("未接收");
                    }else if(taskStatus.equals("2")){
                        taskDetail.setTaskStatus("待办");
                    }else if(taskStatus.equals("3")){
                        taskDetail.setTaskStatus("在办");
                    }else if(taskStatus.equals("4")){
                        taskDetail.setTaskStatus("办结");
                    }else if(taskStatus.equals("5")){
                        taskDetail.setTaskStatus("退回");
                    }
                }
            }
			PageResultBean_New<List<TaskDetail>> pageResultBean_new = new PageResultBean_New(pageInfo);
			pageResultBean_new.setList(informationList);
			return pageResultBean_new;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("根据查询条件获取当前坐席任务外乎列表发生异常！");
			return null;
		}
	}

	/***
	 * 撤回任务
	 * add by wangxiaoyu
	 * 2018-08-30
	 */
	@Override
	@Transactional
	public ResultBean_New<TaskTransfer> taskBack(TaskBackCriteria taskBackCriteria){

		ResultBean_New<TaskTransfer> resultBean = new ResultBean_New<>();
		//初始化赋值
		resultBean.setReturnMsg("操作失败！");
		resultBean.setReturnCode(ResultBean_New.FAIL);

		List<String> businessCodeList = taskBackCriteria.getBusinessCodeList();
		List<TaskDetail> taskDetailList = null;

		String userId = "";
		String taskDetailId = "";
		TaskDetail taskDetail = null;
		if(!MyUtils.isBlank(businessCodeList)){
			for (int i = 0; i < businessCodeList.size(); i++) {
				String businessCode = businessCodeList.get(i);
				//根据businessCode查询任务明细表userId 、taskDetailId
				taskDetailList = taskDetailMapper.selectDetailByBusinessCode(businessCode);

				if(!MyUtils.isBlank(taskDetailList)){

					for (int j = 0; j < taskDetailList.size(); j++) {

						taskDetail = taskDetailList.get(j);
						taskDetailId = taskDetail.getTaskDetailId()==null?"":taskDetail.getTaskDetailId();
						userId = taskDetail.getUserId()==null?"": taskDetail.getUserId();

						// 主键
						UUIDGenerator generator = new UUIDGenerator();
						String taskTransferUuid = generator.generate();

						TaskTransfer taskTransfer = new TaskTransfer();
						// 流转时间
						Timestamp transferTime = new Timestamp(System.currentTimeMillis());

						taskTransfer.setTaskTransferId(taskTransferUuid);
						taskTransfer.setTransferStatus("5"); //流转状态   0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退
						taskTransfer.setTransfeRoperate(TaskTransfer.BACK); //流转操作  0:创建、1：分派、2：接收、3：回退
						taskTransfer.setTransferTime(transferTime); //流转时间
						taskTransfer.setOperatorId(userId); //操作员
						taskTransfer.setTaskDetailId(taskDetailId);//任务明细表主键
						//taskTransfer.setOperatorDept(ngtDeptList.get(0).getDeptId().toString());

						//撤回任务时执行插入任务流转表
						try {
							taskTransferMapper.insert(taskTransfer);
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("撤回任务时执行插入任务流转表发生错误！");
							return resultBean;
						}
						//撤回任务时执行更新任务明细表
						try {
							taskDetailMapper.updateTaskStatus(taskTransfer);
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("撤回任务时执行更新任务明细表发生错误！");
							return resultBean;
						}
					}
				}else{
					resultBean.setReturnMsg("根据businessCode查询任务明细表返回为空！");
					return resultBean;
				}
			}
		}else{
			resultBean.setReturnMsg("入参内容为空！");
			return resultBean;
		}
		resultBean.setReturnMsg("操作成功！");
		resultBean.setReturnCode(ResultBean_New.SUCCESS);
		return resultBean;
	}

	/***
	 * 查询客户任务详情
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	@Override
	public ResultBean_New<JSONObject> showTaskDetailByTaskDetailId(ShowTaskDetailCriteria showTaskDetailCriteria){

		ResultBean_New<JSONObject> resultBean = new ResultBean_New<>();
		//初始化赋值
		resultBean.setReturnMsg("操作失败！");
		resultBean.setReturnCode(ResultBean_New.FAIL);
		String taskDetailId = "";
		//String taskDetailId = showTaskDetailCriteria.getTaskDetailId()==null?"":showTaskDetailCriteria.getTaskDetailId();
		String businessCode = showTaskDetailCriteria.getBusinessCode()==null?"":showTaskDetailCriteria.getBusinessCode();
		if(MyUtils.isBlank(businessCode)){//入参为空
			resultBean.setReturnMsg("入参为空！");
			return resultBean;
		}
		TaskDetail taskDetail = null;
		String taskDetailForAPP = "";//用于手机端返回任务明细
		String isInBlackList = "";//用于手机端返回用户是否进入黑名单
		List<TaskDetail> taskDetailList = null;
		JSONArray taskDetailsArray = new JSONArray();
		JSONObject taskDetailObj = null;
		JSONObject taskDetilInfoJson = new JSONObject();
		String responsible = "";
		String packageName = "";
		String contractName = "";
		String contractEndTime = "";
		String activationTime = "";
		StringBuilder taskDetailForAPPTemp = null;
		String transferStatus = "";//流转状态 0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退
		String callMemo = "";//外呼备注
		String callResult = "";//外呼结果
		try {
			taskDetailList = taskDetailMapper.selectDetailByBusinessCode(businessCode);
			if(!MyUtils.isBlank(taskDetailList)){
				for (int j = 0; j < taskDetailList.size(); j++) {
					taskDetail = taskDetailList.get(j);
					taskDetailId = taskDetail.getTaskDetailId()==null?"":taskDetail.getTaskDetailId();
					//根据任务明细ID查询客户任务详情
					taskDetail =  taskDetailMapper.selectByPrimaryKey(taskDetailId);
					responsible = taskDetail.getResponsible()==null?"":taskDetail.getResponsible();//责任体
					packageName = taskDetail.getPackageName()==null?"":taskDetail.getPackageName();//套餐名称
					contractName = taskDetail.getContractName()==null?"":taskDetail.getContractName();//合约名称
					contractEndTime = taskDetail.getContractEndTime()==null?"":taskDetail.getContractEndTime();//合约结束时间
					activationTime = taskDetail.getActivationTime()==null?"":taskDetail.getActivationTime();//激活时间
					taskDetailForAPPTemp = new StringBuilder();
					taskDetailForAPPTemp.append("责任体：").append(responsible)
							.append(";").append("套餐名称:").append(packageName)
							.append(";").append("合约名称:").append(contractName)
							.append(";").append("合约结束时间:").append(contractEndTime)
							.append(";").append("激活时间:").append(activationTime);

					taskDetailForAPP = taskDetailForAPPTemp.toString();
					transferStatus = taskDetail.getTransferStatus()==null?"":taskDetail.getTransferStatus();
					callMemo = taskDetail.getCallMemo()==null?"":taskDetail.getCallMemo();
					callResult = taskDetail.getCallResult()==null?"":taskDetail.getCallResult();

					//根据businessCode查询客户是否在黑名单
					int type = uccCustomersRepository.checkCustmIsBlack(businessCode);
					if(type==7){
						isInBlackList = "该用户已被拉进黑名单";
					}else{
						isInBlackList = "未进入黑名单";
					}
					taskDetailObj = new JSONObject();
					taskDetailObj.put("taskDetailForAPP",taskDetailForAPP);
					taskDetailObj.put("isInBlackList",isInBlackList);
					taskDetailObj.put("transferStatus",transferStatus);
					taskDetailObj.put("callMemo",callMemo);
					taskDetailObj.put("callResult",callResult);

					taskDetailsArray.add(taskDetailObj);
				}
				taskDetilInfoJson.put("taskDetailsArray",taskDetailsArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("根据任务明细ID查询客户任务详情发生错误！");
			return resultBean;
		}
		resultBean.setData(taskDetilInfoJson);
		resultBean.setReturnMsg("操作成功！");
		resultBean.setReturnCode(ResultBean_New.SUCCESS);
		return resultBean;
	}

	/***
	 * 任务处理结果提交
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	@Override
	@Transactional
	public ResultBean_New<String> taskResultSubmit(TaskSubmitCriteria taskSubmitCriteria) {

		ResultBean_New<String> resultBean = new ResultBean_New<>();
		//初始化赋值
		resultBean.setReturnMsg("操作失败！");
		resultBean.setReturnCode(ResultBean_New.FAIL);

		List<TaskTransfer> taskSubmitList = taskSubmitCriteria.getTaskSubmitList();
		if(MyUtils.isBlank(taskSubmitList)){
			resultBean.setReturnMsg("入参信息内容为空！");
			return resultBean;
		}
		try {
			for(TaskTransfer taskTransfer : taskSubmitList) {

				Timestamp transferTime = new Timestamp(System.currentTimeMillis());
				taskTransfer.setTransferTime(transferTime); //流转时间
                int retCaode = taskTransferMapper.updateTaskResult(taskTransfer);
                if(retCaode>0){
					resultBean.setReturnMsg("提交成功！");
					resultBean.setReturnCode(ResultBean_New.SUCCESS);
				}else{
					resultBean.setReturnMsg("提交失败！");
				}
            }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("任务处理结果提交发生异常！");
			resultBean.setReturnMsg("提交失败！");
			return resultBean;
		}

		return resultBean;
	}

	/***
	 * 根据业务编码查询呼出记录
	 * add by wangxiaoyu
	 * 2018-08-31
	 */
	@Override
	public PageResultBean_New<List<TaskTransfer>> selectCallNotesByBusinessCode(TaskCallNotesCriteria taskCallNotesCriteria){

		Page pageInfo = PageHelper.startPage(taskCallNotesCriteria.getPageNum(), taskCallNotesCriteria.getPageSize());

		List<TaskTransfer> informationList = null;
		String businessCode = taskCallNotesCriteria.getBusinessCode()==null?"":taskCallNotesCriteria.getBusinessCode();
		try {
			//根据businessCode查询任务明细表taskDetailId
			List<TaskDetail> taskDetailList = taskDetailMapper.selectDetailByBusinessCode(businessCode);
			if(!MyUtils.isBlank(taskDetailList)){
                informationList = taskTransferMapper.selectCallNotesByDetailIds(taskDetailList);
                PageResultBean_New<List<TaskTransfer>> pageResultBean_new = new PageResultBean_New(pageInfo);
                pageResultBean_new.setList(informationList);
                return pageResultBean_new;
            }else{
                System.out.println("根据businessCode查询任务明细表taskDetailId为空");
                return null;
            }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("根据业务编码查询呼出记录发生异常！");
			return null;
		}
	}

	/***
	 * 个人中心--获取当前坐席“在办”、“待办”、“本月办结”数量
	 * add by wangxiaoyu
	 * 2018-09-01
	 */
	@Override
	public ResultBean_New<JSONObject> showTaskCountsByUserId(OngoingTaskCriteria ongoingTaskCriteria){

		ResultBean_New<JSONObject> resultBean = new ResultBean_New<>();
		//初始化赋值
		resultBean.setReturnMsg("操作失败！");
		resultBean.setReturnCode(ResultBean_New.FAIL);
        if(MyUtils.isBlank(ongoingTaskCriteria.getUserId())){
            resultBean.setReturnMsg("入参信息内容为空！");
            return resultBean;
        }
		JSONObject retData = new JSONObject();
		try {
			//待办任务数量
			int pendingTaskCounts = taskDetailMapper.selectPendingTaskCounts(ongoingTaskCriteria);

			//在办任务数量
			int toGoTaskCounts = taskDetailMapper.selectToGoTaskCounts(ongoingTaskCriteria);

			//本月办结任务数量
            Map<String, Object> cmap=new HashMap<String, Object>();
            Date now = new Date();
            String dateStr = sdf.format(now);
            cmap.put("dateStart", sdfyyyyMM.format(now)+"-01 00:00:00");
            cmap.put("dateEnd", dateStr+" 23:59:59");
            cmap.put("userId", ongoingTaskCriteria.getUserId());
			int completedTaskCounts = taskDetailMapper.selectCompletedTaskCounts(cmap);

			retData.put("pendingTaskCounts",pendingTaskCounts+"");
			retData.put("toGoTaskCounts",toGoTaskCounts+"");
			retData.put("completedTaskCounts",completedTaskCounts+"");

			resultBean.setData(retData);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取当前坐席“在办”、“待办”、“本月办结”数量发生异常！");
			return resultBean;
		}
		resultBean.setReturnMsg("操作成功！");
		resultBean.setReturnCode(ResultBean_New.SUCCESS);
		return resultBean;
	}
}
