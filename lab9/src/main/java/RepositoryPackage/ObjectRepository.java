package RepositoryPackage;

import DataBase.myLogger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.dom4j.tree.AbstractEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public abstract class ObjectRepository<T extends AbstractEntity, ID extends Serializable> {
    protected final EntityManager entityManager;

    private final myLogger logger = new myLogger();

    /**
     * The constructor for the entityManager class
     * @param entityManager The object of type entityManager
     */
    public ObjectRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Method used to get the entity type of class
     * @return The entity type of class
     */
    protected abstract Class<T> getEntityClass();

    /**
     * This method creates and inserts an object in the database
     * @param object The object type we want to create and insert
     */
    public void create(T object) throws SQLException {}

    /**
     * Here we look for an object with a specified id
     * @param id The id of the object we want to search for
     * @return The object with the specified id
     */
    public T findById(ID id) {
        logger.info("Searching for: " + id + " of " + getEntityClass().getSimpleName() + "\n");
        long startTime = System.currentTimeMillis();

        // Folosește EntityManager pentru a căuta un obiect în baza de date folosind ID-ul specificat
        T rez = this.entityManager.find(getEntityClass(), id);

        long endTime = System.currentTimeMillis();

        if (rez == null) {
            logger.info("There is no object of type " + getEntityClass().getSimpleName() + " with id: " + id + "\n");
        } else {
            logger.info("Found object of type " + getEntityClass().getSimpleName() + " with id: " + id + "\n");
        }

        logger.logExecutionTime(startTime, endTime);
        return rez;
    }

    /**
     * here we get a list of objects with a specified name
     * @param namePattern The name of the object we are looking for
     * @return The object with the specified name
     */
    public List<T> findByName(String namePattern) {
        logger.info("Searching for: " + namePattern + " of " + getEntityClass().getSimpleName() + "\n");
        long startTime = System.currentTimeMillis();

        //Construiește numele argumentului pentru interogare
        String buildArgument = getEntityClass().getSimpleName() + ".findByName";

        // Creează o interogare numită utilizând EntityManager-ul, bazându-se pe numele argumentului
        Query query = entityManager.createNamedQuery(buildArgument);

        // Setează parametrul interogării "namePattern" la valoarea furnizată ca argument
        query.setParameter("namePattern", namePattern);

        @SuppressWarnings("unchecked")
        List<T> rez = query.getResultList();

        long endTime = System.currentTimeMillis();

        if (rez.isEmpty()) {
            System.err.println(buildArgument + ": No" + getEntityClass().toString() + " Found");
        }

        logger.logExecutionTime(startTime, endTime);

        return rez;
    }

    /**
     * Here we persist an element in the database
     * @param entity The entity we want to persist in the database
     * @throws SQLException The exception thrown when something doesn't work
     */
    public void persist(T entity) throws SQLException{
        logger.info("Started to persist transaction");

        // Obține tranzacția curentă din EntityManager
        EntityTransaction transaction = entityManager.getTransaction();


        logger.info("Persisting " + getEntityClass().getSimpleName() + " entity");
        long startTime = System.currentTimeMillis();
        try {

            // Începe tranzacția
            transaction.begin();

            // Persistă entitatea in baza de date
            entityManager.persist(entity);

            // Confirmă modificarile efectuate

            transaction.commit();
            long endTime = System.currentTimeMillis();
            logger.info("Time to persist entity " + getEntityClass().getSimpleName() + ":");
            logger.logExecutionTime(startTime, endTime);
        } catch (Exception e) {
            System.err.println("Error while persisting " + entity.getClass().getSimpleName());
            logger.logException(e);
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.severe("Failed to persist " + entity.getClass().getSimpleName());
            long endTime = System.currentTimeMillis();
            logger.logExecutionTime(startTime, endTime);
            e.printStackTrace();
        }
    }

    /**
     * Here we find all object of a certain type
     * @return The list of objects of a certain type
     */
    public List<T> findAll(){

        logger.info("Searching for all " + getEntityClass().getSimpleName() + "\n");
        long startTime = System.currentTimeMillis();
        String argument = getEntityClass().getSimpleName() + ".findAll";
        Query query = entityManager.createNamedQuery(argument);

        @SuppressWarnings("unchecked")
        List<T> rez = query.getResultList();

        if (rez.isEmpty()) {
            System.err.println(argument + ": No" + getEntityClass().toString() + " Found");
        }

        long endTime = System.currentTimeMillis();

        logger.logExecutionTime(startTime, endTime);

        return rez;
    }
}
