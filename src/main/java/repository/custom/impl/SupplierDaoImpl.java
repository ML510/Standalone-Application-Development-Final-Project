package repository.custom.impl;

import config.HibernateConfig;
import entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.SupplierDao;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
        System.out.println("Supplier Dao : - "+entity);
        Session session = HibernateConfig.geSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(String id,SupplierEntity entity) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.geSession()) {

            transaction = session.beginTransaction();


            SupplierEntity existing = session.get(SupplierEntity.class, Long.parseLong(id));

            if (existing == null) {
                return false;
            }

            existing.setId(entity.getId());
            existing.setName(entity.getName());
            existing.setWeight(entity.getWeight());
            existing.setCompany(entity.getCompany());
            existing.setPrice(entity.getPrice());

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
            SupplierEntity existing = session.get(SupplierEntity.class, Long.parseLong(s));

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
    public SupplierEntity search(String s) {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();

        SupplierEntity supplier = session.get(SupplierEntity.class, Long.parseLong(s));

        session.getTransaction().commit();
        session.close();

        return supplier;
    }

    @Override
    public List<SupplierEntity> getAll() {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();

        List<SupplierEntity> supplierList = session
                .createQuery("FROM SupplierEntity", SupplierEntity.class)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return supplierList;
    }
}
