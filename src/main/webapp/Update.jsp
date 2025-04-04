<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.IOException" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

response.setContentType("text/html");

int id=Integer.parseInt(request.getParameter("id"));
String name="";
String password="";
int salary=0;

Class.forName("oracle.jdbc.driver.OracleDriver");
java.sql.Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
String sql="SELECT * FROM emp1 WHERE id=? ";
PreparedStatement pr=con.prepareStatement(sql);
pr.setInt(1, id);
ResultSet rs=pr.executeQuery();

if (rs.next()) {
    name = rs.getString("name");
    password = rs.getString("password");
    salary = rs.getInt("salary");
}

%>
	<form action="Update" method="post">
		Id : <input type="number" name="id" value="<%= id %>" readonly/>
		Name : <input type="text" name="name" value="<%= name %>" />
		Password : <input type="password" name="password" value="<%= password %>"/>
		Salary : <input type="number" name="salary" value="<%= salary %>" />
		<input type="submit" value="Submit" />
	</form>
</body>
</html>