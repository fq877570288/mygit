package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxShowbusyLogCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxShowbusyLog;
import cn.cucsi.bsd.ucc.data.repo.PbxShowbusyLogRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxShowbusyLogSpecs;
import cn.cucsi.bsd.ucc.service.PbxShowbusyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by jjjjj on 2017-10-16.
 */
@Service(value = "PbxShowbusyLogService")
public class PbxShowbusyLogServiceImpl implements PbxShowbusyLogService {
    @Autowired
    PbxShowbusyLogRepository repository;
    @Override
    public Page<PbxShowbusyLog> findAll(PbxShowbusyLogCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "type");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(),sort);
        return repository.findAll(PbxShowbusyLogSpecs.createSpec(criteria),pageable);
    }

    @Override
    public PbxShowbusyLog findOne(String showbusyLogId) {
        return repository.findOne(showbusyLogId);
    }

    @Override
    public PbxShowbusyLog save(PbxShowbusyLog pbxShowbusyLog) {
        return repository.save(pbxShowbusyLog);
    }

    @Override
    public Boolean delete(String showbusyLogId) {
        this.repository.delete(showbusyLogId);
        return true;
    }
}
