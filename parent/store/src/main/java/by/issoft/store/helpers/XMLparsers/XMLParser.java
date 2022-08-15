package by.issoft.store.helpers.XMLparsers;

import javafx.scene.shape.Arc;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLParser {

    private static final String FILENAME = "parent/store/src/main/resources/config1.fxml";
    private static Arc list;


    public static void main(String[] args) throws ParserConfigurationException {

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try {

           // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();


            // get <staff>
            NodeList nodelist = doc.getElementsByTagName("staff");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = nodelist.item(temp);


                }
            }

        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}