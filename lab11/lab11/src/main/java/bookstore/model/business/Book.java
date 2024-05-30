package bookstore.model.business;

import bookstore.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books", schema = "book_store")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int numberOfPages;

    private Date publicationDate;

    private String genres;

    private String publishingHouse;

    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    public Book(BookDTO bookDTO) {
        this.id = bookDTO.getId();
        this.name = bookDTO.getName();
        this.numberOfPages = bookDTO.getNumberOfPages();
        this.publicationDate = bookDTO.getPublicationDate();
        this.genres = bookDTO.getGenres();
        this.publishingHouse = bookDTO.getPublishingHouse();
    }

    public void addAuthor(Author author) {
        if(author==null)
            return;
        if (authors==null)
            authors=new ArrayList<Author>();
        this.authors.add(author);
    }
}
