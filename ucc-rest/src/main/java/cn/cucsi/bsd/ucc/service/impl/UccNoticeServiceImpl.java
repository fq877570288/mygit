package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.common.mapper.UccNoticeMapper;
import cn.cucsi.bsd.ucc.common.mapper.UccNoticeTraceMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.UccNotice;
import cn.cucsi.bsd.ucc.data.domain.UccNoticeTrace;
import cn.cucsi.bsd.ucc.data.repo.UccNoticeRepository;
import cn.cucsi.bsd.ucc.data.specs.UccNoticeSpecs;
import cn.cucsi.bsd.ucc.service.UccNoticeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccNoticeServiceImpl implements UccNoticeService{

    @Autowired
    private UccNoticeRepository uccNoticeRepository;
    @Autowired
    private UccNoticeMapper uccNoticeMapper;
    @Autowired
    private UccNoticeTraceMapper uccNoticeTraceMapper;

    
    /*
    public List<UccNotice>findAll(UccNoticeCriteria criteria) {
        
        com.github.pagehelper.Page pageInfo = PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<UccNotice> informationList = null;
        try {
            criteria.setNoticeType("1");//(0:公告 1：通知),APP默认查看通知
            informationList = uccNoticeMapper.findAll(criteria);
            //PageResultBean_New<List<UccNotice>> pageResultBean_new = new PageResultBean_New(pageInfo);
            //pageResultBean_new.setList(informationList);
            return informationList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据查询条件获取通知列表--APP用发生异常！");
            return null;
        }
    }
*/
    @Override
    public Page<UccNotice> findAll(UccNoticeCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
               // System.out.println("2222");

        return uccNoticeRepository.findAll(UccNoticeSpecs.createSpec(criteria), pageable);
    }
    
    @Override
    public UccNotice findOne(String noticeId) {
        return uccNoticeRepository.findOne(noticeId);
    }

    @Override
    public UccNotice save(UccNotice uccNotice) {
        return uccNoticeRepository.save(uccNotice);
    }

    @Override
    public Boolean delete(String noticeId) {
        uccNoticeRepository.delete(noticeId);
        return true;
    }
    /***
     * 根据查询条件获取通知列表--APP用
     * 备注：如果是未读的，点击查询后需要将其置为已读
     * add by wangxiaoyu
     * 2018-09-07
     */
    @Override
    public PageResultBean_New<List<UccNotice>> selectByUserId(NoticeShowListCriteria noticeShowListCriteria) {

        com.github.pagehelper.Page pageInfo = PageHelper.startPage(noticeShowListCriteria.getPageNum(), noticeShowListCriteria.getPageSize());
        List<UccNotice> informationList = null;
        try {
            noticeShowListCriteria.setNoticeType("1");//(0:公告 1：通知),APP默认查看通知
            informationList = uccNoticeMapper.selectByUserId(noticeShowListCriteria);
            PageResultBean_New<List<UccNotice>> pageResultBean_new = new PageResultBean_New(pageInfo);
            pageResultBean_new.setList(informationList);
            return pageResultBean_new;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据查询条件获取通知列表--APP用发生异常！");
            return null;
        }
    }

    /***
     * 查询通知详情--APP用
     * 备注：该接口目前仅是APP用。因为该类上面方法也有查看详情的，
     * 但是本接口还需要含判断“如果是未读的，点击查询后需要将其置为已读”，
     * springdata我不太熟，所以单独写了这个 见谅！
     * add by wangxiaoyu
     * 2018-09-07
     */
    @Override
    public ResultBean_New<UccNotice> showNoticeDetailByNoticeId(ShowNoticeDetailCriteria showNoticeDetailCriteria){

        ResultBean_New<UccNotice> resultBean = new ResultBean_New<>();
        //初始化赋值
        resultBean.setReturnMsg("操作失败！");
        resultBean.setReturnCode(ResultBean_New.FAIL);

        String noticeId = showNoticeDetailCriteria.getNoticeId()==null?"":showNoticeDetailCriteria.getNoticeId();
        String flag = showNoticeDetailCriteria.getFlag()==null?"":showNoticeDetailCriteria.getFlag();
        String userId = showNoticeDetailCriteria.getUserId()==null?"":showNoticeDetailCriteria.getUserId();

        if(MyUtils.isBlank(noticeId)){
            resultBean.setReturnMsg("入参noticeId为空！");
            return resultBean;
        }
        UccNotice uccNotice = null;
        try {
            showNoticeDetailCriteria.setNoticeType("1");//(0:公告 1：通知),APP默认查看通知
            uccNotice = uccNoticeMapper.selectByPrimaryKey(showNoticeDetailCriteria);
            //若是未读或者无记录，需要在ucc_notice_trace增加记录
            if(flag.equals("")) {
                UccNoticeTrace trace = new UccNoticeTrace();
                UUIDGenerator generator = new UUIDGenerator();
                String uuid = generator.generate();
                trace.setNoticeTraceId(uuid);
                trace.setUserId(userId);
                trace.setFlag("1");//已读
                trace.setNoticeId(uccNotice.getNoticeId());
                uccNoticeTraceMapper.deleteByNoticeId(uccNotice.getNoticeId());
                uccNoticeTraceMapper.insert(trace);
            }else if(flag.equals("0")){
                UccNoticeTrace trace = new UccNoticeTrace();
                trace.setUserId(userId);
                trace.setUpdatedUserId(userId);
                trace.setFlag("1");//已读
                trace.setNoticeId(uccNotice.getNoticeId());
                uccNoticeTraceMapper.updateByPrimaryKeySelective(trace);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultBean;
        }
        resultBean.setData(uccNotice);
        resultBean.setReturnMsg("操作成功！");
        resultBean.setReturnCode(ResultBean_New.SUCCESS);
        return resultBean;
    }
    //zss
    @Override
    public int selectByFlagCount(String userId,String domainId) {
        return uccNoticeMapper.selectByFlagCount(userId,domainId);
    }
    //zss
    @Override
    public int selectByFlagTypeCount(String userId,String domainId) {
        return uccNoticeMapper.selectByFlagTypeCount(userId,domainId);
    }

}
