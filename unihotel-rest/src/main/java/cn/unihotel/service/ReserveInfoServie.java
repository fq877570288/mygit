package cn.unihotel.service;

import cn.unihotel.entry.ReserveInfo;
import cn.unihotel.entry.bean.GeneralResponse;
import cn.unihotel.entry.bean.GeneralResponsePage;
import cn.unihotel.entry.request.Reserve_info_search;

/**
 * create by 付琦 预定系统Service接口
 * 2019-04-20
 */
public interface ReserveInfoServie {
    GeneralResponsePage<ReserveInfo> selectAll(Reserve_info_search reserve_info_search);
    GeneralResponse insert(ReserveInfo record);
    GeneralResponse upDateByPrimaryKey(ReserveInfo reserveInfo);
    GeneralResponse deleteByPrimaryKey(String reserveId);
}
