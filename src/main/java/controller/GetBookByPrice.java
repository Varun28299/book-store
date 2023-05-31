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

@WebServlet(value="/price")
public class GetBookByPrice extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		double price=Double.parseDouble(req.getParameter("price")) ;
		
		BookCRUD b=new BookCRUD();//nonstatic method
		ArrayList<Book> books=b.getBooksByPrice(price);
		
		if(books.size()>0) {
		RequestDispatcher rd=req.getRequestDispatcher("display.jsp");//passing the books with that particular price(as arraylist--seebookcrud)..we may have multiplebooks with same price
		req.setAttribute("data", books);
		rd.forward(req, resp);
	}
		else {
			  RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
			  req.setAttribute("msg", "No books available");
			  rd.forward(req, resp);
			
		}
		}

}
