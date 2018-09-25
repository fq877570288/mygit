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
import java.util.Date;
import java.util.List;

@Service
public class UccCustomersServiceImpl implements UccCustomersService{

    @Autowired
    private UccCustomersRepository uccCustomersRepository;
    @Autowired
    private UccCustomersMapper uccCustomersMapper;
    @Override
    public Page<UccCustomers> findAll(UccCustomersCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
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

        return this.uccCustomersRepository.inBlackListByBusinessCode(7,userId,updatedTime,pullBlackReason,custId);
    }

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
    public UccCustomers selectByBusinessCode(String businesscode,String domainId) throws Exception {
        return uccCustomersMapper.selectUccCustomersByCode(businesscode,domainId);
    }

}
