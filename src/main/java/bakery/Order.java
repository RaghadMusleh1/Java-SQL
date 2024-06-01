package bakery;

public class Order {
    public String itemName;
    public double itemPrice;
    public int itemQuantity;

    public Order() {

    }

    public Order(String name, double price, int qunt) {
        this.itemName = name;
        this.itemPrice = price;
        this.itemQuantity = qunt;
    }

    void setName(String name) {
        this.itemName = name;
    }

    void setPrice(double price) {
        this.itemPrice = price;
    }

    void setQuant(int quant) {
        this.itemQuantity = quant;
    }

    public String getName() {
        return itemName;
    }

    public double getPrice() {
        return itemPrice;
    }

    public int getQuant() {
        return itemQuantity;
    }

}
