package Controller;

import DB.AppointmentsDB;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
/**

 Controller class for the Appointment View screen.
 Allows the user to view the appointment table, delete appointments, sends them to other forms to add or update an appointment, or to the main form.
 */
public class AppointmentView implements Initializable {

    Stage stage;
    Parent scene;
    public static Appointments changingAppointment()
    {
        return selectedAppointment;
    }
    private static Appointments selectedAppointment;
    @FXML
    private RadioButton week;
    @FXML
    private RadioButton month;
    @FXML
    private RadioButton all;

    @FXML
    private TableView<Appointments> Table;
    @FXML
    private TableColumn<Appointments, Integer> ID;
    @FXML
    private TableColumn<Appointments, String> Title;
    @FXML
    private TableColumn<Appointments, String> Description;
    @FXML
    private TableColumn<Appointments, String> Location;
    @FXML
    private TableColumn<Appointments, String> Contact;
    @FXML
    private TableColumn<Appointments, String> Type;
    @FXML
    private TableColumn<Appointments, LocalDateTime> Start;
    @FXML
    private TableColumn<Appointments, LocalDateTime> End;
    @FXML
    private TableColumn<Appointments, Integer> Customer_ID;
    @FXML
    private TableColumn<Appointments, Integer> User_ID;


    /**

     This method is called when the user clicks on the Main menu and returns to the main form.
     @param event
     @throws IOException
     */
    @FXML
    public void main(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     *
     * This method directs a user to the add appointment form.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void addAppointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/addAppointment.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**

     This method is called when the "All" button is clicked and shows all appointments.
     It also unselects the "Week" and "Month".
     @param event
     @throws SQLException
     */
    @FXML
    public void all(ActionEvent event) throws SQLException {
        week.setSelected(false);
        month.setSelected(false);
        Table.setItems(AppointmentsDB.getAppointments());
    }

    /**

     This method is called when the "month" button is clicked and shows all appointments in the month.
     It also unselects the "Week" and "All".
     @param event
     @throws SQLException
     */
    @FXML
    public void month(ActionEvent event) throws SQLException {
        all.setSelected(false);
        week.setSelected(false);
        Table.setItems(AppointmentsDB.month());
        Table.refresh();
    }

    /**

     This method is called when the "week" button is clicked and shows all appointments in the week.
     It also unselects the "Month" and "All".
     @param event
     @throws SQLException
     */
    @FXML
    public void week(ActionEvent event) throws SQLException {
        all.setSelected(false);
        month.setSelected(false);
        Table.setItems(AppointmentsDB.week());
        Table.refresh();

    }
    /**
     * T
     * his method directs a user to the modify appointment form.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void modifyAppointment(ActionEvent event) throws IOException {
        selectedAppointment = Table.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select an Appointment first");
            alert.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/updateAppointment.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    /**
     *
     * This method deletes an appointment.
     *
     * @param event .
     * @throws SQLException
     */
    @FXML
    public void deleteAppointment(ActionEvent event) throws SQLException {

        selectedAppointment = Table.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Appointment not selected");
            alert.setContentText("Please select an Appointment to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete this part?");
            alert.setContentText("This is not reversible.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int appointmentId = selectedAppointment.getAppointmentId();
                String type = selectedAppointment.getType();
                AppointmentsDB.delete(appointmentId);
                Table.setItems(AppointmentsDB.getAppointments());
                Table.refresh();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("DELETED");
                alert2.setContentText("Appointment with the ID: " + appointmentId + " and type " + type + " has been deleted.");
                alert2.showAndWait();

            }

        }

    }

    /**

     Checks if a new appointment overlaps with existing appointments.
     @param appointmentID
     @param appointmentStart
     @param appointmentEnd
     @return true if the new appointment overlaps, false otherwise
     @throws Exception
     */
    public static boolean overLap(int appointmentID, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) throws Exception {
        ObservableList<Appointments> appList = AppointmentsDB.getAppointments();
        LocalDateTime apptStart;
        LocalDateTime apptEnd;

        for (Appointments a : appList) {
            apptStart = a.getStartTime();
            apptEnd = a.getEndTime();
            if (appointmentID == a.getAppointmentId()) {
                continue;
            } else if (apptStart.isEqual(appointmentStart)
                    || apptStart.isEqual(appointmentEnd)
                    || apptEnd.isEqual(appointmentStart)
                    || apptEnd.isEqual(appointmentEnd)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("ERROR: Appointments cannot overlap with existing appointments.");
                alert.showAndWait();
                return true;
            } else if (appointmentEnd.isAfter(apptStart) && appointmentEnd.isBefore(apptEnd)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("ERROR: Appointments cannot overlap with existing appointments.");
                alert.showAndWait();
                return true;
            } else if (appointmentStart.isAfter(apptStart) && (appointmentStart.isBefore(apptEnd))){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("ERROR: Appointments cannot overlap with existing appointments.");
                alert.showAndWait();
                return true;

            }
        }
        return false;
    }


    /**

     Initializes the appointment table view by populating the table with appointments from the database.
     @param url
     @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("location"));
        Contact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        End.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        User_ID.setCellValueFactory(new PropertyValueFactory<>("userId"));

        try {
            Table.setItems(AppointmentsDB.getAppointments());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
