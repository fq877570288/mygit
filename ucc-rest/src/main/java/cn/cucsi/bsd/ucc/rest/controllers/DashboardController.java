package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import cn.cucsi.bsd.ucc.service.ExtGroupExtsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ZSS on 2018/9/21.
 */
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {
    /*****
     * 分机组、坐席员、分机号集合
     * add by ZSS
     * 2018-9-21
     */
    @Autowired
    private ExtGroupExtsService extGroupExtsService;

    @ApiOperation(value = "查询机组、坐席员、分机号集合", notes = "查询机组、坐席员、分机号集合")
    @RequestMapping(value = "/list", method =  RequestMethod.GET)
    public PageResultBean_New<List<PbxExtGroups>> list(String domainId){
        try {
            return this.extGroupExtsService.queryDashboardList(domainId);
        } catch (Exception e) {
            System.out.println("根据domainId查询查询机组、坐席员、分机号集合失败！");
            e.printStackTrace();
            return new PageResultBean_New<List<PbxExtGroups>>();
        }
    }
}
