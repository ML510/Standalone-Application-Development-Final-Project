package service.custom.impl;

import com.google.inject.Inject;
import dto.Customer;
import entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import repository.custom.CustomerDao;
import service.custom.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Inject
   CustomerDao customerDao;

    @Override
    public boolean addCustomer(Customer customer) {

        System.out.println("Customer Service "+customer);

        CustomerEntity map = new ModelMapper().map(customer, CustomerEntity.class);

        customerDao.save(map);

        return true;
    }

    @Override
    public boolean updateCustomer(String id, Customer customer) {
        CustomerEntity map = new ModelMapper().map(customer, CustomerEntity.class);
        customerDao.update(id,map);
        return true;
    }

    @Override
    public boolean deleteCustomer(String id) {
        customerDao.datele(id);
        return true;
    }

    @Override
    public Customer searchCustomer(String id) {
//        CustomerEntity search = customerDao.search(id);
//        Customer map = new ModelMapper().map(customerDao.search(id), Customer.class);
        return new ModelMapper().map(customerDao.search(id), Customer.class);
    }

    @Override
    public List<Customer> getAll() {
        List<CustomerEntity> customergetAll = customerDao.getAll();

        System.out.println(customergetAll);

        List<Customer> customerDto = new ArrayList<>();

        customergetAll.forEach(customerEntity -> {
            Customer map = new ModelMapper().map(customerEntity, Customer.class);
            customerDto.add(map);

        });

        return customerDto;

    }
}
