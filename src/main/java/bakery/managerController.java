package bakery;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static bakery.App.items;
import static bakery.App.setRoot;
import static bakery.loginController.orderedItems;

public class managerController {

    @FXML
    private Text sellerRole;
    @FXML
    private Text seller;
    @FXML
    public Text totalPrice;

    @FXML
    private Button itemBtn1, itemBtn2, itemBtn3, itemBtn4, itemBtn5, itemBtn6, itemBtn7, itemBtn8, one, two;

    private List<Button> itemButtons;

    @FXML
    private Text userName;
    @FXML
    private TableView<Order> tableItems;
    @FXML
    private TableColumn<Order, String> itemTitle;
    @FXML
    private TableColumn<Order, Integer> itemPrice;
    @FXML
    private TableColumn<Order, Integer> itemQuantity;
    @FXML
    public static ObservableList<Order> orderedItemsManger;

    public String operation = "";
    public int number = -1;
    public int meal = 0;
    public int lastMenu = 0;
    public double comboPrice = 0;
    public int orderdone = -1;
    ArrayList<String> combo = new ArrayList<>();

    public void initialize() {
        userName.setText(loginController.currentUsername);
        orderedItemsManger = orderedItems;
        itemButtons = new ArrayList<>();
        itemButtons.add(itemBtn1);
        itemButtons.add(itemBtn2);
        itemButtons.add(itemBtn3);
        itemButtons.add(itemBtn4);
        itemButtons.add(itemBtn5);
        itemButtons.add(itemBtn6);
        itemButtons.add(itemBtn7);
        itemButtons.add(itemBtn8);

        tableItems.setItems(orderedItemsManger);

        itemTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemQuantity.setCellValueFactory(new PropertyValueFactory<>("quant"));
        orderdone = 0;

        seller.setText(loginController.currentUsername);
        if (loginController.currentUserRole == 1) {
            sellerRole.setText("Supervisor");
        } else {
            sellerRole.setText("Employee");
        }

        updateTotalPrice();

    }

    @FXML
    void one(ActionEvent event) throws IOException {
        number = 1;
    }

    @FXML
    void two(ActionEvent event) throws IOException {
        number = 2;
    }

    @FXML
    void three(ActionEvent event) throws IOException {
        number = 3;
    }

    @FXML
    void four(ActionEvent event) throws IOException {
        number = 4;
    }

    @FXML
    void five(ActionEvent event) throws IOException {
        number = 5;
    }

    @FXML
    void six(ActionEvent event) throws IOException {
        number = 6;

    }

    @FXML
    void seven(ActionEvent event) throws IOException {
        number = 7;

    }

    @FXML
    void eight(ActionEvent event) throws IOException {
        number = 8;

    }

    @FXML
    void nine(ActionEvent event) throws IOException {
        number = 9;
    }

    @FXML
    void zero(ActionEvent event) throws IOException {
        number = 0;
    }

    @FXML
    void div(ActionEvent event) throws IOException {
        operation = "/";

    }

    @FXML
    void mul(ActionEvent event) throws IOException {
        operation = "*";
    }

    @FXML
    void plus(ActionEvent event) throws IOException {
        operation = "+";
    }

    @FXML
    void minus(ActionEvent event) throws IOException {
        operation = "-";
    }

    @FXML
    void BS(ActionEvent event) throws IOException {
        operation = "BS";
    }

    @FXML
    void Enter(ActionEvent event) throws IOException {
        operation = "enter";
    }

    @FXML
    void dot(ActionEvent event) throws IOException {
        operation = "dot";
    }

    @FXML
    void clearItemButttons(ActionEvent event) {
        for (Button btn : itemButtons) {
            btn.setVisible(false);
        }
    }

    @FXML
    void menuOne(ActionEvent event) throws IOException {
        clearItemButttons(event);
        int i = 0;
        meal = 1;
        lastMenu = 1;
        for (Items item : items) {
            if (item.getMenuID() == 1) {
                itemButtons.get(i).setText(item.getItemTitle() + "\n" + item.getItemPrice() + "₪");
                itemButtons.get(i).setVisible(true);
                i++;
            }
        }
    }

