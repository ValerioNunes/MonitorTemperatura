package database;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class JPA {

    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
    private static EntityManagerFactory factory;
    private static Map<Object, Object> connectionProperties = new HashMap<Object, Object>();


    /**
     * Cria uma entity manager factory única e o retorna em todas as demais chamadas
     */
    public static EntityManagerFactory getFactory() {

        if (factory == null || !factory.isOpen()) {
        	
        	
        		
        		factory = Persistence.createEntityManagerFactory("mysql", connectionProperties);
     
        	
        }
        return factory;

    }

    /**
     * Cria um entity manager único (se criar = true) para a thread e o retorna em todas as demais chamadas
     */
    public static EntityManager em(boolean criar) {

        EntityManager em = (EntityManager) threadLocal.get();

        if (em == null || !em.isOpen()) {

            if (criar) {

                em = getFactory().createEntityManager();
                threadLocal.set(em);

            }

        }

        return em;

    }

    /**
     * Cria um entity manager único para a thread e o retorna em todas as demais chamadas
     */
    public static EntityManager em() {

        return em(true);

    }


}
