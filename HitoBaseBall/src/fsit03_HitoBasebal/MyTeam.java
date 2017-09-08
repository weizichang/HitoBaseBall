package fsit03_HitoBasebal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MyTeam")
public class MyTeam extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		
		HttpSession session = request.getSession();
		String teamId = (String)session.getAttribute("teamId");
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties prop = new Properties();
			prop.setProperty("user", "root");
			prop.setProperty("password", "root");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/hitobaseball", prop);
			
			Statement stmt = conn.createStatement();
			String sql ="select * from teams where teamId=" + teamId;
			ResultSet rs =  stmt.executeQuery(sql);
			TeamModel team = new TeamModel();
			
			if(rs.next()) {
				team.setTeamId(teamId);
				team.setTeamname(rs.getString("teamname"));
				team.setFb(rs.getString("fb"));
				team.setEmail(rs.getString("email"));
			}
			
			request.setAttribute("team", team);
			session.setAttribute("team", team);
		}catch (Exception e) {
			System.out.println(e.toString());
			}
		request.getRequestDispatcher("backend_team.jsp").forward(request, response);
	}
	
	
}
