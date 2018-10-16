package cn.cucsi.bsd.ucc.rest.controllers.taskData;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.ResultBean;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.TaskType;
import cn.cucsi.bsd.ucc.service.TaskTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * coding in zss
 * 2018.10.13
 * 移植自outcall 根据需求已更改
 */
@Api(tags = {"任务类别(移植自outcall _ coding zss)"})
@RestController
@RequestMapping(value = "/taskType")
public class TaskTypeController {
    @Autowired
    private TaskTypeService taskTypeService;
    @ApiOperation(value = "查询任务类别", notes = "查询任务类别，参数为true忽略在办任务的类别", httpMethod = "GET")
    @RequestMapping(value = "/selectAll/{ignoreNotask}", method = RequestMethod.GET)
    public PageResultBean<List<TaskType>> selectAll(@PathVariable String ignoreNotask){
        PageResultBean<List<TaskType>> bean = new PageResultBean<List<TaskType>>();
        try{
            //ignoreNotask 忽略没有任务的任务类型
            bean.setMsg("success");
            List<TaskType> list =  taskTypeService.selectAll(ignoreNotask);
            bean.setData(list);
            return bean;
        }catch(Exception e){
            bean.setMsg("error");
            System.out.println("selectAll查询任务类别失败!");
            e.printStackTrace();
            return bean;
        }
    }
    @ApiOperation(value = "修改任务类别", notes = "修改任务类别", httpMethod = "POST")
    @RequestMapping(value = "/{taskTypeId}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PageResultBean<TaskType> update(@PathVariable String taskTypeId, @RequestBody TaskType taskType){
        PageResultBean<TaskType> bean = new PageResultBean<TaskType>();
        try {
            taskType.setTaskTypeId(taskTypeId);
            bean.setMsg("success");
            taskTypeService.updateByPrimaryKeySelective(taskType);
            return bean;
        } catch (Exception e) {
            bean.setMsg("error");
            System.out.println("修改任务类别失败！");
            e.printStackTrace();
            return bean;
        }
    }
    @ApiOperation(value = "根据taskTypeId删除TaskType", notes = "根据taskTypeId删除TaskType")
    @RequestMapping(value = "/{taskTypeId}", method= RequestMethod.DELETE,produces="application/json;charset=UTF-8")
    public ResultBean<Boolean> delete(@PathVariable String taskTypeId){
        ResultBean<Boolean> bean = new ResultBean<Boolean>();
        try {
            bean.setMsg("success");
            taskTypeService.deleteByPrimaryKey(taskTypeId);
            return bean;
        } catch (Exception e) {
            bean.setMsg("error");
            System.out.println("根据taskTypeId删除TaskType失败！");
            e.printStackTrace();
            return bean;
        }
    }

    @ApiOperation(value = "创建TaskType", notes = "创建TaskType")
    @RequestMapping(value = "", method =  RequestMethod.POST,produces="application/json;charset=UTF-8")
    public ResultBean<Boolean> create(@RequestBody TaskType taskType) {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        taskType.setTaskTypeId(uuidGenerator.generate());
        taskType.setCreateTime(new Date(System.currentTimeMillis()));
        boolean result = false;
        try {
            int num = this.taskTypeService.insertSelective(taskType);
            if(num !=0){
                result =true;
            }
        } catch (Exception e) {
            System.out.println("创建TaskType失败！");
            e.printStackTrace();
        }
        return new ResultBean<>(result);
    }
}