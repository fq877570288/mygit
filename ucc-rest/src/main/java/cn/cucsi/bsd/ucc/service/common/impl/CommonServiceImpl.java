/**
 * 
 */
package cn.cucsi.bsd.ucc.service.common.impl;

import cn.cucsi.bsd.ucc.common.mapper.CommonMapper;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import cn.cucsi.bsd.ucc.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xdlfh
 *
 */

@Service
public class CommonServiceImpl implements CommonService {

	public static final Map<String, String> systemConfigMap = new HashMap<String, String>();//用户缓存表
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Override
	public SystemConfig selectSystemConfigByName(String name){
		return this.commonMapper.selectSystemConfigByName(name);
	}
	
	public List<HashMap<String, String>>selectccClientsAll(){
		return this.commonMapper.selectccClientsAll();
	}
	
	public List<HashMap<String, String>>selectccServersAll(){
		return this.commonMapper.selectccServersAll();
	}

	@Override
	public String selectccClientsStr() {
		List<HashMap<String, String>> list = commonMapper.selectccClientsAll();
		if(list==null || list.isEmpty()) return null;
		
		StringBuilder _result = new StringBuilder();
		for(HashMap<String, String> map: list){
			_result.append(map.get("name")).append(":").append(map.get("ip")).append(",");
		}
		_result.deleteCharAt(_result.lastIndexOf(","));
		
		return _result.toString();
	}

	@Override
	public String selectccServersStr() {
		List<HashMap<String, String>> list = commonMapper.selectccServersAll();
		if(list==null || list.isEmpty()) return null;
		
		StringBuilder _result = new StringBuilder();
		for(HashMap<String, String> map: list){
			_result.append(map.get("name")).append(":").append(map.get("ip")).append(",");
		}
		_result.deleteCharAt(_result.lastIndexOf(","));
		
		return _result.toString();
	}
	
	
	
	@Transactional
	@Override
	public int saveccClients(SystemConfig systemConfig){
		if(systemConfig==null){
			return 0;
		}
		
		String ccClients = systemConfig.getCcClients();
		if(ccClients!=null&&!ccClients.isEmpty()){
			String[] clientsArr = ccClients.split(",");
			if(clientsArr.length!=0){
				this.commonMapper.deleteAllccClients();
				for(String client: clientsArr){
					String[] keyValArr = client.split(":");
					if(keyValArr.length==2){
						HashMap<String, String> map= new HashMap<String, String>();
						map.put("name", keyValArr[0]);
						map.put("ip", keyValArr[1]);
						
						this.commonMapper.insertccClients(map);
					}
				}
			}
			
		}

		return 1;
	}
	
	@Transactional
	@Override
	public int saveccServers(SystemConfig systemConfig){
		if(systemConfig==null){
			return 0;
		}

		String ccServers = systemConfig.getCcServers();
		if(ccServers!=null&&!ccServers.isEmpty()){
			String[] serversArr = ccServers.split(",");
			if(serversArr.length!=0){
	            this.commonMapper.deleteAllccServers();
	            for(String server: serversArr){
	                String[] keyValArr = server.split(":");
	                if(keyValArr.length==2){
	                    HashMap<String, String> map= new HashMap<String, String>();
	                    map.put("name", keyValArr[0]);
	                    map.put("ip", keyValArr[1]);
	                    
	                    this.commonMapper.insertccServers(map);
	                }
	            }
	        }
		}
		
		return 1;
	}

	@Transactional
	@Override
	public SystemConfig selectSystemConfigAll(){
		List<HashMap<String, String>> _list =  this.commonMapper.selectSystemConfigAll();
		if(_list==null||_list.isEmpty()) return null;
		SystemConfig systemConfig = new SystemConfig();
		for(HashMap<String, String> _map: _list){
			String name = _map.get("name");
			String value = _map.get("value");
			
			if("recordSaveType".equals(name)){
				systemConfig.setRecordSaveType(value);
				continue;
			}
			
			if("recordPath".equals(name)){
				systemConfig.setRecordPath(value);
				continue;
			}
			
			if("noticeFilePath".equals(name)){
				systemConfig.setNoticeFilePath(value);
				continue;
			}
			if("delMusic".equals(name)){
				systemConfig.setDelMusic(value);
				continue;
			}
			if("delTask".equals(name)){
				systemConfig.setDelTask(value);
				continue;
			}
			if("templateSMS".equals(name)){
				systemConfig.setTemplateSMS(value);
				continue;
			}
			if("externalHearURL".equals(name)){
				systemConfig.setExternalHearURL(value);
				continue;
			}
		}
		
		return systemConfig;
	}

