package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PbxExtsCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExtsPK;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import cn.cucsi.bsd.ucc.data.domain.PbxExts;
import cn.cucsi.bsd.ucc.data.repo.ExtGroupExtsRepository;
import cn.cucsi.bsd.ucc.data.repo.PbxExtGroupsRepository;
import cn.cucsi.bsd.ucc.data.repo.PbxExtsRepository;
import cn.cucsi.bsd.ucc.data.specs.PbxExtsSpecs;
import cn.cucsi.bsd.ucc.service.PbxExtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by jjjjj on 2017-10-13.
 */
@Service(value = "PbxExtsService")
@Transactional(rollbackFor = Exception.class)
public class PbxExtsServiceImpl implements PbxExtsService {
    @Autowired
    PbxExtsRepository pbxExtsRepository;

    @Autowired
    private ExtGroupExtsRepository ExtGroupExtsRepository;
    @Autowired
    private PbxExtGroupsRepository pbxExtGroupsRepository;

    @Override
    public Page<PbxExts> findAll(PbxExtsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        if(criteria.getExtGroupsId()!=null && ! "".equals(criteria.getExtGroupsId())){
           List<ExtGroupExts>  extGroupExtslist = ExtGroupExtsRepository.findExtGroupExtsByGroupId(criteria.getExtGroupsId());
           List<String> extIds = new ArrayList<String>();
            for (ExtGroupExts extGroupExts:extGroupExtslist
                 ) {
                extIds.add(extGroupExts.getExtId());
            }
            if(extIds.size()==0){
                extIds.add("");//给一个不可能查出来的条件
            }
            criteria.setExtIds(extIds);
        }
        Page<PbxExts> pbxExtsPage = pbxExtsRepository.findAll(PbxExtsSpecs.createSpec(criteria),pageable);

        List<PbxExts> pbxExtslist = pbxExtsPage.getContent();
        System.out.println(pbxExtslist.size());
        for (PbxExts pbxExts:pbxExtslist
             ) {
            String extId= pbxExts.getExtId();
            List<PbxExtGroups> extGroupExtslist = pbxExtGroupsRepository.findpbxExtGroupsByExtId(extId);
            String extGroupsByExtId = "";
            for (PbxExtGroups pbxExtGroups:extGroupExtslist
                    ) {
                extGroupsByExtId = extGroupsByExtId + pbxExtGroups.getGroupName()+",";
            }
            if(extGroupsByExtId.length()>0) {
                extGroupsByExtId = extGroupsByExtId.substring(0, extGroupsByExtId.length() - 1);
            }
            pbxExts.setExtGroupsByExtId(extGroupsByExtId);
        }
        return pbxExtsPage;
    }


    @Override
    public List<PbxExts> findAllBySearch(PbxExtsCriteria criteria) {
        if(criteria.getExtId()==null){
            return pbxExtsRepository.findPbxExtsByExtNum(criteria.getDomainId(),criteria.getExtNum());
        }else{
            return pbxExtsRepository.findPbxExtsByExtNumAndExtIdNotEquals(criteria.getDomainId(),criteria.getExtNum(),criteria.getExtId());
        }

    }

    @Override
    public PbxExts findOne(String extId) {
        return pbxExtsRepository.findOne(extId);
    }

