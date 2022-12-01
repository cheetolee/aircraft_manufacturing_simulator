package Utilities;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 * This class is responisble for parsing the xml file for the necessary data to create factories
 */
public class XMLReader {

    //Protected variables will be used by ControlSystem to build factories
    static ArrayList<String> factoryTypeList;
    static ArrayList<ArrayList<String>> factoryIconList;
    static ArrayList<Integer> factoryProductionTimeList;
    static ArrayList<ArrayList<Integer>> factoryInputQuantityList;
    static ArrayList<Integer> factoryIdList;
    static ArrayList<ArrayList<Integer>> factoryPositionList;
    static ArrayList<ArrayList<Integer>> factoryRoadList;

    /**
     * Instantiates a new XML Reader
     */
    public XMLReader() {
        factoryTypeList = new ArrayList<>();
        factoryIconList = new ArrayList<>();
        factoryProductionTimeList = new ArrayList<>();
        factoryInputQuantityList = new ArrayList<>();
        factoryIdList = new ArrayList<>();
        factoryPositionList = new ArrayList<>();
        factoryRoadList = new ArrayList<>();
    }

    /**
     * parse the XML File and stores data in corresponding class fields
     * @param XMLPath the path to the XML File
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void parseXML(String XMLPath) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(XMLPath));
        document.getDocumentElement().normalize();

        //NodeList of all descendant Elements with tag name "factory", in document order
        NodeList factoryList = document.getElementsByTagName("factory");
        for (int i = 0; i < factoryList.getLength(); i++) {
            //Accessing items of factoryList
            Node node = factoryList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                //Saving the types of "factory" in factoryTypeList
                String type = element.getAttribute("type");
                factoryTypeList.add(element.getAttribute("type"));
                //Saving the lists of icons in factoryIconeList
                NodeList iconeList = element.getElementsByTagName("icone");
                ArrayList<String> tmpIcone = new ArrayList<>();
                for (int j = 0; j < iconeList.getLength(); j++) {
                    Node icone = iconeList.item(j);
                    if (icone.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) icone;
                        tmpIcone.add(e.getAttribute("path"));
                    }
                }
                factoryIconList.add(tmpIcone);

                //Saving the quantity of input in factoryInputQuantityList
                NodeList entreeList = element.getElementsByTagName("input");
                ArrayList<Integer> tmpInputQuantity = new ArrayList<>();
                for (int j = 0; j < entreeList.getLength(); j++) {
                    Node entree = entreeList.item(j);
                    if (entree.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) entree;
                        if (type.equals("warehouse")) {
                            tmpInputQuantity.add(Integer.parseInt(e.getAttribute("capacity")));
                        } else {
                            tmpInputQuantity.add(Integer.parseInt(e.getAttribute("quantity")));
                        }
                    }
                }
                factoryInputQuantityList.add(tmpInputQuantity);

                //Saving the production times in factoryProductionTimeList
                NodeList intervalProductionList = element.getElementsByTagName("interval-production");
                if (intervalProductionList.getLength() != 0) {
                    for (int j = 0; j < intervalProductionList.getLength(); j++) {
                        Node intervalProduction = intervalProductionList.item(j);
                        if (intervalProduction.getNodeType() == Node.ELEMENT_NODE) {
                            Element e = (Element) intervalProduction;
                            int tmpProductionTime = Integer.parseInt(e.getTextContent());
                            factoryProductionTimeList.add(tmpProductionTime);
                        }
                    }
                }

                //Saving the IDs in factoryIdList
                //Saving the position in factoryPositionList
                String idString = element.getAttribute("id");
                String posXString = element.getAttribute("x");
                String posYString = element.getAttribute("y");
                if (!(idString.equals("")) && !(posXString.equals("")) && !(posYString.equals(""))) {
                    int id = Integer.parseInt(element.getAttribute("id"));
                    factoryIdList.add(id);
                    ArrayList<Integer> tmpPosition = new ArrayList<>();
                    tmpPosition.add(Integer.parseInt(element.getAttribute("x")));
                    tmpPosition.add(Integer.parseInt(element.getAttribute("y")));
                    factoryPositionList.add(tmpPosition);
                }
            }
        }

        //Saving the roads in factoryRoadList
        NodeList cheminList = document.getElementsByTagName("road");
        for (int i = 0; i < cheminList.getLength(); i++) {
            Node cheminNode = cheminList.item(i);
            if (cheminNode.getNodeType() == Node.ELEMENT_NODE) {
                Element cheminElement = (Element) cheminNode;
                int de = Integer.parseInt(cheminElement.getAttribute("from"));
                int vers = Integer.parseInt(cheminElement.getAttribute("to"));
                ArrayList<Integer> tmpRoad = new ArrayList<>();
                tmpRoad.add(de);
                tmpRoad.add(vers);
                factoryRoadList.add(tmpRoad);
            }
        }
    }
}