package JPAObjects;

import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity

@NamedQuery(
        name = "Publisher.findByName",
        query = "SELECT b FROM Publisher b WHERE b.publisherName LIKE :namePattern"
)

@NamedQuery(
        name = "Publisher.findAll",
        query = "SELECT a FROM Publisher a"
)

/**
 * The Publisher class
 */
public class Publisher extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", nullable = false)
    private Integer id;

    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new LinkedHashSet<>();

    /**
     * Here we get the id of the publisher
     * @return The id of the publisher
     */
    public Integer getId() {
        return id;
    }

    /**
     * Here we set the id of the publisher
     * @param id The id of the publisher
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Here we get the name of the publisher
     * @return The name of the publisher
     */
    public String getPublisherName() {
        return publisherName;
    }

    /**
     * Here we set the name of a publisher
     * @param publisherName The name of a publisher
     */
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    /**
     * Here we get the books a publisher has
     * @return The books a publisher has
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * Here we set the books a publisher has
     * @param book The books a publisher has
     */
    public void addBooks(Book book){
        this.books.add(book);
    }

    /**
     * Here we set the books a publisher has
     * @param books The books a publisher has
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    /**
     * This is the toString method
     * @return The string with information about a publisher
     */
    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", books=" + books +
                '}';
    }
}