package bakery;

public class menues {
    private int menuID;
    private String menuTitle;

    public menues() {
    }

    public menues(int menuID, String menuTitle) {
        this.menuID = menuID;
        this.menuTitle = menuTitle;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    @Override
    public String toString() {
        return "menu{" +
                "menuID=" + menuID +
                ", menuTitle='" + menuTitle + '\'' +
                '}';
    }
}
