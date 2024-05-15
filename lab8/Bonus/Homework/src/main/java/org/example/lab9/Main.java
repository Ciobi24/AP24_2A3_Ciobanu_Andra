package org.example.lab9;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.lab9.model.Author;
import org.example.lab9.repository.AuthorsRepository;

import java.io.IOException;
import java.util.logging.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("application.log", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();

            consoleHandler.setFormatter(simpleFormatter);
            fileHandler.setFormatter(simpleFormatter);

            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            LOGGER.setLevel(Level.ALL);
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
        try {
            AuthorsRepository authorsRepository = new AuthorsRepository();
            Author author = new Author("I. L. Caragiale");
            authorsRepository.save(author);
            Author authorSaved = authorsRepository.findById(1);
            System.out.println(authorSaved);

            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("books-unit");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Author author2 = new Author("Mark Twain");
            em.persist(author2);
            Author a = (Author)em.createQuery(
                            "select e from Author e where e.name='Mark Twain'")
                    .getSingleResult();
            a.setName("Samuel Langhorne Clemens");
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in main method", e);
        }
    }
}