package bakery;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    Button button;
    @FXML
    public static ArrayList<Users> users;
    public static ArrayList<Roles> roles;
    public static ArrayList<Items> items;
    public static ArrayList<menues> menu;
    public static double totalsum = 0.0;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Bekary");
        // WebView webView = new WebView();
        String javafxVersion = System.getProperties().getProperty("javafx.runtime.version");
        System.out.println("JavaFX Version: rrrrrrrrr " + javafxVersion);
        scene = new Scene(loadFXML("login"), 900, 750);

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        connect();
        getRoles();
        getUsers();
        getItems();
        getmenues();
        launch(args);
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        connector.con.connectDB();

    }

    private static void getUsers() throws SQLException, ClassNotFoundException {
        users = new ArrayList<>();
        String sqlQuery = "select * from users";
        Statement tmpStatement = connector.con.connectDB().createStatement();
        ResultSet rs = tmpStatement.executeQuery(sqlQuery);
        while (rs.next()) {
            users.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        rs.close();
        tmpStatement.close();
    }

    private static void getRoles() throws SQLException, ClassNotFoundException {

        String sqlQuery = "select * from roles";
        roles = new ArrayList<>();

        Statement tmpStatement = connector.con.connectDB().createStatement();
        ResultSet rs = tmpStatement.executeQuery(sqlQuery);

        while (rs.next()) {
            roles.add(new Roles(rs.getInt(1), rs.getString(2)));
        }
        rs.close();
        tmpStatement.close();
    }

    private static void getItems() throws SQLException, ClassNotFoundException {

        String sqlQuery = "select * from items";
        items = new ArrayList<>();

        Statement tmpStatement = connector.con.connectDB().createStatement();
        ResultSet rs = tmpStatement.executeQuery(sqlQuery);

        while (rs.next()) {
            items.add(new Items(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
        }
        rs.close();
        tmpStatement.close();
    }

    private static void getmenues() throws SQLException, ClassNotFoundException {

        String sqlQuery = "select * from menu";
        menu = new ArrayList<>();

        Statement tmpStatement = connector.con.connectDB().createStatement();
        ResultSet rs = tmpStatement.executeQuery(sqlQuery);

        while (rs.next()) {
            menu.add(new menues(rs.getInt(1), rs.getString(2)));
        }
        rs.close();
        tmpStatement.close();
    }

}