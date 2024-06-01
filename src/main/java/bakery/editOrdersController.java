package bakery;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class editOrdersController implements Initializable {

    @FXML
    private TableView<Order> ordersTable;
    private ObservableList<Order> orders = FXCollections.observableArrayList();

    @FXML
    private Order selectedItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            a();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ordersTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                try {
                    onEdit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static class Order {
        private final SimpleStringProperty name;
        private final Date date;
        private String seller;
        private final Time time;
        private final List<String> items;
        private final List<String> quantity;
        private final List<String> price;
        private Double total;

        public Order(int name, String seller, Date date, Time time, List<String> items, List<String> quantity,
                List<String> price, Double total) {
            this.name = new SimpleStringProperty(String.valueOf(name));
            this.seller = seller;
            this.date = date;
            this.time = time;
            this.items = items;
            this.quantity = quantity;
            this.price = price;
            this.total = total;
        }

        public String getName() {
            return name.get();
        }

        public Date getDate() {
            return date;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Time getTime() {
            return time;
        }

        public List<String> getItems() {
            return items;
        }

        public List<String> getQuantity() {
            return quantity;
        }

        public List<String> getPrice() {
            return price;
        }
    }

    @SuppressWarnings("unchecked")
    private void a() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT o.order_id, o.seller, o.order_date, o.orderTime FROM orders o WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) ORDER BY o.order_date DESC, o.order_id;";
        Statement tmpStatement = connector.con.connectDB().createStatement();
        ResultSet rs = tmpStatement.executeQuery(SQL);
        System.out.println("Raghad");
        while (rs.next()) {
            int tt = rs.getInt(1);
            String sql2 = "SELECT io.item_name, io.quantity, io.price FROM order_items io WHERE io.order_id = " + tt;
            Statement tmpStatement2 = connector.con.connectDB().createStatement();
            ResultSet rs2 = tmpStatement2.executeQuery(sql2);

            List<String> items = new ArrayList<>();
            List<String> quantity = new ArrayList<>();
            List<String> price = new ArrayList<>();
            System.out.println("Raghad");
            double totprice = 0;
            while (rs2.next()) {
                System.out.println("Raghad");
                items.add(rs2.getString("item_name"));
                quantity.add(String.valueOf(rs2.getInt("quantity")));
                price.add(String.valueOf(rs2.getDouble("price")));
                totprice += rs2.getDouble("price");
            }

            orders.add(new Order(rs.getInt("order_id"), rs.getString("seller"), rs.getDate("order_date"),
                    rs.getTime("orderTime"), items,
                    quantity, price, totprice));

            rs2.close();
            tmpStatement2.close();
        }

        rs.close();
        tmpStatement.close();

        TableColumn<Order, String> col1 = new TableColumn<>("OrderID");
        TableColumn<Order, Date> col2 = new TableColumn<>("Date");
        TableColumn<Order, Time> col3 = new TableColumn<>("Time");
        TableColumn<Order, String> col4 = new TableColumn<>("Seller");
        TableColumn<Order, Double> col5 = new TableColumn<>("Total");
        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("date"));
        col3.setCellValueFactory(new PropertyValueFactory<>("time"));
        col4.setCellValueFactory(new PropertyValueFactory<>("seller"));
        col5.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<Order, List<String>> itemsCol = new TableColumn<>("Items");
        itemsCol.setCellValueFactory(new PropertyValueFactory<>("items"));

        TableColumn<Order, List<String>> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Order, List<String>> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        itemsCol.setCellFactory(column -> new ListCell());
        quantityCol.setCellFactory(column -> new ListCell());
        priceCol.setCellFactory(column -> new ListCell());

        ordersTable.getColumns().addAll(col1, col4, col2, col3, col5, itemsCol, quantityCol, priceCol);
        ordersTable.setItems(orders);
    }

    private class ListCell extends TextFieldTableCell<Order, List<String>> {
        @Override
        public void updateItem(List<String> items, boolean empty) {
            super.updateItem(items, empty);
            if (empty || items == null) {
                setText(null);
            } else {
                setText(String.join("\n", items));
            }
        }
    }

    private void onEdit() throws ClassNotFoundException, SQLException {
        if (ordersTable.getSelectionModel().getSelectedItem() != null) {
            selectedItem = ordersTable.getSelectionModel().getSelectedItem();
            System.out.println("Selected: " + selectedItem);
        }
        if (selectedItem != null) {
            showConfirmationDialog(selectedItem);
        }
    }

    private void showConfirmationDialog(Order selectedItem) throws ClassNotFoundException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal");
        alert.setHeaderText("Remove Selected Row");
        alert.setContentText("Are you sure you want to remove the selected row?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String id = selectedItem.getName();
            String sql = "Delete From order_items where order_items.order_id = " + id;
            Statement tmpStatement2 = connector.con.connectDB().createStatement();
            tmpStatement2.executeUpdate(sql);
            sql = "Delete From orders where orders.order_id = " + id;
            Statement tmpStatement = connector.con.connectDB().createStatement();
            tmpStatement.executeUpdate(sql);
            tmpStatement2.close();
            tmpStatement.close();
            ordersTable.getItems().remove(selectedItem);
        }
    }
}
