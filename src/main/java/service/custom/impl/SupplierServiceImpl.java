package service.custom.impl;

import dto.Supplier;
import entity.SupplierEntity;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.SupplierDao;
import service.custom.SupplierService;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    @Inject
    SupplierDao supplierDao;

    @Override
    public boolean addSupplier(Supplier supplier) {

        SupplierEntity map = new ModelMapper().map(supplier, SupplierEntity.class);

        supplierDao.save(map);

        return true;

    }

    @Override
    public boolean updateSupplier(String id,Supplier supplier) {

        SupplierEntity map = new ModelMapper().map(supplier, SupplierEntity.class);

        supplierDao.update(id,map);

        return true;
    }

    @Override
    public boolean deleteSupplier(String id) {
        supplierDao.datele(id);
        return true;
    }

    @Override
    public Supplier searchSupplier(String id) {
        SupplierEntity supplierEntity = supplierDao.search(id);

        Supplier map = new ModelMapper().map(supplierEntity, Supplier.class);

        return map;
    }

    @Override
    public List<Supplier> getAll() {
        List<SupplierEntity> all = supplierDao.getAll();


        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

        all.forEach(supplierEntity -> {
            Supplier map = new ModelMapper().map(supplierEntity, Supplier.class);
            supplierList.add(map);
        });

        return supplierList;
    }
}
