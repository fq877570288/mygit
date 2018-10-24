package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccDomainCriteria;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.UccDomainServersService;
import cn.cucsi.bsd.ucc.service.UccDomainService;
import cn.cucsi.bsd.ucc.service.UccPermissionsService;
import cn.cucsi.bsd.ucc.service.UccRolesService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@RestController
@RequestMapping(value = "/uccDomain")
public class UccDomainController {
    @Autowired
    UccDomainService uccDomainService;

    @Autowired
    UccPermissionsService uccPermissionsService;

    @Autowired
    UccRolesService uccRolesService;


    @ApiOperation(value="根据查询条件获取域列表", notes="根据查询条件获取域列表", httpMethod = "POST")
    @JsonView(JSONView.DomainWithUser.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public PageResultBean<List<UccDomain>> findAll(@RequestBody UccDomainCriteria search) {
        return new PageResultBean(this.uccDomainService.findAll(search));
    }

    @ApiOperation(value = "根据查询条件获取分机表", notes = "根据查询条件获取分机表", httpMethod = "GET")
    @RequestMapping(value = "/findAllOfNoPage", method = RequestMethod.GET)
    @JsonView(JSONView.DomainWithUser.class)
    public ResultBean<List<UccDomain>> findAllOfNoPage(@ModelAttribute UccDomainCriteria search) {
        return new ResultBean(this.uccDomainService.findAllOfNoPage(search));
    }


    @ApiOperation(value = "根据domainId查询UccDomain", notes = "根据domainId查询UccDomain")
    @RequestMapping(value = "/{domainId}", method = RequestMethod.GET)
    public ResultBean<UccDomain> findOne(@PathVariable String domainId){
        return new ResultBean<>(this.uccDomainService.findOne(domainId));
    }

    @ApiOperation(value = "根据domainId删除UccDomain", notes = "根据domainId删除UccDomain")
    @RequestMapping(value = "/{domainId}", method = RequestMethod.DELETE)
        public ResultBean<Boolean> delete(@PathVariable String domainId){
        return new ResultBean<>(this.uccDomainService.delete(domainId));
    }

    @ApiOperation(value = "创建UccDomain", notes = "创建UccDomain")
    @RequestMapping(value = "", method =  RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResultBean<Object> create(@RequestBody UccDomain uccDomain, HttpServletResponse response,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();

        //加入判断平台管理员和处理 创建租户对时候对应创建一个管理员并且给此管理员赋权的操作
        UccUsers loginUser = (UccUsers) session.getAttribute("LoginUser");
        ResultBean<Object> resultBean = new ResultBean();
        if(loginUser != null && "uccAdmin".equals(loginUser.getUserId())){
//            uccDomain.setCreatedTime(new Date());
            uccDomain.setStatus("1");
            return uccDomainService.createDomin(uccDomain);
        }
        else{
            resultBean.setCode(1);
            resultBean.setMsg("该用户没有此操作权限");
            return resultBean;
        }
    }

    @ApiOperation(value = "修改UccDomain", notes = "修改UccDomain")
    @RequestMapping(value = "/{domainId}", method =  RequestMethod.PUT)
    public ResultBean<UccDomain> save(@PathVariable String domainId, @RequestBody UccDomain uccDomain){
        UccDomain targetDomain = this.uccDomainService.findOne(domainId);
        String targetStatus = targetDomain.getStatus();
        String status = uccDomain.getStatus();
        UpdateUtil.copyNullProperties(targetDomain,uccDomain);
        if(status==null||"".equals(status)){
            uccDomain.setStatus(targetStatus);
        }
        uccDomain.setUpdatedTime(new Date());
        return new ResultBean<>(this.uccDomainService.save(uccDomain));
    }

    @ApiOperation(value = "更新UccDomain的状态", notes = "更新UccDomain的状态")
    @RequestMapping(value = "/${domainId}/${status}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> updateStatusById(@PathVariable String domainId, @PathVariable String status){
        return new ResultBean<>(this.uccDomainService.updateStatusById(domainId, status));
    }

    @ApiOperation(value="获取所有租户的基本信息列表", notes="获取所有租户的基本信息列表", httpMethod = "GET")
    @JsonView(JSONView.DomainWithUser.class)
    @RequestMapping(value = "/allDoamin", method = RequestMethod.GET)
    public PageResultBean<List<UccDomain>> allDoamin() {
        UccDomainCriteria search = new UccDomainCriteria();
        return new PageResultBean(this.uccDomainService.findAll(search));
    }
}
