package org.example.lab9.repository;

import org.example.lab9.DatabaseUtils;
import org.example.lab9.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends DataRepository<Book,Integer> {
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
