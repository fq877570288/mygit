package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.ImportBatch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ImportBatchMapper {

	int insert(ImportBatch record) throws Exception;

	List<ImportBatch> selectAll(ImportBatch importBatch) throws Exception;

	void updateFlagByBatch(Map<String, Object> barchsMap) throws Exception;
	
	List<ImportBatch> selectBySearch(Map<String, Object> barchsMap) throws Exception;

	void deleteBYBatchs(Map<String, Object> map) throws Exception;

	List<String> autoSearchTaskBatch(Map<String, Object> map) throws Exception;

	List<ImportBatch> selectAllByBatchFlag(ImportBatch importBatch) throws Exception;

	List<String> selectForTaskJob() throws Exception;

	List<String> selectAllImportBatch() throws Exception;
}
