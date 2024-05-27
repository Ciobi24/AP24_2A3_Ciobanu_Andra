package bookstore.service;

import bookstore.dto.AuthorDTO;
import bookstore.dto.BookDTO;
import bookstore.model.business.Author;
import bookstore.model.business.Book;
import bookstore.repository.AuthorRepository;
import bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElse(null);
    }
@Transactional
    public Book addAuthor(Book book, AuthorDTO authorDTO) {
        Author author = new Author(authorDTO);
        author = authorRepository.save(author);
        book.addAuthor(author);
        return bookRepository.save(book);
    }
    @Transactional(readOnly = true)

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    @Transactional
    public Book addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }
    @Transactional

    public Book updateBookName(Long id, String name) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setName(name);
        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }

@Transactional
public void deleteBook(Long id) {
   Optional<Book> book= bookRepository.findById(id);
    // Access the lazy collections if needed before deleting
    if (book.isPresent()) {
        Book book1 = book.get();
        book1.getAuthors().size(); // This initializes the collection}
        bookRepository.delete(book1);}
    }@Transactional
    public Book updateBook(BookDTO bookDTO) {
        Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setName(bookDTO.getName());
        book.setNumberOfPages(bookDTO.getNumberOfPages());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setGenres(bookDTO.getGenres());
        book.setPublishingHouse(bookDTO.getPublishingHouse());
        return bookRepository.save(book);
    }

}