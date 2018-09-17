package cn.cucsi.bsd.ucc.common.mapper;

import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.data.domain.DataCustomfield;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DataCustomfieldMapper {

    int deleteByPrimaryKey(String dataCustomfieldsId);

    int insert(DataCustomfield record);

    int insertSelective(DataCustomfield record);

    DataCustomfield selectByPrimaryKey(String dataCustomfieldsId);

    int updateByPrimaryKeySelective(DataCustomfield record);

    int updateByPrimaryKey(DataCustomfield record);
    
    /***
	 * 根据主键查询列表
	 */
    List<DataCustomfield> selectByIDs(Map<String, String> map) throws Exception;

    /***
	 * 条件查询列表
	 */
	List<DataCustomfield> selectBySeachMap(Map<String, String> seachMap) throws Exception;

	/***
	 * 查询全部
	 */
	List<DataCustomfield> selectAll() throws Exception;
	
	
    /***
	 * 导出根据主键查询列表
	 */
    List<DataCustomfield> selectByIDsExport(Map<String, String> map) throws Exception;

    /***
	 * 导出条件查询列表
	 */
	List<DataCustomfield> selectBySeachMapExport(Map<String, String> seachMap) throws Exception;

	/***
	 * 导出查询全部
	 */
	List<DataCustomfield> selectAllExport() throws Exception;
}