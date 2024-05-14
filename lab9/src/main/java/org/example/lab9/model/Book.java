package org.example.lab9.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findById", query = "SELECT g FROM Book g WHERE g.id = :myId"),
        @NamedQuery(name = "Book.findByName", query = "SELECT g FROM Book g WHERE g.title LIKE CONCAT('%', :title,'%')")
})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "language")
    private String language;
    @Column(name = "publication_date")
    private String publicationDate;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors= new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publishing_house_id")
    private PublishingHouse publishingHouse;


    public void addAuthor(Author author) {
        this.authors.add(author);
    }
    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }
}
