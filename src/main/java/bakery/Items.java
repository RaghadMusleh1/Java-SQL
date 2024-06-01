package bakery;

public class Items {
    private int itemID;
    private String itemTitle;
    private double itemPrice;
    private int menuID;

    public Items() {
    }

    public Items(int itemID, String itemTiltle, double itemPrice, int menuID) {
        this.itemID = itemID;
        this.itemTitle = itemTiltle;
        this.itemPrice = itemPrice;
        this.menuID = menuID;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getMenuID() {
        return menuID;
    }

    public int setItemID(int itemId) {
        return this.itemID = itemId;
    }

    public String setItemTitle(String itemTitle) {
        return this.itemTitle = itemTitle;
    }

    public double setItemPrice(double price) {
        return this.itemPrice = price;
    }

    public int setMenuID(int menuId) {
        return this.menuID = menuId;
    }

    @Override
    public String toString() {
        return " Items{" + "itemID=" + itemID + ", itemTitle='" + itemTitle + '\'' + ", itemPrice=" + itemPrice
                + ", menuID=" + menuID + '}';
    }
}
