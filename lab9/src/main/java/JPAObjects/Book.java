package JPAObjects;

import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity

@NamedQuery(
        name = "Book.findByName",
        query = "SELECT a FROM Book a WHERE a.title LIKE :namePattern"
)

@NamedQuery(
        name = "Book.findAll",
        query = "SELECT a FROM Book a"
)

@Table(name = "Books")

/**
 * The Book class
 */
public class Book extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "language", nullable = false, length = 500)
    private String language;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "num_pages")
    private Integer numPages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    )
    private Set<Author> authors;

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * This is the getter of the id of a book
     * @return The id of the book
     */
    public Integer getId() {
        return id;
    }

    /**
     * This is the setter of the id of a book
     * @param id The id of the book
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * This is the getter of title of a book
     * @return The title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * This is the setter of title of a book
     * @return The title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This is the getter of the language of a book
     * @return The language of a book
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This is the setter of the language of a book
     * @param language The language of a book
     */
    public void setLanguage(String language) {
        this.language = language;
    }


    /**
     * This is the getter of the publication date of a book
     * @return The publication date of the book
     */
    public LocalDate getPublicationDate() {
        return publicationDate;
    }


    /**
     * This is the method to set a publication date for a book
     * @param publicationDate The publicatin date of a book
     */
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * This is the getter of the number of pages
     * @return The number of pages of a book
     */
    public Integer getNumPages() {
        return numPages;
    }

    /**
     * This is the setter of the number of pages of a book
     * @param numPages The number of pages of a book
     */
    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    /**
     * This is the getter of a book
     * @return The name of a book
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * This is the getter for a genre of a book
     * @return The name of the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * This is the setter for the author of a book
     * @param author The author of the book
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * This is the setter for the genre of a book
     * @param genre The genre of the book
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * This is the getter for the authors of the books
     * @return The authors of the books
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    /**
     * This is the setter for the authors of the books
     * @param authors The authors of the books
     */
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthors(Author author){
        authors.add(author);
    }
    /**
     * This is the toString method a book
     * @return A string with information about the book
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", publicationDate=" + publicationDate +
                ", numPages=" + numPages +
                ", author=" + author +
                ", genre=" + genre +
                ", publisher=" + publisher.getPublisherName() +
                '}';
    }
}
