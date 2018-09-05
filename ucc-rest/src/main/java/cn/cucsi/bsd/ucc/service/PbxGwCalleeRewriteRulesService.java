package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxGwCalleeRewriteRulesCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxGwCalleeRewriteRules;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxGwCalleeRewriteRulesService {
    Page<PbxGwCalleeRewriteRules> findAll(PbxGwCalleeRewriteRulesCriteria criteria);
    List<PbxGwCalleeRewriteRules> findAllNoPage(PbxGwCalleeRewriteRulesCriteria criteria);
    PbxGwCalleeRewriteRules findOne(String rewriteId);
    PbxGwCalleeRewriteRules save(PbxGwCalleeRewriteRules PbxGwCalleeRewriteRules);
    Boolean deleteManyByitselfList(List<PbxGwCalleeRewriteRules> list);
    Boolean delete(String rewriteId);
}
