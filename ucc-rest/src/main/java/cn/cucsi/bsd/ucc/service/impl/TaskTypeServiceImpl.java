package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.mapper.TaskTypeMapper;
import cn.cucsi.bsd.ucc.data.domain.TaskType;
import cn.cucsi.bsd.ucc.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskTypeServiceImpl implements TaskTypeService {
    @Autowired
    private TaskTypeMapper taskTypeMapper;
    @Override
    public int deleteByPrimaryKey(String taskTypeId) {
        return taskTypeMapper.deleteByPrimaryKey(taskTypeId);
    }

    @Override
    public int insert(TaskType record) {
        return taskTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(TaskType record) {
        return taskTypeMapper.insertSelective(record);
    }

    @Override
    public TaskType selectByPrimaryKey(String taskTypeId) {
        return taskTypeMapper.selectByPrimaryKey(taskTypeId);
    }

    @Override
    public int updateByPrimaryKeySelective(TaskType record) {
        return taskTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TaskType record) {
        return taskTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TaskType> selectAll(String ignoreNotask) throws Exception {
        return taskTypeMapper.selectAll(ignoreNotask);
    }
}
