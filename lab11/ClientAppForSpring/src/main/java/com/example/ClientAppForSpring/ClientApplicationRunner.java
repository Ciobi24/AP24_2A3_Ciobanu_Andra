package com.example.ClientAppForSpring;

import com.example.ClientAppForSpring.dto.BookDTO;
import com.example.ClientAppForSpring.dto.AuthorDTO;
import com.example.ClientAppForSpring.service.BookServiceClient;
import com.example.ClientAppForSpring.service.AuthorServiceClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientApplicationRunner {

    @Autowired
    private BookServiceClient bookServiceClient;

    @Autowired
    private AuthorServiceClient authorServiceClient;

    @PostConstruct
    public void init() {
        try {
            // Get all books
            List<BookDTO> books = bookServiceClient.getAllBooks();
            System.out.println("Books: " + books);

            // Add a new book
            BookDTO newBook = new BookDTO();
            newBook.setName("New Book");
            newBook.setNumberOfPages(100);
            newBook.setPublicationDate("2023-01-01");
            newBook.setGenres("Fiction");
            newBook.setPublishingHouse("Publisher");

            BookDTO createdBook = bookServiceClient.addBook(newBook);
            System.out.println("Created Book: " + createdBook);

            // Update book name
            Long bookId = createdBook.getId();
            BookDTO updatedBook = bookServiceClient.updateBookName(bookId, "Updated Book Name");
            System.out.println("Updated Book: " + updatedBook);

            // Delete book
            bookServiceClient.deleteBook(bookId);
            System.out.println("Book deleted.");
//
//            // Get all authors
//            List<AuthorDTO> authors = authorServiceClient.getAllAuthors();
//            System.out.println("Authors: " + authors);
//
//            // Add a new author
//            AuthorDTO newAuthor = new AuthorDTO();
//            newAuthor.setName("New Author");
//
//            AuthorDTO createdAuthor = authorServiceClient.addAuthor(newAuthor);
//            System.out.println("Created Author: " + createdAuthor);
//
//            // Update author name
//            Long authorId = createdAuthor.getId();
//            AuthorDTO updatedAuthor = authorServiceClient.updateAuthorName(authorId, "Updated Author Name");
//            System.out.println("Updated Author: " + updatedAuthor);
//
//            // Delete author
//            authorServiceClient.deleteAuthor(authorId);
//            System.out.println("Author deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

