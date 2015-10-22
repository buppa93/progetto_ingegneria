package utility;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import view.EstimateView;
import view.GenericWarning;
import view.SalesManView;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class PDFFunctionality 
{
	public void riempiAcroPDF(String percorsoPrestampato, OutputStream out)
			throws IOException, DocumentException
	{

		// Leggo il prestampato
		PdfReader reader = new PdfReader(percorsoPrestampato);
		
		
		// Lo stamper che scriverà il nuovo pdf riempito
		PdfStamper stamper = new PdfStamper(reader, out);

		// Con questo metodo mi riprendo i campi del FORM presente nel pdf,
		// quelli che, in alcuni casi, si possono riempire anche con Acrobat
		// Reader
		AcroFields form = stamper.getAcroFields();
		
		//TODO inserire parametro numero contratto
		/*
		 * Parametri generali
		 */
		form.setField("contract_number", /**/"");
		form.setField("data", MyUtil.getDate());
		
		/*
		 * Parametri commesso
		 */
		form.setField("salasman_name", SalesManView.session.usr.getName());
		form.setField("salasman_surname", SalesManView.session.usr.getSurname());
		form.setField("salasman_phone", SalesManView.session.usr.getTelephoneNumber());
		
		/*
		 * Parametri cliente
		 */
		form.setField("client_name", EstimateView.getInstance().getClient().getName());
		form.setField("client_surname", EstimateView.getInstance().getClient().getSurname());
		form.setField("client_phone", EstimateView.getInstance().getClient().getPhone());
		
		/*
		 * Parametri noleggio
		 */
		form.setField("date_start", EstimateView.getInstance().getParameters().get("dataStart"));
		form.setField("date_end", EstimateView.getInstance().getParameters().get("dataEnd"));
		form.setField("duration", EstimateView.getInstance().getParameters().get("during"));
		form.setField("fact_type", EstimateView.getInstance().getParameters().get("base"));
		form.setField("km_type", EstimateView.getInstance().getParameters().get("typeKm"));
		form.setField("fascia", EstimateView.getInstance().getParameters().get("typeCar"));
		form.setField("agency_return", EstimateView.getInstance().getParameters().get("agencyReturn"));
		if(EstimateView.getInstance().getParameters().get("typeKm").equals("limitato"))
		{
			form.setField("km_nol", EstimateView.getInstance().getParameters().get("km"));
		}
		else
		{
			form.setField("km_nol", "N/D");
		}
		
		/*
		 * Parametri automobile
		 */
		form.setField("brand", EstimateView.getInstance().getAuto().getBrand());
		form.setField("model", EstimateView.getInstance().getAuto().getModel());
		form.setField("targa", EstimateView.getInstance().getAuto().getTarga());
		form.setField("km", String.valueOf(EstimateView.getInstance().getAuto().getKm()));
		form.setField("posti", String.valueOf(EstimateView.getInstance().getAuto().getN_posti()));
		form.setField("porte", String.valueOf(EstimateView.getInstance().getAuto().getN_porte()));
		form.setField("category", EstimateView.getInstance().getAuto().getTipo_vettura());

		// Questo metodo è molto importante perchè serve a chiudere il form in
		// modo che non sia modificabile dall'utente
		stamper.setFormFlattening(false);

		// Questo invece finalizza tutto il pdf
		stamper.close();
	}
	
	public void openFile(String url)
	{
		if (Desktop.isDesktopSupported()) 
		{
		    try 
		    {
		        File myFile = new File(getPercorsoLocale());
		        Desktop.getDesktop().open(myFile);
		    } 
		    catch (IOException ex) 
		    {new GenericWarning("Errore","Il file non può essere aperto!");}
		}
	}
	
	public String getPercorsoLocale()
	{return "res/testModel.pdf";}
	
	public String crateFileUrl()
	{return MyUtil.getDate()+"_"+EstimateView.getInstance().getClient().getPhone()+".pdf";}
	
	public void creaPrestampato(String url) throws FileNotFoundException, SecurityException
	{
		FileOutputStream fout;
				
		try
		{
			if(OsValidator.detect().equals("mac")||OsValidator.detect().equals("lin"))
			{
					fout = new FileOutputStream("contracts/"+url);
					riempiAcroPDF(getPercorsoLocale(), fout);
					fout.close();
			}
			else if(OsValidator.detect().equals("win"))
			{
					fout = new FileOutputStream("..\\contracts\\"+url);
					riempiAcroPDF(getPercorsoLocale(), fout);
					fout.close();
			}
			else
			{new GenericWarning("Errore Sistema", "Il sistema in uso non supporta la creazione di pdf.");}
		}
		catch (IOException ex)
		{new GenericWarning("Attenzione", "Errore di I/O.");}
		catch (DocumentException ex)
		{new GenericWarning("Attenzione", "Errore documento.");}
	}
}