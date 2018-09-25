package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.TaskRecordSearch;
import cn.cucsi.bsd.ucc.data.domain.TaskRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Mapper
@Repository
public interface TaskRecordMapper {

    int deleteByPrimaryKey(String taskRecordId);

    int insert(TaskRecord record);

    int insertSelective(TaskRecord record);

    TaskRecord selectByPrimaryKey(String taskRecordId);

    int updateByPrimaryKeySelective(TaskRecord record);

    int updateByPrimaryKey(TaskRecord record);

    /****
     * 批量插入
     */
    int insertGroup(Map<String, Object> map) throws Exception;

    /****
     * 分页查询
     */
    int selectAllCount (TaskRecordSearch search) throws Exception;

    /****
     * 按时间删除归档流转表
     */
    int deleteTaskRecordTransferByTime(String time) throws Exception;
}