package DataBase;

import DataBase.DAOClasses.AuthorDAO;
import DataBase.DAOClasses.BooksDAO;
import DataBase.DAOClasses.GenreDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static DataBase.Database.commit;

import JPAObjects.Author;
import JPAObjects.Book;
import JPAObjects.Genre;
import JPAObjects.Publisher;
import RepositoryPackage.AuthorRepository;
import RepositoryPackage.BookRepository;
import RepositoryPackage.GenreRepository;
import RepositoryPackage.PublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String args[]) {

        myLogger logger = new myLogger();

        try {

            long startTime = System.currentTimeMillis();

            EntityManagerFactory emf = SingletonEntityManagerFactory.getEntityManagerFactory();
            EntityManager em = emf.createEntityManager();

            Author authorEntity = new Author();
            authorEntity.setAuthorName("I.L.Caragiale");

            Author authorEntity2 = new Author();
            authorEntity2.setAuthorName("Mihai Eminescu");

            AuthorRepository authorObject = new AuthorRepository(em);
            authorObject.create(authorEntity);
            authorObject.create(authorEntity2);

            System.out.println(authorObject.findById(1));
            System.out.println(authorObject.findById(2));

            System.out.println(authorObject.findByName("I.L.Caragiale"));
            System.out.println(authorObject.findByName("Mihai Eminescu"));

            Genre genreEntity = new Genre();
            genreEntity.setGenreName("Comedy");

            Genre genreEntity2 = new Genre();
            genreEntity2.setGenreName("Tragedy");

            GenreRepository genreObject = new GenreRepository(em);
            genreObject.create(genreEntity);
            genreObject.create(genreEntity2);

            System.out.println(genreObject.findById(1));
            System.out.println(genreObject.findById(2));

            System.out.println(genreObject.findByName("Comedy"));
            System.out.println(genreObject.findByName("Tragedy"));

            Publisher publisherEntity = new Publisher();
            publisherEntity.setPublisherName("Humanitas");

            Publisher publisherEntity2 = new Publisher();
            publisherEntity2.setPublisherName("Paralela45");

            PublisherRepository publisherObject = new PublisherRepository(em);
            publisherObject.create(publisherEntity);
            publisherObject.create(publisherEntity2);

            System.out.println(publisherObject.findById(1));
            System.out.println(publisherObject.findById(2));

            System.out.println(publisherObject.findByName("Humanitas"));
            System.out.println(publisherObject.findByName("Paralela45"));

            Book bookEntity = new Book();
            bookEntity.setTitle("O scrisoare pierduta");
            bookEntity.setLanguage("romana");
            LocalDate data = LocalDate.of(2023, 2, 27);
            bookEntity.setPublicationDate(data);
            bookEntity.setNumPages(100);
            bookEntity.setAuthor(authorEntity);
            bookEntity.setGenre(genreEntity);
            bookEntity.setPublisher(publisherEntity);

            Book bookEntity2 = new Book();
            bookEntity2.setTitle("Poezii");
            bookEntity2.setLanguage("romana");
            LocalDate data2 = LocalDate.of(2023, 4, 10);
            bookEntity2.setPublicationDate(data2);
            bookEntity2.setNumPages(120);
            bookEntity2.setAuthor(authorEntity2);
            bookEntity2.setGenre(genreEntity2);
            bookEntity2.setPublisher(publisherEntity2);

            BookRepository bookObject = new BookRepository(em);
            bookObject.create(bookEntity);
            bookObject.create(bookEntity2);

            System.out.println(bookObject.findById(1));
            System.out.println(bookObject.findById(2));

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            bookEntity.addAuthors(authorEntity);
            em.persist(bookEntity);

            bookEntity2.addAuthors(authorEntity2);
            em.persist(bookEntity);

            transaction.commit();

            System.out.println(bookObject.findByName("O scrisoare pierduta"));

            System.out.println(bookObject.findByName("Poezii"));

            System.out.println("Publisher has the books: " + publisherEntity.getBooks());
            System.out.println("Publisher has the books: " + publisherEntity2.getBooks());

            logger.warning("Mesaj nasol");

            logger.severe("E jale!");

            long endTime = System.currentTimeMillis();
            logger.logExecutionTime(startTime, endTime);

            Database.closeConnection();

        } catch (SQLException e) {
            logger.logException(e);
            Database.rollback();
        }
    }
}