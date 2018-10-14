package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.SkillGroupUserCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.common.mapper.SkillGroupUserMapper;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUser;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUserPK;
import cn.cucsi.bsd.ucc.data.domain.UccSkillGroup;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.data.repo.SkillGroupUserRepository;
import cn.cucsi.bsd.ucc.data.repo.UccSkillGroupRepository;
import cn.cucsi.bsd.ucc.data.repo.UccUserRepository;
import cn.cucsi.bsd.ucc.data.specs.PermissionGroupsSpecs;
import cn.cucsi.bsd.ucc.data.specs.SkillGroupUserSpecs;
import cn.cucsi.bsd.ucc.data.specs.UccUserSpecs;
import cn.cucsi.bsd.ucc.service.SkillGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by tianyuwei on 2017/10/17.
 */

@Service
public class SkillGroupUserServiceImpl implements SkillGroupUserService {
    @Autowired
    SkillGroupUserRepository skillGroupUserRepository;
    @Autowired
    UccUserRepository uccUserRepository;
    @Autowired
    UccSkillGroupRepository uccSkillGroupRepository;
    @Autowired
    private SkillGroupUserMapper skillGroupUserMapper;




    @Override
    public Page<SkillGroupUser> findAll(SkillGroupUserCriteria criteria) {
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize());
        Page<SkillGroupUser> pageSkillGroupUser = skillGroupUserRepository.findAll(SkillGroupUserSpecs.createSpec(criteria), pageable);


        List<SkillGroupUser> pbxExtslist = pageSkillGroupUser.getContent();
        for(SkillGroupUser sgu:pbxExtslist){
            String userId=sgu.getUserId();
            //查询用户所属技能组集合id
            List<SkillGroupUser>  groupList= skillGroupUserRepository.findSkillGroupUsersByUserIdEquals(userId );

            String groupName="";
            //根据技能组id查询技能组名称
            for(SkillGroupUser groupId:groupList){
            UccSkillGroup ug= uccSkillGroupRepository.findOne(groupId.getSkillGroupId());
                groupName+=ug.getSkillGroupName()+",";
            }
            sgu.setGroupName(groupName);
           UccUsers uu= uccUserRepository.findOne(userId);
           sgu.setUserName(uu.getUserName());
           sgu.setNickName(uu.getNickName());

        }
        return  pageSkillGroupUser;
    }

    @Override
    public SkillGroupUser findOne(SkillGroupUserPK pk) {
        return skillGroupUserRepository.findOne(pk);
    }

    @Override
    public SkillGroupUser save(SkillGroupUser skillGroupUser) {
        return skillGroupUserRepository.save(skillGroupUser);
    }

    @Override
    public Boolean delete(SkillGroupUserPK pk) {
        skillGroupUserRepository.delete(pk);
        return true;
    }


    @Override
    public List<UccUsers> findAllOfNoPage(UccUserCriteria criteria) {
        List<UccUsers> list =uccUserRepository.findAll(UccUserSpecs.createSpec(criteria));
        List<SkillGroupUser> sList =skillGroupUserRepository.findAll(UccUserSpecs.createSpec(criteria));
        for (int i=0;i<list.size()-1;i++){
            for(int j=0;j<sList.size()-1;j++){
                if(list.get(i).getUserId().equals(sList.get(j).getUserId())){
                    list.remove(i);
                }
            }
        }
        return list;
    }

    @Override
    public Boolean insert(String userId, String skillGroup,String createdPerson) {
        String[] user=userId.split(",");
        String []sg=skillGroup.split(",");
        for (String u:user){
            for (String s:sg){
                UccUsers uu= uccUserRepository.findOne(u);
                SkillGroupUser skillGroupUser=new SkillGroupUser();
                skillGroupUser.setUserId(u);
                skillGroupUser.setSkillGroupId(s);
         //       skillGroupUser.setCreatedPerson(createdPerson);
                skillGroupUser.setUserName(uu.getUserName());
                skillGroupUser.setCreatedTime(new Date());
                skillGroupUserRepository.save(skillGroupUser);
            }
        }
        return true;
    }

    @Override
    public List<SkillGroupUser> findSkillGroupUsersByUserIdEquals(String userId) {
        String num=userId;
        List<SkillGroupUser> list=skillGroupUserRepository.findSkillGroupUsersByUserIdEquals(userId);
        return  skillGroupUserRepository.findSkillGroupUsersByUserIdEquals(userId);
    }

    @Override
    public Boolean deleteByUserId(String userId) {
        List<SkillGroupUser>  groupList= skillGroupUserRepository.findSkillGroupUsersByUserIdEquals(userId );
        for(SkillGroupUser sg:groupList){
            SkillGroupUserPK pk = new SkillGroupUserPK();
            pk.setSkillGroupId(sg.getSkillGroupId());
            pk.setUserId(userId);
            skillGroupUserRepository.delete(pk);
        }
        return true;
    }

    @Override
    public Boolean update(String userId, String skillGroup) {
        deleteByUserId(userId);
        String []sg=skillGroup.split(",");
        for (String s:sg){
            SkillGroupUser skillGroupUser=new SkillGroupUser();
            skillGroupUser.setUserId(userId);
            skillGroupUser.setSkillGroupId(s);
            skillGroupUserRepository.save(skillGroupUser);
        }
        return true;
    }

    @Override
    public List<SkillGroupUser> newFindAll(SkillGroupUserCriteria skillGroupUserCriteria) {
        return  skillGroupUserMapper.selectByPrimaryKey(skillGroupUserCriteria);
    }

    @Override
    public int del(SkillGroupUser skillGroupUser) {
        return  skillGroupUserMapper.deleteByPrimaryKey(skillGroupUser);
    }

    @Override
    public int inse(SkillGroupUser skillGroupUser) {
        return  skillGroupUserMapper.insert(skillGroupUser);
    }
}
