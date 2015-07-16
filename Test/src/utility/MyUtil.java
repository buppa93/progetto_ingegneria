package utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Random;

import database.DbAccess;
import database.DbString;
import database.TableUsers;

public class MyUtil 
{
	
	MyUtil(){}
	
	/**
	  * Crea una stringa codifica in md5 a partire dal parametro di 
	  * input
	  * @param     input la stringa da trasformare
	  * @return    stringa in codifica md5
	  */
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
	
	/**
	  * Effettua un controllo sul database per controllare
	  * che sia un utente lecito oppure no
	  * @param     db variabile di accesso al database
	  * @param     name nome dell'utente
	  * @param     pwd password dell'utente non in md5
	  * @return    true se l'utente e' stato riconosciuto false altrimenti
	  */
	public static boolean login (DbAccess db, String name, String pwd) throws SQLException
	{
		//return true;
		System.out.println("Sto per fare il login");
		Statement st = db.getConnection().createStatement();
		System.out.println("Ho creato lo statement");
		String md5 = MyUtil.getMD5(pwd);
		System.out.println("Ho creato l' md5");
		String query = "SELECT * FROM "+DbString.TBL_CLIENTS+" WHERE ("+TableUsers.FIELD_NAME+"='"+name+
				"' AND "+TableUsers.FIELD_PWD+"='"+md5+"');";
		System.out.println(query);
		ResultSet rs = st.executeQuery(query);
		
		
		if(rs.next())
		{
			st.close();
			rs.close();
			return true;
		}
		else
		{
			st.close();
			rs.close();
			return false;
		}
		
		
	}
	
	public static int estimatedNumberOfDays(String data1, String data2) throws ParseException
	{
		int gg = 0;

		java.text.SimpleDateFormat df =  
				new java.text.SimpleDateFormat("yyyy-MM-dd");  
		java.util.Date dt1 = new java.util.Date();  
		java.util.Date dt2 = new java.util.Date();  
		dt1 = df.parse(data1);  
		dt2 = df.parse(data2);  
		java.util.GregorianCalendar c1 =  
				new java.util.GregorianCalendar();  
		c1.setTime(dt1);  
		java.util.GregorianCalendar c2 =  
				new java.util.GregorianCalendar();  
		c2.setTime(dt2);  
		long dallaDataMilliSecondi = c1.getTimeInMillis();  
		long allaDataMilliSecondi = c2.getTimeInMillis();  
		long millisecondiFraDueDate =  
				allaDataMilliSecondi - dallaDataMilliSecondi;  
		// conversione in giorni con la divisione intera  
		double giorniFraDueDate =  
				millisecondiFraDueDate / 86400000; 
		gg = (int)giorniFraDueDate;


		return gg;
	}
	
	public static String makeId()
	{
		Random random = new Random();
		int j = 1;
		int n = 9-j;
		int k = 0;
		
		StringBuffer result = new StringBuffer (5);
		for(int i=1; i<6; i++)
		{
			k = random.nextInt(n)+j;
			result.append(k);
		}
		return result.toString();
	}

}
