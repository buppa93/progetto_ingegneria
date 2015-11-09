package view;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import entity.User;
import model.SESSION;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class SalesManView extends Application 
{
	public static SESSION session;
	
	public SalesManView(User usr) throws Exception
	{
		session = new SESSION(usr);
		if(session.validateUsr())
		{
			start(new Stage());
		}
		else
		{
			new GenericWarning("Attenzione", "Non puoi loggarti in questa agenzia.").start();
			LoginDialog login = new LoginDialog();
			try 
			{
				login.start(new Stage());
			} catch (IOException e) 
			{
				new GenericWarning("Errore","GenericException").start();
			}
		}
	}
    
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("SalesManView.fxml"));
     
         Scene scene = new Scene(root, 900, 500);
     
         stage.setTitle("FXML Welcome");
         stage.setScene(scene);
         stage.show();
     }
    
    public SESSION getSession()
    {
    	return this.session;
    }
}