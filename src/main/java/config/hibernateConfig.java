package config;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

/**
 * Created by ud on 18/3/17.
 */

@SuppressWarnings("depreciation")
public class hibernateConfig {
    private static SessionFactory sessionFactory;

    static {
        try{
            sessionFactory =new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex){
            System.out.println("Session could not be created :"+ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
