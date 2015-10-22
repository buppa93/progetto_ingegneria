package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import view.SalesManView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FXMLAlertRegisterController  implements Initializable
{
	@FXML CheckBox registered_chbox;
	@FXML CheckBox notregistered_chbox;
	@FXML Button cancel_bttn;
	@FXML Button submit_bttn;
	@FXML AnchorPane rootPane;
	
	@FXML protected void confirm(ActionEvent event) throws Exception
	{
		if(registered_chbox.isSelected())
		{
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("InsertClientDataView.fxml")));
		}
		else
		{
			((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NewClientView.fxml")));
		}
	}
	
	@FXML protected void onCancelEvent(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		registered_chbox.selectedProperty().addListener(new ChangeListener<Boolean>()
				{

					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) 
					{
						if(newValue == true)
						{
							notregistered_chbox.setDisable(true);
						}
						
						if(newValue == false)
						{
							notregistered_chbox.setDisable(false);
						}
						
					}
					
				});
		
		notregistered_chbox.selectedProperty().addListener(new ChangeListener<Boolean>()
				{

					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) 
					{
						if(newValue == true)
						{
							registered_chbox.setDisable(true);
						}
						
						if(newValue == false)
						{
							registered_chbox.setDisable(false);
						}
						
					}
					
				});
	}
}
