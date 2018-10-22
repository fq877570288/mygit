package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.TaskTypeForMybatisCriteria;
import cn.cucsi.bsd.ucc.data.domain.TaskType;

import java.util.List;

public interface TaskTypeService {
    int deleteByPrimaryKey(String taskTypeId);

    int insert(TaskType record);

    int insertSelective(TaskType record);

    TaskType selectByPrimaryKey(String taskTypeId);

    int updateByPrimaryKeySelective(TaskType record);

    int updateByPrimaryKey(TaskType record);

    List<TaskType> selectAll(String ignoreNotask) throws Exception;

    PageResultBean_New<List<TaskType>> selectByPage(TaskTypeForMybatisCriteria taskTypeForMybatisCriteria);

    int selectByName(String name);
}
