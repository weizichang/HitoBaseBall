package fsit03_HitoBasebal;

import java.io.File;
import org.omg.CORBA.Environment;

public class FileReName {

//	public static void main(String[] args) {
//		int a = 55;
//		File oldfile =new File("C:\\Users\\WeiZiChang\\eclipse-workspace\\HitoBaseBall\\WebContent\\upload\\B.png");
//		File newfile =new File("C:\\Users\\WeiZiChang\\eclipse-workspace\\HitoBaseBall\\WebContent\\upload\\"+a+".png");
//		
//		boolean flag = oldfile.renameTo(newfile);
//			if(flag){
//			   System.out.println("File renamed successfully");
//			}else{
//			   System.out.println("Rename operation failed");
//			}
//		}
	
	
	public static boolean rename (String srcFilename, String destFilename) {
		File uploadFile;
		File changFile;
		boolean result = false;
		

		uploadFile = new File(srcFilename);
		changFile = new File(destFilename);
		try {
			result = uploadFile.renameTo(changFile);
		} catch (Exception e) {
			System.out.println("failed to rename file: " + e.toString());
			// e.printStackTrace();
		} finally {
			uploadFile = null;
			changFile = null;
		}
		return result;
	}
	
	public void getFileName(String filename) {;}
	
	}

