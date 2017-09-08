package fsit03_HitoBasebal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

@WebServlet("/AddTeam")
public class AddTeam extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		//取得request參數
		String teamID = request.getParameter("teamId");
		String teamName = request.getParameter("teamName");
		String fb = request.getParameter("fb");
		String email = request.getParameter("email");
		
		//送到Teams執行寫入資料庫
		try {
			Teams team = new Teams();
			team.setteam(teamID);
			team.setteam(teamName);
			team.setfb(fb);
			team.setemail(email);
			boolean result = team.newInsert();
			
			//抓TeamID
			String id = team.geteamID();
			System.out.println("ADDTeam get id:"+id);
			
			//Session
			HttpSession session = request.getSession();
			session.setAttribute("teamId", id);
			
			request.getRequestDispatcher("MyTeam").forward(request, response);
 
		}catch(Exception e) {
			request.setAttribute("error", e);
			System.out.println(e.toString());
			request.getRequestDispatcher("backend.jsp").forward(request, response);
		
		}
	}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
				doGet(request, response);
		}
		
		
}
