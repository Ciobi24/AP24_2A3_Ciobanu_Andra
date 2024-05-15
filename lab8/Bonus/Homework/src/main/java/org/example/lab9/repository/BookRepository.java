package org.example.lab9.repository;

import org.example.lab9.model.Book;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRepository extends DataRepository<Book, Integer> {
    private static final Logger LOGGER = Logger.getLogger(BookRepository.class.getName());

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    public void create(Book book) {
        try {
            save(book);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in create method", e);
        }
    }

    public Book findById(Integer id) {
        try {
            return super.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findById method", e);
            return null;
        }
    }

    public List<Book> findByName(String name) {
        try {
            TypedQuery<Book> query = getEntityManager().createNamedQuery("Book.findByName", Book.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findByName method", e);
            return null;
        }
    }
}