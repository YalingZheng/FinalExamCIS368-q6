

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdatePwdServlet
 */

public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdServlet() {
        super();
    }

  
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		// handle coloring 
		// get the content string 
		//doGet(request, response);
		response.setContentType("text/html");
		// get parameter's value
		String username = request.getParameter("name");
		String oldpassword = request.getParameter("oldpassword");
		
		// modify the following code to get correct posted new password
		String newpassword = "";
		
		User user = new User(username, oldpassword);
		User newuser = new User(username, newpassword);
		String resultstr = "Failed to change password; please check your user name or password or network connection!";
				
		if ((UserDao.getRecord(user) == 1) && 		 
				(UserDao.update(newuser) == 1)) {
					// 1 means success; other values means not successful 
					resultstr = "You have successfully changed password";				
		}
				
		PrintWriter out = response.getWriter();
		System.out.println("user name = " + username);
		System.out.println("old password = " + oldpassword);
		 
		 out.println("<HTML>");
		 out.println("<head>");
		 
		 out.println("</head>");
		 out.println("<body>");
		 
		 out.println("<h1> " + resultstr + "</h1>");
		 
		 out.println("</body></html>");
		 out.close();
	}		
	
}
