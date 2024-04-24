
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    private static final String CREATE_TABLES = "CREATE TABLE IF NOT EXISTS authors (" +
            "id SERIAL PRIMARY KEY," +
            "name VARCHAR(255) UNIQUE NOT NULL);" +
            "CREATE TABLE IF NOT EXISTS genres (" +
            "id SERIAL PRIMARY KEY," +
            "name VARCHAR(255) UNIQUE NOT NULL);" +
            "CREATE TABLE IF NOT EXISTS books (" +
            "id SERIAL PRIMARY KEY," +
            "title VARCHAR(255) NOT NULL," +
            "language VARCHAR(100)," +
            "publication_date DATE," +
            "number_of_pages INTEGER," +
            "author_id INTEGER REFERENCES authors(id)," +
            "genre_id INTEGER REFERENCES genres(id));";

    private static final String POPULATE_TABLES = "INSERT INTO authors (name) VALUES " +
            "('William Shakespeare'), ('J.K. Rowling'), ('Agatha Christie');" +
            "INSERT INTO genres (name) VALUES ('Drama'), ('Fantasy'), ('Mystery');" +
            "INSERT INTO books (title, language, publication_date, number_of_pages, author_id, genre_id) VALUES " +
            "('Hamlet', 'English', '1603-01-01', 500, 1, 1)," +
            "('Harry Potter and the Philosopher''s Stone', 'English', '1997-06-26', 223, 2, 2)," +
            "('Murder on the Orient Express', 'English', '1934-01-01', 256, 3, 3);";

    public static void setupDatabase(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(CREATE_TABLES);
            stmt.executeUpdate(POPULATE_TABLES);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}
