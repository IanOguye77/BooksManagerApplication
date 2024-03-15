package org.example;

import java.sql.*;

public class BookManager {
    // create the connection field
private Connection connection;
    // create the constructor
    public BookManager(String url, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url,user, password);
    }

    // create a method to add an author to the DB
    public void addAuthor(String authorName)throws SQLException{
        String query = "INSERT INTO authors (author_name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, authorName);
            statement.executeUpdate();
        }
    }

    // create a method to add a book to the DB
    public void addBook(String title, String authorName)throws SQLException{
        // first check if the author exists
        int authorId = getAuthorId(authorName);

        if(authorId == -1){
            // if author does not exist, add the author first
            addAuthor(authorName);
            authorId = getAuthorId(authorName);
        }
        // insert the new book with the author_id
        String query = "INSERT INTO books (book_title, author_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, title);
            statement.setInt(2, authorId);
            statement.executeUpdate();

        }
    }

    // create a method to get the author ID
    public int getAuthorId(String authorName)throws SQLException{
        String query = "SELECT author_id FROM authors WHERE author_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, authorName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return  resultSet.getInt("author_id");
            }
        }
        return -1; // the author was not found
    }

    // create a method for getting books from the DB
    public ResultSet getBooks()throws SQLException{
        String query = "SELECT books.book_title, authors.author_name " + "FROM books INNER JOIN authors ON books.author_id = authors.author_id";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // create a method to close the DB connection
    public void closeConnection()throws SQLException{
        if (connection != null){
            connection.close();
        }
    }
}
