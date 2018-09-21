package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.TeamUsers;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import org.springframework.data.domain.Page;

public interface TeamUsersService {
    Page<UccUsers> findAll(UccUserCriteria search);
    Page<UccUsers> addFindAll(UccUserCriteria search);
    UccUsers findOne(String userId);
    TeamUsers save(TeamUsers teamUsers);
    Boolean delete(String userId);
    Integer deleteByPrimaryKey(TeamUsers teamUsers);
}
