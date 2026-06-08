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
                }
            }
        }
        return null;
    }
}
