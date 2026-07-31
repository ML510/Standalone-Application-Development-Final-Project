package repository.custom.impl;

import config.HibernateConfig;
import entity.EmployeeEntity;
import org.hibernate.Session;
import repository.custom.EmployeeDao;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity entity) {
        System.out.println("Employee Dao : - "+entity);
        Session session = HibernateConfig.geSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(String s,EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean datele(String s) {
        return false;
    }

    @Override
    public EmployeeEntity search(String s) {
        return null;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateConfig.geSession();
        session.beginTransaction();

        List<EmployeeEntity> employeeList = session
                .createQuery("FROM EmployeeEntity", EmployeeEntity.class)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return employeeList;
    }
}
