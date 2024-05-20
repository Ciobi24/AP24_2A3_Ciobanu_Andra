package DataBase.DAOClasses;

import DataBase.Database;

import java.sql.*;

/**
 * The GenreDAO class
 */
public class GenreDAO {

    /**
     * Here we try to insert a new genre in the database
     * @param name The name of the genre we want to introduce
     * @throws SQLException The exception thrown when we can't create
     */
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();

        if(this.findByName(name) != null){
            System.err.println("This Genre already exists");
            con.close();
            return;
        }
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (genre_name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            con.commit();
            con.close();
            System.out.println("The genre was inserted successfully");
        }
    }

    /**
     * Here we search the name of a genre and we return the corresponding id
     * @param name The name of the genre
     * @return The first column that contains the ID
     * @throws SQLException The exception thrown when search doesn't work
     */
    public Integer findByName(String name) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer genreId = null;

        try {
            con = Database.getConnection();
            pstmt = con.prepareStatement("SELECT genre_id FROM genres WHERE genre_name=?");
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                genreId = rs.getInt("genre_id");
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
        return genreId;
    }

    /**
     * Here we search the id of a genre and we return the corresponding name
     * @param id The id of the genre we want to search
     * @return The first column that contains the name
     * @throws SQLException The exception thrown when the search doesn't work
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "select genre_name from genres where genre_id=?")) {
            preparedStatement.setInt(1,id);
            try(ResultSet rs=preparedStatement.executeQuery()){
                String result = rs.next() ? rs.getString("genre_name"):null;
                con.close();
                return result;
            }
        }
    }
}
