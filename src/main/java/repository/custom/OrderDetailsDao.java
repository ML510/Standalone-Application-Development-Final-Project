package repository.custom;

import entity.OrderDetailEntity;
import repository.CrudRepository;

public interface OrderDetailsDao extends CrudRepository<OrderDetailEntity,String> {
}
