package JPAObjects;

import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;

import java.io.Serializable;
import java.util.Set;

@Entity

@NamedQuery(
        name = "Author.findByName",
        query = "SELECT a FROM Author a WHERE a.authorName LIKE :namePattern"
)

@NamedQuery(
        name = "Author.findAll",
        query = "SELECT a FROM Author a"
)

@Table(name = "authors")

/**
 * The author class
 */
public class Author extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false)
    private Integer id;


    @Column(name = "author_name", nullable = false, length = 500)
    private String authorName;

    /* @ManyToMany(mappedBy = "authors")
    private Set<Book> books; */

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    )
    private Set<Book> books;
    /**
     * This is the getter for the id of an author
     * @return The id of the author
     */
    public Integer getId() {
        return id;
    }

    /**
     * This is the setter for the id of an author
     * @param id The id of the author
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This is the getter for the name of an author
     * @return The name of the author
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * This is the setter for the name of an author
     * @param authorName The name of an author
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Here we get the books written by the authors
     * @return The books written by authors
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * Here we set the books written by authors
     * @param books The books written by authors
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    /**
     * This is the toString method
     * @return A string with the information about the class
     */
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + ' ' +
                '}';
    }
}
