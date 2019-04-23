package cn.unihotel.rest;

import cn.unihotel.entry.bean.GeneralResponsePage;
import cn.unihotel.entry.request.entry_info_search;
import cn.unihotel.service.demoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restController {
    @Autowired
    demoService demoService;

    @ApiOperation(value="根据查询条件获取受理单列表", notes="根据查询条件获取受理单列表", httpMethod = "GET")
    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public GeneralResponsePage findAll(@RequestBody entry_info_search entry_info_search){
        System.out.println(entry_info_search.toString());
        GeneralResponsePage a = demoService.selectAll(entry_info_search);
        return demoService.selectAll(entry_info_search);
    }
}
