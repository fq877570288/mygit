package cn.cucsi.bsd.ucc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.DataImportCriteria;
import cn.cucsi.bsd.ucc.common.mapper.DataImportMapper;
import cn.cucsi.bsd.ucc.common.mapper.ImportBatchMapper;
import cn.cucsi.bsd.ucc.data.domain.DataImport;
import cn.cucsi.bsd.ucc.data.domain.ImportBatch;
import cn.cucsi.bsd.ucc.service.DataImportService;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/****
 * 数据导入
 */
@Service
public class DataImportServiceImpl implements DataImportService {
	private static Logger logger = Logger.getLogger(DataImportServiceImpl.class);
	
	@Autowired
	private DataImportMapper dataImportMapper;
	
	@Autowired
	private ImportBatchMapper importBatchMapper;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public int insert(DataImport record) throws Exception {
		return dataImportMapper.insert(record);
	}
	
	@Override
//	@Transactional("txManager")
	public int insertGroup(List<DataImport> list) throws Exception {

		ImportBatch importBatch = new ImportBatch();
		importBatch.setImportBatch(list.get(0).getImportBatch());
		importBatch.setImportPersonId(list.get(0).getImportPersonId());
		importBatch.setImportTime(list.get(0).getImportTime());
		importBatch.setTaskType(list.get(0).getTaskTypeName());
		importBatch.setBatchFlag(ImportBatch.BATCHFLAGN);

		importBatchMapper.insert(importBatch);

		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		DataImportMapper dataImportMapperForBatch = sqlSession.getMapper(DataImportMapper.class);
		try {
			int size = 5000;
			List<List<DataImport>> parts = Lists.partition(list, size);
			parts.stream().forEach(partList -> {
				if (partList != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("list", partList);
					try {
						dataImportMapperForBatch.insertGroup(map);
						sqlSession.commit();
						sqlSession.clearCache();
					}catch(Throwable t){
						logger.error(t.getMessage(), t);
						t.printStackTrace();
						sqlSession.rollback();
					}

					map.clear();
				}
			});
		}catch(Throwable t){
			logger.error(t.getMessage(), t);
			t.printStackTrace();
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		return 1;
	}

	@Override
	public List<DataImport> selectBySearch(DataImportCriteria dataImportCriteria) throws Exception {
		return dataImportMapper.selectBySearch(dataImportCriteria);
	}

	@Override
	public int selectBySearchCount(DataImportCriteria dataImportCriteria) throws Exception {
		return dataImportMapper.selectBySearchCount(dataImportCriteria);
	}

	@Override
	public void deleteByBatch(String taskBatchCode) throws Exception {
		
		Map<String, Object> batchMap = new HashMap<String, Object>();
		batchMap.put("importBarchs", "'"+taskBatchCode+"'");

		dataImportMapper.deleteByBatch(batchMap);

		importBatchMapper.deleteBYBatchs(batchMap);
	}
}
