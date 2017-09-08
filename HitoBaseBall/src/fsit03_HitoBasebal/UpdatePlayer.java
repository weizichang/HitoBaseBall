//package fsit03_HitoBasebal;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.PreparedStatement;
//import java.util.Enumeration;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.oreilly.servlet.MultipartRequest;
//
//@WebServlet("/UpdatePlayer")
//public class UpdatePlayer extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		request.setCharacterEncoding("UTF-8");
//		
//		//1.取出JSP端資料
//		String number = request.getParameter("number");
//		String name = request.getParameter("name");
//		String[] position = request.getParameterValues("position");
//		String hit = request.getParameter("hit1");
//		String thr = request.getParameter("thr");
//		String height = request.getParameter("height");
//		String weight = request.getParameter("weight");
//		String birthday = request.getParameter("birthday");
//		
//		//2.封裝為JavaBean
//		//3.寫入資料庫
//		
//		try {
//			Players player = new Players();
//			player.setnumber(number);
//			player.setname(name);
//			player.setposition(position);
//			player.sethit(hit);
//			player.setthr(thr);
//			player.setheight(height);
//			player.setweight(weight);
//			player.setbirthday(birthday);
//			boolean result = player.update();
//			
//		
//			//View
//			request.setAttribute("result", result);
//			request.getRequestDispatcher("backend_team.jsp").forward(request, response);//ssesion連結到不同team網頁 or SendRedirect()
////			response.sendRedirect("");
// 
//		}catch(Exception e) {
//			request.setAttribute("error", e);
//			System.out.println(e.toString());
//			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
//		
//		}
//	}
//
//		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//				throws ServletException, IOException {
//				doGet(request, response);
//		}
//		
//	
//}
