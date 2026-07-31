package repository.custom.impl;

import config.HibernateConfig;
import entity.OrderEntity;
import org.hibernate.Session;
import repository.custom.OrderDao;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity entity) {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;

//        public boolean placeOrder(OrderEntity order) {
//
//            Transaction tx = null;
//
//            try (Session session = HibernateConfig.getSession()) {
//
//                tx = session.beginTransaction();
//
//                session.persist(order); // cascade = ALL required
//
//                tx.commit();
//                return true;
//
//            } catch (Exception e) {
//
//                if (tx != null) tx.rollback();
//                return false;
//            }
//        }
    }

    @Override
    public boolean update(String s,OrderEntity entity) {
        return false;
    }

    @Override
    public boolean datele(String s) {
        return false;
    }

    @Override
    public OrderEntity search(String s) {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();

        OrderEntity order = session.get(OrderEntity.class, Long.parseLong(s));

        session.getTransaction().commit();
        session.close();

        return order;
    }

    @Override
    public List<OrderEntity> getAll() {
        return List.of();
    }
}
