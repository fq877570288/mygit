package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UserExtCriteria;
import cn.cucsi.bsd.ucc.data.domain.UserExt;
import org.springframework.data.domain.Page;

/**
 * Created by mk on 2017/10/16.
 */
public interface UserExtService {
    Page<UserExt> findAll(UserExtCriteria search);
    UserExt findOne(String userId);
    UserExt save(UserExt userExt);
    Boolean delete(String userId);

}
