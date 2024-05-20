package DataBase;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The SingletonEntityManagerFactory class
 */
public class SingletonEntityManagerFactory {

    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static jakarta.persistence.EntityManagerFactory entityManagerFactory;

    private SingletonEntityManagerFactory() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) { //Daca nu e initializat
            entityManagerFactory = jakarta.persistence.Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); //creeeaza automat acel EntityManagerFactory
        }
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

}

