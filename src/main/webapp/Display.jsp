<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Employee Records</h2>
            <a href="Registration.html" class="btn btn-success">Insert New Employee</a>
        </div>

        <div class="card shadow-sm">
            <div class="card-header bg-dark text-white">
                <h5 class="mb-0">Employee Details</h5>
            </div>
            <div class="card-body">
                <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Password</th>
                            <th>Salary</th>
                            <th>Operation</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            try {
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
                                Statement st = con.createStatement();
                                ResultSet rs = st.executeQuery("SELECT * FROM emp1");

                                while (rs.next()) {
                                    int id = rs.getInt(1);
                                    String name = rs.getString(2);
                                    String pass = rs.getString(3);
                                    int salary = rs.getInt(4);
                        %>
                        <tr>
                            <td><%= id %></td>
                            <td><%= name %></td>
                            <td><%= pass %></td>
                            <td><%= salary %></td>
                            <td>
                                <a href="Update.jsp?id=<%= id %>" class="btn btn-warning btn-sm me-2">Update</a>
                                <a href="Delete?id=<%= id %>" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                        <%
                                }
                                rs.close();
                                st.close();
                                con.close();
                            } catch (Exception e) {
                        %>
                        <tr>
                            <td colspan="5" class="text-danger">Error: <%= e.getMessage() %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
