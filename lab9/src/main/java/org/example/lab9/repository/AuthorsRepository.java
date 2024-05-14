package org.example.lab9.repository;

import org.example.lab9.model.Author;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.TypedQuery;

public class AuthorsRepository extends DataRepository<Author, Integer> {
    private static final Logger LOGGER = Logger.getLogger(AuthorsRepository.class.getName());

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    public void create(Author author) {
        try {
            save(author);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in create method", e);
        }
    }

    public Author findById(Integer id) {
        try {
            return super.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findById method", e);
            return null;
        }
    }

    public List<Author> findByName(String name) {
        try {
            TypedQuery<Author> query = getEntityManager().createNamedQuery("Author.findByName", Author.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findByName method", e);
            return null;
        }
    }
}