package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.PbxCdrsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PbxCdrsForAPPCriteria;
import cn.cucsi.bsd.ucc.common.mapper.PbxCdrsMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
import cn.cucsi.bsd.ucc.data.domain.TaskDetail;
import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import cn.cucsi.bsd.ucc.data.repo.PbxCdrsRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxCdrsSpecs;
import cn.cucsi.bsd.ucc.service.PbxCdrsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
 

import java.util.List;
import java.util.ResourceBundle;
import org.springframework.data.domain.Sort;

/**
 * Created by Song on 2017/10/16.
 */
@Service(value = "PbxCdrsService")
public class PbxCdrsServiceImpl implements PbxCdrsService {


    @Autowired
    private PbxCdrsRepository PbxCdrsRepository;

    @Autowired
    private PbxCdrsMapper pbxCdrsMapper;

    @Autowired
    PbxCdrsRepository pbxCdrsRepository;

    
    @Override
    public Page<PbxCdrs> findAll(PbxCdrsCriteria pbxCdrsCriteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "updatedTime");
        Pageable pageable = new PageRequest(pbxCdrsCriteria.getPage(), pbxCdrsCriteria.getSize(), sort);
        return pbxCdrsRepository.findAll(PbxCdrsSpecs.createSpec(pbxCdrsCriteria), pageable);
    }
    @Override
    public List<PbxCdrs> findAllExcel(PbxCdrsCriteria pbxCdrsCriteria) {
        List<PbxCdrs> informationList = null;
        try {
            informationList = pbxCdrsMapper.selectByPrimaryExcel(pbxCdrsCriteria);
            return informationList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据条件查询通话记录列表发生异常！");
            return null;
        }
    }
    @Override
    public PbxCdrs findOne(String cdrId) {
        return this.PbxCdrsRepository.findOne(cdrId);
    }

    @Override
    public PbxCdrs save(PbxCdrs PbxCdrs) {
        return this.PbxCdrsRepository.save(PbxCdrs);
    }

    @Override
    public Boolean delete(String cdrId) {
        this.PbxCdrsRepository.delete(cdrId);
        return true;
    }

    //根据条件查询通话记录列表（移动端查询通话记录） by wangxiaoyu 2018-09-04
    @Override
    public PageResultBean_New<List<PbxCdrs>> showListByTel(PbxCdrsForAPPCriteria pbxCdrsForAPPCriteria){

        com.github.pagehelper.Page pageInfo = PageHelper.startPage(pbxCdrsForAPPCriteria.getPageNum(), pbxCdrsForAPPCriteria.getPageSize());

        List<PbxCdrs> informationList = null;
        try {
            informationList = pbxCdrsMapper.showListByTel(pbxCdrsForAPPCriteria);
            PageResultBean_New<List<PbxCdrs>> pageResultBean_new = new PageResultBean_New(pageInfo);
            pageResultBean_new.setList(informationList);
            return pageResultBean_new;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据条件查询通话记录列表发生异常！");
            return null;
        }
    }
    
    
}
