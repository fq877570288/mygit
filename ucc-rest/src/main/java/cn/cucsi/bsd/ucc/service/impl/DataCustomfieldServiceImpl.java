package cn.cucsi.bsd.ucc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.mapper.DataCustomfieldMapper;
import cn.cucsi.bsd.ucc.common.mapper.UserCustomFieldMapper;
import cn.cucsi.bsd.ucc.data.domain.DataCustomfield;
import cn.cucsi.bsd.ucc.data.domain.UserCustomField;
import cn.cucsi.bsd.ucc.service.DataCustomfieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/****
 * 自定义显示字段
 */
@Service
public class DataCustomfieldServiceImpl implements DataCustomfieldService {
	
	@Autowired
	private DataCustomfieldMapper dataCustomfieldMapper;
	
	@Autowired
	private UserCustomFieldMapper userCustomFieldMapper;

	@Override
	public List<DataCustomfield> selectByIDs(Map<String, String> map) throws Exception {
		return dataCustomfieldMapper.selectByIDs(map);
	}

	@Override
	public List<DataCustomfield> selectImportByUserID(String userId) throws Exception {
		
		List<DataCustomfield> dataCustomfieldList = null;
		String dataCustomfieldsIds = "";

		try {
			// 根据用户ID 查询员工-自定义显示字段中间表
			List<UserCustomField> userCustomFieldList = userCustomFieldMapper.selectImportByUserID(userId);
			if(userCustomFieldList != null && userCustomFieldList.size() > 0){
                for(UserCustomField userCustomField : userCustomFieldList){
                    dataCustomfieldsIds = dataCustomfieldsIds + userCustomField.getDataCustomfieldsId() + ",";
                }
                dataCustomfieldsIds = dataCustomfieldsIds.substring(0, dataCustomfieldsIds.lastIndexOf(","));
                dataCustomfieldsIds = "'" + dataCustomfieldsIds.replaceAll(",", "','") + "'";
            }
			// 根据中间表中存的自定义显示字段主键查询  中间表信息为空  查询所有默认显示字段
			Map<String, String> seachMap = new HashMap<String, String>();
			seachMap.put("dataCustomfieldsIds", dataCustomfieldsIds);
			seachMap.put("isDefault", DataCustomfield.SHOW);

			dataCustomfieldList = dataCustomfieldMapper.selectBySeachMap(seachMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataCustomfieldList;
	}

	@Override
	public List<DataCustomfield> selectAll() throws Exception {
		return dataCustomfieldMapper.selectAll();
	}

	@Override
	public List<DataCustomfield> selectExportByUserID(String userId) throws Exception {
		// TODO Auto-generated method stub
	List<DataCustomfield> dataCustomfieldList = null;
		
		String dataCustomfieldsIds = "";
		
		// 根据用户ID 查询员工-自定义显示字段中间表
		List<UserCustomField> userCustomFieldList = userCustomFieldMapper.selectExportByUserID(userId);
		if(userCustomFieldList != null && userCustomFieldList.size() > 0){
			for(UserCustomField userCustomField : userCustomFieldList){
				dataCustomfieldsIds = dataCustomfieldsIds + userCustomField.getDataCustomfieldsId() + ",";
			}
			
			dataCustomfieldsIds = dataCustomfieldsIds.substring(0, dataCustomfieldsIds.lastIndexOf(","));
			
			dataCustomfieldsIds = "'" + dataCustomfieldsIds.replaceAll(",", "','") + "'";
		}
		
		// 根据中间表中存的自定义显示字段主键查询  中间表信息为空  查询所有默认显示字段   
		Map<String, String> seachMap = new HashMap<String, String>();
		seachMap.put("dataCustomfieldsIds", dataCustomfieldsIds);
		seachMap.put("isDefault", DataCustomfield.SHOW);
		
		dataCustomfieldList = dataCustomfieldMapper.selectBySeachMapExport(seachMap);
		
		return dataCustomfieldList;
	}

	@Override
	public List<DataCustomfield> selectByIDsExport(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return dataCustomfieldMapper.selectByIDsExport(map);
	}

	@Override
	public List<DataCustomfield> selectAllExport() throws Exception {
		// TODO Auto-generated method stub
		return dataCustomfieldMapper.selectAllExport();
	}

}
