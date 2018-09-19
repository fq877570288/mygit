package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.OngoingTaskCriteria;
import cn.cucsi.bsd.ucc.common.beans.ShowTaskDetailCriteria;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
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

    //根据taskDetailId连表查询
    TaskDetail selectByPrimaryKey(String taskDetailId);

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

    //下面两个方法暂时没写sql
    int insertGroup(Map<String, Object> taskMap) throws Exception;

    void deleteBYBatch(Map<String, Object> taskDetailMap) throws Exception;

    // 查询明细表中客户信息部分
    List<TaskDetail> getCustomerInfo() throws Exception;

    List<TaskDetail> selectByWhere(Map<String, Object> whereMap) throws Exception;

    List<TaskDetail> selectImportBatchsBySearch(TaskDetailSearch search) throws Exception;
}