    @FXML
    void menuTwo(ActionEvent event) throws IOException {
        int i = 0;
        lastMenu = 2;

        clearItemButttons(event);
        for (Items item : items) {
            if (item.getMenuID() == 2) {
                itemButtons.get(i).setText(item.getItemTitle() + "\n" + item.getItemPrice() + "₪");
                itemButtons.get(i).setVisible(true);
                i++;
            }
        }
    }

    @FXML
    void menuThree(ActionEvent event) throws IOException {
        int i = 0;
        lastMenu = 3;

        clearItemButttons(event);
        for (Items item : items) {
            if (item.getMenuID() == 3) {
                itemButtons.get(i).setText(item.getItemTitle() + "\n" + item.getItemPrice() + "₪");
                itemButtons.get(i).setVisible(true);
                i++;
            }
        }
    }

    @FXML
    void menuFour(ActionEvent event) throws IOException {
        int i = 0;
        lastMenu = 4;

        clearItemButttons(event);
        for (Items item : items) {
            if (item.getMenuID() == 4) {
                itemButtons.get(i).setText(item.getItemTitle() + "\n" + item.getItemPrice() + "₪");
                itemButtons.get(i).setVisible(true);
                i++;
            }
        }
    }

    @FXML
    void mealOne(ActionEvent event) throws IOException {
        int i = 0;
        if (meal == 0)
            meal++;
        if (meal == 1) {
            clearItemButttons(event);
            for (Items item : items) {
                if (item.getMenuID() == 1) {
                    itemButtons.get(i).setText(item.getItemTitle() + "\n" + item.getItemPrice() + "₪");
                    itemButtons.get(i).setVisible(true);
                    i++;
                }
            }
            meal++;
        } else if (meal == 2) {
            clearItemButttons(event);
            for (Items item : items) {
                if (item.getMenuID() == 2) {
                    itemButtons.get(i).setText(item.getItemTitle() + "\n" + item.getItemPrice() + "₪");
                    itemButtons.get(i).setVisible(true);
                    i++;
                }
            }
            meal++;
        } else if (meal == 3) {
            clearItemButttons(event);
            for (Items item : items) {
                if (item.getMenuID() == 3) {
                    itemButtons.get(i).setText(item.getItemTitle() + "\n" + item.getItemPrice() + "₪");
                    itemButtons.get(i).setVisible(true);
                    i++;
                }
            }
            meal = 4;
        } else {
            comboPrice = 0;
            int listSize = combo.size();
            ArrayList<Order> ord = new ArrayList<>();
            String[] partOfTheMeal = combo.toArray(new String[listSize]);
            for (i = 0; i < listSize; i++) {
                for (Items item : items) {
                    if (item.getItemTitle().equals(partOfTheMeal[i])) {
                        Order tmp = new Order();
                        tmp.setName(item.getItemTitle());
                        tmp.setPrice(item.getItemPrice());
                        comboPrice += item.getItemPrice();
                        ord.add(tmp);
                    }
                }
            }
            boolean f = false;
            for (Order order : ord) {
                if (!f) {
                    f = true;
                    order.setPrice(comboPrice);
                }
                orderedItems.add(order);
            }
        }

    }

    String getBtnSource(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();
        String[] parts = buttonText.split("\n");
        String btnName = parts[0];
        return btnName;
    }

    @FXML
    void itemBtnPressed(ActionEvent event) throws IOException {

        String btnName = getBtnSource(event);

        for (Items item : items) {
            if (item.getItemTitle().equals(btnName)) {

                Order ord = new Order();
                ord.setName(item.getItemTitle());
                ord.setPrice(item.getItemPrice() * Math.max(1, number));
                ord.setQuant(Math.max(1, number));
                System.out.println(ord.getName());
                orderedItems.add(ord);
                number = -1;
                operation = "";
                break;
            }
        }
        updateTotalPrice();

    }

    @FXML
    private void updateTotalPrice() {
        App.totalsum = 0.0;
        // Sum up the prices of all items in the orderedItems list
        for (Order order : orderedItemsManger) {
            App.totalsum += order.getPrice();
        }
        // Set the total price text
        totalPrice.setText(String.format("%.2f", App.totalsum) + "₪");
    }

    @FXML
    void total(ActionEvent event) throws IOException {
        setRoot("total");
    }

    @FXML
    void editOrders(ActionEvent event) throws IOException {
        setRoot("editOrders");
    }

    @FXML
    void correct(ActionEvent event) throws IOException {
        setRoot("correct");
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        App.setRoot("login");
        ;
    }

}
