package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class SalesManView extends Application 
{
    
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("SalesManView.fxml"));
     
         Scene scene = new Scene(root, 900, 500);
     
         stage.setTitle("FXML Welcome");
         stage.setScene(scene);
         stage.show();
     }
}