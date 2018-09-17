package cn.cucsi.bsd.ucc.service;

import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.data.domain.DataCustomfield;

/****
 * 自定义显示字段
 */
public interface DataCustomfieldService {

	/***
	 * 根据编码查询列表
	 */
    List<DataCustomfield> selectByIDs(Map<String, String> map) throws Exception;
    
	/***
	 * 根据导出编码查询列表
	 */
    List<DataCustomfield> selectByIDsExport(Map<String, String> map) throws Exception;

    /***
	 * 获取用户数据导入自定义显示字段
	 */
	List<DataCustomfield> selectImportByUserID(String userId) throws Exception;

    /***
	 * 获取用户导出自定义显示字段
	 */
	List<DataCustomfield> selectExportByUserID(String userId) throws Exception;
	
	/***
	 * 查询全部
	 */
	List<DataCustomfield> selectAll() throws Exception;
	
	/***
	 * 查询导出全部
	 */
	List<DataCustomfield> selectAllExport() throws Exception;
}
