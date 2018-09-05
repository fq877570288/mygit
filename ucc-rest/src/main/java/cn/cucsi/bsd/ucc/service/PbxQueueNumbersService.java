package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxQueueNumbersCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxQueueNumbers;
import cn.cucsi.bsd.ucc.data.domain.PbxQueueNumbersPK;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxQueueNumbersService {
    List<PbxQueueNumbers> findAll(PbxQueueNumbersCriteria criteria);
    PbxQueueNumbers findOne(PbxQueueNumbersPK pbxQueueNumbers);
    PbxQueueNumbers save(PbxQueueNumbers pbxQueueNumbers);
    Boolean delete(PbxQueueNumbersPK pbxQueueNumbers);
}
