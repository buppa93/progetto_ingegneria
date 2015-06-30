import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLNoleggioView extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
   
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("NoleggioView.fxml"));
     
         Scene scene = new Scene(root, 600, 400);
     
         stage.setTitle("Noleggio");
         stage.setScene(scene);
         stage.show();
     }
}