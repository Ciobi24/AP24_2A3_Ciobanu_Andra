package org.example;

import java.util.List;
import java.sql.Date;

public class Book extends Entity{
    private String title;
    private String authors;
    private String genres;
    private String language;
    private String publicationDate;
    private int numberOfPages;

    public Book() {
    }

    public Book(String title, String authors, String genres, String language, String publicationDate, int numberOfPages) {
        this.title = title;
        this.authors = authors;
        this.genres = genres;
        this.language = language;
        this.publicationDate = publicationDate;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    // Override toString method for better representation
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", genres='" + genres + '\'' +
                ", language='" + language + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
