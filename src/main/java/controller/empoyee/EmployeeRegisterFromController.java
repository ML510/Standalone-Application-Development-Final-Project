package controller.empoyee;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.jasypt.util.text.BasicTextEncryptor;
import service.custom.EmployeeService;

import java.util.List;

public class EmployeeRegisterFromController {

    @Inject
    EmployeeService employeeService;

    @FXML
    private JFXTextField txtCPasswoed;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPasswoed;

    @FXML
    void btnEmployeeViewOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

        if(txtPasswoed.getText().equals(txtCPasswoed.getText())){

            boolean isEmail = false;
            List<Employee> employeeList = employeeService.getAll();

            for (Employee employee : employeeList) {
                if (employee.getEmail().equals(txtEmail.getText())) {
                    isEmail = true;
                }
            }

            if (!isEmail){

                String key = "#1234#";

                BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                basicTextEncryptor.setPassword(key);

                String userName = txtName.getText();
                String email = txtEmail.getText();
                String password = txtPasswoed.getText();

                Employee employee = new Employee(null,userName, email, basicTextEncryptor.encrypt(password));

                employeeService.addEmployee(employee);

                new Alert(Alert.AlertType.INFORMATION,"Register Successfully...").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Same Password. Try again...").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Not Same Password...").show();
        }

    }

}
