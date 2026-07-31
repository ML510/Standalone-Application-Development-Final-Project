package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoardFromController {

    @FXML
    private AnchorPane loadFormContent;

    @FXML
    void btnAdminOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/adminLoging_from.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/employeeLoging_from.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);


    }

}
