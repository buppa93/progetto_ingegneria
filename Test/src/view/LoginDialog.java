package view;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginDialog extends Application 
{
    public void start(Stage stage) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("LoginDialog.fxml"));
     
         Scene scene = new Scene(root, 400, 134);
     
         stage.setTitle("Login");
         stage.setScene(scene);
         stage.show();
     }
}