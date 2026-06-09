package LoginService;

import bookDto.Book;
import dao.bookDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

    public void addBook(Connection conn) throws SQLException {
        System.out.print("Enter Serial No of Book:");
        int srNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Name:");
        String bookName = sc.nextLine();

        System.out.print("Enter Author of book:");
        String authorName = sc.nextLine();

        System.out.print("Enter Qty of book:");
        int qty = sc.nextInt();

        bookDao dao = new bookDao();
        Book book = dao.getBooksBySnoOrBookName(conn,authorName,srNo);

        if(book != null){
            System.out.println("Book details exist into our system. Please save  with other book!");
            return;
        }
        Book input = new Book();
        input.setSrNo(srNo);
        input.setBookName(bookName);
        input.setAuthorName(authorName);
        input.setBookQty(qty);

        dao.saveBook(conn, input);

    }
    public void getAllBooks(Connection conn) throws SQLException {

        bookDao dao = new bookDao();

        List<Book> books = dao.getAllBooks(conn);

        System.out.println("+------+----------------------+----------------------+------+\n");
        System.out.printf("| %-4s | %-20s | %-20s | %-4s |\n",
                "SrNo", "Book Name", "Author", "Qty");
        System.out.println("+------+----------------------+----------------------+------+\n");
        for (Book book : books) {

            System.out.printf("| %-4d | %-20s | %-20s | %-4d |\n",
                    book.getSrNo(),
                    book.getBookName(),
                    book.getAuthorName(),
                    book.getBookQty());
        }
        System.out.println("+------+----------------------+----------------------+------+\n");
    }


}
