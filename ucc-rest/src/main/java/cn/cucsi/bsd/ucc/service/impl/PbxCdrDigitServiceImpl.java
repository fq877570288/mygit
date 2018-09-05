package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrDigitCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrDigit;
import cn.cucsi.bsd.ucc.data.repo.PbxCdrDigitRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxCdrDigitSpecs;
import cn.cucsi.bsd.ucc.service.PbxCdrDigitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Song on 2017/10/16.
 */
@Service(value = "PbxCdrDigitService")
public class PbxCdrDigitServiceImpl  implements PbxCdrDigitService {

    @Autowired
    private PbxCdrDigitRepository PbxCdrDigitRepository;

    @Override
    public Page<PbxCdrDigit> findAll(PbxCdrDigitCriteria PbxCdrDigitCriteria) {
        Pageable pageable = new PageRequest(PbxCdrDigitCriteria.getPage(), PbxCdrDigitCriteria.getSize());
        return PbxCdrDigitRepository.findAll(PbxCdrDigitSpecs.createSpec(PbxCdrDigitCriteria), pageable);
    }

    @Override
    public PbxCdrDigit findOne(String cdrDigitId) {
        return this.PbxCdrDigitRepository.findOne(cdrDigitId);
    }

    @Override
    public PbxCdrDigit save(PbxCdrDigit PbxCdrDigit) {
        return this.PbxCdrDigitRepository.save(PbxCdrDigit);
    }

    @Override
    public Boolean delete(String cdrDigitId) {
        this.PbxCdrDigitRepository.delete(cdrDigitId);
        return true;
    }

}