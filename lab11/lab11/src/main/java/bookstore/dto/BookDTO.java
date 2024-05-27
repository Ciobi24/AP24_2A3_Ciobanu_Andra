package bookstore.dto;

import bookstore.model.business.Book;
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
public class BookDTO {

    private Long id;
    private String name;
    private int numberOfPages;
    private String publicationDate;

    private String genres;

    private String publishingHouse;

    private List<AuthorDTO> authors;

//    private transient List<LinkDTO> links;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.authors = book.getAuthors()
                .stream()
                .map(author -> new AuthorDTO(this.id, author))
                .collect(Collectors.toList());
        this.numberOfPages = book.getNumberOfPages();
        this.publicationDate = book.getPublicationDate();
        this.genres = book.getGenres();
        this.publishingHouse = book.getPublishingHouse();
//        decorateWithLinks();
    }


//    private void decorateWithLinks() {
//        this.links = authors.stream()
//                .map(author -> new LinkDTO(
//                        "/api/v1/books" + this.getId() + "/authors/" + author.getId(),
//                        "authors", "GET"))
//                .collect(Collectors.toList());
//  }


}
