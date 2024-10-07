package Controller;

import DB.AppointmentsDB;
import Model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
/**

 Controller class for the reports form.
 Allows the user to view various reports.
 */
public class report implements Initializable {

    Parent scene;
    Stage stage;
    ObservableList<String> months = Combos.monthCombo();
    ObservableList<Integer> contactComboList = Combos.ContIdcombo();
    ObservableList<Integer> appointmentmentID = Combos.appCombo();
    @FXML
    private TableColumn<Appointments, String> month;

    @FXML
    private TableColumn<Appointments, String> type;

    @FXML
    private TableColumn<Appointments, Integer> numOfApp;

    @FXML
    private TableView<MonthAppointment> report;

    @FXML
    private ComboBox<String> Month;



    @FXML
    private TableView<Appointments> Contact;

    @FXML
    private TableColumn<Appointments, Integer> appID;

    @FXML
    private TableColumn<Appointments, String> title;

    @FXML
    private TableColumn<Appointments, String> type2;

    @FXML
    private TableColumn<Appointments, String> description;

    @FXML
    private TableColumn<Appointments, LocalDateTime> start;
    @FXML
    private TableColumn<Appointments, LocalDateTime> end;
    @FXML
    private TableColumn<Appointments, Integer> custID;

    @FXML
    private ComboBox<Integer> ContactID;

    @FXML
    private ComboBox<Integer> AppID;

    @FXML
    private TableView<Appointments> report2;
    @FXML
    private TableColumn<Appointments, Integer> AppointmentID;

    @FXML
    private TableColumn<Appointments, String> contact;

    /**

     Handles the user's selected month.
     Fetches appointments for the selected month .
     @param event
     @throws SQLException
     */
    @FXML
    public void Month(ActionEvent event) throws SQLException {

        String selectedMonth = Month.getSelectionModel().getSelectedItem().toUpperCase();

        report.setItems(AppointmentsDB.appByMAndT(selectedMonth));


    }
    /**

     Displays all appointments for a selected contact.
     Uses a lambda expression to set the cell value factory for the "Contact Name" column.
     The lambda expression is useful as it creates a new column in the table and displays
     the name associated with the contact. It makes it that much more concise and convenient.
     @param event \
     @throws SQLException \
     */
    @FXML
    public void contact(ActionEvent event) throws SQLException {
        Integer contactID = ContactID.getSelectionModel().getSelectedItem();
        ObservableList<Appointments> appointments = AppointmentsDB.appByContId(contactID);

        TableColumn<Appointments, String> contact = new TableColumn<>("Contact Name");
        contact.setCellValueFactory(cellData -> {
            Integer contactId = cellData.getValue().getContactId();
            String contactName = "";
            switch (contactId) {
                case 1:
                    contactName = "Anika Costa";
                    break;
                case 2:
                    contactName = "Daniel Garcia";
                    break;
                case 3:
                    contactName = "Li Lee";
                    break;
                default:
                    break;
            }
            return new SimpleStringProperty(contactName);
        });

        Contact.setItems(appointments);
        Contact.getColumns().add(0, contact);
    }

    /**

     This method activated when the user selects an appointment ID.
      It retrieves the selected appointment ID and uses it to get the contact associated.

      @param event
      @throws SQLException
     */
    @FXML
    public void AppID(ActionEvent event) throws SQLException{

        Integer AppointmentId= AppID.getSelectionModel().getSelectedItem();
        report2.setItems(AppointmentsDB.appByAppId(AppointmentId));
    }

    /**

     This method handles the action of clicking the "Cancel" button.
     Takes the user to the Main view.
     @param event
     @throws IOException
     */
    @FXML
    public void Cancel(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *
     * This method initializes the 'reports' screen. It populates lists used, and populates the various columns.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        month.setCellValueFactory((new PropertyValueFactory<>("month")));
        type.setCellValueFactory((new PropertyValueFactory<>("type")));
        numOfApp.setCellValueFactory((new PropertyValueFactory<>("count")));


        Month.setItems(months);
        Month.getSelectionModel().selectFirst();

        ContactID.setItems(contactComboList);
        AppID.setItems(appointmentmentID);
        appID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type2.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        custID.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contactName"));

    }

}

