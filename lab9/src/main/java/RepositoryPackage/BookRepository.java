package RepositoryPackage;

import JPAObjects.Author;
import JPAObjects.Book;
import JPAObjects.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.SQLException;
import java.util.List;

import static DataBase.Database.commit;
import static DataBase.Database.rollback;

/**
 * The BookRepository class
 */
public class BookRepository extends ObjectRepository<Book, Integer>{

    /**
     * The constructor for the BookRepository class
     * @param em The EntityManager element
     */
    public BookRepository(EntityManager em){
        super(em);
    }

    /**
     * This specifies the class Book for the entity type of this repository
     * @return The entity type of this repository which is Book
     */
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    /**
     *  It creates and persists an entity of type book
     * @param book The object type we want to create and insert
     * @throws SQLException The exception thrown when the persist entity doesn't work
     */
    public void create(Book book) throws SQLException {
        Publisher temp;
        PublisherRepository tempRepo = new PublisherRepository(entityManager);
        temp = book.getPublisher();
        if(tempRepo.findByName(temp.getPublisherName())==null){
            System.err.print("The publication doesn't exist");
        }
        temp.addBooks(book);
        persist(book);
    }

}

/* public void create_specific(Book book) {
    EntityTransaction transaction = entityManager.getTransaction();

    try {
        transaction.begin();
        Publisher temp;
        PublisherRepository tempRepo = new PublisherRepository(entityManager);
        temp = book.getPublisher();
        if(tempRepo.findByName(temp.getPublisherName())==null){
            System.err.print("The publication doesn't exist");
        }
        temp.addBooks(book);
        entityManager.merge(temp);
        entityManager.persist(book);
        transaction.commit();
    } catch (Exception e) {
        System.err.println("Error while persisting " + book.getClass().getSimpleName());
        if (transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}

public Book findById_Specific(int id) {
    Book rez = entityManager.find(Book.class, id);
    if (rez == null) {
        System.err.println("BookRepository.findByID:Book not found");
    }

    return rez;
}

public List<Book> findByName_Specific(String namePattern) {
    Query query = entityManager.createNamedQuery("Book.findByName");
    query.setParameter("namePattern", "%" + namePattern + "%");

    @SuppressWarnings("unchecked") // fiindca mna asa trebuie..
    List<Book> rez = query.getResultList();


    if (rez.isEmpty()) {
        System.err.println("BookRepository.findByName: No Book Found");
    }

    return rez;
} */
