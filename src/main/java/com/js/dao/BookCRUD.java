package com.js.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.js.dto.Book;

public class BookCRUD {
	final static String path = "com.mysql.cj.jdbc.Driver";// final because path is fixed, it is not needed you can do
															// it,we can make this private also private final
															// static......
	final static String url = "jdbc:mysql://localhost:3306/book_store?user=root";

	public static int insertBook(Book b) {
		String query = "insert into book values(?,?,?,?,?)";// ?-->place holder or delimeters
		Connection c = null;
		try {
			Class.forName(path);
			c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, b.getId());
			ps.setString(2, b.getBook_name());
			ps.setString(3, b.getAuthor_name());
			ps.setInt(4, b.getNo_of_pages());
			ps.setDouble(5, b.getPrice());

			return ps.executeUpdate();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;// to avoid error because compiler dont know that try block executes or not has
					// return statement written inside that
	}
	// --------------------------------------------------------------

	public static Book getBookById(int id) {
		String query = "select * from book where id=?";
		Connection c = null;
		try {
			Class.forName(path);
			c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt(1));
				b.setBook_name(rs.getString(2));
				b.setAuthor_name(rs.getString(3));
				b.setNo_of_pages(rs.getInt(4));
				b.setPrice(rs.getDouble(5));
				return b;
			} else {
				return null;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	// --------------------------------------------------------------------
	public static int deleteBookById(int id) {
		String query = "delete from book where id=?";
		Connection c = null;

		try {
			Class.forName(path);
			c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, id);
			return ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	// ------------------------------------------------------------
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> al = new ArrayList<Book>();
		String query = "select * from Book";
		Connection c = null;

		try {
			Class.forName(path);
			c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt(1));
				b.setBook_name(rs.getString(2));
				b.setAuthor_name(rs.getString(3));
				b.setNo_of_pages(rs.getInt(4));
				b.setPrice(rs.getDouble(5));

				al.add(b);
			}
			return al;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;// If try dont execute then return empty arraylist.

	}

	// ------------------------------------------------------------------------------
	public ArrayList<Book> getBooksByPrice(double price) {
		ArrayList<Book> al = new ArrayList<Book>();
		String query = "select * from book where price=?";
		Connection c = null;

		try {
			Class.forName(path);
			c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setDouble(1, price);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt(1));
				b.setBook_name(rs.getString(2));
				b.setAuthor_name(rs.getString(3));
				b.setNo_of_pages(rs.getInt(4));
				b.setPrice(rs.getDouble(5));

				al.add(b);
			}
			return al;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

	// ---------------------------------------------------------------------
	public int updateBookById(int id, Book b) {
		String query = "update book set name=?,author=?,pages=?,price=? where id=?";
		Connection c = null;

		try {
			Class.forName(path);
			c = DriverManager.getConnection(url);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, b.getBook_name());
			ps.setString(2, b.getAuthor_name()); // updated details are added to database through place holders(dynamic)
			ps.setInt(3, b.getNo_of_pages()); // with the reference of book object where details are updated
			ps.setDouble(4, b.getPrice());

			ps.setInt(5, id);// here to this id above details are updating to the database.

			return ps.executeUpdate();// place holders read the values assigned here query will execute, as operation
										// is update it will update to the database for that particular id.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;

	}
//-----------------------------------------------------------------------
	
	public static int[] batchExecution(ArrayList<Book> books) {
		
		String query="insert into book values(?,?,?,?,?)";
		Connection c=null;
		
		try {
			Class.forName(path);
			c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(query);
			for(Book b:books) {
				ps.setInt(1, b.getId());
				ps.setString(2, b.getBook_name());
				ps.setString(3,b.getAuthor_name());
				ps.setInt(4, b.getNo_of_pages());
				ps.setDouble(5, b.getPrice());
				
				ps.addBatch();
			}
			return ps.executeBatch();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
//-------------------------------------------------------------
	//batch execution without returning anything
	
/*public static void batchExecution(ArrayList<Book> books) {
		
		String query="insert into book values(?,?,?,?,?)";
		Connection c=null;
		
		try {
			Class.forName(path);
			c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(query);
			for(Book b:books) {
				ps.setInt(1, b.getId());
				ps.setString(2, b.getBook_name());
				ps.setString(3,b.getAuthor_name());
				ps.setInt(4, b.getNo_of_pages());
				ps.setDouble(5, b.getPrice());
				
				ps.addBatch();
			}
			ps.executeBatch();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}*/
//-------------------------------------------------------------------
	
	public static int[] deletebatch(int[] arr) {
		String query="delete from book where id=?";
		
		Connection c=null;

		try {
			Class.forName(path);
			c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(query);
			for(int id:arr) {        //reading one id at a time & adding it to batch & executing it.As our operation-
				ps.setInt(1, id);    //-isto delete the data from DB will be delted.
				
				
				ps.addBatch();	
			  }
			return ps.executeBatch();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
		
	}

}
