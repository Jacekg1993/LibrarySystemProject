package data_access;

import classes.Book;
import classes.LibraryElement;
import classes.LibraryUser;
import classes.LibraryWorker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDataAccess {

    public static Connection getConnection() {

        String urlConnection = "jdbc:sqlserver://DESKTOP-2NG6J3P;databaseName=LibraryProject_v2;integratedSecurity=true";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(urlConnection);

            System.out.println("Connected to data base");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;

    }

    public static int insertBook(Book book) {

        int status = 0;

        String sqlQuery = "INSERT INTO [LibraryProject_v2].[dbo].[Library_element]"
                + "VALUES (?, ?, ?, ?, NULL, ?)";

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getTypeId());
            preparedStatement.setInt(3, book.getSortId());
            preparedStatement.setInt(4, book.getPagesNumber());
            preparedStatement.setInt(5, book.getStatusId());

            status = preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    //This method is used for Book and Movie element type
    public static int deleteLibraryElement(int libraryElementId) {

        int status = 0;

        try {

            Connection connection = getConnection();

            String sqlQuery = "DELETE FROM [LibraryProject_v2].[dbo].[Library_element] WHERE library_element_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, libraryElementId);

            status = preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static List<Book> getAllBooksByTitleAndSort(String title, String sort) {

        List<Book> bookList = new ArrayList<Book>();

        title = "%" + title + "%";
        sort = "%" + sort + "%";

        Connection connection = getConnection();

        try {

            String sqlQuery = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_element] AS le" +
                            " INNER JOIN [LibraryProject_v2].[dbo].[Library_element_sort] AS s ON s.library_element_sort_id = le.sort_id" +
                            " WHERE title LIKE ?" +
                            " OR s.sort LIKE  ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, sort);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setLibraryElementId(resultSet.getInt(1));
                book.setTitle(resultSet.getString(2));
                book.setSortId(resultSet.getInt(3));
                book.setStatusId(resultSet.getInt(4));
                book.setPagesNumber(resultSet.getInt(5));

                bookList.add(book);
            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bookList;
    }

}
