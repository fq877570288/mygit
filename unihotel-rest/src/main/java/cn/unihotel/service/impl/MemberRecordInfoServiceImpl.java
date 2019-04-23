package cn.unihotel.service.impl;

import cn.unihotel.entry.MemberRecordInfo;
import cn.unihotel.entry.bean.GeneralResponse;
import cn.unihotel.entry.request.MemberRecord_info_search;
import cn.unihotel.entry.bean.GeneralResponsePage;
import cn.unihotel.mapper.MemberRecordInfoMapper;
import cn.unihotel.service.MemberRecordInfoService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * create by 付琦 客史列表Service接口实现类
 * 2019-04-20
 */
@Service
public class MemberRecordInfoServiceImpl implements MemberRecordInfoService {
    @Autowired
    private MemberRecordInfoMapper memberRecordInfoMapper;
    @Override
    public GeneralResponsePage selectAll(MemberRecord_info_search memberRecord_info_search) {
        PageHelper.startPage(memberRecord_info_search.getPageNum(), memberRecord_info_search.getPageSize());
        Page list=memberRecordInfoMapper.selectAll(memberRecord_info_search);
        return new GeneralResponsePage("SUCCESS","查询成功",list);
    }

    @Override
    public GeneralResponse createMemberRecordInfo(MemberRecordInfo memberRecordInfo) {
        memberRecordInfo.setCheckInDate(new Date());
        int i=memberRecordInfoMapper.insertSelective(memberRecordInfo);
        if (i>0){
            return  GeneralResponse.success("客史记录添加成功");
        }else{
            return  GeneralResponse.fail("客史记录添加失败");
        }
    }


}
