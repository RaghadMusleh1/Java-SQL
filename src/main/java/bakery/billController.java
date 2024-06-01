package bakery;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import static bakery.totalController.payed;
import static bakery.totalController.remm;
import static bakery.loginController.orderedItems;

public class billController {
    @FXML
    private Text date, time, orderID, employee, orderTypeBill1, orderTypeBill2, orderTypeCenter, totalItems, totalpayed,
            totalPrice, rem;
    @FXML
    private TableView<Order> tableItems;
    @FXML
    private TableColumn<Order, String> itemTitle;
    @FXML
    private TableColumn<Order, Double> itemPrice;
    @FXML
    private TableColumn<Order, Integer> itemQuantity;
    @FXML
    public static ObservableList<Order> orders;
    @FXML
    private VBox itemContainer;
    @FXML
    private AnchorPane parentAnchor;

    public void initialize() {
        LocalDate currentDate = LocalDate.now();
        String currentDateAsString = currentDate.toString();
        date.setText(currentDateAsString);

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String stringCurrentTime = currentTime.format(formatter);
        time.setText(stringCurrentTime);
        orderID.setText(String.valueOf(totalController.order_id));

        orders = orderedItems;

        orderTypeBill1.setText(totalController.orderTypeString);
        orderTypeBill2.setText(totalController.orderTypeString);
        employee.setText(loginController.currentUsername);
        int sz = orders.size();
        totalItems.setText(Integer.toString(sz));
        double y = 370;
        totalPrice.setText(String.valueOf(App.totalsum));
        totalpayed.setText(payed);
        rem.setText(remm);
        itemContainer.getChildren().clear();
        for (Order order : orders) {
            Label itemNameLabel = new Label(order.getName());
            Label itemQuantityLabel = new Label(Integer.toString(order.getQuant()));
            Label itemPriceLabel = new Label(Double.toString(order.getPrice()));
            HBox hbox = new HBox(200);
            hbox.getChildren().addAll(itemNameLabel, itemQuantityLabel, itemPriceLabel);
            VBox vbox = new VBox();
            vbox.getChildren().add(hbox);
            itemContainer.getChildren().add(vbox);
            y += 20;
        }

        Label goodbyeLabel = new Label("Goodbye!");
        parentAnchor.getChildren().add(goodbyeLabel);
        goodbyeLabel.setLayoutX(385); // Set x coordinate
        goodbyeLabel.setLayoutY(y); // Set y coordinate

        // Make sure the "Goodbye!" label is visible
        goodbyeLabel.setVisible(true);

        // Bring the "Goodbye!" label to the front
        goodbyeLabel.toFront();

    }

}
