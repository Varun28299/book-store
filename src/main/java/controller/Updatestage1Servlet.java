package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.dao.BookCRUD;
import com.js.dto.Book;

@WebServlet(value = "/update")
public class Updatestage1Servlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		
	    Book b=BookCRUD.getBookById(id);
	    if(b!=null) {  //if book is present that is if it is not null do this
	    	RequestDispatcher rd=req.getRequestDispatcher("update.jsp");
	         req.setAttribute("book", b);
	         rd.forward(req, resp);
	    }
	    //here we are reading the previous data(book object) by id and forwarding it to jsp file and there we will receive it and edit it aand click on update. 
	    
	    else {
	    	RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
	         req.setAttribute("msg", "No books to Update");
	         rd.forward(req, resp);
	    }
	    
		
	}
	

}
