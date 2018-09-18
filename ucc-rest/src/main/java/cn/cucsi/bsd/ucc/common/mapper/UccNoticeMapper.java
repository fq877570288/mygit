package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.NoticeShowListCriteria;
import cn.cucsi.bsd.ucc.common.beans.ShowNoticeDetailCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccNoticeCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccNotice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UccNoticeMapper {

    /***
     * 查询通知详情
     * add by wangxiaoyu
     * 2018-09-07
     */
    UccNotice selectByPrimaryKey(ShowNoticeDetailCriteria showNoticeDetailCriteria);

    /***
     * 根据userId获取通知或公告列表
     * add by wangxiaoyu
     * 2018-09-07
     */
    List<UccNotice> selectByUserId(NoticeShowListCriteria noticeShowListCriteria);
    
    List<UccNotice> findAll(UccNoticeCriteria criteria);
}