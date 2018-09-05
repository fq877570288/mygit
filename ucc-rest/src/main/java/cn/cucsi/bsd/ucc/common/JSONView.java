package cn.cucsi.bsd.ucc.common;

/**
 * Created by lanfa on 2017/11/7.
 */
public class JSONView {
    public interface Summary {}

    public interface uccPermissions extends Summary {}


    public interface UccUserWithRole extends Summary {}
    public interface UccUserWithDept extends Summary {}
    public interface UccUserWithExt extends Summary {}
    public interface UccUserWithDeptAndRoleAndExt extends UccUserWithRole,UccUserWithDept,UccUserWithExt  {}

    public interface DomainWithUser extends Summary {}

    public interface PbxIvrsWithDomain extends Summary {}
    public interface PbxIvrsWithUser extends Summary {}
    //角色功能点
    public interface UccRolesWithUccPermissions extends Summary {}

    public interface  SkillGroupUserWithDomain extends  Summary{}


    public  interface PbxIvrsWithDomainAndUser extends  PbxIvrsWithUser,PbxIvrsWithDomain {}

    public interface PbxPbxExtGroupsWithUser extends Summary {}


}
