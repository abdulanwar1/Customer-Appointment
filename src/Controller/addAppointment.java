package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import DB.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;

import java.time.*;
import java.util.Optional;
/**

 Controller class for the Add appointment form.
 Allows the user to add or cancel an add, takes the user back to the appointment view form.
 */
public class addAppointment implements Initializable {
    Stage stage;

    Parent scene;
    ObservableList<String> time = Combos.populateTimeComboBoxes();
    ObservableList<String> contList = Combos.contCombo();
    ObservableList<Integer> custIdList = Combos.custIdCombo();
    ObservableList<Integer> userList = Combos.userIdCombo();
    ObservableList<String> typec = Combos.typeC();
    @FXML
    private DatePicker appDate;
    @FXML
    private TextField LOcation;
    @FXML
    private TextField description;
    @FXML
    private ComboBox<String> contact;
    @FXML
    private ComboBox<String> start;
    @FXML
    private TextField title;
    @FXML
    private ComboBox<String> end;
    @FXML
    private ComboBox<Integer> custID;
    @FXML
    private ComboBox<Integer> userID;
    @FXML
    private ComboBox<String> Type;





    /**

     This method handles the cancel button when adding a new appointment and takes the user back to the appointment view.
     @param event
     */
    @FXML
    public void Cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Go back to Appointment");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() ==  ButtonType.OK)) {
            try {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/AppointmentView.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setContentText("Load Error.");
                alert.showAndWait();
            }
        }
    }
    /**

     Saves a new appointment if all required fields are filled and the appointment's time/date are valid.
     Shows error messages for invalid inputs or time issues.
     @param event
     @throws Exception
     */
    @FXML
    public void Save(ActionEvent event) throws Exception {
        if(
        !custID.getSelectionModel().isEmpty() &&
        !userID.getSelectionModel().isEmpty() &&
        !contact.getSelectionModel().isEmpty() &&
        !title.getText().isEmpty() &&
        !description.getText().isEmpty() &&
        !LOcation.getText().isEmpty() &&
        !Type.getSelectionModel().isEmpty() &&
        !(appDate.getValue() == null) &&
        !(LocalTime.parse(start.getValue()) == null) &&
        !(LocalTime.parse(end.getValue()) == null) &&
        !(appDate.getValue() == null))
        {

        try {


        LocalDateTime.of(appDate.getValue(),LocalTime.parse(start.getValue()));
        LocalDateTime.of(appDate.getValue(),LocalTime.parse(end.getValue()));
        ZonedDateTime startEst = ZonedDateTime.of(appDate.getValue().getYear(), appDate.getValue().getMonthValue(), appDate.getValue().getDayOfMonth(), 8, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime endEst = ZonedDateTime.of(appDate.getValue().getYear(), appDate.getValue().getMonthValue(), appDate.getValue().getDayOfMonth(), 22, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime lStart = startEst.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime lEnd = endEst.withZoneSameInstant(ZoneId.systemDefault());
        appDate.getValue().getDayOfWeek().getValue();


            if (appDate.getValue().getDayOfWeek().getValue() == 6 || appDate.getValue().getDayOfWeek().getValue() == 7) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Weekend Appointments!");
            alert.setContentText("Please pick a day from Monday through Friday.");
            alert.showAndWait();
        }

            else if(LocalTime.parse(start.getValue()).isBefore(LocalTime.from(lStart))
                || LocalTime.parse(end.getValue()).isAfter(LocalTime.from(lEnd))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong timing!");
            alert.setContentText("Please pick a time from 8am to 10pm, EST.");
            alert.showAndWait();
        }
            else if (AppointmentView.overLap(0, LocalDateTime.of(appDate.getValue(),LocalTime.parse(start.getValue())), LocalDateTime.of(appDate.getValue(),LocalTime.parse(end.getValue()))) == true) {
           return;
        }
            else {
            AppointmentsDB.add(contact.getSelectionModel().getSelectedItem(), title.getText(),
                    description.getText(), LOcation.getText(), Type.getSelectionModel().getSelectedItem(),
                    LocalDateTime.of(appDate.getValue(), LocalTime.parse((start.getSelectionModel().getSelectedItem()))),
                    LocalDateTime.of(appDate.getValue(), LocalTime.parse((end.getSelectionModel().getSelectedItem()))),
                    custID.getSelectionModel().getSelectedItem(), userID.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/AppointmentView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to add Appointment.");
                alert.showAndWait();
            }
       }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Appointment. Appointment fields cannot be null.");
            alert.showAndWait();
        }
    }






    /**

     This method initializes the add Appointment view form  and sets the combo boxes.
     @param location
     @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        start.setItems(time);
        end.setItems(time);
        contact.setItems(contList);
        custID.setItems(custIdList);
        userID.setItems(userList);
        Type.setItems(typec);

    }
}



