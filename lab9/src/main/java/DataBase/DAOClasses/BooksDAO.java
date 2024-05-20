package DataBase.DAOClasses;

import DataBase.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static DataBase.Database.commit;

public class BooksDAO{

    /**
     * Here we try to insert a new author in the database
     * @param name The name of the author we want to introduce
     * @throws SQLException The exception when the code doesn't work
     */
    public void create(String name, String language, String date, int num_pages, String author_name, String genre_name) throws SQLException {
        System.out.println("Start inserting books");
        Connection con = Database.getConnection();
        AuthorDAO a = new AuthorDAO();
        Integer authorId;
        if((authorId = a.findByName(author_name)) == null){
            System.err.println("Author does not exist");
            con.close();
            return;
        }

        GenreDAO g = new GenreDAO();
        Integer genreId;
        if((genreId = g.findByName(genre_name)) == null){
            System.err.println("Genre does not exist");
            con.close();
            return;
        }
        if(Objects.equals(this.findBookAuthor(name, authorId), authorId)){
            System.err.println("This book already exists");
            con.close();
            return;
        }
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into books (title,language,publication_date,num_pages,author_id,genre_id) " +
                        "values (?,?,?,?,?,?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, language);
            pstmt.setString(3, date);
            pstmt.setInt(4, num_pages);
            pstmt.setInt(5, authorId);
            pstmt.setInt(6, genreId);
            pstmt.executeUpdate();
            con.commit();
            con.close();
            System.out.println("Book was inserted successfully");

        }
    }

    public synchronized void createImport(String name, String language, String date, int num_pages, String author_name, String genre_name) throws SQLException {
        Connection con = Database.getConnection();
        AuthorDAO a = new AuthorDAO();
        Integer authorId;
        if((authorId = a.findByName(author_name)) == null){
            a.create(author_name);
            authorId = a.findByName(author_name);
        }

        GenreDAO g = new GenreDAO();
        Integer genreId;
        if((genreId = g.findByName(genre_name)) == null){
            g.create(genre_name);
            genreId = g.findByName(genre_name);
        }

        if(Objects.equals(this.findBookAuthor(name, authorId), authorId)){
            System.err.println("This book already exists");
            con.close();
            return;
        }
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into books (title,language,publication_date,num_pages,author_id,genre_id) " +
                        "values (?,?,STR_TO_DATE(?, '%m/%d/%Y'),?,?,?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, language);
            pstmt.setString(3, date);
            pstmt.setInt(4, num_pages);
            pstmt.setInt(5, authorId);
            pstmt.setInt(6, genreId);
            pstmt.executeUpdate();
            con.commit();
            con.close();
            System.out.println("Book was inserted successfully");


        }
    }

    /**
     * Here we search the name of a book and we return the corresponding id
     * @return The first column that contains the ID
     * @throws SQLException The exception thrown when the search doesn't work
     */
    public Integer findByName(String name) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer bookId = null;

        try {
            con = Database.getConnection();
            pstmt = con.prepareStatement("SELECT book_id FROM books WHERE title=?");
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookId = rs.getInt("book_id");
            }
        } finally {
            // Close resources in a final block to ensure they are always closed
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return bookId;
    }

    public Integer findBookAuthor(String title,Integer authorID) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("SELECT author_id FROM Books WHERE title = ?")) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    if (authorID.equals(rs.getInt("author_id"))) {

                        int res = rs.getInt("author_id");

                        con.close();

                        return res;
                    }
                }

                con.close();
                return null;
            }
        }
    }


    /**
     * Here we search the id of a book and we return the corresponding title
     * @param id The id of the book we want to search
     * @return The first column that contains the title
     * @throws SQLException The exception thrown when the search doesn't work
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "select title from Books where book_id=?")) {
            preparedStatement.setInt(1,id);
            try(ResultSet rs=preparedStatement.executeQuery()){
                String result = rs.next() ? rs.getString("title"):null;
                con.close();
                return result;
            }
        }
    }

    /**
     * Here we search the language of a book and we return the corresponding title
     * @param language The language of the book we want to search
     * @return The first column that contains the title
     * @throws SQLException The exception thrown when the search doesn't work
     */
    public List<String> findByLanguage(String language) throws SQLException {
        List<String> books = new ArrayList<>();
        Connection con = Database.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "select title from Books where language=?")) {
            preparedStatement.setString(1,language);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    books.add(title);
                }
            }
        }
        con.close();
        return books;
    }

    /**
     * Here we search the date of a book and we return the corresponding title
     * @param date The date of the book we want to search
     * @return The first column that contains the title
     * @throws SQLException The exception thrown when the search doesn't work
     */
    public List<String> findByPublicationDate(String date) throws SQLException {
        List<String> books = new ArrayList<>();
        Connection con = Database.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "select title from Books where publication_date=?")) {
            preparedStatement.setString(1,date);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    books.add(title);
                }
            }
        }
        con.close();
        return books;
    }

    /**
     * Here we search the number of pages of a book and we return the corresponding title
     * @param pages The number of pages of the book we want to search
     * @return The first column that contains the title
     * @throws SQLException The exception thrown when the search doesn't work
     */
    public List<String> findByNumberPages(int pages) throws SQLException {
        List<String> books = new ArrayList<>();
        Connection con = Database.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "select title from Books where num_pages=?")) {
            preparedStatement.setInt(1,pages);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    books.add(title);
                }
            }
        }
        con.close();
        return books;
    }
}
