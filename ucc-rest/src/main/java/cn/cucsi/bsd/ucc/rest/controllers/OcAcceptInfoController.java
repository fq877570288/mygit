package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.OcAcceptInfoCriteria;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.OcAcceptInfo;
import cn.cucsi.bsd.ucc.data.domain.PbxCallTransfer;
import cn.cucsi.bsd.ucc.service.OcAcceptInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tnn on 2018/10/25.
 */
@RestController
@RequestMapping(value = "/OcAcceptInfo")
public class OcAcceptInfoController {
    @Autowired
    private OcAcceptInfoService ocAcceptInfoService;
    @ApiOperation(value="根据查询条件获取受理单列表", notes="根据查询条件获取受理单列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean_New<List<OcAcceptInfo>> findAll(@RequestBody OcAcceptInfoCriteria criteria){
        Page pageInfo = PageHelper.startPage(criteria.getPage(), criteria.getSize());
        List<OcAcceptInfo> list = ocAcceptInfoService.findAll(criteria);
        PageResultBean_New<List<OcAcceptInfo>> pageResultBean_new = new PageResultBean_New(pageInfo);
        pageResultBean_new.setList(list);
        return pageResultBean_new;
    }
    @ApiOperation(value = "创建受理单", notes = "创建受理单")
    @RequestMapping(value = "/create", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody OcAcceptInfo ocAcceptInfo) {
        // 主键
        UUIDGenerator generator = new UUIDGenerator();
        String uuid=generator.generate();
        ocAcceptInfo.setId(uuid);
        boolean result = false;
        result = this.ocAcceptInfoService.create(ocAcceptInfo) >0;
        if(result){
          return   new ResultBean<>(0,"添加成功");
        }else{
            return   new ResultBean<>(1,"添加失败");
        }
    }
}
