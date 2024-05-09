package org.example.lab9.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(name = "Author.findByIdNative", query = "SELECT * FROM authors WHERE id = :myId", resultClass = Author.class)
})


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "Author.findById", query = "SELECT p FROM Author p WHERE p.id = :myId"),
        @NamedQuery(name = "Author.findByName", query = "SELECT p FROM Author p WHERE p.name LIKE CONCAT('%', :name,'%')")
})
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
    public Author(String name) {
        this.name = name;
    }
}
