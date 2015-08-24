import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Servlet implementation class GetList
 */
@WebServlet("/FindPeople")
public class FindPeople extends HttpServlet {
	String lastname;
	String fullList;
	String content;
	public void init() throws ServletException {
		// Do required initialization
		
	}
	
	@SuppressWarnings("null")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:testuser/password@localhost";
				//properties for creating connection to Oracle database
		        Properties props = new Properties();
		        props.setProperty("user", "testdb");
		        props.setProperty("password", "password");
		        Connection conn=DriverManager.getConnection(url,props);
				
		        lastname=request.getParameter("lastname");
		        
        String sql = "select * from (select excelcustomer.customerid, excelcustomer.fullname, excelcustomer.title, excelcustomer.firstname, excelcustomer.lastname, "
        		+ "excelcustomer.streetaddress, excel_state_city.state, excel_state_city.city, "
        		+ "excelcustomer.zipcode, excelcustomer.emailaddress, excelcustomer.position, excelcompany.companyname "
        		+ "from excelcustomer left join excel_state_city on excelcustomer.state_city=excel_state_city.STATE_CITY_ID "
        		+ "left join excelcompany on excelcustomer.COMPANY=excelcompany.COMPANYID) "
        		+ "where lastname like \'"+lastname+"%\' or companyname like \'"+lastname+"%\'";
        		
        
        PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
				if(result.next()){
					while(result.next()){
						fullList+=("<tr><td>"+
								result.getString("customerid")+"</td><td>"+
								result.getString("fullname")+"</td><td>"+
								result.getString("title")+"</td><td>"+
								result.getString("firstname")+"</td><td>"+
								result.getString("lastname")+"</td><td>"+
								result.getString("streetaddress")+"</td><td>"+
								result.getString("state")+"</td><td>"+
								result.getString("city")+"</td><td>"+
								result.getString("zipcode")+"</td><td>"+
								result.getString("emailaddress")+"</td><td>"+
								result.getString("position")+"</td><td>"+
								result.getString("companyname")+
								"</td></tr>");
						//System.out.println(fullList);
					}
					response.setContentType("text/html");
					request.setAttribute("fullList",fullList);
					request.setAttribute("lastname",lastname);
					getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
				}else{
					content="Not found!";
					response.setContentType("text/html");
					request.setAttribute("content",content);
					request.setAttribute("lastname",lastname);
					getServletContext().getRequestDispatcher("/NotFound.jsp").forward(request, response);
				}
				conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

		fullList="";
	}



	public void destroy() {
		// do nothing.
	}

	
	
}
