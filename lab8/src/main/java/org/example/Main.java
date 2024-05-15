package org.example;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        try {
            Database.createAuthorsTable();
            Database.createGenresTable();
            Database.createBooksTable();


            AuthorDAO authorDAO = new AuthorDAO();
            authorDAO.create("William Shakespeare");
            authorDAO.create("J.K.Rowling");

            System.out.println(new AuthorDAO().findByName("William Shakespeare"));
            System.out.println(new AuthorDAO().findAll());

            GenreDAO genreDAO = new GenreDAO();
            genreDAO.create("Tragedy");
            genreDAO.create("Science Fiction");
            System.out.println(new GenreDAO().findAll());


            Book romeoAndJuliet = new Book("Romeo and Juliet", "William Shakespeare", "Tragedy", "eng", "1597-07-01", 400);
            Book hitchhikersGuide = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Science fiction, Comedy, Adventure", "eng", "1979-10-12", 224);


            BookDAO bookDAO = new BookDAO();
            bookDAO.create(romeoAndJuliet);
            bookDAO.create(hitchhikersGuide);


            List<Book> allBooks = bookDAO.getAllBooks();
            for (Book book : allBooks) {
                System.out.println(book);
            }

            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}