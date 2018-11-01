package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.UccBlackListCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccCustomersCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccToBlackCriteria;
import cn.cucsi.bsd.ucc.common.mapper.UccCustomersMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import cn.cucsi.bsd.ucc.data.repo.UccCustomersRepository;
import cn.cucsi.bsd.ucc.data.specs.UccCustomersSpecs;
import cn.cucsi.bsd.ucc.service.UccCustomersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UccCustomersServiceImpl implements UccCustomersService {

    @Autowired
    private UccCustomersRepository uccCustomersRepository;
    @Autowired
    private UccCustomersMapper uccCustomersMapper;
    @Override
    public Page<UccCustomers> findAll(UccCustomersCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage()==null?0:criteria.getPage(), criteria.getSize()==null?15:criteria.getSize(), sort);
        return uccCustomersRepository.findAll(UccCustomersSpecs.createSpec(criteria), pageable);
    }

    @Override
    public UccCustomers findOne(String custId) {
        return this.uccCustomersRepository.findOne(custId);
    }

    @Override
    public UccCustomers save(UccCustomers uccCustomers) {
        return this.uccCustomersRepository.save(uccCustomers);
    }

    @Override
    public Boolean delete(String custId) {
        this.uccCustomersRepository.delete(custId);
        return true;
    }
    /***
     * 将客户移至黑名单
     * add by wangxiaoyu
     * 2018-08-24
     */
    @Override
    public int inBlackListByBusinessCode(UccToBlackCriteria uccToBlackCriteria) throws Exception {
        String userId = uccToBlackCriteria.getUserId()==null?"":uccToBlackCriteria.getUserId();
        String custId = uccToBlackCriteria.getCustId()==null?"":uccToBlackCriteria.getCustId();
        String pullBlackReason = uccToBlackCriteria.getPullBlackReason()==null?"":uccToBlackCriteria.getPullBlackReason();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date updatedTime = sdf.parse(MyUtils.getToday());
        Date releaseTime = null;
        if(uccToBlackCriteria.getReleaseTime()!=null&&uccToBlackCriteria.getReleaseTime().length()!=0){
            releaseTime = sdf.parse(uccToBlackCriteria.getReleaseTime());
        }
        if(releaseTime!=null&&releaseTime.getTime()>System.currentTimeMillis()){//解锁时间大于系统时间 开启定时任务 zss
            Timer timer = new Timer();
            timer.schedule(new MyTask(custId,timer,uccCustomersMapper), releaseTime.getTime()-System.currentTimeMillis());
        }
        return this.uccCustomersRepository.inBlackListByBusinessCode(7,userId,updatedTime,pullBlackReason,releaseTime,custId);
    }
    class MyTask extends TimerTask {
        // 自定义定时任务（解除黑名单） zss
        private String custId;
        private Timer timer;
        private UccCustomersMapper uccCustomersMapper;
        public MyTask(String custId,Timer timer,UccCustomersMapper uccCustomersMapper){
            this.custId = custId;
            this.timer = timer;
            this.uccCustomersMapper = uccCustomersMapper;
        }
        @Override
        public void run() {
            if(this.custId != null && this.custId.length()!=0){
                /**
                 * 移除黑名单
                 */
                UccCustomers uccCustomers = new UccCustomers();
                uccCustomers.setCustId(this.custId);
                uccCustomers.setType(8);
                this.uccCustomersMapper.updateByPrimaryKeySelective(uccCustomers);
                System.out.println(custId +" 该客户已从黑名单移出。");
                this.timer.cancel();
            }else{
                this.timer.cancel();
            }
        }
    }
    /***
     * 拉黑客户--WEB
     * add by wangxiaoyu
     * 2018-09-27
     */
   /* @Override
    public int inBlackListByBusinessCodeWEB(UccCustomers customer) throws Exception {
        return uccCustomersMapper.inBlackListByBusinessCodeWEB(customer);
    }*/

    /***
     * 根据条件查询客户是否在黑名单
     * add by wangxiaoyu
     * 2018-08-31
     */
    @Override
    public int custmIsBlack(String businesscode,String domainId){
        return this.uccCustomersRepository.checkCustmIsBlack(businesscode,domainId);
    }

    /***
     * 根据条件查询黑名单列表
     * add by zss
     * 2018-09-6
     */
    @Override
    public PageResultBean_New<List<UccCustomers>> findBlackList(UccBlackListCriteria uccBlackListCriteria){
        com.github.pagehelper.Page pageInfo = PageHelper.startPage(uccBlackListCriteria.getPageNum(), uccBlackListCriteria.getPageSize());
        List<UccCustomers> list = uccCustomersMapper.selectByPrimaryKey(uccBlackListCriteria);
        PageResultBean_New<List<UccCustomers>> pageResultBean = new PageResultBean_New(pageInfo);
        pageResultBean.setList(list);
        return pageResultBean;
    }
    /*****
     * 修改客户信息
     * add by ZSS
     * 2018-9-13
     */
    public UccCustomers update(UccCustomers uccCustomers){
        uccCustomersMapper.updateByPrimaryKeySelective(uccCustomers);
        return uccCustomers;
    }

    @Override
    public UccCustomers selectByBusinessCode(String businessCode,String domainId) throws Exception {
        Map<String, Object> whereMap = new HashMap<String, Object>();
        whereMap.put("businessCode", businessCode);
        whereMap.put("domainId", domainId);

        return uccCustomersMapper.selectUccCustomersByCode(whereMap);
    }

}
