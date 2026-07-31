package controller.item;

import com.jfoenix.controls.JFXComboBox;
import dto.Item;
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
import service.custom.ItemService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFromController implements Initializable {

    public JFXComboBox cmbSize;
    @FXML
    private JFXComboBox cmbCategories;

    @FXML
    private JFXComboBox cmbItemId;

    @FXML
    private JFXComboBox cmbSupplierId;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colItemName;

    @FXML
    private TableColumn colPackSize;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableView tblItem;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @Inject
    ItemService itemService;

    String catrgo;
    String size;
    String supplierId;
    String itemId;


    @FXML
    void btnAddOnAction(ActionEvent event) {

        String name = txtName.getText();
        String ct = catrgo;
        String siz = size;
        Double price = Double.parseDouble(txtUnitPrice.getText());
        Integer qty = Integer.parseInt(txtQty.getText());
        String supId = supplierId;

        Item item = new Item(null, name, ct, siz, price, qty, supId);

        itemService.addItem(item);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        itemService.deleteItem(itemId);

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        List<Item> all = itemService.getAll();
        ObservableList<Item> items = FXCollections.observableArrayList(all);
        tblItem.setItems(items);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Item item = itemService.searchItem(itemId);

        cmbCategories.setPromptText(item.getCategories());
        cmbSize.setPromptText(item.getSize());
        txtName.setText(item.getName());
        cmbSupplierId.setPromptText(item.getSupplierId());
        txtUnitPrice.setText(item.getPrice().toString());
        txtQty.setText(item.getQty().toString());


        cmbCategories.setDisable(true);
        cmbSize.setDisable(true);
        txtName.setEditable(false);
        cmbSupplierId.setDisable(true);
        txtUnitPrice.setEditable(false);
        txtQty.setEditable(false);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        Long id = Long.parseLong(itemId);
        String name = txtName.getText();
        String ct = catrgo;
        String siz = size;
        Double price = Double.parseDouble(txtUnitPrice.getText());
        Integer qty = Integer.parseInt(txtQty.getText());
        String supId = supplierId;

        Item item = new Item(id, name, ct, siz, price, qty, supId);

        itemService.updateItem(itemId,item);

    }

    private void cmbSet(){
        List<Item> itemIds = itemService.getAll();
        System.out.println(itemIds);

        ObservableList<Long> itemIdList = FXCollections.observableArrayList();

        itemIds.forEach(item -> {
            itemIdList.add(item.getId());
            System.out.println(item.getId());
        });
        cmbItemId.setItems(itemIdList);

        ObservableList<String> categories = FXCollections.observableArrayList();
        String[] ct = {"Ladies","Gents","Kids"};
        for (String s : ct) {
            categories.add(s);
        }
        cmbCategories.setItems(categories);

        ObservableList<String> sizeObserverList = FXCollections.observableArrayList();
        String[] size = {"S","M","L","XL","XXL"};
        for (String s : size) {
            sizeObserverList.add(s);
        }
        cmbSize.setItems(sizeObserverList);

        List<Supplier> supplierList = itemService.supplierData();
        ObservableList<String> supplierId = FXCollections.observableArrayList();

        supplierList.forEach(supplier -> {
            supplierId.add(supplier.getId());
        });
        cmbSupplierId.setItems(supplierId);

    }

    private void tableLoad(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbSet();
        tableLoad();
        cmbCategories.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            if(newValue != null){
                catrgo = newValue.toString();
            }
        });

        cmbSize.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            if(newValue != null){
                size = newValue.toString();
            }
        });

        cmbSupplierId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            if(newValue != null){
                supplierId = newValue.toString();
            }
        });

        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            if(newValue != null){
                itemId = newValue.toString();
            }
        });
    }
}
