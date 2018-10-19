package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.FsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface FsRecordMapper {
	
    int deleteByPrimaryKey(@Param("recordId")String recordId,@Param("domainId")String domainId);
    
    int deleteByTime(@Param("recordTime")String recordTime,@Param("domainId")String domainId);

    FsRecord selectByPrimaryKey(@Param("recordId")String recordId,@Param("domainId")String domainId);
    
    List<FsRecord> selectAll(@Param("domainId")String domainId);
    
    List<FsRecord> selectByTime(@Param("recordTime")String recordTime,@Param("domainId")String domainId);
    
    /****
     * 删除数据库存放录音文件ById
     */
    int updateContentByRecordsId(@Param("recordsId")String recordsId,@Param("domainId")String domainId);
    
    /****
     * 查询recordTime之前的录音文件ID
     */
    List<String> selectRecordsIdByTime(@Param("recordTime")String recordTime,@Param("domainId")String domainId);

}