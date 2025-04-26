package lk.ijse.config;

import lk.ijse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(User.class).addAnnotatedClass(Course.class).addAnnotatedClass(Registration.class).addAnnotatedClass(PaymentDetails.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        if(factoryConfiguration == null){
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }


    /*// 1. Load Configurations
Configuration configuration = new Configuration().configure();

// 2. Build Service Registry
StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
    .applySettings(configuration.getProperties())
    .build();

// 3. Build SessionFactory
SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

// 4. Open Session
Session session = sessionFactory.openSession();

// 5. DB Operations
session.beginTransaction();
session.save(new Customer("John Doe"));
session.getTransaction().commit();
session.close();
*/


}
