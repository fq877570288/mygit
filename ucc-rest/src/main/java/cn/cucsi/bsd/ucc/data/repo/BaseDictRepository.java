package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.BaseDict;
import cn.cucsi.bsd.ucc.data.domain.BaseDictPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BaseDictRepository extends PagingAndSortingRepository<BaseDict, BaseDictPK>,JpaSpecificationExecutor {
    Page<BaseDict> findAll(Pageable pageable);
    List<BaseDict> findAllByDictType(String dictType);
}
