package service.custom;


import dto.Item;
import dto.Supplier;
import service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {
    boolean addItem (Item item);
    boolean updateItem(String id,Item item);
    boolean deleteItem(String id);
    Item searchItem (String id);
    List<Item> getAll();
    List<Supplier> supplierData();
}