    @Override
    public Boolean saveMany(PbxExts pbxExts, String extGroupExts, Integer extNumStart, Integer extNumCount, String cover) {

        //判断是否覆盖
        String[]  extGroupId = extGroupExts.split(",");//分机组id
        List<String> extNumList = new ArrayList<String>();//分机号码集合
        Date updateTime = null;
        PbxExtsCriteria search = new PbxExtsCriteria();
        for(int i =0 ; i< extNumCount;i++){
            extNumList.add(String.valueOf(extNumStart));
            extNumStart = extNumStart+1;
        }

        for (String extNum:extNumList
                ) {
            PbxExts PbxExt = new PbxExts(); //保存的对象
            PbxExt.setDomainId(pbxExts.getDomainId());
            PbxExt.setExtPwd(pbxExts.getExtPwd());
            PbxExt.setCreatedTime(pbxExts.getCreatedTime());
            if(cover!=null && cover.equals("1")){//如果有重复覆盖原数据
                search.setExtNum(extNum);
                List<PbxExts> pbxExtslist = this.pbxExtsRepository.findAll(PbxExtsSpecs.createSpec(search));
                if(pbxExtslist.size()>0){//有重复的分机号
                    PbxExt.setExtId(pbxExtslist.get(0).getExtId());
                    PbxExt.setExtNum(extNum);
                    this.pbxExtsRepository.save(PbxExt);
                }else {
                    PbxExt.setExtNum(extNum);
                    this.pbxExtsRepository.save(PbxExt);
                }

            }else{//如果有重复 就忽略
                search.setExtNum(extNum);
                List<PbxExts> pbxExtslist =  this.pbxExtsRepository.findAll(PbxExtsSpecs.createSpec(search));
                if(pbxExtslist.size()==0){
                    PbxExt.setExtNum(extNum);
                    this.pbxExtsRepository.save(PbxExt);
                }else{
                    PbxExt.setExtId(pbxExtslist.get(0).getExtId());
                }
            }

            for (String GroupId: extGroupId
                    ) {//这一步是 插入中间表
                ExtGroupExtsPK extGroupExtsPK = new ExtGroupExtsPK();
                extGroupExtsPK.setExtId(PbxExt.getExtId());
                extGroupExtsPK.setGroupId(GroupId);
                ExtGroupExts ExtGroupExts = ExtGroupExtsRepository.findOne(extGroupExtsPK);
                if(ExtGroupExts!=null){//如果不等于空就说明有重复的
                   // ExtGroupExts.setUpdatedTime(new Date());
                    ExtGroupExtsRepository.save(ExtGroupExts);
                }else{
                    ExtGroupExts = new ExtGroupExts();
                    ExtGroupExts.setExtId(PbxExt.getExtId());
                    ExtGroupExts.setGroupId(GroupId);
                  //  ExtGroupExts.setCreatedTime(new Date());
                    ExtGroupExtsRepository.save(ExtGroupExts);
                }
            }
        }
        return true;
    }

    @Override
    public Boolean saveOne(PbxExts pbxExts, String extGroupExts) {
        this.pbxExtsRepository.save(pbxExts);//保存
        List<ExtGroupExts> extGroupExtslist = this.ExtGroupExtsRepository.findExtGroupExtsByExtId(pbxExts.getExtId());
        if(extGroupExtslist.size()>0){
            for (ExtGroupExts extGroupExt:extGroupExtslist
                    ) {
                ExtGroupExtsPK pk = new ExtGroupExtsPK();
                pk.setExtId(extGroupExt.getExtId());
                pk.setGroupId(extGroupExt.getGroupId());
                ExtGroupExtsRepository.delete(pk);
            }
        }
        String[]  extGroupId ={} ;
        if(extGroupExts!=null&&!extGroupExts.equals("")){
            extGroupId = extGroupExts.split(",");//分机组id
        }

        if(extGroupId.length>0){
            for (String groupId:extGroupId
                    ) {
                if(extGroupExtslist.size()>0){
                    ExtGroupExts ExtGroupExts =new ExtGroupExts();
                    ExtGroupExts.setExtId(pbxExts.getExtId());
                    ExtGroupExts.setGroupId(groupId);
                    ExtGroupExtsRepository.save(ExtGroupExts);
                }else{
                    ExtGroupExts ExtGroupExts =new ExtGroupExts();
                    ExtGroupExts.setExtId(pbxExts.getExtId());
                    ExtGroupExts.setGroupId(groupId);
                    ExtGroupExtsRepository.save(ExtGroupExts);
                }

            }
        }


        return true;
    }

    @Override
    public Boolean delete(String extId) {
        List<ExtGroupExts> extGroupExtslist = this.ExtGroupExtsRepository.findExtGroupExtsByExtId(extId);
        if(extGroupExtslist.size()>0){
            for (ExtGroupExts extGroupExt:extGroupExtslist
                    ) {
                ExtGroupExtsPK pk = new ExtGroupExtsPK();
                pk.setExtId(extGroupExt.getExtId());
                pk.setGroupId(extGroupExt.getGroupId());
                ExtGroupExtsRepository.delete(pk);
            }
        }
        this.pbxExtsRepository.delete(extId);
        return true;
    }

    @Override
    public List<PbxExts> findAllFreeExts(String domainId) {
        return this.pbxExtsRepository.findAllFreeExts(domainId);
    }

    @Override
    public List<PbxExts> findAllOfNoPage(PbxExtsCriteria search) {
        return pbxExtsRepository.findAll(PbxExtsSpecs.createSpec(search));
    }
}
