package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccTeamsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccTeams;
import cn.cucsi.bsd.ucc.data.repo.UccTeamsRepository;
import cn.cucsi.bsd.ucc.data.specs.UccTeamsSpecs;
import cn.cucsi.bsd.ucc.service.UccTeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@Service
public class UccTeamsServiceImpl implements UccTeamsService{
    @Autowired
    UccTeamsRepository uccTeamsRepository;
    @Override
    public Page<UccTeams> findAll(UccTeamsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccTeamsRepository.findAll(UccTeamsSpecs.createSpec(criteria),pageable);
    }

    @Override
    public List<UccTeams> findAllOfNoPage(UccTeamsCriteria criteria) {
        return uccTeamsRepository.findAll(UccTeamsSpecs.createSpec(criteria));
    }

    @Override
    public UccTeams findOne(String teamId) {
        return uccTeamsRepository.findOne(teamId);
    }

    @Override
    public UccTeams save(UccTeams uccTeams) {
        return uccTeamsRepository.save(uccTeams);
    }

    @Override
    public Boolean delete(String teamId) {
        this.uccTeamsRepository.delete(teamId);
        return true;
    }
}
