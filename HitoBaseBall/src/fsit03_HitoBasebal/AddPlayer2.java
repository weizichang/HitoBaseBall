package fsit03_HitoBasebal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.LineListener;

import com.oreilly.servlet.MultipartRequest;

@WebServlet("/AddPlayer2")
public class AddPlayer2 extends HttpServlet {
	
	LinkedList<PlayerModel> playerList = new LinkedList<>();
	private List datalist; 
	private String teamId;
	private String playerid;
	private HttpSession session;
	private HttpServletRequest request;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse response) 
			  throws ServletException, IOException {
		request=req;
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		session = request.getSession();
		teamId = (String)session.getAttribute("teamId");
		
		
         try {
        	 Map<String,String[]> inputParams = null;
        	 inputParams = request.getParameterMap(); 
        	 int dataCount = inputParams.size()/8;//1
        	 System.out.println("數量:"+inputParams.size());
        	 
        	 datalist =  getDatas(inputParams, dataCount);
        	 
			 Players player = new Players();
			 player.newInsert(playerList);
			 
			 uploadphoto();
			 
			 request.getRequestDispatcher("backend_players.jsp").forward(request, response);

         	} catch (Exception e) {
         		 e.toString();
         	}
         
		}
	 
	 //球員照片
	 private void uploadphoto()throws Exception{ 
		
         String uploadPath = 
				  getInitParameter("upload-path");
        
				  MultipartRequest r = new MultipartRequest(request,uploadPath,"UTF-8");
				  
				  Enumeration items = r.getFileNames();
				  
				  
				  while (items.hasMoreElements()){
				  	
				  	//取得playerid
				  	String playerid = (String)session.getAttribute("playerid");
				  	
				  	String strFile = (String)items.nextElement();
				  	File file = r.getFile(strFile);
				  	String FSName = r.getFilesystemName(strFile);
				  	String upName = r.getOriginalFileName(strFile);
				  	
				  	
				  	
//				  	for(int i=0 ; i<4;i++){
				  		String srcFilename = "C:\\Users\\WeiZiChang\\eclipse-workspace\\HitoBaseBall\\WebContent\\upload\\"+upName;
				  		String destFilename = "C:\\Users\\WeiZiChang\\eclipse-workspace\\HitoBaseBall\\WebContent\\upload\\"+playerid+"_player.png"; 
				  		boolean filechange = FileReName.rename(srcFilename, destFilename);
//				  	}
				  	
				  }
			}
	
		private LinkedList<PlayerModel> getDatas(Map dataset, int len){
			
			//System.out.println(len);
			for(int i=0;i<len;i++) {
				PlayerModel play = new PlayerModel();
				
				String number = ((String[])dataset.get("number0"+(i+1)))[0];
				String name = ((String[])dataset.get("name0"+(i+1)))[0];
				String height = ((String[])dataset.get("height0"+(i+1)))[0];
				String weight = ((String[])dataset.get("weight0"+(i+1)))[0];
				String birthday = ((String[])dataset.get("birthday0"+(i+1)))[0];
				String[] position = ((String[])dataset.get("position0"+(i+1)));
				String hit = ((String[])dataset.get("hit0"+(i+1)))[0];
				String thr = ((String[])dataset.get("thr0"+(i+1)))[0];
				
				int le = position.length;
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < le; j++) {
					sb=(j==le-1)?sb.append(position[j]):sb.append(position[j] +"/");
				}
				
				play.setTemid(teamId);
				play.setNumber(number);
				play.setName(name);
				play.setHeight(height);
				play.setWeight(weight);
				play.setBirthday(birthday);
				play.setPosition(sb.toString());
				play.setHit(hit);
				play.setThr(thr);
				
				playerList.add(play);
				//System.out.println("session teamid:"+teamId);
					
			}
			session.setAttribute("playerid", playerid);
			session.setAttribute("sqlplayers",playerList);
			return playerList;
			
		}
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
				doGet(request, response);
		}
	
}
