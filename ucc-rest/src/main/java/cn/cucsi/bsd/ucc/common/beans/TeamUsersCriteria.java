package cn.cucsi.bsd.ucc.common.beans;


import io.swagger.annotations.ApiModel;

@ApiModel
public class TeamUsersCriteria extends  BasicCriteria{

    private String usersId;

    private String teamId;

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

}
