package LoginService;

import bookDto.Book;
import dao.bookDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BookService {
    Scanner sc = new Scanner(System.in);
    public void searchBySrNo(Connection conn) throws SQLException {
        System.out.println("Enter Serial No of Book:");
        int srNo = sc.nextInt();

        bookDao  dao = new bookDao();
        Book book = dao.getBookBySno(conn, srNo);

        if(book != null){
            System.out.println("==== Book Details ====");
            System.out.println("Sr No: " + book.getSrNo() + " Book Name: " + book.getBookName() + "Author Name: " + book.getAuthorName());
        } else {
            System.out.println("Book is not found! This serial No: " + srNo + "is Not Found.");
        }
    }


    public void searchByAuthorName(Connection conn) {
        try {
            System.out.println("Enter Author name: ");
            String authorName = sc.nextLine();

            bookDao dao = new bookDao();
            Book book = dao.getBookByAuthorame(conn, authorName);

            System.out.println("==== Book Details ====");
            System.out.println("Sr No: " + book.getSrNo());
            System.out.println("Book Name: " + book.getBookName());
            System.out.println("Author Name: " + book.getAuthorName());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
