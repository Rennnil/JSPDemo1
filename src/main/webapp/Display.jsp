<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.IOException" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="2">
		<thead>
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>PASSWORD</th>
				<th>SALARY</th>
				<th>OPERATION</th>
			</tr>
		</thead>
		<tbody>
<%
response.setContentType("text/html");
int id=0;
int salary=0;
String name="";
String pass="";
Class.forName("oracle.jdbc.driver.OracleDriver");
java.sql.Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
Statement st=con.createStatement();
String sql="SELECT * FROM emp1";
ResultSet rs=st.executeQuery(sql);
while (rs.next()) {
	
	id=rs.getInt(1);
	name=rs.getString(2);
	pass=rs.getString(3);
	salary=rs.getInt(4);

%>		
			<tr>
				<td><%=id %></td>
				<td><%=name %></td>
				<td><%=pass %></td>
				<td><%=salary %></td>
				<td><a href="Delete?id=<%=id%>">Delete</a></td>
			</tr>
		</tbody>
<%} %>
	</table>
</body>
</html>