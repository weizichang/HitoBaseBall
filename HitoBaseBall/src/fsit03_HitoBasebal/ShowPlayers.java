package fsit03_HitoBasebal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.LineListener;

import com.oreilly.servlet.MultipartRequest;

@WebServlet("/ShowPlayers")
public class ShowPlayers extends HttpServlet {
	
	ArrayList<HashMap<String, String>> playerList = new ArrayList<>();
	private LinkedList<PlayerModel> datalist; 
	private String teamId;
	private HttpSession session;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		session = request.getSession();
		teamId = (String)session.getAttribute("teamId");
			
        datalist =  getDatas();
        
        request.getRequestDispatcher("backend_players.jsp").forward(request, response);
        	 
	}
		
		
	
		private LinkedList<PlayerModel> getDatas(){
			LinkedList<PlayerModel> temp = new LinkedList<>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Properties prop = new Properties();
				prop.setProperty("user", "root");
				prop.setProperty("password", "root");
				
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/hitobaseball", prop);
				System.out.println("showerplayers:連線OK");
				
				Statement stmt = conn.createStatement();
				String sql ="select * from players where temid=" + teamId;
				ResultSet rs =  stmt.executeQuery(sql);
				
				PlayerModel player = new PlayerModel();
				
				if(rs.next()) {
					player.setTemid(teamId);
					player.setNumber(rs.getString("number"));
					player.setName(rs.getString("name"));
					player.setPosition(rs.getString("position"));
					player.setHit(rs.getString("hit"));
					player.setThr(rs.getString("thr"));
					player.setHeight(rs.getString("height"));
					player.setWeight(rs.getString("weight"));
					player.setBirthday(rs.getString("birthday"));
					
					temp.add(player);
				}
				session.setAttribute("sqlplayers", temp);
			}catch (Exception e) {
				System.out.println(e.toString());
				}
			return temp;
		}
		
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
				doGet(request, response);
		}
	
}



