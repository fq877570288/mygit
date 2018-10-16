package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.TeamUsers;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeamUsersService {
    List<UccUsers> findAll(UccUserCriteria uccUserCriteria);
    List<UccUsers> addFindAll(UccUserCriteria uccUserCriteria);
    UccUsers findOne(String userId);
    TeamUsers save(TeamUsers teamUsers);
    Boolean delete(String userId);
    Integer deleteByPrimaryKey(TeamUsers teamUsers);
}
