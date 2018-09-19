/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.cucsi.bsd.ucc.common.mapper;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeFile;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UccNoticeFileMapper {
    List<UccNoticeFile> findFileList(String noticeId);
}
