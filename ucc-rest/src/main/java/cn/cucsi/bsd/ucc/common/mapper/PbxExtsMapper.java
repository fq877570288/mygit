package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.PbxExts;

import java.util.List;

public interface PbxExtsMapper {
    int deleteByPrimaryKey(String extId);

    int insert(PbxExts record);

    int insertSelective(PbxExts record);

    PbxExts selectByPrimaryKey(String extId);

    int updateByPrimaryKeySelective(PbxExts record);

    int updateByPrimaryKey(PbxExts record);

    List<PbxExts> findAllFreeExts(PbxExts search);
}