package com.example.ClientAppForSpring.service;

import com.example.ClientAppForSpring.dto.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8666/api/v1/authors";

    private String getBearerToken() {
        // Implement your method to obtain the Bearer token
        return "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRyYUBlbWFpbC5jb20iLCJpYXQiOjE3MTY3MjEwMzYsImV4cCI6MTcxNjgwNzQzNn0.ZfNZ__vd6rKuCRSzd8p7JCLo0E3oxG_4hm0zAsuDCaq5aKVPWO-cPOm-3yygubgeEYY0qoPoDsZ543ufTbVwmQ";
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getBearerToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public List<AuthorDTO> getAllAuthors() {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<AuthorDTO[]> response = restTemplate.exchange(BASE_URL+"/", HttpMethod.GET, entity, AuthorDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        HttpEntity<AuthorDTO> entity = new HttpEntity<>(authorDTO, createHeaders());
        ResponseEntity<AuthorDTO> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, AuthorDTO.class);
        return response.getBody();
    }

    public AuthorDTO updateAuthorName(Long id, String newName) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(newName);
        HttpEntity<AuthorDTO> entity = new HttpEntity<>(authorDTO, createHeaders());
        ResponseEntity<AuthorDTO> response = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT, entity, AuthorDTO.class);
        return response.getBody();
    }

    public void deleteAuthor(Long id) {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.DELETE, entity, Void.class);
    }
}
