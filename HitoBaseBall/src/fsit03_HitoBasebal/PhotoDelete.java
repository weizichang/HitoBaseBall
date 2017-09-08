package fsit03_HitoBasebal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PhotoDelete")
public class PhotoDelete extends HttpServlet {  
	  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws FileNotFoundException, IOException {  
    	
	    	HttpSession session = request.getSession();
			String teamId = (String)session.getAttribute("teamId");
			
			String photo = request.getParameter("photo");
			System.out.println("photo:"+photo);
			
			
			switch (photo) {
			case "p0" :
				File file01 = new File
        		("C:\\Users\\WeiZiChang\\eclipse-workspace\\HitoBaseBall\\WebContent\\upload\\"+teamId+"_0_slides.png");
				file01.delete(); 
				break; 
			case "p1" :
				File file02 = new File
        		("C:\\Users\\WeiZiChang\\eclipse-workspace\\HitoBaseBall\\WebContent\\upload\\"+teamId+"_1_slides.png");
				file02.delete();  
				break; 
			case "p2" :
				File file03 = new File
        		("C:\\Users\\WeiZiChang\\eclipse-workspace\\HitoBaseBall\\WebContent\\upload\\"+teamId+"_2_slides.png");
				file03.delete();  
				break; 
			case "p3" :
				File file04 = new File
        		("C:\\Users\\WeiZiChang\\eclipse-workspace\\HitoBaseBall\\WebContent\\upload\\"+teamId+"_3_slides.png");
				file04.delete();  
				break; 
			}
  
//        	if (!file.isDirectory()) {  
//        		file.delete();  
//        		System.out.println("刪圖OK");  
//        	} else {  
//        		System.out.println("刪圖XX");
//        		}  
			//request.getRequestDispatcher("MyTeam").forward(request, response);
			  response.sendRedirect("MyTeam");
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws IOException, ServletException {  
        doGet(request, response);  
    }  
}  