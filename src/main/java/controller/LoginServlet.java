package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.dao.PersonCRUD;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     //resp.setContentType("text/html");-->Using this because we are using both printwriter and request dispatcher sometimes we may get the response including the html tags- 
		                                   //-to avooid that we use contenttype os response is html or text but now i am not getting the error,if it comes use this.
		String email=req.getParameter("email");
		int password=Integer.parseInt(req.getParameter("pwd"));
		
		boolean result=PersonCRUD.validatePerson(email, password);
		
		if(result) { //no need to write result==true...it works as it is beacuse if the result true it enters
			RequestDispatcher rd=req.getRequestDispatcher("home.jsp");
			rd.forward(req, resp);
		}
		else {
			PrintWriter pw=resp.getWriter();
			pw.write("<html><body><h2 style=\"color: red\";>Email or password is wrong</h2></body></html>");
			RequestDispatcher rd=req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
			
		}
		
		
		
	}

}
