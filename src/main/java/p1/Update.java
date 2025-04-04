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

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.setContentType("text/html");
//		PrintWriter out=response.getWriter();
//		
//		int id=Integer.parseInt(request.getParameter("id"));
//		
//		try {
//			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			java.sql.Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
//			String sql="SELECT * FROM emp1 WHERE id=? ";
//			PreparedStatement pr=con.prepareStatement(sql);
//			pr.setInt(1, id);
//			ResultSet rs=pr.executeQuery();
//			if(rs.next()) {
//				
//				out.println("<html><head></head>");
//				out.println("<body><form action='Update' method='POST'>");
//				out.println("Id :<input type='number' name='id' value='"+rs.getInt("id")+"'readonly/>");
//				out.println("Name :<input type='text' name='name' value='"+rs.getString("name")+"'/>");
//				out.println("Password :<input type='password' name='password' value='"+rs.getString("password")+"'/>");
//				out.println("Salary :<input type='number' name='salary' value='"+rs.getInt("salary")+"'/>");
//				out.println("<input type='submit' value='Submit'/>");
//				out.println("</form></body>");
//				out.println("</html>");
//			}
//			else {
//				out.println("Record not found");
//			}
//			
//			
//		} catch (Exception e) {
//			e.getMessage();
//		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String pass=request.getParameter("password");
		int salary=Integer.parseInt(request.getParameter("salary"));
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			String sql="UPDATE emp1 SET name=?,password=?,salary=? WHERE id=? ";
			PreparedStatement pr=con.prepareStatement(sql);
			pr.setString(1, name);
			pr.setString(2, pass);
			pr.setInt(3, salary);
			pr.setInt(4, id);
			int Update = pr.executeUpdate();
			if(Update > 0) {
				response.sendRedirect("Display");
				//out.println("Updated");
			}
			else {
				out.println("Not Updated");
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

}
