package bakery;

import java.util.Optional;
import static bakery.App.setRoot;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import static bakery.loginController.orderedItems;

public class correctController {
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
    private Button back;

    @FXML
    private Order selectedItem;
    @FXML
    private Text seller;

    public void initialize() {
        tableItems.setItems(orderedItems);
        itemTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemQuantity.setCellValueFactory(new PropertyValueFactory<>("quant"));
        tableItems.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
        seller.setText(loginController.currentUsername);
    }

    private void onEdit() {
        // Get selected row data
        if (tableItems.getSelectionModel().getSelectedItem() != null) {
            selectedItem = tableItems.getSelectionModel().getSelectedItem();
            System.out.println("Selected: " + selectedItem);
        }
        if (selectedItem != null) {
            showConfirmationDialog(selectedItem);
        }
    }

    private void showConfirmationDialog(Order selectedItem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal");
        alert.setHeaderText("Remove Selected Row");
        alert.setContentText("Are you sure you want to remove the selected row?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            tableItems.getItems().remove(selectedItem);
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        App.setRoot("manager");
    }

}
