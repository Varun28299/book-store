package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.dao.PersonCRUD;
import com.js.dto.Person;

@WebServlet(value="/save")
public class InsertPerson extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt( req.getParameter("id"));
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		int password=Integer.parseInt( req.getParameter("pwd"));
		String address=req.getParameter("add");
		int pincode=Integer.parseInt( req.getParameter("pin"));
		long phone=Long.parseLong( req.getParameter("phone"));
	       
		Person p=new Person();
		p.setId(id);
		p.setName(name);
		p.setEmail(email);
		p.setPassword(password);
		p.setAddress(address);
		p.setPincode(pincode);
		p.setPhone(phone);
		
		int res=PersonCRUD.insertPerson(p);
		
		RequestDispatcher rd =req.getRequestDispatcher("result.jsp");//creating the object outside if block because whatever the result  here we are forwarding it to same page 
		if(res==1) {                                                 //if we have 2different pages then we can create 2 objects of request dispatcher by passing different page address
		req.setAttribute("msg", "Successful");                       //or we can include it in same page everything depends on programmer
		rd.forward(req, resp);
		}
		else {
			 req.setAttribute("msg", "UnSuccessful");
			 rd.forward(req, resp);
		}
	
	}

}
