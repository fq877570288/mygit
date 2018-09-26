package cn.cucsi.bsd.ucc.common.beans;

import cn.cucsi.bsd.ucc.data.domain.UccUsers;

import java.util.List;

//根据部门查询用户列表出参

public class UccUserByDept {
    private String deptName;
    private String deptId;
    private List<UccUsers> users;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<UccUsers> getUsers() {
        return users;
    }

    public void setUsers(List<UccUsers> users) {
        this.users = users;
    }
}
