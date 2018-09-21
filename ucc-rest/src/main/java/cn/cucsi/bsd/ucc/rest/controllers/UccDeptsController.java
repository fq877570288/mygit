package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
//import cn.cucsi.bsd.ucc.data.beans.AllDeptsTreeBean;
import cn.cucsi.bsd.ucc.data.beans.DeptsTree;
import cn.cucsi.bsd.ucc.data.domain.UccClients;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianyuwei on 2017/10/16.
 */

@RestController
@RequestMapping(value = "/uccDepts")
public class UccDeptsController {

    @Autowired
    private UccDeptsService uccDeptsService;

    @ApiOperation(value="根据查询条件获取部门列表（不分页了查全部）", notes="根据查询条件获取部门列表（不分页了查全部）", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public PageResultBean<List<UccDepts>> findAll(@RequestBody UccDeptsCriteria search) {
        try {
            return new PageResultBean(this.uccDeptsService.findAll(search));
        } catch (Exception e) {
            System.out.println("根据查询条件获取部门列表失败！");
            e.printStackTrace();
            return new PageResultBean<List<UccDepts>>();
        }
    }

    @ApiOperation(value = "根据deptId查询UccDepts", notes = "根据deptId查询UccDepts")
    @RequestMapping(value = "/{deptId}", method = RequestMethod.POST)
    public ResultBean<UccDepts> findOne(@PathVariable String deptId){
        try {
            return new ResultBean<>(this.uccDeptsService.findOne(deptId));
        } catch (Exception e) {
            System.out.println("根据deptId查询UccDepts失败！");
            e.printStackTrace();
            return new ResultBean<UccDepts>();
        }
    }

    @ApiOperation(value = "根据deptId删除UccDepts", notes = "根据deptId删除UccDepts")
    @RequestMapping(value = "/{deptId}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String deptId){
        try {
            return new ResultBean<>(this.uccDeptsService.delete(deptId));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据deptId删除UccDepts失败！");
            return new ResultBean<Boolean>();
        }
    }

    @ApiOperation(value = "创建UccDepts", notes = "创建UccDepts")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody UccDepts uccDepts) {
        boolean result = false;
        try {
            result = this.uccDeptsService.save(uccDepts) != null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建UccDepts失败！");
        }
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccDepts", notes = "修改UccDepts")
    @RequestMapping(value = "/{deptId}", method =  RequestMethod.PUT)
    public ResultBean<UccDepts> save(@PathVariable String deptId,@RequestBody UccDepts uccDepts){
        try {
            return new ResultBean<>(this.uccDeptsService.save(uccDepts));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("修改UccDepts失败！");
            return new ResultBean<UccDepts>();
        }
    }
    //zss
    @ApiOperation(value="根据查询条件获取部门列表", notes="根据查询条件获取部门列表", httpMethod = "POST")
    @RequestMapping(value = "/deptTree", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<List<UccDepts>> findAllTree(UccDeptsCriteria search) {
        try {
            ResultBean<List<UccDepts>> bean = new PageResultBean(this.uccDeptsService.findAllTree(search));
            List<UccDepts> list = bean.getData();
            List<UccDepts> listOne = new ArrayList<UccDepts>();
            if(list!=null&&list.size()!=0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null && list.get(i).getDeptPid().equals("0")) {//顶级
                        listOne.add(list.get(i));
                    }
                }
                if(listOne.size()!=0){
                    for(int i=0;i<listOne.size();i++){
                        queryChildren(listOne.get(i),list);
                    }
                }
            }
            bean.setData(listOne);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据查询条件获取部门列表失败!");
        }
        return new PageResultBean<List<UccDepts>>();
    }
    //zss
    public void queryChildren(UccDepts uccDepts,List<UccDepts> list){
        List<UccDepts> Childrens = new ArrayList<UccDepts>();
        if(list.size()!=0){
            for(int i=0;i<list.size();i++){
                if(uccDepts!=null&&uccDepts.getDeptId().equals(list.get(i).getDeptPid())){
                    Childrens.add(list.get(i));
                    System.out.println(uccDepts.getDeptId()+" "+list.get(i).getDeptPid()+uccDepts.getDeptId().equals(list.get(i).getDeptPid()));
                }
            }
            if(Childrens.size()!=0){
                uccDepts.setDepts(Childrens);
                for(int a = 0;a<Childrens.size();a++){
                    queryChildren(Childrens.get(a),list);
                }
            }
        }

    }
}
