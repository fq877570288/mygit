package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.UserCustomField;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface UserCustomFieldMapper {

    int deleteByPrimaryKey(String userCustomfieldsId);

    int insert(UserCustomField record);

    int insertSelective(UserCustomField record);

    UserCustomField selectByPrimaryKey(String userCustomfieldsId);

    int updateByPrimaryKeySelective(UserCustomField record);

    int updateByPrimaryKey(UserCustomField record);
    
    /***
	 * 批量插入
	 */
    int insertGroup(List<UserCustomField> list) throws Exception;

    /***
	 * 根据员工ID　删除导入
	 */
	int deleteImportByUserID(String userId) throws Exception;
	
	/***
	 * 根据员工ID　删除导出
	 */
	int deleteExportByUserID(String userId) throws Exception;

	/***
	 * 根据员工ID　查询导入
	 */
	List<UserCustomField> selectImportByUserID(String userId) throws Exception;
	
	/***
	 * 根据员工ID　查询导出
	 */
	List<UserCustomField> selectExportByUserID(String userId) throws Exception;
}