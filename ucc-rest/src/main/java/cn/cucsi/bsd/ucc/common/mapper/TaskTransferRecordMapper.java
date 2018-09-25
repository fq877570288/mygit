package cn.cucsi.bsd.ucc.common.mapper;

import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.data.domain.TaskTransferRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskTransferRecordMapper {

	int insertGroup(Map<String, Object> taskMap) throws Exception;

	List<TaskTransferRecord> selectTransferRecordByTaskRecordId(String taskRecordId) throws Exception;
}
