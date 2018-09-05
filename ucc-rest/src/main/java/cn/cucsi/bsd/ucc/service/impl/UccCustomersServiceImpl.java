package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccCustomersCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccToBlackCriteria;
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

    @Override
    public int inBlackListByBusinessCode(UccToBlackCriteria uccToBlackCriteria) throws Exception {
        Integer type = uccToBlackCriteria.getType()==null?null:uccToBlackCriteria.getType();
        String userId = uccToBlackCriteria.getUserId()==null?"":uccToBlackCriteria.getUserId();
        String pullBlackReason = uccToBlackCriteria.getPullBlackReason()==null?"":uccToBlackCriteria.getPullBlackReason();
        String businesscode = uccToBlackCriteria.getBusinesscode()==null?"":uccToBlackCriteria.getBusinesscode();
        Date updatedTime = uccToBlackCriteria.getUpdatedTime()==null?new Date():uccToBlackCriteria.getUpdatedTime();

        return this.uccCustomersRepository.inBlackListByBusinessCode(type,userId,updatedTime,pullBlackReason,businesscode);
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
