import entity.Agency;
import entity.User;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import utility.KeyValuePair;

import javax.xml.parsers.*;

import java.io.*;
import java.util.ArrayList;


public class SESSION 
{
	Agency filiale;
	static User usr;
	
	public SESSION(User usrx) throws ParserConfigurationException, SAXException, IOException
	{
		ArrayList<KeyValuePair<String,String>> agencyProperties = getPropertiesFromXML();
		filiale = new Agency(agencyProperties.get(0).getValue(), agencyProperties.get(1).getValue(),
				agencyProperties.get(2).getValue());
		usr = usrx;
	}
	
	public ArrayList<KeyValuePair<String,String>> getPropertiesFromXML() throws ParserConfigurationException, SAXException, IOException
	{
		ArrayList<KeyValuePair<String,String>> properties = new ArrayList<KeyValuePair<String,String>>();
		
		File inputFile = new File("./properties/agencyProperties.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList list = doc.getElementsByTagName("agency");
        Node node = list.item(0);
        Element agenzia = (Element) node;
        KeyValuePair<String, String> numero = new KeyValuePair<String, String>("numero",agenzia.getAttribute("numero"));
        KeyValuePair<String, String> nome = new KeyValuePair<String, String>("nome",agenzia.getAttribute("nome"));
        KeyValuePair<String, String> indirizzo = new KeyValuePair<String, String>("indirizzo",agenzia.getAttribute("indirizzo"));
        
        properties.add(numero);
        properties.add(nome);
        properties.add(indirizzo);
        
		return properties;
	}
}
