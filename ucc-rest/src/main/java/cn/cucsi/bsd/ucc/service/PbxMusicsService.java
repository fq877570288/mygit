package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxMusicsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxMusics;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxMusicsService {
    Page<PbxMusics> findAll(PbxMusicsCriteria criteria);
    PbxMusics findOne(String musicId);
    PbxMusics save(byte[] fileBox,PbxMusics PbxMusics);
    PbxMusics updateById(PbxMusics PbxMusics);
    Boolean delete(String musicId);
    List<PbxMusics> findAllOfNoPage(PbxMusicsCriteria criteria);

}
