package cn.cucsi.bsd.ucc.service;


import cn.cucsi.bsd.ucc.common.beans.UccTeamsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccTeams;
import org.springframework.data.domain.Page;

/**
 * Created by mk on 2017/10/16.
 */
public interface UccTeamsService {
    Page<UccTeams> findAll(UccTeamsCriteria search);
    UccTeams findOne(String teamId );
    UccTeams save(UccTeams uccTeams);
    Boolean delete(String teamId);
}
