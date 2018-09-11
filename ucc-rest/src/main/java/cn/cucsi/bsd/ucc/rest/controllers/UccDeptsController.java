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
    UccDeptsService uccDeptsService;

    @ApiOperation(value="根据查询条件获取部门列表", notes="根据查询条件获取部门列表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public PageResultBean<List<UccDepts>> findAll(UccDeptsCriteria search) {
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

    @ApiOperation(value="根据查询条件获取部门列表", notes="根据查询条件获取部门列表", httpMethod = "POST")
    @RequestMapping(value = "/deptTree", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<List<UccDepts>> findAllTree(UccDeptsCriteria search) {

        Page<UccDepts> pages = null;
        List<DeptsTree> trees = new ArrayList<DeptsTree>();
        try {
            pages = this.uccDeptsService.findAllTree(search);
            List<UccDepts> list = pages.getContent();
//        List<AllDeptsTreeBean>  allDeptsTreeBeen = new ArrayList<AllDeptsTreeBean>();
//        if(list != null && list.size() > 0){
//            for(int i = 0; i < list.size(); i++){
//                UccDepts uccDept = list.get(i);
//
//                if(uccDept.getDeptPid() == null || "".equals(uccDept.getDeptPid())){
//                    AllDeptsTreeBean bean = new AllDeptsTreeBean();
//                    bean.setLabel(uccDept.getDeptName());
//                    bean.setKey(uccDept.getDeptId());
//                    bean.setValue(uccDept.getDeptId());
//
//                    bean.setChildren(chindernDepts(uccDept.getDeptId()));
//
//
//                    allDeptsTreeBeen.add(bean);
//                }
//            }
//        }
//        return new ResultBean(allDeptsTreeBeen);

        if(list != null && list.size() > 0){

            for(int i = 0; i < list.size(); i++){
                UccDepts uccDept = list.get(i);
                DeptsTree deptsTree = new DeptsTree();
                deptsTree.setKey(uccDept.getDeptId());
                deptsTree.setPid(uccDept.getDeptPid());
                deptsTree.setLabel(uccDept.getDeptName());
                deptsTree.setValue(uccDept.getDeptId());

                trees.add(deptsTree);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据查询条件获取部门列表失败!");
        }
        return new ResultBean(trees);
    }


//    private List<AllDeptsTreeBean> chindernDepts(String  deptId){
//        List<AllDeptsTreeBean> list = new ArrayList<AllDeptsTreeBean>();
//        UccDeptsCriteria criteria = new UccDeptsCriteria();
//        criteria.setDeptPid(deptId);
//        Page<UccDepts> pages = this.uccDeptsService.findAllTree(criteria);
//        List<UccDepts> pDeptList = pages.getContent();
//
//        if(pDeptList != null && pDeptList.size() > 0){
//            for(int i = 0; i< pDeptList.size(); i++){
//                String id = pDeptList.get(i).getDeptId();
//
//                AllDeptsTreeBean bean = new AllDeptsTreeBean();
//                bean.setLabel(pDeptList.get(i).getDeptName());
//                bean.setKey(deptId);
//                bean.setValue(deptId);
//                bean.setChildren(chindernDepts(id));
//
//                list.add(bean);
//            }
//        }
//
//        return list;
//    }

}
