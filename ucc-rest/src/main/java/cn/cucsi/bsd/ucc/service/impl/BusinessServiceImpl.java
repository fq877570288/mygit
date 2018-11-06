package cn.cucsi.bsd.ucc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.TaskTypeCriteria;
import cn.cucsi.bsd.ucc.common.mapper.BusinessMapper;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.TaskType;
import cn.cucsi.bsd.ucc.service.BusinessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class BusinessServiceImpl implements BusinessService {
	
	public static final Map<String, TaskType> taskTypeMap = new HashMap<String, TaskType>();//任务类型缓存
	public static final Map<String, TaskType> taskTypeNameMap = new HashMap<String, TaskType>();//任务类型缓存，按名称


	@Autowired
	private BusinessMapper m;

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		
		int r = m.deleteByPrimaryKey(id);
		putAllToMap();
		return r;
	}

	@Override
	public int insert(TaskType record) throws Exception {
		// TODO Auto-generated method stub
		if(record.getTaskTypeId()==null||record.getTaskTypeId().equals("")){
			UUIDGenerator generator = new UUIDGenerator();
	        String uuid = generator.generate();
	        record.setTaskTypeId(uuid);
		}
		int r = m.insert(record);
		putAllToMap();
		return r;
		
	}

	@Override
	public TaskType selectByPrimaryKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return m.selectByPrimaryKey(id);
	}

	@Override
	public List<TaskType> selectAll(TaskTypeCriteria search) throws Exception {
		// TODO Auto-generated method stub
		return m.selectAll(search);
	}

	@Override
	public int selectBySearchCount(TaskTypeCriteria search) throws Exception {
		// TODO Auto-generated method stub
		return m.selectBySearchCount(search);
	}

	@Override
	@Transactional
	public int deleteByGroup(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		m.deleteByGroup(map);
		putAllToMap();
		return 0;
	}
	
	@Override
	public TaskType selectByIdInCache(String taskTypeId) throws Exception {
		
		if(taskTypeMap.isEmpty()){
			this.putAllToMap();
		}
		if(taskTypeMap.containsKey(taskTypeId)){
			return taskTypeMap.get(taskTypeId);
		}
		TaskType tt = selectByPrimaryKey(taskTypeId);
		putTaskTypeToCache(tt);

		return tt;
	}
	
	@Override
	public TaskType selectByNameInCache(String taskTypeName) throws Exception {

		if(taskTypeNameMap.isEmpty()){
			this.putAllToMap();
		}
		if(taskTypeNameMap.containsKey(taskTypeName)){
			return taskTypeNameMap.get(taskTypeName);
		}

		return null;
	}
	
	@Override
	public void putTaskTypeToCache(TaskType taskType) throws Exception {
		
		if(taskType==null){
			return;
		}
		
		taskTypeMap.put(taskType.getTaskTypeId(), taskType);
		taskTypeNameMap.put(taskType.getTaskTypeName(), taskType);
	}

	public TaskType selectByNameAndDomainIdInCache(String taskTypeName,String domainId) throws Exception {

		if(taskTypeNameMap.isEmpty()){
			this.putAllToMapFroDomainId(domainId);
		}
		if(taskTypeNameMap.containsKey(taskTypeName)){
			return taskTypeNameMap.get(taskTypeName);
		}

		return null;
	}

	private void putAllToMap() throws Exception {
		List<TaskType> taskTypeList =  m.selectAll(null);
		if(taskTypeList==null || taskTypeList.isEmpty()) return;
		
		if(taskTypeMap!=null){
			taskTypeMap.clear();
		}
		if(taskTypeNameMap!=null){
			taskTypeNameMap.clear();
		}
		for(TaskType taskType: taskTypeList){
			taskTypeMap.put(taskType.getTaskTypeId(), taskType);
			taskTypeNameMap.put(taskType.getTaskTypeName(), taskType);
		}
	}
	private void putAllToMapFroDomainId(String domainId) throws Exception {
		TaskTypeCriteria search = new TaskTypeCriteria();
        search.setDomainId(domainId);
		List<TaskType> taskTypeList =  m.selectAllBySearch(search);
		if(taskTypeList==null || taskTypeList.isEmpty()) return;

		if(taskTypeMap!=null){
			taskTypeMap.clear();
		}
		if(taskTypeNameMap!=null){
			taskTypeNameMap.clear();
		}
		for(TaskType taskType: taskTypeList){
			taskTypeMap.put(taskType.getTaskTypeId(), taskType);
			taskTypeNameMap.put(taskType.getTaskTypeName(), taskType);
		}
	}
	@Override
	public String getJson() throws Exception {
		if (taskTypeMap.isEmpty()) {
			putAllToMap();
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(taskTypeMap.values());
		return json;
	}
}
