package org.example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        try {
            Database.createAuthorsTable();
            Database.createGenresTable();
            Database.createBooksTable();
            Database.createReadingListsTable();

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

            // Creating a ReadingList
            List<String> bookSet = new ArrayList<>();
            bookSet.add(romeoAndJuliet.getTitle());
            bookSet.add(hitchhikersGuide.getTitle());

            ReadingList readingList = new ReadingList("My Reading List", new Timestamp(System.currentTimeMillis()), bookSet);
            ReadingListDAO readingListDAO = new ReadingListDAO();
            readingListDAO.create(readingList);
            List<ReadingList> allReadingList = readingListDAO.getAllReadingLists();
            for (ReadingList readingList1 : allReadingList) {
                System.out.println(readingList1);
            }

            bonus();

            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    private static void bonus() {
        System.out.println();
        Book book1 = new Book("A", "Eminescu", "romantic", "ro", "1949-06-08", 328);
        Book book2 = new Book("B", "Creanga", "aventura", "ro", "1960-07-11", 281);
        Book book3 = new Book("C", "Rebreanu", "aventura", "ro", "1925-04-10", 218);
        Book book4 = new Book("D", "Rebreanu", "dramatic", "ro", "1851-10-18", 585);
        Book book5 = new Book("F", "Mircea", "romantic", "ro", "1866-11-14", 671);
        Book book6 = new Book("G", "Mircea", "romantic", "ro", "1866-11-14", 671);
        Book book7 = new Book("H", "Ionut", "dramatic", "ro", "1866-11-14", 671);
        Book book8 = new Book("I", "Marr", "aventura", "ro", "1866-11-14", 671);

        List<Book> books = Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8);

        List<List<Book>> readingLists = partitionBooks(books);

        for (List<Book> readingList : readingLists) {
            if(readingList.size() != 0) {
                System.out.println("Lista: ");
                for (Book book : readingList) {
                    System.out.println(book.getTitle() + " - " + book.getAuthors() + " - " + book.getGenres());
                }
            }
        }
    }
    private static List<List<Book>> partitionBooks(List<Book> books) {
        List<List<Book>> readingLists = new ArrayList<>();

        books.sort(Comparator.comparing(Book::getAuthors).thenComparing(Book::getGenres));

        for (int i = 0; i < books.size(); i++) {
            readingLists.add(new ArrayList<>());
        }
        for (Book book : books) {
            boolean added = false;

            for (List<Book> readingList : readingLists) {
                // Verificam daca diferenta de marime este mai mica sau egala cu 2
                if (readingList.size() + 1 - secondMaxSize(readingLists) <= 2) {
                    boolean canAdd = true;
                    for (Book existingBook : readingList) {
                        if (existingBook.getAuthors().equals(book.getAuthors()) || existingBook.getGenres().equals(book.getGenres())) {
                            canAdd = false;
                            break;
                        }
                    }
                    if (canAdd) {
                        readingList.add(book);
                        added = true;
                        break;
                    }
                }
            }

            if (!added) {
                for (List<Book> readingList : readingLists) {
                    if (readingList.isEmpty()) {
                        readingList.add(book);
                        break;
                    }
                }
            }
        }
        return readingLists;
    }

    private static int secondMaxSize(List<List<Book>> readingLists) {
        int firstMaxSize = -1;
        int secondMaxSize = -1;
        for (List<Book> readingList : readingLists) {
            int size = readingList.size();
            if (size > firstMaxSize) {
                secondMaxSize = firstMaxSize;
                firstMaxSize = size;
            } else if (size > secondMaxSize && size != firstMaxSize) {
                secondMaxSize = size;
            }
        }
        return secondMaxSize;
    }
}
