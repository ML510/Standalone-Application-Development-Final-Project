package service.custom.impl;

import dto.Customer;
import dto.Employee;
import dto.Item;
import dto.Order;
import entity.OrderEntity;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import repository.custom.OrderDao;
import service.custom.CustomerService;
import service.custom.EmployeeService;
import service.custom.ItemService;
import service.custom.OrdersService;

import java.util.List;

public class OrderServiceImpl implements OrdersService {

    @Inject
    OrderDao orderDao;

    @Inject
    ItemService itemService;

    @Inject
    EmployeeService employeeService;

    @Inject
    CustomerService customerService;

    @Override
    public boolean addOrders(Order order) {
        OrderEntity map = new ModelMapper().map(order, OrderEntity.class);
        orderDao.save(map);
        return true;
    }

    @Override
    public boolean updateOrders(String id, Order order) {
        return false;
    }

    @Override
    public boolean deleteOrders(String id, Order order) {
        return false;
    }

    @Override
    public Order searchOrders(String id) {
        OrderEntity search = orderDao.search(id);

        return new ModelMapper().map(search, Order.class);

    }

    @Override
    public List<Order> getAll() {
        return List.of();
    }

    @Override
    public List<Item> getAllItem(){
        return  itemService.getAll();
    }

    @Override
    public List<Employee> getEmployeeAll(){
        return employeeService.getAll();
    }

    @Override
    public List<Customer> getCustomerAll(){
        return customerService.getAll();
    }

}
