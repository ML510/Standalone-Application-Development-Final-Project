package config;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {

    private static SessionFactory sessian = createSession();

    private static SessionFactory createSession() {

        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(ItemEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(OrderDetailEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();

        return metadata.getSessionFactoryBuilder().build();
    }
    public static Session geSession(){
        return sessian.openSession();
    }
}
