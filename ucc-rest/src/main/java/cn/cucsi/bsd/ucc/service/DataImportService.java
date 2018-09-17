package cn.cucsi.bsd.ucc.service;

import java.util.List;
import cn.cucsi.bsd.ucc.common.beans.DataImportCriteria;
import cn.cucsi.bsd.ucc.data.domain.DataImport;

/****
 * 数据导入
 */
public interface DataImportService {

	/***
	 * 插入
	 */
    int insert(DataImport record) throws Exception;
    
    /***
	 * 批量插入
	 */
    int insertGroup(List<DataImport> list) throws Exception;

    /***
	 * 分页查询
	 */
    List<DataImport> selectBySearch(DataImportCriteria dataImportCriteria) throws Exception;
    
    int selectBySearchCount(DataImportCriteria dataImportCriteria) throws Exception;

	void deleteByBatch(String taskBatchCode) throws Exception;

}
