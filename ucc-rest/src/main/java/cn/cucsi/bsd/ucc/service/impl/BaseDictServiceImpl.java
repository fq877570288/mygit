package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.BaseDictCriteria;
import cn.cucsi.bsd.ucc.data.domain.BaseDict;
import cn.cucsi.bsd.ucc.data.domain.BaseDictPK;
import cn.cucsi.bsd.ucc.data.repo.BaseDictRepository;
import cn.cucsi.bsd.ucc.data.specs.BaseDictSpecs;
import cn.cucsi.bsd.ucc.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/10/13.
 */
@Service
public class BaseDictServiceImpl implements BaseDictService {

    @Autowired
    private BaseDictRepository baseDictRepository;

    @Override
    public Page<BaseDict> findAll(BaseDictCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC,"dictKey");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return baseDictRepository.findAll(BaseDictSpecs.createSpec(criteria),pageable);

    }

    @Override
    public BaseDict findOne(BaseDictPK baseDictPK) {
        return this.baseDictRepository.findOne(baseDictPK);
    }

    @Override
    public BaseDict save(BaseDict baseDict) {
        return this.baseDictRepository.save(baseDict);
    }

    @Override
    public Boolean delete(BaseDictPK baseDictPK) {
        this.baseDictRepository.delete(baseDictPK);
        return true;
    }

    @Override
    public List<BaseDict> findAllByDictType(String dictType) {
        return this.baseDictRepository.findAllByDictType(dictType);
    }


}
