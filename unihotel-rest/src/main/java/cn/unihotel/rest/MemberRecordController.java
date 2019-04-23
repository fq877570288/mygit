package cn.unihotel.rest;

import cn.unihotel.entry.MemberRecordInfo;
import cn.unihotel.entry.bean.GeneralResponse;
import cn.unihotel.entry.request.MemberRecord_info_search;
import cn.unihotel.entry.bean.GeneralResponsePage;
import cn.unihotel.service.MemberRecordInfoService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by 付琦 2019 04 18
 * 客史列表Controller
 */
@RestController
public class MemberRecordController {
    @Autowired
    private MemberRecordInfoService memberRecordInfoService;

    @RequestMapping(value = "/hms/v1/guest/list", method = RequestMethod.POST)
    public GeneralResponsePage selectAll(@RequestBody MemberRecord_info_search memberRecord_info_search) {
        System.out.println(memberRecord_info_search.getCreatedTimeFrom());
        System.out.println(memberRecord_info_search.getCreatedTimeTo());
        return memberRecordInfoService.selectAll(memberRecord_info_search);
    }

    /*
     * @Description 增加客史信息
     * @Date
     * @Param
     * @return
     **/
    @RequestMapping(value = "/hms/v1/guest/create",
            method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    public GeneralResponse createMemberRecordInfo(@RequestBody MemberRecordInfo memberRecordInfo) {
        System.out.println(memberRecordInfo);
        return memberRecordInfoService.createMemberRecordInfo(memberRecordInfo);
    }
}