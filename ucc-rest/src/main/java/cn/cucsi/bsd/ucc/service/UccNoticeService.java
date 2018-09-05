package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccNoticeCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNotice;
import org.springframework.data.domain.Page;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public interface UccNoticeService {
    Page<UccNotice> findAll(UccNoticeCriteria criteria);
    UccNotice findOne(String noticeId);
    UccNotice save(UccNotice uccNotice);
    Boolean delete(String noticeId);
}
