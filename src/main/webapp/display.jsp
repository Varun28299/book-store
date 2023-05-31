<%@page import="com.js.dto.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All</title>
</head>
<body>
<%
ArrayList<Book> books=(ArrayList)request.getAttribute("data");
%>

<h1>BOOK LIST</h1>
<table border="5px">
<tr>
<th>ID</th>
<th>Book Name</th>
<th>Author Name</th>
<th>Pages</th>
<th>Price</th>
<th>Delete</th>
<th>Update</th>
</tr>
<tr>
<%for(Book b:books) {%>
<td><%= b.getId()%></td>
<td><%= b.getBook_name() %></td>
<td><%= b.getAuthor_name()%></td>
<td><%= b.getNo_of_pages()%></td>
<td><%= b.getPrice()%></td>
<td><a href="delete?id=<%=b.getId() %>" >delete</a></td>  <!-- along with url we are passing id when we click delete we are passing id also to our deleteservlet-->
<td><a href="update?id=<%= b.getId()%>">edit</a></td>
</tr>
<% }%>

</table>

</body>
</html>