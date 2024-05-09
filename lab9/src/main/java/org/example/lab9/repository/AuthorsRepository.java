package org.example.lab9.repository;

import org.example.lab9.model.Author;

import java.util.List;
import jakarta.persistence.TypedQuery;

public class AuthorsRepository extends DataRepository<Author, Integer> {

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    public void create(Author author) {
        save(author);
    }

    public Author findById(Integer id) {
        return super.findById(id);
    }

    public List<Author> findByName(String name) {
        TypedQuery<Author> query = getEntityManager().createNamedQuery("Author.findByName", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}