package repository.custom.impl;

import config.HibernateConfig;
import entity.OrderDetailEntity;
import org.hibernate.Session;
import repository.custom.OrderDetailsDao;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetailEntity entity) {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(String s, OrderDetailEntity entity) {
        return false;
    }

    @Override
    public boolean datele(String s) {
        return false;
    }

    @Override
    public OrderDetailEntity search(String s) {
        return null;
    }

    @Override
    public List<OrderDetailEntity> getAll() {
        return List.of();
    }
}
