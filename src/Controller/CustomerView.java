package Controller;

import DB.*;
import Model.Customers;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.Optional;
import java.util.ResourceBundle;
/**

 Controller class for the Customer View screen.
 Allows the user to view the customer table, delete customers, sends them to other forms to add or update a customer, or the main form.
 */
public class CustomerView implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Customers> table;
    @FXML
    private TableColumn<Customers, Integer> id;
    @FXML
    private TableColumn<Customers, String> name;
    @FXML
    private TableColumn<Customers, String> address;
    @FXML
    private TableColumn<Customers, Integer> division;
    @FXML
    private TableColumn<Customers, String> postalCode;
    @FXML
    private TableColumn<Customers, String> phone;

    private static Customers selectedCustomer;

    /**
     *
     * This method selects the customer from the table view
     * @return the selected Customer
     */
    public static Customers changingCustomer() {
        return selectedCustomer;
    }

    /**
     *
     *  This method deletes a customer and all its associated appointments.
     *
     * @param event .
     * @throws SQLException
     */
    @FXML
    public void deleteCustomer(ActionEvent event) throws SQLException {

        selectedCustomer = table.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Customer not selected");
            alert.setContentText("Please select a Customer to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete this Customer?");
            alert.setContentText("This is not reversible.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int customerId = selectedCustomer.getCustomerId();
                CustomersDB.delete(customerId);
                table.setItems(CustomersDB.getCustomers());
                table.refresh();
            }

        }
    }

    /**
     *
     *  This method directs a user to the update form.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void updateCustomer(ActionEvent event) throws IOException {

        selectedCustomer = table.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a Customer first");
            alert.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/updateCustomer.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     *
     * This method directs a user to the add form.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void addCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     *
     * This method directs a user to the Main form.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void mainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *
     * Initializes the customer table view by setting up the cell value factories for each column.
     * Uses **LAMBDA** expressions to define the behavior of the setCellValueFactory method for each column,
     * making it more concise and expressive.
     *
     * @param url The location
     * @param resourceBundle The resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            table.setItems(CustomersDB.getCustomers());
            id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomerId()).asObject());
            name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
            address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
            division.setCellValueFactory(new PropertyValueFactory<>("division"));
            postalCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostalCode()));
            phone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}