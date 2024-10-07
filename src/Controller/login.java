package Controller;
import DB.AppointmentsDB;
import DB.UsersDB;
import Model.Appointments;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
/**

 Controller class for the Login screen.
 Allows the user to log in.
 */
public class login implements Initializable {
    private ResourceBundle resourceBundle;
    LocalDateTime startTime;
    private boolean appointmentAboutToStart;
    public TextField userName;
    public TextField password;
    public Label labelLogin;
    public Button buttonLogin;
    public Button buttonCancel;
    public Label labelLocation;
    public Label labelUserName;
    public Label fieldPassword;
    public TextField fieldLocation;
    static boolean properUser;
    static int AppointmentId;
    LocalDateTime date;
    File file;
    /**

     Logs out of the application and exits the platform.
     */
    @FXML
    public void cancelButton() {
        Platform.exit();

    }
    /**

     This method is called when the login button is clicked. It tries to log in the user with the provided username and password.
     If the login is successful, it alerts the user about upcoming appointments and redirects them to the main screen.
     If the login is unsuccessful, it shows a warning message.
     @param event
     @throws Exception
     */
    @FXML
    public void loginButton(ActionEvent event) throws Exception {
        try {

            String user = userName.getText();
            password.getText();
            String activity = "login_activity.txt", name;
            file = new File(activity);
            FileWriter writer = new FileWriter(activity, true);
            PrintWriter outcome = new PrintWriter(writer);
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

            properUser = UsersDB.goodUser(userName.getText(), this.password.getText());
            if (properUser) {
                alertForAppointments();
                name = user;
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
                outcome.println("At " + Timestamp.valueOf(localDateTime) + " User: " + user + " logged in successfully!");
                outcome.close();

            }
            else {
                if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                    resourceBundle = ResourceBundle.getBundle("Language/language", Locale.getDefault());

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText(resourceBundle.getString("wrongUserPassword"));
                    alert.show();
                }
                name = user;
                outcome.println("At " + Timestamp.valueOf(localDateTime) + " User: " + user + " was unsuccessful at logging in!");

                outcome.close();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    /**

     Generates an alert if there is any appointment within 15 minutes from the current time.
     If appointment is within 15 minutes, it shows the details such as the appointment ID and start time.
     If there is no such appointment, it shows a message stating that there is no upcoming appointment.
     @throws SQLException
     */
    public void alertForAppointments() throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        ObservableList<Appointments> appointments = AppointmentsDB.getAppointments();
        ObservableList<Appointments> newAppointments = FXCollections.observableArrayList();
        if (appointments != null) {
            for (Appointments appointment : appointments) {
                startTime = (appointment.getStartTime());
                if ((startTime.isBefore(now.plusMinutes(15))) && (startTime.isAfter(now))) {
                    newAppointments.add(appointment);
                    AppointmentId = appointment.getAppointmentId();
                    date = appointment.getStartTime();
                    appointmentAboutToStart = true;
                }
            }

            if (appointmentAboutToStart) {
                if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
                    resourceBundle = ResourceBundle.getBundle("Language/language", Locale.getDefault());
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(resourceBundle.getString("appointmentFifteen"));
                    alert.setContentText(resourceBundle.getString("appointmentWithId") + " " + AppointmentId + " " + resourceBundle.getString("startsAt") + " " + date);
                    alert.showAndWait();
                }

            } else {
                if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
                    resourceBundle = ResourceBundle.getBundle("Language/language", Locale.getDefault());
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(resourceBundle.getString("noAppointment"));
                    alert.setContentText(resourceBundle.getString("noAppointmentFifteen"));
                    alert.showAndWait();
                }

            }
        }
    }
/**

 Initializes the login view with text based on the user's default language settings.
 This method retrieves the appropriate language bundle, and sets the appropriate text for the
 login view's labels, buttons, and fields.
 @param url The URL
 @param resourceBundle The ResourceBundle
*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourceBundle = ResourceBundle.getBundle("Language/language", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            fieldLocation.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
            labelLogin.setText(resourceBundle.getString("loginLable"));
            buttonLogin.setText(resourceBundle.getString("loginButton"));
            buttonCancel.setText(resourceBundle.getString("cancelButtonText"));
            labelUserName.setText(resourceBundle.getString("labelUserName"));
            fieldPassword.setText(resourceBundle.getString("labelPassword"));
            labelLocation.setText(resourceBundle.getString("locationLabel"));
        }
    }
}
