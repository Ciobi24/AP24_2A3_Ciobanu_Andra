package bookstore.dto;


import bookstore.model.business.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorDTO {

    private Long id;
    private String name;
    private List<BookDTO> books;
//    private transient List<LinkDTO> links;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }

    public AuthorDTO(Long id, Author author) {
        this.id = id;
        this.name = author.getName();
    }

//    private void decorateWithLinks(Long bookId) {
//        this.links = books.stream()
//                .map(student -> new LinkDTO(
//                        "/api/v1/authors" + this.getId() + "/books/" + bookId ,
//                        "books", "GET"))
//                .collect(Collectors.toList());
//    }

}
