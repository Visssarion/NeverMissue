package dev.vissa.nevermissue.server.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {
	private static SessionFactory sessionFactory = null;

    private static SessionFactory buildSessionFactory() {
        try {
            // Load the configuration and build the SessionFactory
        	Configuration conf = new Configuration().configure();
            return conf.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
        sessionFactory = null;
    }
    
    public static void startup() {
    	sessionFactory = buildSessionFactory();
    }
    
    /**
     * Deprecated.
     * <br>
     * Opens new session. Deprecated due to not supporting multiple threads.
     * <br>
     * Use getCurrentSession instead
     */
    @Deprecated(forRemoval = false)
    public static Session openSession() {
    	return sessionFactory.openSession();
    }
    
    public static Session getCurrentSession() {
    	return sessionFactory.getCurrentSession();
    }
}
