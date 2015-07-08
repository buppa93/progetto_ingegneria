package controller;
import java.io.IOException;

import view.SalesManView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FXMLAlertRegisterController 
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
			//TODO Alert cliente non registrato
			//flag = "unregistered";
		}
	}
	
	@FXML protected void onCancelEvent(ActionEvent event) throws IOException
	{
		((BorderPane) rootPane.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));
	}
}
