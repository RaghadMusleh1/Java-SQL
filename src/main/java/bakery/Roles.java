package bakery;

public class Roles {

    private int roleID;
    private String roleType;

    public Roles(int roleID, String roleType) {
        this.roleID = roleID;
        this.roleType = roleType;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

}
