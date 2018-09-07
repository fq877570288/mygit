package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccCustomersCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccToBlackCriteria;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import cn.cucsi.bsd.ucc.data.repo.UccCustomersRepository;
import cn.cucsi.bsd.ucc.data.specs.UccCustomersSpecs;
import cn.cucsi.bsd.ucc.service.UccCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UccCustomersServiceImpl implements UccCustomersService{

    @Autowired
    private UccCustomersRepository uccCustomersRepository;

    @Override
    public Page<UccCustomers> findAll(UccCustomersCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
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
        String pullBlackReason = uccToBlackCriteria.getPullBlackReason()==null?"":uccToBlackCriteria.getPullBlackReason();
        String businesscode = uccToBlackCriteria.getBusinesscode()==null?"":uccToBlackCriteria.getBusinesscode();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date updatedTime = sdf.parse(MyUtils.getToday());

        return this.uccCustomersRepository.inBlackListByBusinessCode(7,userId,updatedTime,pullBlackReason,businesscode);
    }

    /***
     * 根据businessCode查询客户是否在黑名单
     * add by wangxiaoyu
     * 2018-08-31
     */
    @Override
    public int custmIsBlack(String businessCode){
        return this.uccCustomersRepository.checkCustmIsBlack(businessCode);
    }
}
