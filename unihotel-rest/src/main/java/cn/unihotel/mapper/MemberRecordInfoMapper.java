package cn.unihotel.mapper;

import cn.unihotel.entry.MemberRecordInfo;
import cn.unihotel.entry.request.MemberRecord_info_search;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

/**
 * 客史列表Mapper接口
 * create by 付琦 2019-04-20
 */
@Repository
public interface MemberRecordInfoMapper {

    /**
     * 查询按条件|全查
     * @return
     */
    Page<MemberRecordInfo> selectAll(MemberRecord_info_search memberRecord_info_search);
//    //TODO 待实现
//    int addRemberRecord(MemberRecordInfo memberRecordInfo);


    int insertSelective(MemberRecordInfo memberRecordInfo);
}