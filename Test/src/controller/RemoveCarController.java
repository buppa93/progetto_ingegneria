package controller;

import java.io.IOException;
import java.sql.SQLException;

import view.GenericDialogView;
import view.GenericWarning;
import view.SQLWarning;
import view.SalesManView;
import database.DAOTableAuto;
import database.DatabaseConnectionException;
import database.DbAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RemoveCarController
{
	@FXML private AnchorPane rootLayout;
	@FXML private TextField targa_field;
	
	@FXML protected void onSubmitAction(ActionEvent event) throws DatabaseConnectionException
	{
		if(targa_field.getText().equals("")||targa_field.getText()==null)
		{
			new GenericWarning("Il campo Targa non puo' essere vuoto!",
					"Assicurati di aver riempito tutti i campi correttamente.").start();
		}
		else
		{
			DbAccess db = new DbAccess();
			db.initConnection();
			DAOTableAuto ta = new DAOTableAuto(db);
			int result = 0;
			
			try {
				result = ta.deleteAutoByTargaAndAgency(targa_field.getText(),SalesManView.session.filiale.getNumber());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(result!=0)
			{
				targa_field.setText("");
				new GenericDialogView("Transazione eseguita con sussesso.", "Automobile rimossa con successo.").start();
			}
			else {new SQLWarning();}
		}
	}
	
	@FXML protected void onCancelAction(ActionEvent event) throws IOException
	{((BorderPane) rootLayout.getParent()).setCenter(FXMLLoader.load(SalesManView.class.getResource("NothingView.fxml")));}
}
