package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class employeeDashFrom {

    Injector injector = Guice.createInjector(new AppModule());

    @FXML
    private AnchorPane loadEmployee;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/customerFrom.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent load = loader.load();

        loadEmployee.getChildren().clear();
        loadEmployee.getChildren().add(load);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/itemFrom.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent load = loader.load();

        loadEmployee.getChildren().clear();
        loadEmployee.getChildren().add(load);
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/orderFrom.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent load = loader.load();

        loadEmployee.getChildren().clear();
        loadEmployee.getChildren().add(load);
    }

    @FXML
    void btnSupperOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/supplierFrom.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent load = loader.load();

        loadEmployee.getChildren().clear();
        loadEmployee.getChildren().add(load);

    }

}
