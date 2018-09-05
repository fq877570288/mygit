package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxRecordsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxRecords;
import org.springframework.data.domain.Page;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxRecordsService {
    Page<PbxRecords> findAll(PbxRecordsCriteria criteria);
    PbxRecords findOne(String recordId);
    PbxRecords save(PbxRecords PbxRecords);
    Boolean delete(String recordId);
}
