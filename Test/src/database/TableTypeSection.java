package database;

import java.sql.SQLException;
import java.sql.Statement;

import entity.TypeSection;

public class TableTypeSection 
{
			public static final String FIELD_NOME = "fascia";
			public static final String FIELD_N_PORTE = "numero porte";
			public static final String FIELD_N_POSTI = "numero posti";
			
			DbAccess db;
			
			public TableTypeSection(DbAccess db)
			{this.db = db;}
			
			public void insertTypeSection (TypeSection typesection)
			{
				try 
				{
					Statement st = db.getConnection().createStatement();
					String query = "INSERT INTO "+DbString.TBL_SECTIONS+" "
							+ "("+FIELD_NOME+","+FIELD_N_PORTE+","+FIELD_N_POSTI+") values ('" +typesection.getFascia()+"','"+ typesection.getN_porte() +
							"','" + typesection.getN_posti()+"');";
					st.executeUpdate(query);
					st.close();
				} 
				catch (SQLException e) 
				{ 
					System.out.println("inserimento non eseguito"); 
					e.printStackTrace();
				}
			}
			
			public TypeSection getTypeSectionByNome (int nome)
			{
				TypeSection c = null;
				try 
				{
					Statement st = db.getConnection().createStatement();
					c = (TypeSection) st.executeQuery("SELECT * FROM "+DbString.TBL_SECTIONS+" WHERE "+FIELD_NOME+"="+nome+";");
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
