package service.custom;


import dto.Customer;
import dto.Employee;
import dto.Item;
import dto.Order;
import service.SuperService;

import java.util.List;

public interface OrdersService extends SuperService {
    boolean addOrders (Order order);
    boolean updateOrders(String id,Order order);
    boolean deleteOrders(String id,Order order);
    Order searchOrders (String id);
    List<Order> getAll();
    List<Item> getAllItem();
    List<Employee> getEmployeeAll();
    List<Customer> getCustomerAll();
}
