package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxGwNumbersCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxGwNumbers;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxGwNumbersService {
    Page<PbxGwNumbers> findAll(PbxGwNumbersCriteria criteria);
    PbxGwNumbers findOne(String gwNumber);
    PbxGwNumbers save(PbxGwNumbers pbxGwNumbers);
    Boolean delete(String gwNumber);
    Boolean deleteByitselfList(List<PbxGwNumbers> list);
    List<PbxGwNumbers> findAllOfNoPage(PbxGwNumbersCriteria criteria);
}
