package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.SkillGroupUserCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUser;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUserPK;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.SkillGroupUserService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/17.
 */

@RestController
@RequestMapping(value = "/skillGroupUser")
public class SkillGroupUserController {
    @Autowired
    SkillGroupUserService skillGroupUserService;

    @ApiOperation(value="根据查询条件获取技能组用户列表", notes="根据查询条件获取技能组用户列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public PageResultBean<List<SkillGroupUser>> findAll(@RequestBody SkillGroupUserCriteria search) {
        return new PageResultBean(this.skillGroupUserService.findAll(search));
    }

    @ApiOperation(value = "根据SkillGroupUserPK查询SkillGroupUser", notes = "根据SkillGroupUserPK查询SkillGroupUser")
    @RequestMapping(value = "/{skillGroupId}/{userId}", method= RequestMethod.POST)
    public ResultBean<SkillGroupUser> findOne(@RequestBody String skillGroupId, @PathVariable String userId){
        SkillGroupUserPK pk = new SkillGroupUserPK();
        pk.setSkillGroupId(skillGroupId);
        pk.setUserId(userId);

        return new ResultBean<>(this.skillGroupUserService.findOne(pk));
    }

    @ApiOperation(value = "根据SkillGroupUserPK删除SkillGroupUser", notes = "根据SkillGroupUserPK删除SkillGroupUser")
    @RequestMapping(value = "/{skillGroupId}/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String skillGroupId, @PathVariable String userId){
        SkillGroupUserPK pk = new SkillGroupUserPK();
        pk.setSkillGroupId(skillGroupId);
        pk.setUserId(userId);
        return new ResultBean<>(this.skillGroupUserService.delete(pk));
    }

    @ApiOperation(value = "创建SkillGroupUser", notes = "创建SkillGroupUser")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody SkillGroupUser skillGroupUser) {
        boolean result = this.skillGroupUserService.save(skillGroupUser) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改SkillGroupUser", notes = "修改SkillGroupUser")
    @RequestMapping(value = "/{skillGroupId}/{userId}", method = RequestMethod.PUT)
    public ResultBean<SkillGroupUser> save(@PathVariable String skillGroupId, @PathVariable String userId, @RequestBody SkillGroupUser skillGroupUser){
        SkillGroupUserPK pk = new SkillGroupUserPK();
        pk.setSkillGroupId(skillGroupId);
        pk.setUserId(userId);

        return new ResultBean<>(this.skillGroupUserService.save(skillGroupUser));
    }


    @ApiOperation(value = "查询没有绑定技能组的用户", notes = "查询没有绑定技能组的用户", httpMethod = "POST")
    @RequestMapping(value = "/findAllOfNoPage", method = RequestMethod.POST)
    public ResultBean<List<UccUsers>> findAllOfNoPage(@RequestBody UccUserCriteria search) {
        return new ResultBean(this.skillGroupUserService.findAllOfNoPage(search));
    }

    @ApiOperation(value = "批量创建SkillGroupUser", notes = "批量创建SkillGroupUser")
    @RequestMapping(value = "/insert", method =  RequestMethod.POST)
    public ResultBean<Boolean> insert(@RequestBody String userId,  String skillGroup,   String createdPerson) {
       boolean result = this.skillGroupUserService.insert(userId,skillGroup,createdPerson) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "根据userId查询所绑定的技能组集合", notes = "根据userId查询所绑定的技能组集合", httpMethod = "POST")
    @RequestMapping(value = "/findSkillGroupUsersByUserIdEquals/{userId}", method = RequestMethod.POST)
    public ResultBean<List<SkillGroupUser>> findSkillGroupUsersByUserIdEquals(@RequestBody String  userId) {
        String num=userId;
        return new ResultBean<>(skillGroupUserService.findSkillGroupUsersByUserIdEquals(userId));

    }

    @ApiOperation(value = "根据userId删除多条SkillGroupUser", notes = "根据userId删除多条SkillGroupUser")
    @RequestMapping(value = "/delete/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> deleteByUserId(@PathVariable String userId){
        return new ResultBean<>(this.skillGroupUserService.deleteByUserId(userId));
    }
    @ApiOperation(value = "修改SkillGroupUser", notes = "修改SkillGroupUser")
    @RequestMapping(value = "/update", method =  RequestMethod.POST)
    public ResultBean<Boolean> update(  String userId, String skillGroup) {
        boolean result = this.skillGroupUserService.update(userId,skillGroup) != null;
        return new ResultBean<>(result);
    }


}
