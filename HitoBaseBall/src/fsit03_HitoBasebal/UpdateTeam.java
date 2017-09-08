package fsit03_HitoBasebal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

@WebServlet("/UpdateTeam")
public class UpdateTeam extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
	  	String teamId = (String)session.getAttribute("teamId");
		
		//1.取出JSP端資料
		//String teamId = request.getParameter("teamId");
		String teamName = request.getParameter("teamName");
		String fb = request.getParameter("fb");
		String email = request.getParameter("email");
//		System.out.println("teamName:"+teamName);
		//2.封裝為JavaBean
		//3.寫入資料庫
		
		try {
			Teams team = new Teams();
			team.setteamId(teamId);
			team.setteam(teamName);
			team.setfb(fb);
			team.setemail(email);
			
			boolean result = team.update();
		
			//View
			request.setAttribute("result", result);
			request.getRequestDispatcher("MyTeam").forward(request, response);//ssesion連結到不同team網頁 or SendRedirect()
//			response.sendRedirect("");
 
		}catch(Exception e) {
			request.setAttribute("error", e);
			System.out.println(e.toString());
			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
		
		}
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
				doGet(request, response);
		}
		
	
}
