package LoginService;

import dao.DatabaseService;
import dao.logindao;
import userDao.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginService {

   Scanner sc = new Scanner(System.in);
   public void doLogin() throws ClassNotFoundException, SQLException {
       System.out.print("Please provide user name:");
       String userName = sc.nextLine();

       System.out.print("Please provide user password:");
       String password = sc.nextLine();

       try(Connection conn = DatabaseService.getConnection()){
           logindao loginDao = new logindao();

           String userType = loginDao.dologin(conn, userName, password);

           if (userType == null){
               System.out.println("Invalid user");
               return;
           }
           System.out.println("Login Success. You logged in as a " +userType+". Please Select from below option");

           if (userType != null && userType.trim().equalsIgnoreCase("admin")) {
               displayAdminmenu(conn);
           } else {

           }
       }
   }

   public void displayAdminmenu(Connection conn) throws SQLException {
       int choice;
       BookService bookService = new BookService();
       do {
           System.out.println("===============================");
           System.out.println("1. Search a Book!.");
           System.out.println("2. Add a New Book!.");
           System.out.println("3. Upgrade Quantity of book!.");
           System.out.println("4. Show All book!.");
           System.out.println("5. Register Student!.");
           System.out.println("6. Show All Register Student!.");
           System.out.println("7. Exit From Application!.");
           System.out.println("===============================");

           System.out.println("Please enter your choice.");
           choice = sc.nextInt();

           switch (choice){
               case 1:
                   searchBook(conn);
                   break;
               case 2:
                   bookService.addBook(conn);
                   break;
               case 3:
                   bookService.updateBookQty(conn);
                   break;
               case 4:
                   bookService.getAllBooks(conn);
                   break;
               case 5:
                   registerUser(conn);
                   break;
               case 6:
                   logindao dao = new logindao();
                   dao.getAllUsers(conn);
                   break;
               case 7:
                   System.out.println("Exiting application... Goodbye!");
                   System.exit(0);
                   break;
               default:
                   System.out.println("Invalid chocing number!");

           }


       } while ( choice != 7);
   }
    private void  searchBook(Connection conn) throws SQLException {

        BookService bookService = new BookService();

        System.out.println("1. Search with book Serial No!.");
        System.out.println("1. Search with book's  Author Name!.");

        System.out.println("Please Enter your choice!.");

        int choice = sc.nextInt();

        switch (choice){
            case 1:
                bookService.searchBySrNo(conn);
                break;
            case 2:
                bookService.searchByAuthorName(conn);
                break;
            default:
                System.out.println("Invilate Number!");
        }

    }

    public void registerUser(Connection conn) throws SQLException {

        Scanner sc = new Scanner(System.in);

        User user = new User();

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        System.out.print("Enter User Type (Admin/User): ");
        String userType = sc.nextLine();

        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);

        logindao dao = new logindao();
        dao.registerUser(conn, user);
    }
}



