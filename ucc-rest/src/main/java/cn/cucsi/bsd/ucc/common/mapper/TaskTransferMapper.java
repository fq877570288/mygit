package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskTransferMapper {

    int deleteByPrimaryKey(String taskTransferId);

    int insert(TaskTransfer record);

    int insertSelective(TaskTransfer record);

    TaskTransfer selectByPrimaryKey(String taskTransferId);

    int updateByPrimaryKeySelective(TaskTransfer record);

    int updateByPrimaryKey(TaskTransfer record);

    int updateTaskResult(TaskTransfer record);

    /***
     * 根据任务明细主键查询呼出记录
     * add by wangxiaoyu
     * 2018-08-31
     */
    List<TaskTransfer> selectCallNotesByDetailIds(@Param("taskDetailList") List<TaskDetail> taskDetailList);

}