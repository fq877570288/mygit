package cn.cucsi.bsd.ucc.service;

import java.util.List;
import cn.cucsi.bsd.ucc.data.domain.FsRecord;

public interface FsRecordService {

    int deleteByPrimaryKey(String recordId,String domainId) throws Exception;
    
    int deleteByTime(String recordTime,String domainId) throws Exception;

    FsRecord selectByPrimaryKey(String recordId,String domainId) throws Exception;
    
    List<FsRecord> selectAll(String domainId) throws Exception;

    List<FsRecord> selectByTime(String recordTime,String domainId) throws Exception;

    /****
     * 删除数据库存放录音文件ById
     */
    int updateContentByRecordsId(String recordsId,String domainId) throws Exception;
    
    /****
     * 查询recordTime之前的录音文件ID
     */
    List<String> selectRecordsIdByTime(String recordTime,String domainId) throws Exception;
    
}
