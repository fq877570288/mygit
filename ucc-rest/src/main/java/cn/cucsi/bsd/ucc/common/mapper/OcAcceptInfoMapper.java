package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.OcAcceptInfoCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import cn.cucsi.bsd.ucc.data.domain.OcAcceptInfo;

import java.util.List;

/**
 * Created by mk on 2018/10/25.
 */
@Mapper
@Repository
public interface OcAcceptInfoMapper {
    List<OcAcceptInfo> findAll(OcAcceptInfoCriteria ocAcceptInfoCriteria);
    int create(OcAcceptInfo ocAcceptInfo);
}
