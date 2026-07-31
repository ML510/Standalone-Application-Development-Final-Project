package service.custom;


import dto.Supplier;
import service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {

    boolean addSupplier (Supplier supplier);
    boolean updateSupplier(String id,Supplier supplier);
    boolean deleteSupplier(String id);
    Supplier searchSupplier (String id);
    List<Supplier> getAll();
}




