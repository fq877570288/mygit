package cn.cucsi.bsd.ucc.service;


import cn.cucsi.bsd.ucc.common.beans.UccNoticeFileCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeFile;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public interface UccNoticeFileService {
    Page<UccNoticeFile> findAll(UccNoticeFileCriteria criteria);
    UccNoticeFile findOne(String noticeFileId);
    UccNoticeFile save(byte[] fileBox,UccNoticeFile uccNoticeFile);
    UccNoticeFile save(UccNoticeFile uccNoticeFile);
    Boolean delete(String noticeFileId);
    List<UccNoticeFile> findFileList(String noticeId);
}
