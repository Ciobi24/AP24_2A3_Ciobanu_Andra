package com.example.ClientAppForSpring.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BookDTO {
    private Long id;
    private String name;
    private int numberOfPages;
    private String publicationDate;
    private String genres;
    private String publishingHouse;
    private List<AuthorDTO> authors;
    private List<LinkDTO> links;
}