package controller.admin;

import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminFromController {

    @FXML
    private AnchorPane loadAdminFrom;

    Injector injector = Guice.createInjector(new AppModule());

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {



        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/customerFrom.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent load = loader.load();

        loadAdminFrom.getChildren().clear();
        loadAdminFrom.getChildren().add(load);
    }

    @FXML
    void btnEmployeeRegisterOnAction(ActionEvent event) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/employeeRegisterFrom.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent load = loader.load();

        loadAdminFrom.getChildren().clear();
        loadAdminFrom.getChildren().add(load);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/itemFrom.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent load = loader.load();

        loadAdminFrom.getChildren().clear();
        loadAdminFrom.getChildren().add(load);
    }

    @FXML
    void btnSupperOnAction(ActionEvent event) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/view/supplierFrom.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent load = loader.load();

        loadAdminFrom.getChildren().clear();
        loadAdminFrom.getChildren().add(load);
    }

}
