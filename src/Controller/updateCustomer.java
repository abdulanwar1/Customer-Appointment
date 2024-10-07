package Controller;

import DB.CustomersDB;
import DB.DivisionDB;
import Model.Customers;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
/**

 Controller class for the Update Customer form.
 Allows the user to update or cancel an update and takes the user back to the Customer view form.
 */
public class updateCustomer implements Initializable {
    Stage stage;
    Parent scene;
    ObservableList<String> divionItems = Combos.divCombo();
    ObservableList<String> countryItems = Combos.countCombo();
    private Customers selectedCustomer;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private ComboBox<String> country;
    @FXML
    private ComboBox<String> division;
    @FXML
    private TextField postal;
    @FXML
    private TextField phone;

    public updateCustomer() throws SQLException {
    }

    /**

     This method is called when a user selects a country and updates the divisions based on the selected country.
     @param event
     */
    @FXML
    public void divBasedOnCntry(ActionEvent event) {
        ObservableList<String> div = FXCollections.observableArrayList();
        try {
            String selectedCountry = country.getSelectionModel().getSelectedItem();
            if (selectedCountry != null) {
                List<Division> divisions = DivisionDB.getDivByCount(selectedCountry);
                for (Division division : divisions) {
                    div.add(division.getDivision());
                }
            }
            division.setItems(div);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**

     Handles the "Update Customer" button clicked.
     Updates an existing customer to the database if all requirements are met and takes the user back to the Customer View.
     Displays an error message if input is invalid or if there is an error adding.
     @param event
     @throws SQLException
     @throws IOException
     */
    @FXML
    public void updateCustomer(ActionEvent event) throws SQLException, IOException {


        if (!(name.getText().isEmpty()) &&
                !(address.getText().isEmpty()) &&
                !(postal.getText().isEmpty()) &&
                !(postal.getText().isEmpty()) &&
                !(division.getSelectionModel().isEmpty()) &&
                !(country.getSelectionModel().isEmpty()))
        {
            try {
                CustomersDB.update(Integer.parseInt(id.getText()), name.getText(), address.getText(), postal.getText(), phone.getText(), division.getValue());
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/CustomerView.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to update Customer.");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Customer. Customer fields cannot be null.");
            alert.showAndWait();
        }



    }



    /**

     Handles the Cancel button clicked on the Update Customer form.
     Shows a confirmation alert before taking the user back to the Customer view.
     @param event
     @throws IOException
     */
    @FXML
    public void cancel(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/CustomerView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }


    /**

     Initializes the ModifyCustomerController and populates the fields with the selected customer's information.
     @param url
     @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedCustomer = CustomerView.changingCustomer();
        country.setItems(countryItems);
        division.setItems(divionItems);
        id.setText(Integer.toString(selectedCustomer.getCustomerId()));
        name.setText(selectedCustomer.getCustomerName());
        postal.setText(selectedCustomer.getPostalCode());
        address.setText(selectedCustomer.getAddress());
        phone.setText(selectedCustomer.getPhoneNumber());
        country.getSelectionModel().select(selectedCustomer.getCountry());
        division.getSelectionModel().select(selectedCustomer.getDivision());

    }


}
