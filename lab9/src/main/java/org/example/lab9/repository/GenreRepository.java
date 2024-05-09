package org.example.lab9.repository;

import org.example.lab9.DatabaseUtils;
import org.example.lab9.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository extends DataRepository<Genre,Integer> {
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }
}
