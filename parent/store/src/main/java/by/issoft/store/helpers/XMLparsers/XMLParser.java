package by.issoft.store.helpers.XMLparsers;

import javafx.scene.shape.Arc;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {

    private static final String FILENAME = "parent/store/src/main/resources/config1.xml";
    private static Arc list;


    public Map<String, String> getFieldSortOrderMap() {
        Map<String, String> fieldSortDirectionMap = new LinkedHashMap<>();


        try {
            // Instantiate the Factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // get <staff>
            NodeList nodelist = doc.getElementsByTagName("sort");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = nodelist.item(temp);

            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return fieldSortDirectionMap;
    }

}




