package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccNoticeTraceCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeTrace;
import org.springframework.data.domain.Page;

/**
 * Created by mk on 2017/10/13.
 */
public interface UccNoticeTraceService {
    Page<UccNoticeTrace> findAll(UccNoticeTraceCriteria search);
    UccNoticeTrace findOne(String noticeTraceId );
    UccNoticeTrace save(UccNoticeTrace uccNoticeTrace);
    Boolean delete(String noticeTraceId);

}
