package bakery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import static bakery.App.setRoot;
import static bakery.loginController.orderedItems;

public class totalController {
    @SuppressWarnings("exports")
    public static Time sqlTime;
    public String number = "";
    public String operation = "";
    @FXML
    private TextField address, name, phone;
    @FXML
    private TableView<Order> tableItems;
    @FXML
    private TableColumn<Order, String> itemTitle;
    @FXML
    private TableColumn<Order, Double> itemPrice;
    @FXML
    private TableColumn<Order, Integer> itemQuantity;
    @FXML
    public static ObservableList<Order> Ordered;
    @FXML
    private Text totalPayed;
    @FXML
    private Text totalprice;
    @FXML
    private Text rem;

    @FXML
    private Button clearBtn, done;

    private List<Button> priceButtons;

    @FXML
    private Button priceBtn, priceBtn1, priceBtn2, priceBtn3, priceBtn4, priceBtn5, priceBtn6, priceBtn7, priceBtn8;

    @FXML
    private MenuButton orderType;
    @SuppressWarnings("exports")
    @FXML
    public MenuButton paymentMethod;

    public static String Address, Phone, Name, orderTypeString, Time, payed, remm;
    public static LocalDate date;

    public static ArrayList<branches> branch;
    public static int order_id;

    public void initialize() {

        tableItems.setItems(orderedItems);
        totalprice.setText(String.valueOf(App.totalsum));
        itemTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemQuantity.setCellValueFactory(new PropertyValueFactory<>("quant"));
        Ordered = orderedItems;
        priceButtons = new ArrayList<>();
        priceButtons.add(priceBtn);
        priceButtons.add(priceBtn1);
        priceButtons.add(priceBtn2);
        priceButtons.add(priceBtn3);
        priceButtons.add(priceBtn4);
        priceButtons.add(priceBtn5);
        priceButtons.add(priceBtn6);
        priceButtons.add(priceBtn7);
        priceButtons.add(priceBtn8);
    }

    String getBtnSource(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();
        String[] parts = buttonText.split("\n");
        String btnName = parts[0];
        return btnName;
    }

