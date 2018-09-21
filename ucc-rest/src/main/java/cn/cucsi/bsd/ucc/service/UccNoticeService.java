package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.UccNotice;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public interface UccNoticeService {
    //List<UccNotice> findAll(UccNoticeCriteria criteria);
    Page<UccNotice> findAll(UccNoticeCriteria criteria);
    UccNotice findOne(String noticeId);
    UccNotice save(UccNotice uccNotice);
    Boolean delete(String noticeId);

    /***
     * 根据查询条件获取通知列表--APP用
     * 备注：如果是未读的，点击查询后需要将其置为已读
     * add by wangxiaoyu
     * 2018-09-07
     */
    PageResultBean_New<List<UccNotice>> selectByUserId(NoticeShowListCriteria noticeShowListCriteria);

    /***
     * 查询通知详情--APP用
     * 备注：该接口目前仅是APP用。因为该类上面方法也有查看详情的，
     * 但是本接口还需要含判断“如果是未读的，点击查询后需要将其置为已读”，
     * springdata我不太熟，所以单独写了这个 见谅！
     * add by wangxiaoyu
     * 2018-09-07
     */
    ResultBean_New<UccNotice> showNoticeDetailByNoticeId(ShowNoticeDetailCriteria showNoticeDetailCriteria);
}
