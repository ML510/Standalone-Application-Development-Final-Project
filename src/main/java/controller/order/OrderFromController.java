package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import jakarta.inject.Inject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import service.custom.ItemService;
import service.custom.OrderDetailsService;
import service.custom.OrdersService;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class OrderFromController implements Initializable {

    public JFXTextField txtItemName;
    public JFXComboBox cmbPayment;
    public JFXComboBox cmbCustomer;
    @FXML
    private JFXComboBox cmbEmployeeId;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private JFXComboBox comboItemCode;

    @FXML
    private Label lblDay;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblTime;

    @FXML
    private TableView tblOrder;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtUnitPrice;

    @Inject
    OrdersService ordersService;

    @Inject
    OrderDetailsService orderDetailsService;

    @Inject
    ItemService itemService;

    String employeeCode;
    String itemCode;
    String customerCode;
    String payMent;

    List<OrderDetail> orderData = new ArrayList<>();

    ObservableList<OrderTBM> orderTBMS = FXCollections.observableArrayList();
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String itemId = comboItemCode.getValue().toString();
        String itemName = txtItemName.getText();
        Integer qty = parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Double total = unitPrice*qty;

        OrderTBM orderTBM = new OrderTBM(itemId, itemName, qty, unitPrice, total);

        orderTBMS.add(orderTBM);
        tblOrder.setItems(orderTBMS);
        netTotal();

        String tm = cmbPayment.getValue().toString();
        System.out.println(tm);

    }

    @FXML
    void btnCommitOnAction(ActionEvent event) {
//        String date = lblDay.getText();

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {


        String date = lblDay.getText();
        String payMent = cmbPayment.getValue().toString();
        Double netTotal = Double.parseDouble(lblNetTotal.getText());
        String cumstomerId = cmbCustomer.getValue().toString();
        String employeeId = cmbEmployeeId.getValue().toString();

        Order order = new Order(null, date, payMent, netTotal, cumstomerId, employeeId);
        boolean isOrderAdd = ordersService.addOrders(order);

        if (isOrderAdd){
            orderTBMS.forEach(orderTBM -> {
                orderData.add(new OrderDetail(null,orderTBM.getItemId(),orderTBM.getQty(),orderTBM.getUnitPrice()));
            });
            boolean isOrderDetailsAdd = orderDetailsService.addOrderDetails(orderData);
            if (isOrderDetailsAdd){
                boolean isUpdateItem = orderUpdateItems(orderData);
                if (isUpdateItem){
                    new Alert(Alert.AlertType.INFORMATION,"Order Placed").show();
                }

            }
        }


    }

    private boolean orderUpdateItems(List<OrderDetail> orderDetails){
        for (OrderDetail orderDetail : orderDetails) {
            boolean isUpdate = orderUpdateItems(orderDetail);
            if (!isUpdate){
                return false;
            }
        }
        return true;
    }

    private boolean orderUpdateItems(OrderDetail orderDetail){
        Item item = itemService.searchItem(orderDetail.getItemId());
                    int newQty = item.getQty() - orderDetail.getQty();
                    return itemService.updateItem(orderDetail.getItemId(), new Item(
                            item.getId(),
                            item.getName(),
                            item.getCategories(),
                            item.getSize(),
                            item.getPrice(),
                            newQty,
                            item.getSupplierId()
                    ));
    }
    private void netTotal(){
        Double netTotal = 0.0;
        for (OrderTBM orderTBM : orderTBMS) {
            netTotal += orderTBM.getTotal();
        }
        lblNetTotal.setText(netTotal.toString());
    }
    private void setDateAndTime(){

        //------------------ Set Date ------------------------------
        Date date = new Date();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dataFormat.format(date);
        lblDay.setText(format);

        System.out.println(format);

        //------------------ Set Time ------------------------------
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }



    private void setItemId(){
        List<Item> allItem = ordersService.getAllItem();
        ObservableList<String> itemId = FXCollections.observableArrayList();
        allItem.forEach(item -> {
            itemId.add(item.getId().toString());
        });
        comboItemCode.setItems(itemId);
    }

    private void setItemData(String id){
        List<Item> allItem = ordersService.getAllItem();
        Item selectItem = null;
        for (Item item : allItem) {
            item.getId();
            if (id.equals(item.getId().toString())){
                selectItem = item;
            }
        }
        txtItemName.setText(selectItem.getName());
        txtSize.setText(selectItem.getSize());
        txtUnitPrice.setText(selectItem.getPrice().toString());
    }

    private void setEmployeeId(){
        List<Employee> employeeAll = ordersService.getEmployeeAll();
        ObservableList<String> employeeId = FXCollections.observableArrayList();

        employeeAll.forEach(employee -> {
           employeeId.add(employee.getId());
        });
        cmbEmployeeId.setItems(employeeId);
    }

    private void setEmployeeData(String id){
        List<Employee> employeeAll = ordersService.getEmployeeAll();

        Employee selectEmployee = null;
        for (Employee employee : employeeAll) {
            if (id.equals(employee.getId())){
                selectEmployee = employee;
            }
        }
        txtName.setText(selectEmployee.getName());
        txtEmail.setText(selectEmployee.getEmail());
    }

    private void setCustomer(){
        List<Customer> customerAll = ordersService.getCustomerAll();
        ObservableList<String> customerIds = FXCollections.observableArrayList();

        customerAll.forEach(customer -> {
            customerIds.add(customer.getId());
        });
        cmbCustomer.setItems(customerIds);
    }

    private void setPaymentType(){
        String[] payType = {"Cash","Card"};
        ObservableList<String> strings = FXCollections.observableArrayList(payType);
        cmbPayment.setItems(strings);

    }

    private void tableLoad(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDateAndTime();
        setItemId();
        setEmployeeId();
        setCustomer();
        setPaymentType();
        tableLoad();

        comboItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            if (newValue!=null){
                itemCode =newValue.toString();
                setItemData(itemCode);
            }

        });
        cmbEmployeeId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            if (newValue!=null){
                employeeCode = newValue.toString();
                setEmployeeData(employeeCode);
            }
        });

        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            if (newValue!=null){
                customerCode = newValue.toString();
            }
        });

        cmbPayment.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            if (newValue!=null){
                payMent = newValue.toString();
            }
        });
    }
}
