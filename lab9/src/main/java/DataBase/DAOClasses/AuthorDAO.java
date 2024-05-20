package DataBase.DAOClasses;

import DataBase.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The AuthorDAO class
 */
public class AuthorDAO {

    /**
     * Here we try to insert a new author in the database
     *
     * @param name The name of the author we want to introduce
     * @throws SQLException The exception when insertion doesn't work
     */
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();

        if (this.findByName(name) != null) {
            System.err.println("This Author already exists");
            con.close();
            return;
        }

        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into authors (author_name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            con.commit();
            con.close();
            System.out.println("The author was inserted successfully");
        }
    }

    /**
     * Here we search the name of an author and we return the corresponding id
     *
     * @param name The name of the author
     * @return The first column that contains the ID
     * @throws SQLException The exception thrown when the search doesn't work
     */
    public Integer findByName(String name) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer authorId = null;

        try {
            con = Database.getConnection();
            pstmt = con.prepareStatement("SELECT author_id FROM Authors WHERE author_name=?");
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                authorId = rs.getInt("author_id");
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
        return authorId;
    }

    /**
     * Here we search the id of an author and we return the corresponding name
     *
     * @param id The id of the author we want to search
     * @return The first column that contains the name
     * @throws SQLException The exception thrown when the search doesn't work
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "select author_name from authors where author_id=?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                String result = rs.next() ? rs.getString("author_name") : null;
                con.close();
                return result;
            }
        }
    }
}