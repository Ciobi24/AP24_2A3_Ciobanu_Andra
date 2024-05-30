package bookstore.service;

import bookstore.dto.AuthorDTO;
import bookstore.dto.BookDTO;
import bookstore.model.business.Author;
import bookstore.model.business.Book;
import bookstore.model.business.RecommendedOrder;
import bookstore.repository.AuthorRepository;
import bookstore.repository.BookRepository;
import bookstore.repository.RecommendedOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    private RecommendedOrderRepository recommendedOrderRepository;

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
    public List<Book> getLongestSequenceOfBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            return Collections.emptyList();
        }

        books.sort(Comparator.comparing(Book::getPublicationDate));
        Map<Book, List<Book>> graph = buildGraph(books);

        List<Book> longestSequence = new ArrayList<>();
        for (Book book : books) {
            List<Book> currentSequence = findLongestSequenceFrom(book, graph, new HashMap<>());
            if (currentSequence.size() > longestSequence.size()) {
                longestSequence = currentSequence;
            }
        }

        return longestSequence;
    }

    private Map<Book, List<Book>> buildGraph(List<Book> books) {
        Map<Book, List<Book>> graph = new HashMap<>();

        // Initialize the graph with empty lists for each book
        for (Book book : books) {
            graph.put(book, new ArrayList<>());
        }

        // Populate the graph based on the recommended order
        List<RecommendedOrder> recommendedOrders = recommendedOrderRepository.findAll();
        for (RecommendedOrder order : recommendedOrders) {
            Book bookA = bookRepository.findById(order.getBookAId()).orElse(null);
            Book bookB = bookRepository.findById(order.getBookBId()).orElse(null);

            if (bookA != null && bookB != null && bookA.getPublicationDate() != null && bookB.getPublicationDate() != null) {
                // Ensure the publication dates are not null and add bookB to the adjacency list of bookA
                graph.get(bookA).add(bookB);
            }
        }

        return graph;
    }


    private List<Book> findLongestSequenceFrom(Book book, Map<Book, List<Book>> graph, Map<Book, List<Book>> memo) {
        if (memo.containsKey(book)) {
            return memo.get(book);
        }

        List<Book> longestSequence = new ArrayList<>();
        for (Book next : graph.get(book)) {
            if (next.getPublicationDate().compareTo(book.getPublicationDate()) > 0) {
                List<Book> currentSequence = findLongestSequenceFrom(next, graph, memo);
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = currentSequence;
                }
            }
        }

        List<Book> result = new ArrayList<>();
        result.add(book);
        result.addAll(longestSequence);
        memo.put(book, result);

        return result;
    }
}