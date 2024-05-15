package org.example.lab9.repository;

import org.example.lab9.model.Genre;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenreRepository extends DataRepository<Genre, Integer> {
    private static final Logger LOGGER = Logger.getLogger(GenreRepository.class.getName());

    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }

    public void create(Genre genre) {
        try {
            save(genre);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in create method", e);
        }
    }

    public Genre findById(Integer id) {
        try {
            return super.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findById method", e);
            return null;
        }
    }

    public List<Genre> findByName(String name) {
        try {
            TypedQuery<Genre> query = getEntityManager().createNamedQuery("Genre.findByName", Genre.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findByName method", e);
            return null;
        }
    }
}