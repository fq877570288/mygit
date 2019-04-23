package cn.unihotel.service.impl;

import cn.unihotel.entry.ReserveInfo;
import cn.unihotel.entry.bean.GeneralResponse;
import cn.unihotel.entry.bean.GeneralResponsePage;
import cn.unihotel.entry.request.Reserve_info_search;
import cn.unihotel.mapper.ReserveInfoMapper;
import cn.unihotel.service.ReserveInfoServie;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
/**
 * create by 付琦 预定系统Service接口实现类
 * 2019-04-20
 */
@Service
public class ReserveInfoServiceImpl implements ReserveInfoServie {
    @Autowired
    private ReserveInfoMapper reserveInfoMapper;
    @Override
    public GeneralResponsePage<ReserveInfo> selectAll(Reserve_info_search reserve_info_search) {
        PageHelper.startPage(reserve_info_search.getPageNum(),reserve_info_search.getPageSize());
        Page list=reserveInfoMapper.selectAll(reserve_info_search);
        return new GeneralResponsePage<>("SUCCESS","成功",list);
    }
    @Transactional
    @Override
    public GeneralResponse insert(ReserveInfo reserveInfo) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=simpleDateFormat.format(new Date());
        reserveInfo.setReserveTime(date);
        String uuid=UUID.randomUUID().toString();//生成随机数
        reserveInfo.setReserveId("A"+uuid.substring(0,8));//截取8位随机数
       // System.out.println(reserveInfo.toString());
        int stat=reserveInfoMapper.insert(reserveInfo);
        if (stat>0){
            return GeneralResponse.success("添加成功");
        }else {
            return GeneralResponse.fail("添加失败");
        }
    }
    @Transactional
    @Override
    public GeneralResponse upDateByPrimaryKey(ReserveInfo reserveInfo) {
        int stat=reserveInfoMapper.updateByPrimaryKey(reserveInfo);
        if (stat>0){
            return GeneralResponse.success("修改成功");
        }else {
            return GeneralResponse.fail("修改失败");
        }
    }
    @Transactional
    @Override
    public GeneralResponse deleteByPrimaryKey(String reserveId) {
        int stat=reserveInfoMapper.deleteByPrimaryKey(reserveId);
        if (stat>0){
            return GeneralResponse.success("删除成功");
        }else {
            return GeneralResponse.fail("删除失败");
        }
    }

}
