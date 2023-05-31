package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.dao.BookCRUD;
import com.js.dto.Book;

@WebServlet(value = "/view")
public class ViewAllServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BookCRUD b= new BookCRUD();//because get allbooks method is non static
		  ArrayList<Book> books=  b.getAllBooks();
		  
		  if(books.size()>0) {
		
		  RequestDispatcher rd=req.getRequestDispatcher("display.jsp");
		  req.setAttribute("data", books);
		  rd.forward(req, resp);
		  }
	
	else {
		  RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
		  req.setAttribute("msg", "No books Available");
		  rd.forward(req, resp);
		
	}

}}
