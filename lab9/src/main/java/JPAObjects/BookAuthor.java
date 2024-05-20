package JPAObjects;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_authors")
public class BookAuthor extends AbstractEntity {

    @Id
    @Column(name = "book_id")
    private int bookId;

    @Id
    @Column(name = "author_id")
    private int authorId;

    // Getters and setters


    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getAuthorId() {
        return authorId;
    }
}
