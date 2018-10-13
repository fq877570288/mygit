package cn.cucsi.bsd.ucc.service;

import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.AllocationTaskCriteria;
import cn.cucsi.bsd.ucc.data.domain.ImportBatch;

public interface ImportBatchService {

	/***
	 * 查询全部
	 * @return
	 * @throws Exception
	 */
	List<ImportBatch> selectAll(ImportBatch importBatch) throws Exception;

	List<ImportBatch> selectAllByBatchFlag(ImportBatch importBatch) throws Exception;

	List<ImportBatch> selectAllocationAllByBatchFlag(ImportBatch importBatch, AllocationTaskCriteria allocationTaskCriteria) throws Exception;

	List<ImportBatch> selectBySearch(Map<String, Object> barchsMap) throws Exception;

	List<String> selectRecentImportBatch() throws Exception;

	List<ImportBatch> queryImportBatch(String taskTypeName, String recent) throws Exception;
}
