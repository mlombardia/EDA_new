import java.io.IOException;
import java.util.Properties;

public class Utils {
	private static Properties properties = new Properties();

	static 
	{
		String fileName= "config.txt";
		 
	     try {
	            properties.load(Utils.class.getClassLoader().getResourceAsStream(fileName));
	     } 
	     catch (IOException e) {
	    	 System.out.println(e);
	     }
	}
	
	public static String getDir()
	{
		return properties.getProperty("dir");
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(Utils.getDir());
	
	}

}
