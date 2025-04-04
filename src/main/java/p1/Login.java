
package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String user=request.getParameter("name");
		String pass=request.getParameter("password");
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			String sql=" SELECT name, password FROM emp1 WHERE name=? AND password=? ";
			PreparedStatement pr=con.prepareStatement(sql);
			pr.setString(1, user);
			pr.setString(2, pass);
			ResultSet rs=pr.executeQuery();
			if(rs.next()) {
				HttpSession session=request.getSession();
				session.setAttribute("name", user);
				response.sendRedirect("Home");
			}
			else {
				out.println("Not Login");
			}
			rs.close();
			pr.close();
			con.close();
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
