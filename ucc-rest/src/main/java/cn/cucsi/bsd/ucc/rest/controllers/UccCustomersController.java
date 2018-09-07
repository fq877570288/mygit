package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import cn.cucsi.bsd.ucc.service.UccCustomersService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public PageResultBean<List<UccCustomers>> findAll(UccCustomersCriteria search) {
        return new PageResultBean(this.uccCustomersService.findAll(search));
    }

    @ApiOperation(value = "根据custId查询UccCustomers", notes = "根据custId查询UccCustomers")
    @RequestMapping(value = "/{custId}", method= RequestMethod.POST)
    public ResultBean<UccCustomers> findOne(@PathVariable String custId){
        return new ResultBean<>(this.uccCustomersService.findOne(custId));
    }

    @ApiOperation(value = "根据custId删除UccCustomers", notes = "根据custId删除UccCustomers")
    @RequestMapping(value = "/{custId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String custId){
        return new ResultBean<>(this.uccCustomersService.delete(custId));
    }

    @ApiOperation(value = "创建UccCustomers", notes = "创建UccCustomers")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccCustomers uccCustomers) {
        boolean result = this.uccCustomersService.save(uccCustomers) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccCustomers", notes = "修改UccCustomers")
    @RequestMapping(value = "/{custId}",method =  RequestMethod.PUT)
    public ResultBean<UccCustomers> save(@PathVariable String custId, @RequestBody UccCustomers uccCustomers){
        return new ResultBean<>(this.uccCustomersService.save(uccCustomers));
    }

    /***
     * 将客户移至黑名单
     * add by wangxiaoyu
     * 2018-08-24
     */
    @ApiOperation(value = "将客户移至黑名单", notes = "将客户移至黑名单")
    @RequestMapping(value = "/toBlackList", method= RequestMethod.POST)
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
    @RequestMapping(value = "/findBlackList", method= RequestMethod.POST)
    @ResponseBody
    public PageResultBean_New<List<UccCustomers>> findBlackList( UccBlackListCriteria uccBlackListCriteria){
        PageResultBean_New<List<UccCustomers>> pageResultBean = null;
        try{
            pageResultBean = this.uccCustomersService.findBlackList(uccBlackListCriteria);
        }catch (Exception e){
            System.out.println("查询黑名单列表异常"+e);
        }
        return pageResultBean;
    }


}
