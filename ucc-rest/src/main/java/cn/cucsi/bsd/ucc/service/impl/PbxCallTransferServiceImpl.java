package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxCallTransferCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCallTransfer;
import cn.cucsi.bsd.ucc.data.repo.PbxCallTransferRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxCallTransferSpecs;
import cn.cucsi.bsd.ucc.service.PbxCallTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Song on 2017/10/16.
 */
@Service
public class PbxCallTransferServiceImpl implements PbxCallTransferService{


    @Autowired
    private PbxCallTransferRepository pbxCallTransferRepository;

    @Override
    public Page<PbxCallTransfer> findAll(PbxCallTransferCriteria pbxCallTransferCriteria) {
        Pageable pageable = new PageRequest(pbxCallTransferCriteria.getPage(), pbxCallTransferCriteria.getSize());
        return pbxCallTransferRepository.findAll(PbxCallTransferSpecs.createSpec(pbxCallTransferCriteria), pageable);
    }

    @Override
    public PbxCallTransfer findOne(String extId) {
        return this.pbxCallTransferRepository.findOne(extId);
    }

    @Override
    public PbxCallTransfer save(PbxCallTransfer pbxCallTransfer) {
        return this.pbxCallTransferRepository.save(pbxCallTransfer);
    }

    @Override
    public Boolean delete(String extId) {
        this.pbxCallTransferRepository.delete(extId);
        return true;
    }

}
