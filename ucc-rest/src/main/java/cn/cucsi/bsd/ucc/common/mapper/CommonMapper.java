package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface CommonMapper {
	

    List<HashMap<String, String>> selectccClientsAll();
    List<HashMap<String, String>> selectccServersAll();

    List<HashMap<String, String>> selectSystemConfigAll();
    
    SystemConfig selectSystemConfigByName(String name);
    
    void insertccClients(HashMap<String, String> map);
    void insertccServers(HashMap<String, String> map);
    void insertSystemConfig(SystemConfig systemConfig);
    void updateSystemConfig(SystemConfig systemConfig);
    
    void deleteccClients(String ip);
    void deleteccServers(String ip);

    void deleteAllccClients();
    void deleteAllccServers();

}