package cn.cucsi.bsd.ucc.common.beans;

import cn.cucsi.bsd.ucc.data.domain.DataCustomfield;
import cn.cucsi.bsd.ucc.data.domain.Paging;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/****
 * 数据导入搜索条件
 */
@ApiModel
public class DataImportCriteria extends Paging {

	@ApiModelProperty(value = "？",required = false)
	private List<DataCustomfield> DataCustomfields;

	@ApiModelProperty(value = "业务编码",required = false)
	private String businessCode; //业务号码

	@ApiModelProperty(value = "坐席员ID",required = false)
	private String userId; //坐席员ID

	@ApiModelProperty(value = "任务类型名称",required = false)
    private String taskTypeName; //任务类型名称

	@ApiModelProperty(value = "网给名称",required = false)
    private String deptMeshName; //网给名称

	@ApiModelProperty(value = "包区名称",required = false)
    private String deptAreaName; //包区名称

	@ApiModelProperty(value = "发展部门",required = false)
    private String developmentDept; //发展部门

	@ApiModelProperty(value = "导入人",required = false)
    private String importPersonId; //导入人

	public String getImportPersonId() {
		return importPersonId;
	}

	public void setImportPersonId(String importPersonId) {
		this.importPersonId = importPersonId;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public String getDeptMeshName() {
		return deptMeshName;
	}

	public void setDeptMeshName(String deptMeshName) {
		this.deptMeshName = deptMeshName;
	}

	public String getDeptAreaName() {
		return deptAreaName;
	}

	public void setDeptAreaName(String deptAreaName) {
		this.deptAreaName = deptAreaName;
	}

	public String getDevelopmentDept() {
		return developmentDept;
	}

	public void setDevelopmentDept(String developmentDept) {
		this.developmentDept = developmentDept;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<DataCustomfield> getDataCustomfields() {
		return DataCustomfields;
	}

	public void setDataCustomfields(List<DataCustomfield> dataCustomfields) {
		DataCustomfields = dataCustomfields;
	}
}
