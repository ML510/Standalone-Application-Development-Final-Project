package service.custom.impl;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import repository.custom.OrderDetailsDao;
import service.custom.OrderDetailsService;

import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Inject
    OrderDetailsDao orderDetailsDao;

    @Override
    public boolean addOrderDetails(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            boolean isAddOrderDetail = addOrderDetails(orderDetail);
            if (!isAddOrderDetail){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addOrderDetails(OrderDetail orderDetail) {
        OrderDetailEntity map = new ModelMapper().map(orderDetail, OrderDetailEntity.class);
        return orderDetailsDao.save(map);
    }
}
