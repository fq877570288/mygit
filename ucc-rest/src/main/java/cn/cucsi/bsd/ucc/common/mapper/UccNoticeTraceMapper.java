package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.UccNoticeTrace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UccNoticeTraceMapper {

    int deleteByPrimaryKey(String noticeTraceId);

    int insert(UccNoticeTrace record);

    int insertSelective(UccNoticeTrace record);

    UccNoticeTrace selectByPrimaryKey(String noticeTraceId);

    int updateByPrimaryKeySelective(UccNoticeTrace record);

    int updateByPrimaryKey(UccNoticeTrace record);

    int deleteByNoticeId(String noticeId);
}