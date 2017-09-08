package fsit03_HitoBasebal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Teams{
	private String teamId,teamName,fb,email;
	private Connection conn;
	private PreparedStatement pstmt;
	
//teamid??	
	
	public Teams() throws Exception{
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hitobaseball", "root", "root");
	System.out.println("Teams連線成功");
	};
	
	public void setteamId(String teamId) {this.teamId = teamId;}
	public void setteam(String teamName) {this.teamName = teamName;}
	public void setfb(String fb) {this.fb = fb;}
	public void setemail(String email) {this.email = email;}
	
	public String getteamId() {return teamId;}
	public String getteam() {return teamName;}
	public String getfb() {return fb;}
	public String getmail() {return email;}
	
	
	//insert
	public boolean newInsert() throws Exception{
		String sql = "insert into teams(teamname,fb,email) values (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, teamName);
		pstmt.setString(2, fb);
		pstmt.setString(3, email);
		int n = pstmt.executeUpdate();
		return n>0;
	} 
	
	//update
	public boolean update() throws Exception{
		String sql = "update teams set teamname=? , fb=? , email=? where teamId="+teamId+ "";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, teamName);
		pstmt.setString(2, fb);
		pstmt.setString(3, email);
		
		int n = pstmt.executeUpdate();
		return n>0;
	} 
	
	//抓TeamID//找最新加入的ID
	public String geteamID() throws Exception {
		String res ="";
	String getIDsql ="SELECT * FROM teams WHERE teamname=? AND fb=? AND email=?";//''
	PreparedStatement pstmt = conn.prepareStatement(getIDsql);
	pstmt.setString(1, teamName);
	pstmt.setString(2, fb);
	pstmt.setString(3, email);
	
	ResultSet rs = pstmt.executeQuery();
	
	
	boolean ret = rs.next();
	//System.out.println(ret);
	
	if(ret) {
		String teamName = rs.getString("teamname");
		String fb = rs.getString("fb");
		String email = rs.getString("email");
		
		String MyTeamId = "SELECT * FROM teams WHERE teamname='"+ teamName +"' AND fb='"+ fb + "' AND email='"+ email+"'";
		
		Statement stmt = conn.createStatement(
                		 ResultSet.TYPE_SCROLL_INSENSITIVE,  
                		 ResultSet.CONCUR_READ_ONLY); 
						ResultSet result = stmt.executeQuery(MyTeamId); 
		
				result.afterLast(); 
				
				while(result.previous()) { 
					String myid = (result.getString("teamId")); 
					String myteamname = (result.getString("teamname")); 
					String myfb = (result.getString("fb")); 
					String myemail = (result.getString("email")); 
					res= myid ;
				}
				
				stmt.close(); 
		}
	
		return res;
	}
}
	
	
	//delete
	
//	public boolean delete() throws Exception{
//		String sql = "insert into teams (teamname,fb,email) values (?,?,?)";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, teamName);
//		pstmt.setString(2, fb);
//		pstmt.setString(3, email);
//		int n = pstmt.executeUpdate();
//		return n>0;
//	} 

