package view;

import java.io.IOException;import model.SESSION;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class AdminView extends Application 
{
public static SESSION session;
	
	public AdminView(User usr) throws Exception
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
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAdminView.fxml"));
     
         Scene scene = new Scene(root, 900, 500);
     
         stage.setTitle("Finestra Admin");
         stage.setScene(scene);
         stage.show();
     }
    
    public SESSION getSession()
    {
    	return this.session;
    }
}
