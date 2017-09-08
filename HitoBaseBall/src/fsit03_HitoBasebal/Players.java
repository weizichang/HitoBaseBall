package fsit03_HitoBasebal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import javax.servlet.http.HttpSession;

public class Players{
	private String playerId,number,name,position,hit,thr,height,weight,birthday;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public Players() throws Exception{
		System.out.println("OK");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hitobaseball", "root", "root");
			System.out.println("Players連線成功");
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	};
	
	//insert
	public boolean newInsert(LinkedList<PlayerModel> list) throws Exception{
		int n = 0;
		
		for(int j=0;j<list.size();j++) {
			PlayerModel playmap = list.get(j);
			
			
			String sql = "insert into players(temid,number,name,height,weight,birthday,position,hit,thr) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, playmap.getTemid());
			pstmt.setString(2, playmap.getNumber());
			pstmt.setString(3, playmap.getName());
			pstmt.setString(4, playmap.getHeight());
			pstmt.setString(5, playmap.getWeight());
			pstmt.setString(6, playmap.getBirthday());
			pstmt.setString(7, playmap.getPosition());
			pstmt.setString(8, playmap.getHit());
			pstmt.setString(9, playmap.getThr());
			
			try {
				n = pstmt.executeUpdate();
			}catch(Exception e) {
				System.out.println(e.toString());
			}
		}
	
		return n>0;
	} 
	
	//update
	public boolean update() throws Exception{
		String sql = "update players set number=? ,name=? , ,height=?,weight=?,birthday=? where teamId=2 ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, number);
		pstmt.setString(2, name);
		pstmt.setString(3, position);
		pstmt.setString(4, hit);
		pstmt.setString(5, thr);
		pstmt.setString(6, height);
		pstmt.setString(7, weight);
		pstmt.setString(8, birthday);
		
		int n = pstmt.executeUpdate();
		return n>0;
	} 
	
	
	//取playerid
	public String gePlayerID() throws Exception {
	String res ="";
	String getIDsql ="SELECT * FROM players WHERE number=? AND name=? ";
	PreparedStatement pstmt = conn.prepareStatement(getIDsql);
	pstmt.setString(1, number);
	pstmt.setString(2, name);
	
	ResultSet rs = pstmt.executeQuery();
	
	
	boolean ret = rs.next();
	//System.out.println(ret);
	
	if(ret) {
		//String teamId = rs.getString("teamId");
		String number = rs.getString("number");
		String name = rs.getString("name");
		
		String MyPlayerId = "SELECT * FROM players WHERE number='"+ number +"' AND name='"+ name + "' ";
		
		System.out.println("MyPlayerId:"+MyPlayerId);
		
		Statement stmt = conn.createStatement(
                		 ResultSet.TYPE_SCROLL_INSENSITIVE,  
                		 ResultSet.CONCUR_READ_ONLY); 
						ResultSet result = stmt.executeQuery(MyPlayerId); 
		
				result.afterLast(); 
				
				while(result.previous()) { 
					String mypid = (result.getString("playerId")); 
					String mypnumber = (result.getString("number")); 
					String mypname = (result.getString("name")); 
					//System.out.println(myid);
					res= mypid ;
				}
				
				stmt.close(); 
				//conn.close();
				
		
	}
	
	return res;
	
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
}
