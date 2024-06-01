package bakery;

public class Users {
    private int userID;
    private String userName;
    private String password;
    private int userRoleID;

    public Users(int userID, String userName, String password, int userRole) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.userRoleID = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserPassword() {
        return password;
    }

    public int getUserRoleID() {
        return userRoleID;
    }
}
