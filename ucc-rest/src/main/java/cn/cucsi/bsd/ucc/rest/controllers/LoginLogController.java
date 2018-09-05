package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.LoginLogCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.LoginLog;
import cn.cucsi.bsd.ucc.service.LoginLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */

@RestController
@RequestMapping(value = "/loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;
    @ApiOperation(value="根据查询条件获取登陆日志表", notes="根据查询条件获取登陆日志表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<LoginLog>> findAll(@ModelAttribute LoginLogCriteria loginLogCriteria, HttpServletResponse response
    ){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new PageResultBean(this.loginLogService.findAll(loginLogCriteria));
    }
    @ApiOperation(value = "根据logId查询loginLog", notes = "根据logId查询loginLog")
    @RequestMapping(value = "/{logId}", method= RequestMethod.GET)
    public ResultBean<LoginLog> findOne(@PathVariable String logId){
        return new ResultBean<>(this.loginLogService.findOne(logId));
    }
    @ApiOperation(value = "根据logId删除loginLog", notes = "根据logId删除loginLog")
    @RequestMapping(value = "/{logId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String logId){
        return new ResultBean<>(this.loginLogService.delete(logId));
    }
    @ApiOperation(value = "创建loginLog", notes = "创建loginLog")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody LoginLog loginLog) {
        boolean result = this.loginLogService.save(loginLog) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改loginLog", notes = "修改loginLog")
    @RequestMapping(value = "/{logId}", method =  RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String logId,@RequestBody LoginLog loginLog) {
        boolean result = this.loginLogService.save(loginLog) != null;
        return new ResultBean<>(result);
    }

}
