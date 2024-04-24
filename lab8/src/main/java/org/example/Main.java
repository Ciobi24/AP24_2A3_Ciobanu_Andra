package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = Database.getConnection();
//            DatabaseSetup.setupDatabase(connection);
            AuthorDAO authorDao = new AuthorDAO();

            String authorName1 = "Mihai";
            String authorName2 = "Ion";

            authorDao.create(authorName1);
            authorDao.create(authorName2);
            System.out.println("Authors added to the database.");

            Integer authorId1 = authorDao.findByName(authorName1);
            if (authorId1 != null) {
                System.out.println("Found " + authorName1 + " with ID: " + authorId1);
            } else {
                System.out.println(authorName1 + " not found in the database.");
            }

            Integer authorId2 = authorDao.findByName(authorName2);
            if (authorId2 != null) {
                System.out.println("Found " + authorName2 + " with ID: " + authorId2);
            } else {
                System.out.println(authorName2 + " not found in the database.");
            }

            connection.commit();

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());

            Database.rollback();

        } finally {
            try {
                Database.closeConnection();
            } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}