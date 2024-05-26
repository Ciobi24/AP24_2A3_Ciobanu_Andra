package bookstore.controller;

import bookstore.dto.AuthorDTO;
import bookstore.dto.BookDTO;
import bookstore.model.business.Author;
import bookstore.model.business.Book;
import bookstore.payload.response.MessageResponse;
import bookstore.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    private static ResponseEntity<MessageResponse> getBadRequestMessage(String message, Long id) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(message + id + " not found."));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        List<BookDTO> bookDTOList = bookService.findAll().stream()
                .map(BookDTO::new )
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(bookDTOList);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Book book = bookService.findById(id);
        if (book == null) {
            return getBadRequestMessage("Book with id ", id);
        }
        return ResponseEntity
                .ok()
                .body(new BookDTO(book));
    }
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.addBook(bookDTO);
        return ResponseEntity
                .ok()
                .body(new BookDTO(book));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<BookDTO> updateBook( @PathVariable Long id,@RequestBody BookDTO bookDTO) {
        Book book = bookService.findById(id);
        if (book == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }else {
            bookDTO.setId(id);
            book = bookService.updateBook(bookDTO);
            return ResponseEntity
                    .ok()
                    .body(new BookDTO(book));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        Book book = bookService.findById(id);
        if (book == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }else {
            bookService.deleteBook(id);
            return ResponseEntity
                    .ok()
                    .body(new BookDTO(book));
        }
    }

    @GetMapping("/{id}/authors/{authorId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAuthor(@PathVariable Long id, @PathVariable Long authorId) {
        Book book = bookService.findById(id);
        if (book == null) {
            return getBadRequestMessage("Book with id ", id);
        }
        Optional<Author> authorOptional = book.getAuthors()
                .stream()
                .filter(fac -> fac.getId().equals(authorId))
                .findFirst();
        if (authorOptional.isEmpty()) {
            return getBadRequestMessage("Author with id ", id);
        }
        return ResponseEntity
                .ok()
                .body(new AuthorDTO(id, authorOptional.get()));
    }

    @PostMapping("/{id}/authors")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        Book book = bookService.findById(id);
        if (book == null) {
            return getBadRequestMessage("Book with id ", id);
        }
        book = bookService.addAuthor(book, authorDTO);

        BookDTO bookDTO = new BookDTO(book);
        return ResponseEntity
                .ok()
                .body(bookDTO);
    }

    @PatchMapping("/{id}/authors/{authorId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> patchAuthor(@PathVariable Long id,
                                          @PathVariable Long authorId,
                                          @RequestBody AuthorDTO authorDTO) {
        Book book = bookService.findById(id);
        if (book == null) {
            return getBadRequestMessage("Book with id ", id);
        }
        Optional<Author> facultyOptional = book.getAuthors()
                .stream()
                .filter(fac -> fac.getId().equals(authorId))
                .findFirst();

        if (facultyOptional.isEmpty()) {
            return getBadRequestMessage("Author with id ", id);
        }

        Author author = facultyOptional.get();
        author.setName(authorDTO.getName());
        return ResponseEntity
                .ok()
                .body(new AuthorDTO(id, author));
    }

}
