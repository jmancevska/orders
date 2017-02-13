<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Thank you for submitting your request.</h2>
<div style="color:red"><%=request.getAttribute("error")  %></div>
<div style="color:blue"><%=request.getAttribute("status")  %></div>
<br>

</body>
</html>