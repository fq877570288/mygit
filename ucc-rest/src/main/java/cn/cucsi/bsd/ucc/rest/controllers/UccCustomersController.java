package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import cn.cucsi.bsd.ucc.service.UccCustomersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/13.
 */

@Api(tags={"客户信息操作接口"})
@RestController
@RequestMapping(value = "/uccCustomers")
public class UccCustomersController {

    @Autowired
    private UccCustomersService uccCustomersService;

    @ApiOperation(value="根据查询条件获取客户列表", notes="根据查询条件获取客户列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public PageResultBean<List<UccCustomers>> findAll(@RequestBody UccCustomersCriteria search) {
        try {
            return new PageResultBean(this.uccCustomersService.findAll(search));
        } catch (Exception e) {
            System.out.println("查询客户列表失败！");
            e.printStackTrace();
            return new PageResultBean<List<UccCustomers>>();
        }
    }

    @ApiOperation(value = "根据custId查询UccCustomers", notes = "根据custId查询UccCustomers")
    @RequestMapping(value = "/{custId}", method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResultBean<UccCustomers> findOne(@PathVariable String custId){
        try {
            return new ResultBean<>(this.uccCustomersService.findOne(custId));
        } catch (Exception e) {
            System.out.println("根据custId查询客户失败！");
            e.printStackTrace();
            return new PageResultBean<UccCustomers>();
        }
    }

    @ApiOperation(value = "根据custId删除UccCustomers", notes = "根据custId删除UccCustomers")
    @RequestMapping(value = "/{custId}", method= RequestMethod.DELETE,produces="application/json;charset=UTF-8")
    public ResultBean<Boolean> delete(@PathVariable String custId){
        try {
            return new ResultBean<>(this.uccCustomersService.delete(custId));
        } catch (Exception e) {
            System.out.println("根据custId删除客户失败！");
            e.printStackTrace();
            return new PageResultBean<Boolean>();
        }
    }

    @ApiOperation(value = "创建UccCustomers", notes = "创建UccCustomers")
    @RequestMapping(value = "", method =  RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResultBean<Boolean> create(@RequestBody UccCustomers uccCustomers) {
        boolean result = false;
        try {
            result = this.uccCustomersService.save(uccCustomers) != null;
        } catch (Exception e) {
            System.out.println("创建UccCustomers客户失败！");
            e.printStackTrace();
        }
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccCustomers", notes = "修改UccCustomers")
    @RequestMapping(value = "/{custId}",method =  RequestMethod.PUT,produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResultBean<UccCustomers> save(@PathVariable String custId,@RequestBody UccCustomers uccCustomers){
        try {
            return new ResultBean<>(this.uccCustomersService.update(uccCustomers));
        } catch (Exception e) {
            System.out.println("修改UccCustomers客户失败！");
            e.printStackTrace();
            return new ResultBean<UccCustomers>();
        }
    }

    /***
     * 将客户移至黑名单
     * add by wangxiaoyu
     * 2018-08-24
     */
    @ApiOperation(value = "将客户移至黑名单", notes = "将客户移至黑名单")
    @RequestMapping(value = "/toBlackList", method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResultBean<String> mvCustomersToBlackList(@RequestBody UccToBlackCriteria uccToBlackCriteria){
        int resultCode = 0;
        String message = "操作失败！";
        try {
            resultCode = this.uccCustomersService.inBlackListByBusinessCode(uccToBlackCriteria);
            if(resultCode>0){
                message = "操作成功！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("将客户移至黑名单操作异常");
        }
        return new ResultBean<>(message);
    }
    /*****
     * 查询黑名单列表
     * add by ZSS
     * 2018-9-6
     */
    @ApiOperation(value = "根据条件查询黑名单列表", notes = "根据条件查询黑名单列表")
    @RequestMapping(value = "/findBlackList", method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public PageResultBean_New<List<UccCustomers>> findBlackList(@RequestBody UccBlackListCriteria uccBlackListCriteria){
        PageResultBean_New<List<UccCustomers>> pageResultBean = null;
        try{
            pageResultBean =  this.uccCustomersService.findBlackList(uccBlackListCriteria);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查询黑名单列表异常"+e.toString());
        }
        return pageResultBean;
    }

}
