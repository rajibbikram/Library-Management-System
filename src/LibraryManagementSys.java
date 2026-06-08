import LoginService.LoginService;

import java.sql.SQLException;

public class LibraryManagementSys {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("----------Welcome to College Library----------");
        System.out.println("Please do login first for accessing menu:");

        LoginService  loginService = new LoginService();
        loginService.doLogin();
    }
}