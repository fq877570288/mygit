package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.repo.UccDeptsRepository;
import cn.cucsi.bsd.ucc.data.specs.UccDeptsSpecs;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@Service
public class UccDeptsServiceImpl implements UccDeptsService {
    @Autowired
    UccDeptsRepository uccDeptsRepository;

    @Override
    public Page<UccDepts> findAll(UccDeptsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "deptCreateTime");
        Pageable pageable = new PageRequest(0, 999999, sort);
        return uccDeptsRepository.findAll(UccDeptsSpecs.createSpec(criteria), pageable);
    }

    @Override
    public UccDepts findOne(String deptId) {
        return this.uccDeptsRepository.findOne(deptId);
    }

    @Override
    public UccDepts save(UccDepts uccCustomers) {
        return this.uccDeptsRepository.save(uccCustomers);
    }

    @Override
    public Boolean delete(String deptId) {
        this.uccDeptsRepository.delete(deptId);
        return true;
    }

    @Override
    public Page<UccDepts> findAllTree(UccDeptsCriteria criteria) {
        List<UccDepts> listOne = new ArrayList<UccDepts>();
        Pageable pageable = new PageRequest(0, 999999);
        Sort sort = new Sort(Sort.Direction.ASC, "deptLevel");
        Page<UccDepts> pages  = this.uccDeptsRepository.findAll(UccDeptsSpecs.createSpec(criteria), pageable);
        List<UccDepts> list = pages.getContent();
        if(list!=null&&list.size()!=0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null && list.get(i).getDeptPid().equals("0")) {//顶级
                    listOne.add(list.get(i));
                }
            }
            if(listOne.size()!=0){
                for(int i=0;i<listOne.size();i++){
                    queryChildren(listOne.get(i),list);
                }
            }
        }
        return pages;
    }
    public void queryChildren(UccDepts uccDepts,List<UccDepts> list){
        List<UccDepts> Childrens = new ArrayList<UccDepts>();
        if(list.size()!=0){
            for(int i=0;i<list.size();i++){
                if(uccDepts!=null&&uccDepts.getDeptId().equals(list.get(i).getDeptPid())){
                    Childrens.add(list.get(i));
                }
            }
            if(Childrens.size()!=0){
                uccDepts.setDepts(Childrens);
                for(int a = 0;a<Childrens.size();a++){
                    queryChildren(Childrens.get(a),list);
                }
            }
        }

    }
}
