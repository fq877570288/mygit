package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.BaseDictCriteria;
import cn.cucsi.bsd.ucc.data.domain.BaseDict;
import cn.cucsi.bsd.ucc.data.domain.BaseDictPK;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by home on 2017/10/13.
 */
public interface BaseDictService {
    Page<BaseDict> findAll(BaseDictCriteria search);
    BaseDict findOne(BaseDictPK baseDictPK);
    BaseDict save(BaseDict baseDict);
    Boolean delete(BaseDictPK baseDictPK);

    List<BaseDict> findAllByDictType(String dictType);

}
