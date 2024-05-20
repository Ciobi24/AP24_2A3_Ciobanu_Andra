package RepositoryPackage;

import JPAObjects.Book;
import JPAObjects.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.SQLException;
import java.util.List;

import static DataBase.Database.commit;
import static DataBase.Database.rollback;

/**
 * The GenreRepository class
 */
public class GenreRepository extends ObjectRepository<Genre, Integer>{

    /**
     * The constructor for the GenreRepository class
     * @param em The EntityManager element
     */
    public GenreRepository(EntityManager em){
        super(em);
    } //Apeleaza constructorul superclasei ObjectRepository

    /**
     * This specifies the class Genre for the entity type of this repository
     * @return The entity type of this repository which is Genre
     */
    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class; //O referinta la obiectul Class, asociat cu clasa Genre
    }

    /**
     *  It creates and persists an entity of type genre
     * @param genre The object type we want to create and insert
     * @throws SQLException The exception thrown when the persist entity doesn't work
     */
    public void create(Genre genre) throws SQLException {
        persist(genre);
    }
}

/* public void create_specific(Genre genre) {
    //Obține o referință la tranzacția curentă de la EntityManager.
    EntityTransaction transaction = entityManager.getTransaction();

    try {
        transaction.begin();
        entityManager.persist(genre);
        transaction.commit();
    } catch (Exception e) {
        System.err.println("Error while persisting " + genre.getClass().getSimpleName());
        if (transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace(); //Afiseaza erorile
    }
}

public Genre findById_Specific(int id) {
    Genre rez = entityManager.find(Genre.class, id);
    if (rez == null) {
        System.err.println("GenreRepository.findByID: Genre not found");
    }

    return rez;
}

public List<Genre> findByName_Specific(String namePattern) {
    Query query = entityManager.createNamedQuery("Genre.findByName");
    query.setParameter("namePattern", "%" + namePattern + "%");

    @SuppressWarnings("unchecked") // fiindca mna asa trebuie..
    List<Genre> rez = query.getResultList();


    if (rez.isEmpty()) {
        System.err.println("GenreRepository.findByName: No Genre Found");
    }

    return rez;
} */


