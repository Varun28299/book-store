package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.dao.BookCRUD;

@WebServlet(value = "/delete")
public class DeleteBookServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));//here we are receving the id that we get when the particular row get deleted
	
	    int result=BookCRUD.deleteBookById(id); //we are deleting that book using id
	    
	    if(result==1) {
			  RequestDispatcher rd=req.getRequestDispatcher("view");//here we are sending the request to viewallservlet using view url, as one book get deleted now it display new table without that book. 
			  rd.forward(req, resp);                                //in database also we dont have that book
			  }
	    else {          //this is if crud operation goes wrong in bookcrud because if book dont present it dont show you in view all books.so book is available something is wrong
			  RequestDispatcher rd=req.getRequestDispatcher("result.jsp");
			  req.setAttribute("msg", "Something is wrong.please try again");
			  rd.forward(req, resp);
			
		}
	}

}
