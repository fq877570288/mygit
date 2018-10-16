package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.TeamUsersService;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/teamUser")
public class TeamUsersController {

    @Autowired
    private TeamUsersService teamUsersService;

    @ApiOperation(value="根据查询条件获取班组成员列表", notes="根据查询条件获取班组成员列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public  PageResultBean_New<List<UccUsers>> findAll(@RequestBody UccUserCriteria criteria){
        Page pageInfo = PageHelper.startPage(criteria.getPage(), criteria.getSize());
        List<UccUsers> list = teamUsersService.findAll(criteria);
        PageResultBean_New<List<UccUsers>> pageResultBean_new = new PageResultBean_New(pageInfo);
        pageResultBean_new.setList(list);
        return pageResultBean_new;
    }

    @ApiOperation(value = "根据userId查询班组成员列表", notes = "根据userId班组成员列表")
    @RequestMapping(value = "/{userId}", method= RequestMethod.GET)
    public ResultBean<UccUsers> findOne(@PathVariable String userId){
        return new ResultBean<>(this.teamUsersService.findOne(userId));
    }

    @ApiOperation(value = "创建TeamUsers", notes = "创建TeamUsers")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    @ResponseBody
    public ResultBean<Boolean> create(@RequestBody TeamUsersCriteria teamUsersCriteria) {
        String[] usersIds = teamUsersCriteria.getUsersId().split(",");
        String[] teamIds = teamUsersCriteria.getTeamId().split(",");
        boolean result=false;
        for (String usersId : usersIds) {
            TeamUsers teamUsers = new TeamUsers();
            teamUsers.setUserId(usersId);
            for (String teamId:teamIds) {
                teamUsers.setTeamId(teamId);
                result = this.teamUsersService.save(teamUsers)!=null;
            }
        }
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改TeamUsers", notes = "修改TeamUsers")
    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String userId, @RequestBody TeamUsersCriteria teamUsersCriteria){
        boolean result=false;
        TeamUsers teamUsers = new TeamUsers();
        teamUsers.setUserId(userId);
        if("".equals(teamUsersCriteria.getTeamId()) || teamUsersCriteria.getTeamId() == null){
            Integer i = teamUsersService.deleteByPrimaryKey(teamUsers);
            if(i>0){
                result = true;
            }
        }else {
            String[] teamIds = teamUsersCriteria.getTeamId().split(",");
            Integer i = teamUsersService.deleteByPrimaryKey(teamUsers);
            if(i>0){
                for (String teamId:teamIds) {
                    teamUsers.setTeamId(teamId);
                    result = this.teamUsersService.save(teamUsers)!=null;
                }
            }
        }
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "根据userId删除班组成员", notes = "根据userId删除班组成员")
    @RequestMapping(value = "/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String userId){
        boolean result=false;
        TeamUsers teamUsers = new TeamUsers();
        teamUsers.setUserId(userId);
        Integer i = teamUsersService.deleteByPrimaryKey(teamUsers);
        if(i>0){
            result = true;
        }
        return new ResultBean<>(result);
    }
}
