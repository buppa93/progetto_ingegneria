package database;

import java.sql.SQLException;
import java.sql.Statement;

import entity.TypeContract;

public class TableTypeContract 
{
		public static final String FIELD_ID = "num. contratto";
		public static final String FIELD_TYPE_NOLEGGIO = "tipo noleggio";
		public static final String FIELD_TYPE_KM = "tipo chilometraggio";
		
		DbAccess db;
		
		public TableTypeContract(DbAccess db)
		{this.db = db;}
		
		public void insertTypeContract (TypeContract typecontract)
		{
			try 
			{
				Statement st = db.getConnection().createStatement();
				String query = "INSERT INTO "+DbString.TBL_TYPECONTRATC+" "
						+ "("+FIELD_ID+","+FIELD_TYPE_NOLEGGIO+","+FIELD_TYPE_KM+") values ('" +typecontract.getTypeContract()+"','"+ typecontract.getTypeContract() +
						"','" + typecontract.getChilometraggio()+","+"');";
				System.out.println(query);
				st.executeUpdate(query);
				System.out.println(st.toString());
				st.close();
			} 
			catch (SQLException e) 
			{ 
				System.out.println("inserimento non eseguito"); 
				e.printStackTrace();
			}
		}
		
		public TypeContract getTypeContractById (int id)
		{
			TypeContract c = null;
			try 
			{
				Statement st = db.getConnection().createStatement();
				c = (TypeContract) st.executeQuery("SELECT * FROM "+DbString.TBL_TYPECONTRATC+" WHERE "+FIELD_ID+"="+id+";");
				st.close();
			} 
			catch (SQLException e) 
			{ 
				System.out.println("inserimento non eseguito"); 
				e.printStackTrace();
			}
			return c;
		}

	}
