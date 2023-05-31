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
@WebServlet(value = "/edit")
public class Updatestage2Servlet extends HttpServlet {
	
	//only for update we are creating two servlets because we want to show user the previous data(stage1) here we are updating it in database after the user edits in update webpage
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
        
        BookCRUD bc=new BookCRUD();
        int result=bc.updateBookById(id, b);
        
        if(result==1) {          // this if else used to check if it is updating in database or not and also if there any problem in bookcrud(crud operations)
        	RequestDispatcher rd=req.getRequestDispatcher("view"); //this url goes to viewall servlet where we read all  the books .Now we get updated data 
        	rd.forward(req, resp);
        }
        else {
        	RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
        	req.setAttribute("msg", "No books available to edit");
        	rd.forward(req, resp);
        	
        }
		
		
		
	}

}
