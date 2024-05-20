package JPAObjects;

import RepositoryPackage.GenreRepository;
import jakarta.persistence.*;
import org.dom4j.tree.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity

@NamedQuery(
        name = "Genre.findByName",
        query = "SELECT a FROM Genre a WHERE a.genreName LIKE :namePattern"
)

@NamedQuery(
        name = "Genre.findAll",
        query = "SELECT a FROM Genre a"
)

@Table(name = "genres")

/**
 * The genre class
 */
public class Genre extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Integer id;

    @Column(name = "genre_name", nullable = false, length = 500)
    private String genreName;

    /**
     * The getter of the id of a genre
     * @return The id of the genre
     */
    public Integer getId() {
        return id;
    }

    /**
     * The setter of the id of a genre
     * @param id The id of a genre
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The getter of the name of a book
     * @return The name of a genre
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * The setter for the name of a genre
     * @param genreName The name of a genre
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    /**
     * This is the toString method
     * @return The string with information about the book
     */
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreName='" + genreName + ' ' +
                '}';
    }
}
