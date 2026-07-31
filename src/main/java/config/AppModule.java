package config;

import com.google.inject.AbstractModule;
import repository.custom.*;
import repository.custom.impl.*;
import service.custom.*;
import service.custom.impl.*;

public class AppModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(CustomerService.class).to(CustomerServiceImpl.class);
        bind(CustomerDao.class).to(CustomerDaoImpl.class);

        bind(EmployeeService.class).to(EmployeeServiceImpl.class);
        bind(EmployeeDao.class).to(EmployeeDaoImpl.class);

        bind(ItemService.class).to(ItemServiceImpl.class);
        bind(ItemDao.class).to(ItemDaoImpl.class);

        bind(OrdersService.class).to(OrderServiceImpl.class);
        bind(OrderDao.class).to(OrderDaoImpl.class);

        bind(SupplierService.class).to(SupplierServiceImpl.class);
        bind(SupplierDao.class).to(SupplierDaoImpl.class);

        bind(OrderDetailsService.class).to(OrderDetailsServiceImpl.class);
        bind(OrderDetailsDao.class).to(OrderDetailDaoImpl.class);

    }
}
