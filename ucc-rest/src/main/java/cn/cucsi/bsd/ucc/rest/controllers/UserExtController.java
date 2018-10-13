package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.common.beans.UserExtCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.data.domain.UserExt;
import cn.cucsi.bsd.ucc.service.UccUserService;
import cn.cucsi.bsd.ucc.service.UserExtService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "userExt")
public class UserExtController {

    @Autowired
    private UserExtService userExtService;
    @ApiOperation(value = "根据userId查询UserExt", notes = "根据userId查询UserExt")
    @RequestMapping(value = "/{userId}", method= RequestMethod.POST)
    public ResultBean<UserExt> findOne(@RequestBody String userId,@RequestBody UserExt userExt){
        return new ResultBean<>(this.userExtService.findOne(userExt));
    }
    @ApiOperation(value = "根据userId删除UserExt", notes = "根据userId查询UserExt")
    @RequestMapping(value = "/{userId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String userId,@RequestBody UserExt userExt){
        return new ResultBean<>(this.userExtService.delete(userExt)>0);
    }
    @ApiOperation(value = "创建UserExt", notes = "创建UserExt")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UserExt userExt) {
        boolean result=this.userExtService.insert(userExt)>0;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UserExt", notes = "修改UserExt")
    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String userId, @RequestBody UserExt userExt){
        boolean result=this.userExtService.insert(userExt)>0;
        return new ResultBean<>(result);
    }
}