	@Transactional
	@Override
	public void saveOrUpdateSystemConfig(SystemConfig systemConfig){
		
		if(systemConfig.getRecordSaveType()!=null && !systemConfig.getRecordSaveType().isEmpty()){
			
			systemConfig.setName("recordSaveType");
			systemConfig.setValue(systemConfig.getRecordSaveType());
			
			SystemConfig localConfig = this.commonMapper.selectSystemConfigByName("recordSaveType");
			if(localConfig==null){
				this.commonMapper.insertSystemConfig(systemConfig);
			}else{
				this.commonMapper.updateSystemConfig(systemConfig);
			}
		}
		
		else if(systemConfig.getRecordPath()!=null && !systemConfig.getRecordPath().isEmpty()){
			
			systemConfig.setName("recordPath");
			systemConfig.setValue(systemConfig.getRecordPath());
			
			SystemConfig localConfig = this.commonMapper.selectSystemConfigByName("recordPath");
			if(localConfig==null){
				this.commonMapper.insertSystemConfig(systemConfig);
			}else{
				this.commonMapper.updateSystemConfig(systemConfig);
			}
		}

		else if(systemConfig.getNoticeFilePath()!=null && !systemConfig.getNoticeFilePath().isEmpty()){
			
			systemConfig.setName("noticeFilePath");
			systemConfig.setValue(systemConfig.getNoticeFilePath());
			
			SystemConfig localConfig = this.commonMapper.selectSystemConfigByName("noticeFilePath");
			if(localConfig==null){
				this.commonMapper.insertSystemConfig(systemConfig);
			}else{
				this.commonMapper.updateSystemConfig(systemConfig);
			}
		}
		
		else if(systemConfig.getDelMusic()!=null && !systemConfig.getDelMusic().isEmpty()){
			
			systemConfig.setName("delMusic");
			systemConfig.setValue(systemConfig.getDelMusic());
			
			SystemConfig localConfig = this.commonMapper.selectSystemConfigByName("delMusic");
			if(localConfig==null){
				this.commonMapper.insertSystemConfig(systemConfig);
			}else{
				this.commonMapper.updateSystemConfig(systemConfig);
			}
		}
		
		else if(systemConfig.getDelTask()!=null && !systemConfig.getDelTask().isEmpty()){
			
			systemConfig.setName("delTask");
			systemConfig.setValue(systemConfig.getDelTask());
			
			SystemConfig localConfig = this.commonMapper.selectSystemConfigByName("delTask");
			if(localConfig==null){
				this.commonMapper.insertSystemConfig(systemConfig);
			}else{
				this.commonMapper.updateSystemConfig(systemConfig);
			}
		}
		
		else if(systemConfig.getTemplateSMS()!=null && !systemConfig.getTemplateSMS().isEmpty()){
			
			systemConfig.setName("templateSMS");
			systemConfig.setValue(systemConfig.getTemplateSMS());
			
			SystemConfig localConfig = this.commonMapper.selectSystemConfigByName("templateSMS");
			if(localConfig==null){
				this.commonMapper.insertSystemConfig(systemConfig);
			}else{
				this.commonMapper.updateSystemConfig(systemConfig);
			}
		}
		
		else if(systemConfig.getExternalHearURL()!=null && !systemConfig.getExternalHearURL().isEmpty()){
			
			systemConfig.setName("externalHearURL");
			systemConfig.setValue(systemConfig.getExternalHearURL());
			
			SystemConfig localConfig = this.commonMapper.selectSystemConfigByName("externalHearURL");
			if(localConfig==null){
				this.commonMapper.insertSystemConfig(systemConfig);
			}else{
				this.commonMapper.updateSystemConfig(systemConfig);
			}
		}
		
		putSystemConfigToCache(systemConfig.getName(), systemConfig.getValue());
		
	}
	
	private void putAllToMap() {
		List<HashMap<String, String>> _list =  this.commonMapper.selectSystemConfigAll();
		if(_list==null || _list.isEmpty()) return;
		
		for(HashMap<String, String> systemConfig: _list){
			systemConfigMap.put(systemConfig.get("name"), systemConfig.get("value"));
		}
	}
	
	public String selectByNameInCache(String name) {
		
		if(systemConfigMap.isEmpty()){
			this.putAllToMap();
		}
		if(systemConfigMap.containsKey(name)){
			return systemConfigMap.get(name);
		}

		return null;
		
	}
	
	@Override
	public boolean isRecordSaveIntoDB(){
		String recordSaveType = this.selectByNameInCache("recordSaveType");
		if("1".equals(recordSaveType)){//保存录音文件到数据库
			return true;
		}else if("2".equals(recordSaveType)){//保存录音文件到磁盘
			return false;
		}
		
		return true;
	}
	
	@Override
	public void putSystemConfigToCache(String name, String value) {
		
		if(name==null || value==null){
			return;
		}
		
		systemConfigMap.put(name, value);
	}

}
