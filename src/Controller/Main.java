package Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 Controller class for the Main screen
 Allows the user to go to the different forms within the project.
 */
public class Main implements Initializable {
    /**

     Displays the Customer View form.
     @param
     @throws IOException
     */
    @FXML
    public void Customer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**

     Displays the Appointment View form.
     @param
     @throws IOException
     */
    @FXML
    public void Appointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AppointmentView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**

     Displays the Reports View form.
     @param
     @throws IOException
     */
    @FXML
    public void Reports(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/reports.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**

    Logs out of the application and exits the platform.
*/
    @FXML
    public void Logout() {
        Platform.exit();

    }
    /**
     Initializer is empty

     @param url The location
     @param resourceBundle The resources
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
