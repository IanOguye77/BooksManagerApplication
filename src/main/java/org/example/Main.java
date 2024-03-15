package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // DB params
        String url = "jdbc:postgresql://localhost/booksdb";
        String user = "postgres";
        String password = "246813579";

        try {
          // BookManager bookApp = new BookManager(url, user, password);
           // Add Authors and Books
          //  bookApp.addAuthor("Kent Beck");
           // bookApp.addBook("Test Driven Programming","Robert c. Martin");
          //  bookApp.displayBooks();

            BookMenu bookMenu = new BookMenu(url, user, password);
            bookMenu.displayMenu();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}