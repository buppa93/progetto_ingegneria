package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class AdminView extends Application 
{
    
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAdminView.fxml"));
     
         Scene scene = new Scene(root, 900, 500);
     
         stage.setTitle("Finestra Admin");
         stage.setScene(scene);
         stage.show();
     }
}
