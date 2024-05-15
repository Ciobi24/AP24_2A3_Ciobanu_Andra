package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadingListDAO {
    public void create(ReadingList readingList) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO reading_lists (name, creation_timestamp, book_names) VALUES (?, ?, ?)")) {
            pstmt.setString(1, readingList.getName());
            pstmt.setTimestamp(2, readingList.getCreationTimestamp());

            String[] bookNamesArray = readingList.getBookSet().toArray(new String[0]);
            pstmt.setArray(3, con.createArrayOf("VARCHAR", bookNamesArray));
            pstmt.executeUpdate();
        }
    }

    public List<ReadingList> getAllReadingLists() throws SQLException {
        Connection con = Database.getConnection();
        List<ReadingList> readingLists = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM reading_lists");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ReadingList readingList = new ReadingList();
                readingList.setId(rs.getInt("id"));
                readingList.setName(rs.getString("name"));
                readingList.setCreationTimestamp(rs.getTimestamp("creation_timestamp"));

                Array bookNamesArray = rs.getArray("book_names");
                String[] bookNames = (String[]) bookNamesArray.getArray();
                List<String> bookNamesList = Arrays.asList(bookNames);
                readingList.setBookSet(bookNamesList);
                readingLists.add(readingList);
            }
        }
        return readingLists;
    }


    public ReadingList findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT * FROM reading_lists WHERE id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ReadingList readingList = new ReadingList();
                    readingList.setId(rs.getInt("id"));
                    readingList.setName(rs.getString("name"));
                    readingList.setCreationTimestamp(rs.getTimestamp("creation_timestamp"));

                    Array bookNamesArray = rs.getArray("book_names");
                    String[] bookNames = (String[]) bookNamesArray.getArray();
                    List<String> bookNamesList = Arrays.asList(bookNames);
                    readingList.setBookSet(bookNamesList);
                    return readingList;
                } else {
                    return null;
                }
            }
        }
    }
}
