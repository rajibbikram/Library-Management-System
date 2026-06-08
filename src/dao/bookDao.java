package dao;

import bookDto.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bookDao {
    public Book getBookBySno(Connection conn, int srNo) throws SQLException{
        String query = "select * from books where sr_no = ? ";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1,srNo);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setSrNo(rs.getInt("sr_no"));
                    return book;
                } else {
                    System.out.println("No book found with this: " + srNo + "Number!");
                }
            }
        }
        return null;
    }

    public Book getBookByAuthorame(Connection conn, String authorName) throws SQLException{
        String query = "select * from books where author_name = ? ";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1,authorName);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setSrNo(rs.getInt("sr_no"));
                    return book;
                } else {
                    System.out.println("No book found for author: " + authorName);
                }


            }
        }
        return null;
    }

    public Book getBooksBySnoOrBookName(Connection conn, String authorName, int srNo) throws SQLException {
        String query = "SELECT * FROM books WHERE author_name = ? OR sr_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, authorName != null ? authorName.trim() : "");
            ps.setInt(2, srNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setSrNo(rs.getInt("sr_no"));
                    return book;
                }
            }
        }
        System.out.println("No book found for author: " + authorName + " or srNo: " + srNo);
        return null;
    }

    public void  saveBook(Connection conn, Book book) throws SQLException{
        String query = "insert into books (sr_no, name, author_name,qty) values(?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, book.getSrNo());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthorName());
            ps.setInt(4, book.getBookQty());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Book added SuccesFully!.");
            } else  {
                System.out.println("Failed to add book!.");
            }
        }
    }

}
