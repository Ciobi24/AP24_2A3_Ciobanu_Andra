package org.example.lab9.model;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "publishing_houses")
@NamedQueries({
        @NamedQuery(name = "PublishingHouse.findById", query = "SELECT p FROM PublishingHouse p WHERE p.id = :myId"),
        @NamedQuery(name = "PublishingHouse.findByName", query = "SELECT p FROM PublishingHouse p WHERE p.name LIKE CONCAT('%', :name,'%')")
})
public class PublishingHouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "publishingHouse")
    private List<Book> books = new ArrayList<>();

}
