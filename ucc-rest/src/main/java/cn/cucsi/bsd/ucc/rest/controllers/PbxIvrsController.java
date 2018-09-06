package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxIvrsCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxIvrs;
import cn.cucsi.bsd.ucc.service.PbxIvrsService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/pbxIvrs")
public class PbxIvrsController {

    @Autowired
    private PbxIvrsService PbxIvrsService;

    @ApiOperation(value = "根据查询条件获取IVR列表", notes = "根据查询条件获取IVR列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @JsonView(JSONView.PbxIvrsWithDomainAndUser.class)
    public PageResultBean<List<PbxIvrs>> findAll(@RequestBody PbxIvrsCriteria search) {
        return new PageResultBean(this.PbxIvrsService.findAll(search));
    }

    @ApiOperation(value = "根据查询条件获取IVR列表", notes = "根据查询条件获取IVR列表", httpMethod = "POST")
    @RequestMapping(value = "/findAllOfNoPage", method = RequestMethod.POST)
    @JsonView(JSONView.PbxIvrsWithDomainAndUser.class)
    public ResultBean<List<PbxIvrs>> findAllOfNoPage(@RequestBody PbxIvrsCriteria search) {
        return new ResultBean(this.PbxIvrsService.findAllOfNoPage(search));
    }

    @ApiOperation(value = "根据ivrId查询PbxIvrs", notes = "根据ivrId查询PbxIvrs")
    @RequestMapping(value = "/{ivrId}", method = RequestMethod.GET)
    public ResultBean<PbxIvrs> findOne(@PathVariable String ivrId) {
        return new ResultBean<>(this.PbxIvrsService.findOne(ivrId));
    }

    @ApiOperation(value = "根据ivrId删除PbxIvrs", notes = "根据ivrId删除PbxIvrs")
    @RequestMapping(value = "/{ivrId}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String ivrId) {
        return new ResultBean<>(this.PbxIvrsService.delete(ivrId));
    }

    @ApiOperation(value = "创建PbxIvrs", notes = "创建PbxIvrs")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxIvrs PbxIvrs) {
        boolean result = this.PbxIvrsService.save(PbxIvrs) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改PbxIvrs", notes = "修改PbxIvrs")
    @RequestMapping(value = "/{ivrId}", method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String ivrId, @RequestBody PbxIvrs PbxIvrs) {

        boolean result = this.PbxIvrsService.save(PbxIvrs) != null;
        return new ResultBean<>(result);
    }

}
