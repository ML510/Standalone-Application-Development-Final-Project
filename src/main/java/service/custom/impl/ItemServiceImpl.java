package service.custom.impl;

import dto.Item;
import dto.Supplier;
import entity.ItemEntity;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.ItemDao;
import service.custom.ItemService;
import service.custom.SupplierService;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    @Inject
    ItemDao itemDao;

    @Inject
    SupplierService supplierService;

    @Override
    public boolean addItem(Item item) {
        ItemEntity map = new ModelMapper().map(item, ItemEntity.class);

        itemDao.save(map);

        return true;
    }

    @Override
    public boolean updateItem(String id, Item item) {
        ItemEntity map = new ModelMapper().map(item, ItemEntity.class);

        itemDao.update(id,map);
        return true;
    }

    @Override
    public boolean deleteItem(String id) {
        itemDao.datele(id);
        return true;
    }

    @Override
    public Item searchItem(String id) {
        ItemEntity searchEntity = itemDao.search(id);
        Item map = new ModelMapper().map(searchEntity, Item.class);
        return map;
    }

    @Override
    public List<Item> getAll() {
        List<ItemEntity> all = itemDao.getAll();
        System.out.println("Item Service :- "+all);

        ObservableList<Item> itemList = FXCollections.observableArrayList();

        all.forEach(itemEntity -> {
            System.out.println("ItemEntity ...."+itemEntity.getId());
            Item map = new ModelMapper().map(itemEntity, Item.class);
            System.out.println("Map........."+map.getId());
            itemList.add(map);
        });

        System.out.println("Item Service retun :- "+itemList);
        return itemList;
    }

    @Override
    public List<Supplier> supplierData(){
        return supplierService.getAll();
    }
}
