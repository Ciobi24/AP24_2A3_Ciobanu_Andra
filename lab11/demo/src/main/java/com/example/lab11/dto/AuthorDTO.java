package com.example.lab11.dto;

import com.example.lab11.model.business.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorDTO {

    private Long id;
    private String name;
    private transient List<LinkDTO> links;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }

    private void decorateWithLinks(Long univId) {
//        this.links = students.stream()
//                .map(student -> new LinkDTO(
//                        "/api/v1/universities" + univId + "/faculties/" + this.getId() + "/students/" + student.getId(),
//                        "students", "GET"))
//                .collect(Collectors.toList());
    }

}
