package cn.cucsi.bsd.ucc.service;

/****
 * 员工-自定义显示字段 关联中间表
 */
public interface UserCustomFieldService {
	
	/***
	 * 保存
	 */
	int saveUserCustomField(String customfieldNames, String userId) throws Exception;
	
	int saveUserCustomExportField(String customfieldNames, String userId) throws Exception;

}
