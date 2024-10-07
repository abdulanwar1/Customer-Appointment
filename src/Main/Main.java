package Main;

import JDBC.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**

 The Main class is responsible for launching the application.
 It extends the javafx Application class
 By:Mohammad Haji
 */
public class Main extends Application {
   /**
    *  The start method loads the login.fxml file and sets it as the primary stage's scene.
            */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        primaryStage.setTitle("Appointment/Customer system");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
/**
    The main method is used to open a connection to the database using JDBC, launch the application, and then close the database connection.
 */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();

    }
}
