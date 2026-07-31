package repository.custom.impl;

import config.HibernateConfig;
import entity.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.CustomerDao;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity entity) {

        System.out.println("Customer Dao : - "+entity);
        Session session = HibernateConfig.geSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(String s,CustomerEntity entity) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.geSession()) {

            transaction = session.beginTransaction();


            CustomerEntity existing = session.get(CustomerEntity.class, Long.parseLong(s));

            if (existing == null) {
                return false;
            }

            existing.setId(entity.getId());
            existing.setName(entity.getName());
            existing.setAddress(entity.getAddress());
            existing.setPhoneNumber(entity.getPhoneNumber());

            session.merge(existing);

            transaction.commit();
            return true;

        } catch (Exception e) {

            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean datele(String s) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.geSession()) {

            transaction = session.beginTransaction();

            // Fetch entity from DB first
            CustomerEntity existing = session.get(CustomerEntity.class, Long.parseLong(s));

            if (existing == null) {
                return false; // entity not found
            }

            session.remove(existing); // delete

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CustomerEntity search(String s) {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();

        CustomerEntity customer = session.get(CustomerEntity.class, Long.parseLong(s));

        session.getTransaction().commit();
        session.close();

        return customer;
    }

    @Override
    public List<CustomerEntity> getAll() {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();

        List<CustomerEntity> customerList = session
                .createQuery("FROM CustomerEntity", CustomerEntity.class)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return customerList;
    }
}
