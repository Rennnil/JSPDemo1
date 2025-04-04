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
		PrintWriter out=response.getWriter();
		out.println("<a href='Registration.html'>Insert</a><br>");
		HttpSession session=request.getSession(false);
		String sessionname = (String) session.getAttribute("name");
		out.println("<h1>"+sessionname+"</h1>");
		out.println("<a href='Logout'>Logout</a><br><br><br>");
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			Statement st=con.createStatement();
			String sql="SELECT * FROM emp1";
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String pass=rs.getString(3);
				int salary=rs.getInt(4);
				
				out.println(id);
				out.println(name);
				out.println(pass);
				out.println(salary);
				out.println("<a href='Delete?id="+id+"'>Delete</a>");
				out.println("<a href='Update.jsp?id="+id+"'>Update</a><br>");
				out.println();
				
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
