package utility;

public class OsValidator 
{

	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public static String detect() 
	{
		String OSType = "";
		
		//System.out.println(OS);
		
		if (isWindows()) 
		{OSType = "win";} 
		else if (isMac()) 
		{OSType = "mac";} 
		else if (isUnix()) 
		{OSType = "lin";} 
		else 
		{OSType = "undetected";}
		
		return OSType;
	}

	public static boolean isWindows() 
	{return (OS.indexOf("win") >= 0);}

	public static boolean isMac() 
	{return (OS.indexOf("mac") >= 0);}

	public static boolean isUnix() 
	{return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );}
}

