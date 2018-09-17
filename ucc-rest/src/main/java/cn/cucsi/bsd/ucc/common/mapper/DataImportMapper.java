package cn.cucsi.bsd.ucc.common.mapper;

import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.DataImportCriteria;
import cn.cucsi.bsd.ucc.data.domain.DataImport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DataImportMapper {

    int deleteByPrimaryKey(String dataImportId);

    int insert(DataImport record);

    int insertSelective(DataImport record);

    DataImport selectByPrimaryKey(String dataImportId);

    int updateByPrimaryKeySelective(DataImport record);

    int updateByPrimaryKey(DataImport record);
    
    // 批量插入
    int insertGroup(Map<String, Object> map) throws Exception;
    
    // 分页查询
    List<DataImport> selectBySearch(DataImportCriteria dataImportCriteria) throws Exception;
    
    int selectBySearchCount(DataImportCriteria dataImportCriteria) throws Exception;

    // 查询全部
	List<DataImport> selectAll() throws Exception;

	// 按批次查询
	List<DataImport> selectByBarchs(Map<String, Object> barchsMap) throws Exception;

	// 按批次删除数据
	int deleteByBatch(Map<String, Object> barchsMap) throws Exception;
	
}