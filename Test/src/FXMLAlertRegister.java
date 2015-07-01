import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FXMLAlertRegister extends Application {
	
	    public void start(Stage stage) throws Exception 
	    {
	        Parent root = FXMLLoader.load(getClass().getResource("FXMLAlertRegister.fxml"));
	     
	         Scene scene = new Scene(root, 202, 213);
	     
	         stage.setTitle("FXML Welcome");
	         stage.setScene(scene);
	         stage.show();
	     }
	}