    @FXML
    void pricePressed(ActionEvent event) throws IOException {
        String btnPrice = getBtnSource(event);
        int price = Integer.parseInt(btnPrice);
        rem.setText(String.valueOf(price - App.totalsum));
        remm = String.valueOf(price - App.totalsum);
        totalPayed.setText(String.valueOf(price));
        number = "";
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    @FXML
    void one(ActionEvent event) throws IOException {
        number += '1';
    }

    @FXML
    void two(ActionEvent event) throws IOException {
        number += '2';
    }

    @FXML
    void three(ActionEvent event) throws IOException {
        number += '3';
    }

    @FXML
    void four(ActionEvent event) throws IOException {
        number += '4';
    }

    @FXML
    void five(ActionEvent event) throws IOException {
        number += '5';
    }

    @FXML
    void six(ActionEvent event) throws IOException {
        number += '6';

    }

    @FXML
    void seven(ActionEvent event) throws IOException {
        number += '7';

    }

    @FXML
    void eight(ActionEvent event) throws IOException {
        number += '8';

    }

    @FXML
    void nine(ActionEvent event) throws IOException {
        number += '9';
    }

    @FXML
    void zero(ActionEvent event) throws IOException {
        number += '0';
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
        updateTotalPayed();
        operation = "";

    }

    void updateTotalPayed() {
        if (number.isEmpty()) {
            String message = "You didn't enter the payed amount of money!";
            warning(message);
            return;
        }
        double number1 = Double.parseDouble(number);
        totalPayed.setText(String.valueOf(number1));
        payed = String.valueOf(number1);
        rem.setText(String.valueOf(number1 - App.totalsum));
        remm = String.valueOf(number1 - App.totalsum);
        number = "";
    }

    @FXML
    void dineIn(ActionEvent event) throws IOException {
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

        orderType.setText("Dine In");
        orderTypeString = "Dine In";
        name.setVisible(true);
        address.setVisible(false);
        phone.setVisible(false);
    }

    @FXML
    void takeAway(ActionEvent event) throws IOException {
        orderType.setText("Take Away");
        name.setVisible(true);
        orderTypeString = "Take Away";
        address.setVisible(false);
        phone.setVisible(false);
    }

    @FXML
    void clear(ActionEvent event) throws IOException {
        orderType.setText("Order Type");
        paymentMethod.setText("Payment Method");
        name.clear();
        address.clear();
        phone.clear();
        name.setVisible(false);
        address.setVisible(false);
        phone.setVisible(false);
    }

    @FXML
    void delivery(ActionEvent event) throws IOException {
        orderType.setText("Delivery");
        orderTypeString = "Delivery";
        name.setVisible(true);
        address.setVisible(true);
        phone.setVisible(true);
    }

    @FXML
    void cash(ActionEvent event) throws IOException {
        paymentMethod.setText("Cash");
    }

    @FXML
    void visa(ActionEvent event) throws IOException {
        paymentMethod.setText("Visa");

    }

    @FXML
    private void warning(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred!");
        alert.setContentText(message);

        alert.showAndWait();
    }

    @FXML
    void dot(ActionEvent event) throws IOException {
        operation = "dot";
    }

    @FXML
    void done(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Address = address.getText();
        Phone = phone.getText();
        Name = name.getText();

        if (Address.isEmpty() && address.isVisible() || Phone.isEmpty() && phone.isVisible()
                || Name.isEmpty() && name.isVisible()) {
            String message = "Required ";
            Boolean[] tmp;
            tmp = new Boolean[3];
            for (int i = 0; i < 3; i++) {
                tmp[i] = false;
            }
            if (Address.isEmpty() && address.isVisible()) {
                tmp[0] = true;
            }
            if (Phone.isEmpty() && address.isVisible()) {
                tmp[1] = true;
            }
            if (Phone.isEmpty() && address.isVisible()) {
                tmp[2] = true;
            }
            Boolean f = false;

            if (tmp[0]) {
                if (!f) {
                    f = true;
                    message += "Address";
                } else
                    message += ", Address";
            }
            if (tmp[1]) {
                if (!f) {
                    f = true;
                    message += "Phone";
                } else
                    message += ", Phone";
            }
            if (tmp[2]) {
                if (!f) {
                    f = true;
                    message += "Name";
                } else
                    message += ", Name";
            }
            message += '.';
            warning(message);
            return;
        }
        if (!isNumeric(phone.getText()) && phone.isVisible()) {
            String message;
            if (phone.getText().isEmpty()) {
                message = "Required phone Number";
            } else
                message = "The entered Number contains character/s";
            warning(message);
            return;
        }
        if (totalPayed.getText() == null) {
            totalPayed.setText(totalprice.getText());
            payed = totalprice.getText();
        }

        String sql;
        // insert order
        sqlTime = new Time(Calendar.getInstance().getTime().getTime());
        date = LocalDate.now();
        String SQL = "INSERT INTO `orders` (seller, orderTime, order_date) VALUES ('" +
                loginController.currentUsername + "','" + sqlTime + "','" + date + "')";
        Statement stmt = null;
        connector.con.connectDB();
        stmt = connector.con.connectDB().createStatement();
        stmt.executeUpdate(SQL);

        // seclect the last orderid

        sql = "SELECT MAX(order_id) AS last_order_id FROM orders";

        Statement tmpStatement = connector.con.connectDB().createStatement();
        ResultSet rs = tmpStatement.executeQuery(sql);
        branches branch = new branches();
        order_id = 0;
        if (rs.next()) {
            order_id = (rs.getInt("last_order_id"));
        } else {
            warning(sql);
        }

        // enter order items
        for (Order order : Ordered) {
            SQL = "INSERT INTO `order_items` (order_id, item_name, quantity, price, orderDate, ordertime) VALUES ('"
                    + order_id + "','" + order.getName() + "','"
                    + order.getQuant() + "','" + order.getPrice() + "','" + date + "','" + sqlTime + "')";

            connector.con.connectDB();
            stmt = connector.con.connectDB().createStatement();
            stmt.executeUpdate(SQL);
        }
        // branch.setOrderID(branch.getorderID() + 1);
        // sql = "UPDATE branches SET orderID = " + branch.getorderID() + " WHERE
        // branchID = " + branch.getBranchID();
        // connector.con.connectDB();
        // stmt = connector.con.connectDB().createStatement();
        // stmt.executeUpdate(SQL);
        // stmt.close();
        connector.con.connectDB().close();
        setRoot("bill");
    }

}