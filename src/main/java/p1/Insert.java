package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String pass=request.getParameter("password");
		int salary=Integer.parseInt(request.getParameter("salary"));
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			String sql="INSERT INTO emp1(ID,NAME,PASSWORD,SALARY) VALUES(?,?,?,?)";
			PreparedStatement pr=con.prepareStatement(sql);
			pr.setInt(1, id);
			pr.setString(2, name);
			pr.setString(3, pass);
			pr.setInt(4, salary);
			int insert = pr.executeUpdate();
			if(insert > 0) {
				response.sendRedirect("Display");
				//out.println("Inserted");
			}
			else {
				out.println("Not Inserted");
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
