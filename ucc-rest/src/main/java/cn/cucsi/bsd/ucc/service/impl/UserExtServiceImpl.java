package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UserExtCriteria;
import cn.cucsi.bsd.ucc.data.domain.UserExt;
import cn.cucsi.bsd.ucc.data.repo.UserExtRepository;
import cn.cucsi.bsd.ucc.data.specs.UserExtSpecs;
import cn.cucsi.bsd.ucc.service.UserExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by mk on 2017/10/16.
 */
@Service
public class UserExtServiceImpl  implements UserExtService{
    @Autowired
    private UserExtRepository userExtRepository;
    @Override
    public Page<UserExt> findAll(UserExtCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return userExtRepository.findAll(UserExtSpecs.createSpec(criteria),pageable);
    }

    @Override
    public UserExt findOne(String userId) {
        return userExtRepository.findOne(userId);
    }

    @Override
    public UserExt save(UserExt userExt) {
        return userExtRepository.save(userExt);
    }

    @Override
    public Boolean delete(String userId) {
        this.userExtRepository.delete(userId);
        return true;
    }
}
