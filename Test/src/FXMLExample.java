import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class FXMLExample extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
     
         Scene scene = new Scene(root, 390, 400);
     
         stage.setTitle("FXML Welcome");
         stage.setScene(scene);
         stage.show();
     }
}