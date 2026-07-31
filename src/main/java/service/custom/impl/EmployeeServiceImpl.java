package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    EmployeeDao employeeDao;

    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeEntity map = new ModelMapper().map(employee, EmployeeEntity.class);

        employeeDao.save(map);

        return true;
    }

    @Override
    public boolean updateEmployee(String id, Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(String id, Employee employee) {
        return false;
    }

    @Override
    public Employee searchEmployee(String id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        List<EmployeeEntity> all = employeeDao.getAll();
        List<Employee> employeeList = new ArrayList<>();

        all.forEach(employeeEntity -> {
            Employee map = new ModelMapper().map(employeeEntity, Employee.class);
            employeeList.add(map);
        });
        return employeeList;
    }
}
