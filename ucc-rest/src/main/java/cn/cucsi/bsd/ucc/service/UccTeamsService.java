package cn.cucsi.bsd.ucc.service;


import cn.cucsi.bsd.ucc.common.beans.UccTeamsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccTeams;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
public interface UccTeamsService {
    Page<UccTeams> findAll(UccTeamsCriteria search);
    List<UccTeams> findAllOfNoPage(UccTeamsCriteria search);
    UccTeams findOne(String teamId );
    UccTeams save(UccTeams uccTeams);
    Boolean delete(String teamId);
}
