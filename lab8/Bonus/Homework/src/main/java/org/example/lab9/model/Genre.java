package org.example.lab9.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = :myId"),
        @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name LIKE CONCAT('%', :name,'%')")
})
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books = new ArrayList<>();

}
