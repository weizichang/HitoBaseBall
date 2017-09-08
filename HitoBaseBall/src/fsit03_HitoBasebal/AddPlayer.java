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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.LineListener;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

@WebServlet("/AddPlayer")
public class AddPlayer extends HttpServlet {
	
	private HttpServletRequest request;
	private List<FileItem> items;
	private List players;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		request = req;
		players = getplayers();
		
		 for(int j = 0; j < players.size(); j ++) {
			 ArrayList<String> player = (ArrayList<String>)players.get(j);
		    	for(int i = 0; i < player.size(); i++) {
		    		
		    	System.out.println(player.get(i) + ":" + j);
		    	}
		 }
	}
	
	private ArrayList getplayers() {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		// Process the uploaded items
		Iterator<FileItem> iter = items.iterator();

		ArrayList<ArrayList<String>> players = new ArrayList<>();
		ArrayList<String> tmp = new ArrayList<>();
		
		while (iter.hasNext()) {
		    FileItem item = iter.next();
		    String name = item.getName();
		    String str =item.getString();
		    String fieldName = item.getFieldName();
		    
		    tmp.add(str);
		    
		    if (str.equals("d")) {
		    	ArrayList<String> player = new ArrayList<>();
		    	for(int i = 0; i < tmp.size(); i++) {
		    		player.add(tmp.get(i));
		    		System.out.println("hi");
		    	}
		    	tmp = new ArrayList<>();
		    	players.add(player);
		    	
		    }
		}
		return players;
	}

}
	 




