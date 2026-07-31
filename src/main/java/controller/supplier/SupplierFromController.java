package controller.supplier;

import com.jfoenix.controls.JFXComboBox;
import dto.Supplier;
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
import service.custom.SupplierService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierFromController implements Initializable {

    public JFXComboBox cmbSupplierId;
    @FXML
    private TableColumn colCompName;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colWeight;

    @FXML
    private TableView tblSupplier;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSuppweight;

    @Inject
    SupplierService supplierService;

    String id;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String name = txtName.getText();
        Integer weight = Integer.parseInt(txtSuppweight.getText());
        String company = txtCompanyName.getText();
        Double price = Double.parseDouble(txtPrice.getText());

        Supplier supplier = new Supplier(null,name, weight, company, price);

        supplierService.addSupplier(supplier);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        supplierService.deleteSupplier(id);
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

        List<Supplier> all = supplierService.getAll();
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList(all);

        tblSupplier.setItems(supplierList);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Supplier supplier = supplierService.searchSupplier(id);
        txtName.setText(supplier.getName());
        txtSuppweight.setText(supplier.getWeight()+"");
        txtCompanyName.setText(supplier.getCompany());
        txtPrice.setText(supplier.getPrice()+"");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String suppId = id;
        String name = txtName.getText();
        Integer weight = Integer.parseInt(txtSuppweight.getText());
        String company = txtCompanyName.getText();
        Double price = Double.parseDouble(txtPrice.getText());

        Supplier supplier = new Supplier(suppId,name, weight, company, price);

        supplierService.updateSupplier(suppId,supplier);

    }

    private void supplierIdSet(){
        List<Supplier> all = supplierService.getAll();

        ObservableList<String> supplierId = FXCollections.observableArrayList();

        all.forEach(supplier -> {
            supplierId.add(supplier.getId());
        });

        cmbSupplierId.setItems(supplierId);
    }

    private void loadTable(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colCompName.setCellValueFactory(new PropertyValueFactory<>("company"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierIdSet();
        loadTable();

        cmbSupplierId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                id = newValue.toString();
            }
        });


    }
}
