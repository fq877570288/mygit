package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxShowbusyLogCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxShowbusyLog;
import org.springframework.data.domain.Page;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxShowbusyLogService {
    Page<PbxShowbusyLog> findAll(PbxShowbusyLogCriteria criteria);
    PbxShowbusyLog findOne(String showbusyLogId);
    PbxShowbusyLog save(PbxShowbusyLog PbxShowbusyLog);
    Boolean delete(String showbusyLogId);
}
