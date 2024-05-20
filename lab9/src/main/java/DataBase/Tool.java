package DataBase;

import DataBase.DAOClasses.BooksDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tool {
    private BufferedReader read;

    private String path;

    /**
     * The cosntructor for the Tool Class
     * @param path The path to the file we want to extract elements from
     */
    public Tool(String path) {
        this.path = path;
    }


    public void CSV_DB_Import(){

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path)))
        {
            String lineOfCsv;

            bufferedReader.readLine();

            while((lineOfCsv= bufferedReader.readLine())!=null){

                if (!lineOfCsv.isEmpty()) { // Check if the line is not empty
                    String[] data = lineOfCsv.split(",");
                    String bookName = data[1];
                    bookName = bookName.replaceAll("'", "''");
                    String bookAuthor = data[2];
                    bookAuthor = bookAuthor.replaceAll("'", "''");
                    bookAuthor = bookAuthor.trim();
                    String bookLanguage = data[6];
                    int bookPages = Integer.parseInt(data[7]);
                    String bookDate = data[10];
                    String bookGenre = "Unknown";

                    BooksDAO book = new BooksDAO();

                    book.createImport(bookName, bookLanguage, bookDate, bookPages, bookAuthor, bookGenre);


                }
            }

            System.out.println("Am iesit");

        } catch (IOException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
