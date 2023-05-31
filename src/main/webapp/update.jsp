<%@page import="com.js.dto.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>update</title>
</head>
<body>
<h1>Update the details</h1>

<% Book b=(Book)request.getAttribute("book"); %>

<!-- here we are reading the data and display it by using get method in scripletexpression tag and we dont update id so making it readonly -->
<form action="edit">
Book ID : <input type="number" name="id" value=<%=b.getId()%> readonly="readonly"> <br>  <br>  
Book name : <input type="text" name="name" value=<%=b.getBook_name()  %>> <br>  <br>
Book author : <input type="text" name="author" value=<%=b.getAuthor_name()  %>><br>  <br>
No of pages : <input type="number" name="pages" value=<%=b.getNo_of_pages()%>><br>  <br>
Book price : <input type="number" name="price" value=<%=b.getPrice()%>><br>  <br>

<input type="submit" value="UPDATE">
</form>
</body>
</html>