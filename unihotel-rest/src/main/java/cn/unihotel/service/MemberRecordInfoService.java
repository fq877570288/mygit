package cn.unihotel.service;

import cn.unihotel.entry.MemberRecordInfo;
import cn.unihotel.entry.bean.GeneralResponse;
import cn.unihotel.entry.request.MemberRecord_info_search;
import cn.unihotel.entry.bean.GeneralResponsePage;

/**
 * create by 付琦 客史列表Service接口
 * 2019-04-20
 */
public interface MemberRecordInfoService {
    //查询全部信息
    GeneralResponsePage<MemberRecordInfo> selectAll(MemberRecord_info_search memberRecord_info_search);

    GeneralResponse createMemberRecordInfo(MemberRecordInfo memberRecordInfo);
}
