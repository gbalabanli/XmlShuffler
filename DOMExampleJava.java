import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
/**
 * @author Crunchify.com
 */
 
public class DOMExampleJava {
	static PrintWriter writer;
 
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
    	writer = new PrintWriter("kirik_linkV7.txt", "UTF-8");
    	//READ FILE
    	/////////////////////////////////////////////////////////////
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        
		File stocks = new File("wallpapersV7_CAT_FROM.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document docRead = dBuilder.parse(stocks);
		docRead.getDocumentElement().normalize();

		//System.out.println("root of xml file" + doc.getDocumentElement().getNodeName());
		NodeList nodes = docRead.getElementsByTagName("WALLPAPER");
		
		
		
        
        
        /////////////////////////////////////////////////////////////////////////////////
        
        /// WRITE TO FILE AND CREATE NEW ELEMENT
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement = doc.createElementNS("http://masamagelsin.net", "Wallpapers");
            doc.appendChild(mainRootElement);
 
            // append child elements to root element
            
            writer.println("Kirik Linkler:");
            
         
            for (int i =  nodes.getLength() - 1; i >= 0; i--)
    		{
    			Random rnd = new Random();
    			int index = rnd.nextInt(i + 1);
    			
    			
    			
    			
    			Node randNode = nodes.item(index);
    			//Node node = nodes.item(i);
    			
    			randNode.getParentNode().removeChild(randNode);
    			
    			Element randElement = (Element) randNode;
    			//Element element = (Element) node;
    			getCrashedLink("http://masamagelsin.net/wallpaper/preview/",getValue("FILENAME", randElement));
    			
    			//getCrashedLink(getValue("URL", randElement));
    			System.out.print(i +" ");
    			//System.out.println("URL: "+"index"+index + getValue("URL", element) + "--->rand:" +getValue("URL",randElement));
    			//System.out.println("WP FILENAME: " + getValue("FILENAME", element));
    			mainRootElement.appendChild(getWallpaper(doc, getValue("URL",randElement),  getValue("FILENAME", randElement)));
    			
    		       //append first child element to root element
                //rootElement.appendChild(getEmployee(doc,  getValue("URL",randElement), getValue("FILENAME", randElement)) );      
               
    		}
    		
            //mainRootElement.appendChild(getWallpaper(doc, "3", "Google", "Search", "3000"));
 
            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            DOMSource source = new DOMSource(doc);
            //StreamResult console = new StreamResult(System.out);  
            StreamResult file = new StreamResult(new File("OUTPUTV7.xml"));
            //transformer.transform(source, console);
            transformer.transform(source, file);
            writer.close();
            System.out.println("\nXML DOM Created Successfully..");
           
            findHasOne();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getCrashedLink(String url) throws IOException{
    	 URL u = new URL ( url); 
         HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
         huc.setRequestMethod ("GET"); 
         huc.connect () ; 
     
         int code = huc.getResponseCode (  ) ;
         if(code == 404){
         	System.out.println("\ncode:"+code+" url:"+u);
         	writer.println(u);
         }
    }
    
    private static void getCrashedLink(String root, String fileName) throws IOException{
   	 URL u = new URL ( root + fileName); 
        HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
        huc.setRequestMethod ("GET"); 
        huc.connect () ; 
    
        int code = huc.getResponseCode (  ) ;
        if(code == 404){
        	System.out.println("\ncode:"+code+" url:"+u);
        	writer.println(u);
        }
   }
 
    private static Node getWallpaper(Document doc, String url, String fileName) {
        Element wallpaper = doc.createElement("WALLPAPER");
        //company.setAttribute("id", id);
        wallpaper.appendChild(getWallpaperElements(doc, wallpaper, "URL", url));
        wallpaper.appendChild(getWallpaperElements(doc, wallpaper, "FILENAME", fileName));
        //company.appendChild(getCompanyElements(doc, company, "Employees", role));
        return wallpaper;
    }
 
    // utility method to create text node
    private static Node getWallpaperElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    
    
	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
	private static void findHasOne() throws ParserConfigurationException, SAXException, IOException{
		File stocks = new File("OUTPUTV7.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document docRead = dBuilder.parse(stocks);
		docRead.getDocumentElement().normalize();

		//System.out.println("root of xml file" + doc.getDocumentElement().getNodeName());
		NodeList nodes = docRead.getElementsByTagName("WALLPAPER");
		
		for (int j =  nodes.getLength() - 1; j >= 0; j--)
  		{
			for (int i =  nodes.getLength() - 1; i >= 0; i--)
	  		{
	  			Node node = nodes.item(i);
	  			Node nodej = nodes.item(j);
	  			
	  			Element element = (Element) node;
	  			Element elementj = (Element) nodej;
	  			//System.out.println("AYNI MI BAK BAKALIM--------------------------");
	  			if (getValue("URL", element).equals(getValue("URL", elementj)) && i != j )
	  				System.out.println("ayniiiiii URL: "+  getValue("URL", element) );
	  			
	  			//System.out.println("FINISH AYNI MI BAK BAKALIM--------------------------FINISH");
	  			//System.out.println("WP FILENAME: " + getValue("FILENAME", element));
	  		}
  		}
	}
}