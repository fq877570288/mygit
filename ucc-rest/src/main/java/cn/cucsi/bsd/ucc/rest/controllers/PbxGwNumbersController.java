package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PbxGwNumbersCriteria;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.PbxGwNumbers;
import cn.cucsi.bsd.ucc.service.PbxGwNumbersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-13.
 */
@RestController
@RequestMapping(value = "/PbxGwNumbers")
public class PbxGwNumbersController {

    @Autowired
    private PbxGwNumbersService PbxGwNumbersService;

    @ApiOperation(value="根据查询条件获取网关大号表", notes="根据查询条件获取网关大号表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<PbxGwNumbers>> findAll(@RequestBody PbxGwNumbersCriteria search){
        return new PageResultBean(this.PbxGwNumbersService.findAll(search));
    }
    @ApiOperation(value="根据查询条件获取网关大号表", notes="根据查询条件获取网关大号表", httpMethod = "POST")
    @RequestMapping(value = "/findAllOfNoPage", method= RequestMethod.POST)
    public ResultBean<List<PbxGwNumbers>> findAllOfNoPage(@RequestBody PbxGwNumbersCriteria search){
        return new ResultBean(this.PbxGwNumbersService.findAllOfNoPage(search));
    }
    @ApiOperation(value = "根据gwNumber查询PbxGwNumbers", notes = "根据gwNumber查询PbxGwNumbers")
    @RequestMapping(value = "/{gwNumber}", method= RequestMethod.GET)
    public ResultBean<PbxGwNumbers> findOne(@PathVariable String gwNumber){
        return new ResultBean<>(this.PbxGwNumbersService.findOne(gwNumber));
    }
    @ApiOperation(value = "根据gwNumber删除PbxGwNumbers", notes = "根据gwNumber删除PbxGwNumbers")
    @RequestMapping(value = "/{gwNumber}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String gwNumber){
        return new ResultBean<>(this.PbxGwNumbersService.delete(gwNumber));
    }
    @ApiOperation(value = "创建PbxGwNumbers", notes = "创建PbxGwNumbers")
    @RequestMapping(value = "" , method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody PbxGwNumbers PbxGwNumbers) {
        boolean result = this.PbxGwNumbersService.save(PbxGwNumbers) != null;
        return new ResultBean<>(result);
    }
    @ApiOperation(value = "修改PbxGwNumbers", notes = "修改PbxGwNumbers")
    @RequestMapping(value = "/{gwNumber}", method= RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String gwNumber,@RequestBody PbxGwNumbers PbxGwNumbers){

        boolean result = this.PbxGwNumbersService.save(PbxGwNumbers) != null;
        return new ResultBean<>(result);
    }

}
