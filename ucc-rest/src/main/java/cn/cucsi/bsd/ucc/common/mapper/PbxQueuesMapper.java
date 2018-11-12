package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.PbxQueuesCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxQueues;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tnn on 2018/11/12.
 */
@Mapper
@Repository
public interface PbxQueuesMapper {
     List<PbxQueues> findQueueList(PbxQueuesCriteria criteria);
}
