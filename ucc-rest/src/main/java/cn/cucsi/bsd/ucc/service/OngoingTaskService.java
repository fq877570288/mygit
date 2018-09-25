package cn.cucsi.bsd.ucc.service;

import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;

/***
 * 在办任务业务处理类
 */
public interface OngoingTaskService {
	
	/***
	 * 在办任务列表查询
	 */
	List<UccCustomers> selectOngoingBySearch(TaskDetailSearch search) throws Exception;

	
	/***
	 * 根据业务编码查询任务列表
	 */
	List<TaskDetail> selectDetailByBusinessCode(String businessCode,String domainId) throws Exception;

	
	/***
	 * 根据业务编码保存变更号码
	 */
	void saveChangePhone(String changePhone, String fPhone, String businessCode, String domainId) throws Exception;

	
	/***
	 * 根据业务编码、租户判断该客户是否加入黑名单
	 */
	String custmIsBlack(String businessCode,String domainId) throws Exception;


	/***
	 * 保存任务明细
	 */
	void saveDetail(String callinfo, String userId, String cdrId,String domainId) throws Exception;


	/***
	 * 查询businessCode呼出记录
	 */
	List<TaskTransfer> selectCallNotesByDetailIds(String taskDetailIds) throws Exception;
	
	
	/***
	 * 保存用户自定义显示字段
	 */
	void cusfsSave(Map<String, Object> map) throws Exception;

	
	/***
	 * 保存并继续
	 */
	String saveAndGoonDetail(String callinfo, String userId, String taskTypeId, String cdrId, String businessCode,String domainId) throws Exception;

}
