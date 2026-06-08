package LoginService;

import dao.DatabaseService;
import dao.logindao;

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
           if (userType.equals("admin")){

           } else {

           }
       }
   }
}

