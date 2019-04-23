package cn.unihotel.rest;

import cn.unihotel.entry.ReserveInfo;
import cn.unihotel.entry.bean.GeneralResponse;
import cn.unihotel.entry.bean.GeneralResponsePage;
import cn.unihotel.entry.request.Reserve_info_search;
import cn.unihotel.service.ReserveInfoServie;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * create by 付琦 2019-04-20
 * 预定系统Controller
 */
@RestController
public class ReserveInfoController {
    @Autowired
    private ReserveInfoServie reserveInfoServie;

    /**
     * 全查
     * @param reserve_info_search
     * @return
     */
    @RequestMapping(value = "/hms/v1/reserve/list", method = RequestMethod.POST)
    public GeneralResponsePage selectAll(@RequestBody Reserve_info_search reserve_info_search) {
        System.out.println(reserve_info_search.getReservePerson());
        return reserveInfoServie.selectAll(reserve_info_search);
    }

    /**
     * 添加
     *
     * @param reserveInfo
     * @return
     */
    @RequestMapping(value = "/hms/v1/reserve/create", method = RequestMethod.POST)
    public GeneralResponse insert(@RequestBody ReserveInfo reserveInfo) {
        return reserveInfoServie.insert(reserveInfo);
    }

    /**
     * 修改
     *
     * @param reserveId
     * @param reserveInfo
     * @return
     */
    @RequestMapping(value = "/hms/v1/member/{reserve_id}", method = RequestMethod.PUT)
    public GeneralResponse upDateByPrimaryKey(@PathVariable("reserve_id") String reserveId, @RequestBody ReserveInfo reserveInfo) throws ParseException {
        reserveInfo.setReserveId(reserveId);
        return reserveInfoServie.upDateByPrimaryKey(reserveInfo);
    }

    /**
     * 删除
     *
     * @param reserveId
     * @param reserveId
     * @return
     */
    @RequestMapping(value = "/hms/v1/reserve/{reserve_id}", method = RequestMethod.DELETE)
    public GeneralResponse deleteByPrimaryKey(@PathVariable("reserve_id") String reserveId) {
        return reserveInfoServie.deleteByPrimaryKey(reserveId);
    }


}
