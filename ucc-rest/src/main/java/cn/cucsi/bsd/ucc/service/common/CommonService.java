/**
 * 
 */
package cn.cucsi.bsd.ucc.service.common;

import cn.cucsi.bsd.ucc.data.domain.SystemConfig;

import java.util.HashMap;
import java.util.List;

/**
 * @author xdlfh
 *
 */
public interface CommonService {
	String selectccClientsStr();
	String selectccServersStr();
	List<HashMap<String, String>> selectccServersAll();
	List<HashMap<String, String>> selectccClientsAll();
	int saveccClients(SystemConfig systemConfig);
	int saveccServers(SystemConfig systemConfig);
	SystemConfig selectSystemConfigAll();
	void saveOrUpdateSystemConfig(SystemConfig systemConfig);
	SystemConfig selectSystemConfigByName(String name);
	void putSystemConfigToCache(String name, String value);
	String selectByNameInCache(String name);
	boolean isRecordSaveIntoDB();
}
