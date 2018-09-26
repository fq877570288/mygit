package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PbxDialplansCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.untils.PbxReload;
import cn.cucsi.bsd.ucc.common.untils.ZooKeeperUtils;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/pbxDialplans")
public class PbxDialplansController {

    @Autowired
    private PbxDialplansService PbxDialplansService;
    @Autowired
    private ZooKeeperUtils zk;

    @ApiOperation(value="根据查询条件获取拨号方案表", notes="根据查询条件获取拨号方案表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public PageResultBean<List<PbxDialplans>> findAll(@RequestBody PbxDialplansCriteria PbxDialplansCriteria){
        return new PageResultBean(this.PbxDialplansService.findAll(PbxDialplansCriteria));
    }
    @ApiOperation(value = "根据dialplanId查询PbxDialplans", notes = "根据dialplanId查询PbxDialplans")
    @RequestMapping(value = "/{dialplanId}", method= RequestMethod.GET)
    public ResultBean<PbxDialplans> findOne(@PathVariable String dialplanId){
        return new ResultBean<>(this.PbxDialplansService.findOne(dialplanId));
    }
    @ApiOperation(value = "根据dialplanId删除PbxDialplans", notes = "根据dialplanId删除PbxDialplans")
    @RequestMapping(value = "/{dialplanId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String dialplanId){
        boolean result = this.PbxDialplansService.delete(dialplanId);
        if(result){
            PbxDialplans pbxDialplans = new PbxDialplans();
            pbxDialplans.setDialplanId(dialplanId);
            PbxReload.reloadDlAsync(pbxDialplans, "delete", zk);
        }
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "创建PbxDialplans", notes = "创建PbxDialplans")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxDialplans PbxDialplans) {
        System.out.println("==========saveData=============="+PbxDialplans);
        boolean result = this.PbxDialplansService.save(PbxDialplans) != null;
        if(result){
            PbxReload.reloadDlAsync(PbxDialplans, "create", zk);
        }
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxDialplans", notes = "修改PbxDialplans")
    @RequestMapping(value = "/{dialplanId}", method =  RequestMethod.POST)
    public ResultBean<Boolean> save(@PathVariable String dialplanId,@RequestBody PbxDialplans PbxDialplans) {
        boolean result = this.PbxDialplansService.save(PbxDialplans) != null;
        if(result){
            PbxReload.reloadDlAsync(PbxDialplans, "update", zk);
        }
        return new ResultBean<>(result);
    }

}
