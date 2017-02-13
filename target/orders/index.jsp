<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.benevity.orders.services.CompanyService" %>
<%@ page import="com.benevity.orders.services.UserService" %>
<%@ page import="com.benevity.orders.entities.Company" %>
<%@ page import="com.benevity.orders.entities.User" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request Order List</title>
</head>
<body>
<%
  List<Company> companies = CompanyService.listCompanies();
  List<User> users = UserService.listUsers();
%>

<h2>Please select values to filter the Orders List</h2>

<form action="ordersservlet" method="get"> 
<table>
<tr><td>Company:</td>
<td>
<select name="company">
<option value="0">-- Select --</option>
<%
for (Company comp : companies) {
%>
  <option value="<%=comp.getId()%>"><%=comp.getCompanyName()%></option>
<%
}
%>
</select>
</td><td></td>
</tr>
<tr><td>User:</td>
<td>
<select name="user">
<option value="0">-- Select --</option>
<%
for (User user : users) {
%>
  <option value="<%=user.getId()%>"><%=user.getUserName()%></option>
<%
}
%>
</select>
</td><td></td>
</tr>
<tr>
<td>Start Date:</td>
<td><input name="startDate" type="date"  size="12" placeholder="yyyy/mm/dd" value="" />(yyyy/mm/dd)</td><td></td>
</tr>
<tr>
<td>End Date:</td>
<td><input name="endDate" type="date" size="12" placeholder="yyyy/mm/dd" value="" />(yyyy/mm/dd)</td><td></td>
</tr>
<tr>
<td>Country:</td>
<td><input name="country" type="text" value="" /></td><td></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Submit"></td>
</tr>
</table>


</form>
</body>
</html>