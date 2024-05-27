package com.example.ClientAppForSpring.service;

import com.example.ClientAppForSpring.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BookServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8666/api/v1/books";

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

    public List<BookDTO> getAllBooks() {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<BookDTO[]> response = restTemplate.exchange(BASE_URL+"/", HttpMethod.GET, entity, BookDTO[].class);
        return Arrays.asList(response.getBody());
    }

    public BookDTO addBook(BookDTO bookDTO) {
        HttpEntity<BookDTO> entity = new HttpEntity<>(bookDTO, createHeaders());
        ResponseEntity<BookDTO> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, BookDTO.class);
        return response.getBody();
    }

    public BookDTO updateBookName(Long id, String newName) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName(newName);
        HttpEntity<BookDTO> entity = new HttpEntity<>(bookDTO, createHeaders());
        ResponseEntity<BookDTO> response = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT, entity, BookDTO.class);
        return response.getBody();
    }

    public BookDTO deleteBook(Long id) {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<BookDTO> response=restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.DELETE, entity, BookDTO.class);
    return response.getBody();
    }
}
