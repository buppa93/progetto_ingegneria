package utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DbAccess;
import database.DbString;
import database.TableClient;

public class MyUtil 
{
	
	MyUtil(){}
	
	public static String getMD5(String input) 
	{
        try 
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) 
            {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) 
        {
            throw new RuntimeException(e);
        }
    }
	
	public static boolean login (DbAccess db, String name, String pwd) throws SQLException
	{
		//return true;
		Statement st = db.getConnection().createStatement();
		String md5 = MyUtil.getMD5(pwd);
		ResultSet rs = st.executeQuery("SELECT * FROM "+DbString.TBL_CLIENTS+" WHERE ("+TableClient.FIELD_NAME+"="+name+
				"&&"+TableClient.FIELD_PWD+"="+md5+");");
		
		if(rs.next())
			return true;
		else
			return false;	
	}

}
