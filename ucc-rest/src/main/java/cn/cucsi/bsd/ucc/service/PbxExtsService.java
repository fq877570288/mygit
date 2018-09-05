package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxExtsCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxExts;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
public interface PbxExtsService {
    Page<PbxExts> findAll(PbxExtsCriteria criteria);
    List<PbxExts> findAllBySearch(PbxExtsCriteria criteria);
    PbxExts findOne(String extId);
    Boolean saveMany(PbxExts pbxExts, String extGroupExts, Integer extNumStart, Integer extNumCount, String cover);
    Boolean saveOne(PbxExts pbxExts, String extGroupExts);
    Boolean delete(String extId);
    List<PbxExts> findAllFreeExts(String domainId);

    List<PbxExts> findAllOfNoPage(PbxExtsCriteria search);
}
