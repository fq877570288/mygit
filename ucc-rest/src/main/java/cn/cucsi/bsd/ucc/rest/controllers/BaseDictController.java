package cn.cucsi.bsd.ucc.rest.controllers;


import cn.cucsi.bsd.ucc.common.beans.BaseDictCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.data.domain.BaseDict;
import cn.cucsi.bsd.ucc.data.domain.BaseDictPK;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.util.List;


@RestController
@RequestMapping(value = "/baseDict")
public class BaseDictController {

    @Autowired
    private BaseDictService baseDictService;

    @ApiOperation(value="根据查询条件获取基础参数码表", notes="根据查询条件获取基础参数码表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public PageResultBean<List<BaseDict>> findAll(@ModelAttribute BaseDictCriteria search){
        return new PageResultBean(this.baseDictService.findAll(search));
    }

    @ApiOperation(value = "根据dictType、dictKey查询baseDict", notes = "根据dictType、dictKey查询baseDict")
    @RequestMapping(value = "/{dictType}/{dictKey}", method= RequestMethod.GET)
    public ResultBean<BaseDict> findOne(@PathVariable String dictType,@PathVariable Integer dictKey){
        BaseDictPK baseDictPK =new BaseDictPK();
        baseDictPK.setDictType(dictType);
        baseDictPK.setDictKey(dictKey);
        return new ResultBean<>(this.baseDictService.findOne(baseDictPK));
    }

    @ApiOperation(value = "根据dictType、dictKey删除baseDict", notes = "根据dictType、dictKey删除baseDict")
    @RequestMapping(value = "/{dictType}/{dictKey}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String dictType,@PathVariable Integer dictKey){
        BaseDictPK baseDictPK =new BaseDictPK();
        baseDictPK.setDictType(dictType);
        baseDictPK.setDictKey(dictKey);
        return new ResultBean<>(this.baseDictService.delete(baseDictPK));
    }

    @ApiOperation(value = "创建baseDict", notes = "创建baseDict")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody BaseDict baseDict){
        Boolean result = this.baseDictService.save(baseDict) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改baseDict", notes = "修改baseDict")
    @RequestMapping(value = "/{dictType}/{dictKey}", method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String dictType,@PathVariable Integer dictKey,@RequestBody BaseDict baseDict){
        Boolean result = this.baseDictService.save(baseDict) != null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "根据dictType查询baseDict list", notes = "根据dictType查询baseDict list")
    @RequestMapping(value = "/{dictType}", method= RequestMethod.GET)
    public ResultBean<List<BaseDict>> findAllByDictType(@PathVariable  String dictType){
        List<BaseDict> baseDicts = this.baseDictService.findAllByDictType(dictType);
        return new ResultBean<>(baseDicts);
    }

}
