package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccServersCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccServers;
import cn.cucsi.bsd.ucc.data.repo.UccServersRepository;
import cn.cucsi.bsd.ucc.data.specs.UccServersSpecs;
import cn.cucsi.bsd.ucc.service.UccServersService;
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
public class UccServersServiceImpl implements UccServersService{
    @Autowired
    UccServersRepository uccServersRepository;
    @Override
    public Page<UccServers> findAll(UccServersCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "serverName");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccServersRepository.findAll(UccServersSpecs.createSpec(criteria),pageable);
    }

    @Override
    public UccServers findOne(String serverName) {
        return uccServersRepository.findOne(serverName);
    }

    @Override
    public UccServers save(UccServers uccServers) {
        return uccServersRepository.save(uccServers);
    }

    @Override
    public Boolean delete(String serverName) {
        this.uccServersRepository.delete(serverName);
        return true;
    }

    @Override
    public Boolean deleteAll() {
        this.uccServersRepository.deleteAll();
        return true;
    }
}
