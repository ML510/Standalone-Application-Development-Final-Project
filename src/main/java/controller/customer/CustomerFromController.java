package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import dto.Customer;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.CustomerService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CustomerFromController implements Initializable {

    public JFXComboBox cmbCustomerId;
    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNo;

    @Inject
    CustomerService customerService;

    String custId;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        //String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phoneNo = txtPhoneNo.getText();

        Customer customer = new Customer(null,name,address,phoneNo);

        System.out.println("CUSTOMER FROM "+customer);

        customerService.addCustomer(customer);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        customerService.deleteCustomer(custId);
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

        List<Customer> all = customerService.getAll();
        ObservableList<Customer> customers = FXCollections.observableArrayList(all);

        tblCustomer.setItems(customers);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        Customer customer = customerService.searchCustomer(custId);
        txtName.setText(customer.getName());
        txtPhoneNo.setText(customer.getPhoneNumber());
        txtAddress.setText(customer.getAddress());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = custId;
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phoneNo = txtPhoneNo.getText();

        Customer customer = new Customer(id,name,address,phoneNo);

        customerService.updateCustomer(id,customer);

    }

    private void tableLoad(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableLoad();
        List<Customer> all = customerService.getAll();
        ObservableList<String> customerId = FXCollections.observableArrayList();

        all.forEach(customer -> {
            customerId.add(customer.getId());
        });

        cmbCustomerId.setItems(customerId);

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if (t1 != null){
                custId = t1.toString();
            }
        });
    }
}
