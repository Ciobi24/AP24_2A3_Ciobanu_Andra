package com.example.ClientAppForSpring.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthorDTO {
    private Long id;
    private String name;
    private List<LinkDTO> links;
}