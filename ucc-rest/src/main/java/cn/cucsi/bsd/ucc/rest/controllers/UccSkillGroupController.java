package cn.cucsi.bsd.ucc.rest.controllers;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccSkillGroupCriteria;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.UccSkillGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@RestController
@RequestMapping(value = "/uccSkillGroup")
public class UccSkillGroupController {
    @Autowired
    UccSkillGroupService uccSkillGroupService;

    @ApiOperation(value="根据查询条件获取技能组表", notes="根据查询条件获取技能组表", httpMethod = "POST")
    @RequestMapping(value = "/findAll", method= RequestMethod.POST)
    public PageResultBean<List<UccSkillGroup>> findAll(@RequestBody UccSkillGroupCriteria search){
        PageResultBean<List<UccSkillGroup>> list= new PageResultBean(this.uccSkillGroupService.findAll(search));
        return new PageResultBean(this.uccSkillGroupService.findAll(search));
    }

    @ApiOperation(value = "根据skillGroupId查询UccSkillGroup", notes = "根据skillGroupId查询UccSkillGroup")
    @RequestMapping(value = "/{skillGroupId}", method= RequestMethod.POST)
    public ResultBean<UccSkillGroup> findOne(@RequestBody String skillGroupId){
        return new ResultBean<>(this.uccSkillGroupService.findOne(skillGroupId));
    }

    @ApiOperation(value = "根据skillGroupId删除uccSkillGroup", notes = "根据skillGroupId删除uccSkillGroup")
    @RequestMapping(value = "/{skillGroupId}", method= RequestMethod.DELETE)
    public ResultBean<Boolean> delete(@PathVariable String skillGroupId){
        return new ResultBean<>(this.uccSkillGroupService.delete(skillGroupId));
    }

    @ApiOperation(value = "创建uccSkillGroup", notes = "创建uccSkillGroup")
    @RequestMapping(value = "", method =  RequestMethod.POST)
    public ResultBean<Boolean> create(@RequestBody  UccSkillGroup uccSkillGroup) {
        uccSkillGroup.setStatus("1");
        boolean result=this.uccSkillGroupService.save(uccSkillGroup)!=null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "修改UccSkillGroup", notes = "修改UccSkillGroup")
    @RequestMapping(value = "/{skillGroupId}",method = RequestMethod.PUT)
    public ResultBean<Boolean> save(@PathVariable String skillGroupId, @RequestBody  UccSkillGroup uccSkillGroup){
        boolean result=this.uccSkillGroupService.save(uccSkillGroup)!=null;
        return new ResultBean<>(result);
    }

    @ApiOperation(value = "更新UccSkillGroup的状态", notes = "更新UccSkillGroup的状态")
    @RequestMapping(value = "/${skillGroupId}/${status}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> updateStatusById( @PathVariable String status,@PathVariable String skillGroupId){
        return new ResultBean<>(this.uccSkillGroupService.updateStatusById( status,skillGroupId));
    }

    @ApiOperation(value="根据查询获取技能组树", notes="根据查询获取技能组树", httpMethod = "POST")
    @RequestMapping(value = "/tree", method= RequestMethod.POST)
    public PageResultBean<List<UccSkillGroup>> tree(@RequestBody UccSkillGroupCriteria search){
        try {
            search.setStatus("1");
            PageResultBean<List<UccSkillGroup>> uccSkillGroupList= new PageResultBean(this.uccSkillGroupService.findAllTree(search));
            List<UccSkillGroup> list = uccSkillGroupList.getData();
            List<UccSkillGroup> pIdList = new ArrayList<>();
            if(list!=null&&list.size()!=0) {
                //父ID
                for (UccSkillGroup uccPermissions : list) {
                    if (uccPermissions.getSkillGroupPid() == null || "".equals(uccPermissions.getSkillGroupPid()) || "0".equals(uccPermissions.getSkillGroupPid())) {
                        pIdList.add(uccPermissions);
                    }
                }
                //往父ID中添加子节点
                if(pIdList.size()!=0){
                    for(int i=0;i<pIdList.size();i++){
                        queryChildren(pIdList.get(i),list);
                    }
                }
                uccSkillGroupList.setData(pIdList);
                return uccSkillGroupList;
            }
        } catch (Exception e) {
            System.out.println("根据查询获取技能组树异常："+e);
        }
        return new PageResultBean(this.uccSkillGroupService.findAllTree(search));
    }

    public void queryChildren(UccSkillGroup uccSkillGroup, List<UccSkillGroup> list){
        List<UccSkillGroup> Childrens = new ArrayList<UccSkillGroup>();
        if(list.size()!=0){
            for(int i=0;i<list.size();i++){
                if(uccSkillGroup!=null&&uccSkillGroup.getSkillGroupId().equals(list.get(i).getSkillGroupPid())){
                    Childrens.add(list.get(i));
                }
            }
            if(Childrens.size()!=0){
                uccSkillGroup.setUccSkillGroups(Childrens);
                for(int a = 0;a<Childrens.size();a++){
                    queryChildren(Childrens.get(a),list);
                }
            }
        }
    }

}
