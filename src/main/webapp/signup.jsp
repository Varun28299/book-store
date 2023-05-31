<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign UP</title>
</head>

<body>
<h1 align="center">Enter your details</h1>

<form action="save" align="center"  method="post" >

ID       : <input type="number" name="id"> <br> <br>
Name     : <input type="text" name="name"> <br> <br>
Email    :<input type="email" name="email"> <br> <br>
Password :<input type="password" name="pwd"> <br> <br>
Address  :<input type="text" name="add"> <br> <br>
Pincode  :<input type="number" name="pin"> <br> <br>
Phone    :<input type="number" name="phone"> <br> <br>

<input type="submit" value="Register">
</form>

</body>
</html>