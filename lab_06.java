import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
  
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
  
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Attr;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class lab_06 
{
	public static void main(String[] args)throws ParserConfigurationException
    ,SAXException, IOException {
		
	try {
			String filename = args[0];
			if(!filename.endsWith(".arxml"))
			{
				throw new NotValidAutosarFileException("invalid extension");
			}
	File file= new File(filename);
  String outputfile=getOutputFileName(filename);
	if(file.length()==0)
		throw new EmptyAutosarFileException("file is empty");
		
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    // Load the input XML document, parse it and return an instance of the
    // Document class.
    Document document = builder.parse(file);
    System.out.println(document.getDocumentElement());
    NodeList s= document.getDocumentElement().getChildNodes();
    ArrayList<Container> containers= new ArrayList<>();
    for(int i=0;i<s.getLength();i++)
    {
    	
    	Node node=s.item(i);
    	if(node.getNodeType()==Node.ELEMENT_NODE)
    	{
    		Element elem=(Element)node;
    		String Id=node.getAttributes().getNamedItem("UUID").getNodeValue();
    		String shortname = elem.getElementsByTagName("SHORT-NAME")
                    .item(0).getChildNodes().item(0).getNodeValue();
    		String longname= elem.getElementsByTagName("LONG-NAME")
                    .item(0).getChildNodes().item(0).getNodeValue();
    		containers.add(new Container(Id,shortname,longname));
    		
    	}
     }
   
   //sorting the arraylist of containers
    Collections.sort(containers);
    //creating xml file after modification
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.newDocument();
    
     // root element
    Element rootElement = doc.createElement("AUTOSAR");
    doc.appendChild(rootElement);
     // container element
    for(int i=0;i<containers.size();i++) {
    Element container = doc.createElement("CONTAINER");
    rootElement.appendChild(container);
    // setting attribute to element
    Attr attr = doc.createAttribute("UUID");
    attr.setValue(containers.get(i).getUUID());
    container.setAttributeNode(attr);
    
    // short-name element
    Element shortname = doc.createElement("SHORTNAME");
    shortname.appendChild(doc.createTextNode(containers.get(i).getShortName()));
    container.appendChild(shortname);
    
    // long-name element
    Element longname = doc.createElement("LONGNAME");
    longname.appendChild(doc.createTextNode(containers.get(i).getLongName()));
    container.appendChild(longname);
    }
    
     // write the content into xml file
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File(outputfile));
    transformer.transform(source, result);
    
    // Output to console for testing
    StreamResult consoleResult = new StreamResult(System.out);
    transformer.transform(source, consoleResult);

    
    
    }
    catch(NotValidAutosarFileException ex)
	  {
		ex.printStackTrace();
	  }
   
    catch (Exception e) {
        e.printStackTrace();
     } 
	}
  private static String getOutputFileName(String inputfile) {
    int ind = inputfile.lastIndexOf(".");
    String fileWithoutExtension = inputfile.substring(0, ind);
    String fileWithExtension = inputfile.substring(ind);
    return fileWithoutExtension + "_mod" + fileWithExtension;
}

}
