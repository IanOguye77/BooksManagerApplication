package org.example;

import java.sql.*;
import java.util.Scanner;

public class BookMenu {

    private  BookManager bookManager;

    // constructor

    public BookMenu(String url, String user, String password)throws SQLException {
        this.bookManager = new BookManager(url, user, password);
    }

    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);

        int userChoice;

        do {

            System.out.println("\n CPL Books Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Exit");
            System.out.print("Enter Your Choice: ");
            userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice){
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("Invalid Choice. Please Try Again!!");

            }



        } while (userChoice != 3);
    }

    // addBook () method
    public void addBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter The Title Of The New Book: ");
        String title = scanner.nextLine();

        System.out.println("Enter The Author Of The New Book: ");
        String author = scanner.nextLine();

        try {
            bookManager.addBook(title, author);
            System.out.println("Book added successfully");
        } catch (SQLException ex){
            System.out.println("Error adding Book: " + ex.getMessage());
        }
    }
    // displayBooks () method
    public void displayBooks(){
        try {
            ResultSet resultSet = bookManager.getBooks();
            System.out.println("\nAll Available Books");
            while (resultSet.next()){
                System.out.println("Title: " + resultSet.getString("book_title") + ", Author: " + resultSet.getString("author_name"));
            }
        } catch (SQLException ex) {
            System.out.println("Error displaying books: " + ex.getMessage());
        }
    }
}
