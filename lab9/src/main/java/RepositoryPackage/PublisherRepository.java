package RepositoryPackage;

import JPAObjects.Genre;
import JPAObjects.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.SQLException;
import java.util.List;

import static DataBase.Database.commit;
import static DataBase.Database.rollback;

/**
 * The PublisherRepository class
 */
public class PublisherRepository extends ObjectRepository<Publisher, Integer>{

    /**
     * The constructor for the PublisherRepository class
     * @param em The EntityManager element
     */
    public PublisherRepository(EntityManager em){
        super(em);
    }

    /**
     * This specifies the class Publisher for the entity type of this repository
     * @return The entity type of this repository which is Publisher
     */
    @Override
    protected Class<Publisher> getEntityClass() {
        return Publisher.class;
    }

    /**
     *  It creates and persists an entity of type genre
     * @param publisher The object type we want to create and insert
     * @throws SQLException The exception thrown when the persist entity doesn't work
     */
    public void create(Publisher publisher) throws SQLException {
        persist(publisher);
    }
}

/* public void create_specific(Publisher publisher) {
    EntityTransaction transaction = this.entityManager.getTransaction();

    try {
        transaction.begin();
        entityManager.persist(publisher);
        transaction.commit();
    } catch (Exception e) {
        System.err.println("Error while persisting " + publisher.getClass().getSimpleName());
        if (transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}

public Publisher findById_Specific(int id) {
    Publisher rez = this.entityManager.find(Publisher.class, id);
    if (rez == null) {
        System.err.println("PublisherRepository.findByID: Publisher not found");
    }
    return rez;
}

public List<Publisher> findByName_Specific(String namePattern) {
    Query query = this.entityManager.createNamedQuery("Publisher.findByName");
    //query.setParameter("namePattern", "%" + namePattern + "%");
    query.setParameter("namePattern", namePattern);

    @SuppressWarnings("unchecked")
    List<Publisher> rez = query.getResultList();


    if (rez.isEmpty()) {
        System.err.println("PublisherRepository.findByName: No Publisher Found");
    }

    return rez;
} */
