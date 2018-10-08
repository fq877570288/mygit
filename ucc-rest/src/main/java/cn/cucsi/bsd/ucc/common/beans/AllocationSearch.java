package cn.cucsi.bsd.ucc.common.beans;

import cn.cucsi.bsd.ucc.data.domain.Paging;
import java.util.List;

public class AllocationSearch extends Paging {

	private String meshID;//网格

	private String areaID;//包区

	private String developmentID;//发展部门

	private String taskDetailIds;//任务明细ID

	private Integer taskNumberStart;

	private Integer taskNumberEnd;

	private List<String>  taskDetailIdListForEditDeptList;

	public String getMeshID() {
		return meshID;
	}

	public void setMeshID(String meshID) {
		this.meshID = meshID;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getDevelopmentID() {
		return developmentID;
	}

	public void setDevelopmentID(String developmentID) {
		this.developmentID = developmentID;
	}

	public String getTaskDetailIds() {
		return taskDetailIds;
	}

	public void setTaskDetailIds(String taskDetailIds) {
		this.taskDetailIds = taskDetailIds;
	}

	public Integer getTaskNumberStart() {
		return taskNumberStart;
	}

	public void setTaskNumberStart(Integer taskNumberStart) {
		this.taskNumberStart = taskNumberStart;
	}

	public Integer getTaskNumberEnd() {
		return taskNumberEnd;
	}

	public void setTaskNumberEnd(Integer taskNumberEnd) {
		this.taskNumberEnd = taskNumberEnd;
	}

	public List<String> getTaskDetailIdListForEditDeptList() {
		return taskDetailIdListForEditDeptList;
	}

	public void setTaskDetailIdListForEditDeptList(List<String> taskDetailIdListForEditDeptList) {
		this.taskDetailIdListForEditDeptList = taskDetailIdListForEditDeptList;
	}
}