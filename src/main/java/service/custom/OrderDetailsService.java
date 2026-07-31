package service.custom;

import dto.OrderDetail;
import service.SuperService;

import java.util.List;

public interface OrderDetailsService extends SuperService {
    boolean addOrderDetails (OrderDetail orderDetail);
    boolean addOrderDetails (List<OrderDetail> orderDetails);
//    boolean updateOrderDetails(String id,OrderDetail orderDetail);
//    boolean deleteSOrderDetails(String id);
//    OrderDetail searchOrderDetails (String id);
//    List<OrderDetail> getAll();
}
