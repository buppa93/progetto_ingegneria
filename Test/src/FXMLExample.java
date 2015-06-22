import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class FXMLExample extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
    
   /* @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) 
            {
                System.out.println("Hello World!");
            }
      });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }*/
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
     
         Scene scene = new Scene(root, 390, 400);
     
         stage.setTitle("FXML Welcome");
         stage.setScene(scene);
         stage.show();
     }
}