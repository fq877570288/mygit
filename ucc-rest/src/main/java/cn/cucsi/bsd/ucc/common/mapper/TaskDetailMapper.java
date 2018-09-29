package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.OngoingTaskCriteria;
import cn.cucsi.bsd.ucc.common.beans.ShowTaskDetailCriteria;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskRecord;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TaskDetailMapper {

    int deleteByPrimaryKey(String taskDetailId);

    int insert(TaskDetail record);

    int insertSelective(TaskDetail record);

    //根据条件连表查询（移动端用，因为关联流转表所以可能会返回多个）
    List<TaskDetail> selectByPrimaryKey(ShowTaskDetailCriteria showTaskDetailCriteria);

    //根据taskDetailId连表查询（WEB端用）
    TaskDetail selectByPrimaryKeyForWEB(String taskDetailId);

    int updateByPrimaryKeySelective(TaskDetail record);

    int updateByPrimaryKey(TaskDetail record);

    /***
     * 根据查询条件获取当前坐席任务外乎列表
     * add by wangxiaoyu
     * 2018-08-27
     */
    List<TaskDetail> selectDetailByUserId(OngoingTaskCriteria ongoingTaskCriteria);


    /***
     * 任务回退更新任务明细任务状态
     * add by wangxiaoyu
     * 2018-08-30
     */
    void updateTaskStatus(TaskTransfer taskTransfer);

    /***
     * 根据businessCode、domainId查询任务明细表
     * add by wangxiaoyu
     * 2018-08-30
     */
    List<TaskDetail> selectDetailByCriteria(ShowTaskDetailCriteria showTaskDetailCriteria);

    /***
     * 根据businessCode、domainId查询外呼任务列表--web端用
     * add by wangxiaoyu
     * 2018-09-24
     */
    List<TaskDetail> selectDetailByCode(Map<String, Object> whereMap) throws Exception;

    /***
     * 根据查询条件获取待办任务数量
     * add by wangxiaoyu
     * 2018-09-01
     */
    int selectPendingTaskCounts(OngoingTaskCriteria ongoingTaskCriteria);

    /***
     * 根据查询条件获取在办任务数量
     * add by wangxiaoyu
     * 2018-09-01
     */
    int selectToGoTaskCounts(OngoingTaskCriteria ongoingTaskCriteria);

    /***
     * 根据查询条件获取本月办结任务数量
     * add by wangxiaoyu
     * 2018-09-01
     */
    int selectCompletedTaskCounts(Map<String, Object> map);

    int insertGroup(Map<String, Object> taskMap) throws Exception;

    void deleteBYBatch(Map<String, Object> taskDetailMap) throws Exception;

    // 查询明细表中客户信息部分
    List<TaskDetail> getCustomerInfo() throws Exception;

    List<TaskDetail> selectByWhere(Map<String, Object> whereMap) throws Exception;

    List<TaskDetail> selectImportBatchsBySearch(TaskDetailSearch search) throws Exception;

    int selectBySearchCount(TaskDetailSearch search) throws Exception;

    // 分页查询
    List<TaskDetail> selectBySearch(TaskDetailSearch search) throws Exception;

    List<String> selectTaskDetailIdBySearch(TaskDetailSearch search) throws Exception;

    void editTaskDept(Map<String, Object> taskDetailMap) throws Exception;

    int selectwaitBySearchCount(TaskDetailSearch search) throws Exception;

    List<TaskDetail> selectwaitBySearch(TaskDetailSearch search) throws Exception;

    List<String> selectWaitTaskDetailIdBySearch(TaskDetailSearch search) throws Exception;

    int updateTaskByTaskDetail(TaskDetail taskDetail) throws Exception;

    int deleteWithListId(List<TaskRecord> list) throws Exception;
    //zss
    int selectWaitAllCount(Map<String, Object> map);
    //zss
    int selectWaitTodayCount(Map<String, Object> map);
    //zss
    int selectOngoingAllCount(Map<String, Object> map);
    //zss
    int selectOngoingNoCount(Map<String, Object> map);
    //zss
    int selectCompleteByDaysCount(Map<String, Object> map);
    //zss
    int selectCompleteTodayCount(Map<String, Object> map);

    //视图
    int queryCompleteTask(Map<String, Object> map) throws Exception;

    int queryECall(Map<String, Object> emap) throws Exception;

    int queryACall(Map<String, Object> amap) throws Exception;

    int selectMonitorBySearchSearchCount(TaskDetailSearch search) throws Exception;

    List<TaskDetail> selectMonitorBySearch(TaskDetailSearch search) throws Exception;

    List<String> selectTaskDetailIdByMonitorSearch(TaskDetailSearch search) throws Exception;

    int selectCompleteBySearchCount(TaskDetailSearch search) throws Exception;

    List<TaskDetail> selectCompleteBySearch(TaskDetailSearch search) throws Exception;

    List<TaskDetail> selectTaskStatusByEndDate(TaskDetailSearch search) throws Exception;

}