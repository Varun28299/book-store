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

@WebServlet(value = "/book")
public class InsertBook extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));
		String name =req.getParameter("name");
		String author=req.getParameter("author");
		int pages=Integer.parseInt(req.getParameter("pages"));
		double price=Double.parseDouble(req.getParameter("price"));
		
		Book b=new Book();
		b.setId(id);
		b.setBook_name(name);
		b.setAuthor_name(author);
        b.setNo_of_pages(pages);
        b.setPrice(price);
        
        int res=BookCRUD.insertBook(b);
        
        RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
         if(res==1) {
        	 req.setAttribute("msg", "successful");
        	 rd.forward(req, resp);
         }
		else {
			 req.setAttribute("msg", "UnSuccessful");
			 rd.forward(req, resp);
		}
		
		
	}

}
