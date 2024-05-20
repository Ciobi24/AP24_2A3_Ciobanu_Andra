package RepositoryPackage;

import DataBase.myLogger;
import JPAObjects.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.SQLException;
import java.util.List;

/**
 * The AuthorRepository class
 */
public class AuthorRepository extends ObjectRepository<Author, Integer> {

    /**
     * The constructor for the AuthorRepository class
     * @param em The EntityManager element
     */
    public AuthorRepository(EntityManager em){
        super(em);
    } //Paseaza constructorul catre clasa parinte pentru initializare

    /**
     * This specifies the class Author for the entity type of this repository
     * @return The entity type of this repository which is Author
     */
    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    /**
     *  It creates and persists an entity of type author
     * @param author The object type we want to create and insert
     * @throws SQLException The exception thrown when the persist entity doesn't work
     */
    public void create(Author author) throws SQLException {
        AuthorRepository tempRepo = new AuthorRepository(entityManager);
        if(tempRepo.findByName(author.getAuthorName())!=null) {
            System.err.println("This Author already exists");
        }
        persist(author);

    }
}

/* public void create_specific(Author author) {
    EntityTransaction transaction = entityManager.getTransaction();
    long time1 = System.currentTimeMillis();
    try {
        transaction.begin();
        entityManager.persist(author);
        transaction.commit();

    } catch (Exception e) {
        System.err.println("Error while persisting " + author.getClass().getSimpleName());
        if (transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}



public Author findById_Specific(int id) {
    Author rez = entityManager.find(Author.class, id);
    if (rez == null) {
        System.err.println("AuthorRepository.findByID:Author not found");
    }
    return rez;
}

public List<Author> findByName_Specific(String namePattern) {
    Query query = entityManager.createNamedQuery("Author.findByName");
    query.setParameter("namePattern", "%" + namePattern + "%");

    @SuppressWarnings("unchecked")
    List<Author> rez = query.getResultList();


    if (rez.isEmpty()) {
        System.err.println("AuthorRepository.findByName: No Author Found");
    }

    return rez;
} */
