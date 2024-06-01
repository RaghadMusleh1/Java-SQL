package bakery;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.SQLException;

import static bakery.App.users;
import static bakery.App.items;

public class loginController {
    public static int currentId = 0;
    public static int currentUserRole = 0;
    public static String currentUsername = "";
    @FXML
    private PasswordField password;
    @FXML
    private Label login;
    @FXML
    private Button enter;
    @FXML
    private Text loginDetection;
    @FXML
    private TextField userName;

    @FXML
    private Button loginBtn;
    @FXML
    public static ObservableList<Order> orderedItems;

    public void initialize() {
        orderedItems = FXCollections.observableArrayList();
    }

    @FXML
    void Login(ActionEvent event) throws ClassNotFoundException, IOException, SQLException {
        System.out.println("debug");
        String currentUserName = userName.getText();
        String currentPassword = password.getText();
        int usersNumber = users.size();
        System.out.println(usersNumber);
        int i;
        for (i = 0; i < usersNumber; i++) {
            if (users.get(i).getUserName().equals(currentUserName)
                    && users.get(i).getUserPassword().equals(currentPassword))
                break;
        }
        if (i == usersNumber) {// if the user name and the password was entered by the user are not found in
                               // the list as a pair ( so the counter i is equal to the users number), then
                               // print error message for the user
            System.out.println("currentPassword");
            loginDetection.setVisible(true);
            loginDetection.setText("invalid user name or password");
        } else {
            currentId = users.get(i).getUserID();
            currentUserRole = users.get(i).getUserRoleID();
            currentUsername = users.get(i).getUserName();
            if (currentUserRole == 1)
                App.setRoot("manager");
            else if (currentUserRole == 2)
                App.setRoot("employee");
        }
    }

}
