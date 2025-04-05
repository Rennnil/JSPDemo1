package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession(false);
	    String sessionname = (String) session.getAttribute("name");

	    out.println("<!DOCTYPE html>");
	    out.println("<html lang='en'>");
	    out.println("<head>");
	    out.println("<meta charset='UTF-8'>");
	    out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
	    out.println("<title>Employee List</title>");
	    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
	    out.println("</head>");
	    out.println("<body class='bg-light'>");

	    out.println("<div class='container mt-5'>");

	    out.println("<div class='d-flex justify-content-between align-items-center mb-4'>");
	    out.println("<h2>Welcome, " + sessionname + "</h2>");
	    out.println("<div>");
	    out.println("<a href='Registration.html' class='btn btn-success me-2'>Insert New</a>");
	    out.println("<a href='Logout' class='btn btn-danger'>Logout</a>");
	    out.println("</div>");
	    out.println("</div>");

	    out.println("<div class='card'>");
	    out.println("<div class='card-header bg-primary text-white'><h4>Employee Details</h4></div>");
	    out.println("<div class='card-body'>");

	    out.println("<table class='table table-bordered table-hover'>");
	    out.println("<thead class='table-dark'>");
	    out.println("<tr>");
	    out.println("<th>ID</th>");
	    out.println("<th>Name</th>");
	    out.println("<th>Password</th>");
	    out.println("<th>Salary</th>");
	    out.println("<th>Actions</th>");
	    out.println("</tr>");
	    out.println("</thead>");
	    out.println("<tbody>");

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
	        Statement st = con.createStatement();
	        String sql = "SELECT * FROM emp1";
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            int id = rs.getInt(1);
	            String name = rs.getString(2);
	            String pass = rs.getString(3);
	            int salary = rs.getInt(4);

	            out.println("<tr>");
	            out.println("<td>" + id + "</td>");
	            out.println("<td>" + name + "</td>");
	            out.println("<td>" + pass + "</td>");
	            out.println("<td>" + salary + "</td>");
	            out.println("<td>");
	            out.println("<a href='Update.jsp?id=" + id + "' class='btn btn-warning btn-sm me-2'>Update</a>");
	            out.println("<a href='Delete?id=" + id + "' class='btn btn-danger btn-sm'>Delete</a>");
	            out.println("</td>");
	            out.println("</tr>");
	        }

	        out.println("</tbody>");
	        out.println("</table>");
	        out.println("</div></div></div>");
	        out.println("</body></html>");

	        rs.close();
	        st.close();
	        con.close();

	    } catch (Exception e) {
	        out.println("<div class='alert alert-danger mt-3'>Error: " + e.getMessage() + "</div>");
	    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
