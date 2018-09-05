package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccDomainCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.domain.UccDomain;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccDomainService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public ResultBean<Boolean> create(@RequestBody UccDomain uccDomain,HttpServletResponse response) {
        uccDomain.setCreatedTime(new Date());
        boolean result = this.uccDomainService.save(uccDomain) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccDomain", notes = "修改UccDomain")
    @RequestMapping(value = "/{domainId}", method =  RequestMethod.PUT)
    public ResultBean<UccDomain> save(@PathVariable String domainId, @RequestBody UccDomain uccDomain){
        uccDomain.setUpdatedTime(new Date());
        return new ResultBean<>(this.uccDomainService.save(uccDomain));
    }

    @ApiOperation(value = "更新UccDomain的状态", notes = "更新UccDomain的状态")
    @RequestMapping(value = "/${domainId}/${status}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> updateStatusById(@PathVariable String domainId, @PathVariable String status){
        return new ResultBean<>(this.uccDomainService.updateStatusById(domainId, status));
    }
}
