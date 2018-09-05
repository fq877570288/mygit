package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxGwCalleeRewriteRulesCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxGwCalleeRewriteRules;
import cn.cucsi.bsd.ucc.data.repo.PbxGwCalleeRewriteRulesRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxGwCalleeRewriteRulesSpecs;
import cn.cucsi.bsd.ucc.service.PbxGwCalleeRewriteRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
@Service(value = "PbxGwCalleeRewriteRulesService")
public class PbxGwCalleeRewriteRulesServiceImpl implements PbxGwCalleeRewriteRulesService {
    @Autowired
    PbxGwCalleeRewriteRulesRepository repository;
    @Override
    public Page<PbxGwCalleeRewriteRules> findAll(PbxGwCalleeRewriteRulesCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "gatewayId");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return repository.findAll(PbxGwCalleeRewriteRulesSpecs.createSpec(criteria),pageable);
    }

    @Override
    public List<PbxGwCalleeRewriteRules> findAllNoPage(PbxGwCalleeRewriteRulesCriteria criteria) {
        return repository.findAll(PbxGwCalleeRewriteRulesSpecs.createSpec(criteria));
    }

    @Override
    public PbxGwCalleeRewriteRules findOne(String gwId) {
        return repository.findOne(gwId);
    }

    @Override
    public PbxGwCalleeRewriteRules save(PbxGwCalleeRewriteRules pbxGwCalleeRewriteRules) {

        return repository.save(pbxGwCalleeRewriteRules);
    }

    @Override
    public Boolean deleteManyByitselfList(List<PbxGwCalleeRewriteRules> list) {
        repository.delete(list);
        return true;
    }

    @Override
    public Boolean delete(String gwId) {
        this.repository.delete(gwId);
        return true;
    }
}
