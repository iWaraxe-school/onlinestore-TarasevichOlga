package by.issoft.store.helpers.XMLparsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class XMLParser {

    private static final String FILENAME = "parent/store/src/resources/config1.xml";

    public Map<String, String> getFieldSortOrderMap() {
        String sortTag = "sort";
        Map<String, String> fieldSortDirectionMap = new LinkedHashMap<>();

        try {
            // Instantiate the Factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            // get <staff>
            Node node = doc.getElementsByTagName(sortTag).item(0);
            NodeList sortProperties = node.getChildNodes();

            Element elementary;
            for (int i = 0; i < sortProperties.getLength(); i++) {

                //get all child tag names and values from config file and add them in properties map
                if (sortProperties.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    elementary = (Element) sortProperties.item(i);

                    String key = elementary.getTagName().toLowerCase(Locale.ROOT);
                    String value = elementary.getTextContent().toUpperCase(Locale.ROOT);

                    fieldSortDirectionMap.put(key, value);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return fieldSortDirectionMap;
    }

}