package repository.custom.impl;

import config.HibernateConfig;
import entity.ItemEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.ItemDao;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity entity) {
        System.out.println("Customer Dao : - "+entity);
        Session session = HibernateConfig.geSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(String s,ItemEntity entity) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.geSession()) {

            transaction = session.beginTransaction();


            ItemEntity existing = session.get(ItemEntity.class, Long.parseLong(s));

            if (existing == null) {
                return false;
            }

            existing.setId(entity.getId());
            existing.setName(entity.getName());
            existing.setCategories(entity.getCategories());
            existing.setSize(entity.getSize());
            existing.setPrice(entity.getPrice());
            existing.setQty(entity.getQty());
            existing.setSupplierId(entity.getSupplierId());

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
            ItemEntity existing = session.get(ItemEntity.class, Long.parseLong(s));

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
    public ItemEntity search(String s) {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();

        ItemEntity item = session.get(ItemEntity.class, Long.parseLong(s));

        session.getTransaction().commit();
        session.close();

        return item;
    }

    @Override
    public List<ItemEntity> getAll() {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();

        List<ItemEntity> itemEntityList = session
                .createQuery("FROM ItemEntity", ItemEntity.class)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        System.out.println("Item Dao " +itemEntityList);

        return itemEntityList;
    }
}
