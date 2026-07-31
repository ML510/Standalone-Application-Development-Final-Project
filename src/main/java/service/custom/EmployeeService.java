package service.custom;


import dto.Employee;
import service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {
    boolean addEmployee (Employee employee);
    boolean updateEmployee(String id,Employee employee);
    boolean deleteEmployee(String id,Employee employee);
    Employee searchEmployee (String id);
    List<Employee> getAll();
}
