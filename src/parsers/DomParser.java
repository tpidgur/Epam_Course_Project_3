/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import generated.ObjectFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import generated.Plane.Model.Price;
import generated.Plane;
import generated.Plane.Model.Chars;
import generated.Plane.Model.Chars.AmmunitionLoad;
import generated.Plane.Model.Parameters;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Document Object Model (DOM) parser for XML
 *
 * @author Pidhurska Tetiana
 */
public class DomParser {
    /**
     * instance of Plane has been created
     */
    Plane plane = new ObjectFactory().createPlane();
    /**
     * List of models of the plane has been created
     */
    List<Plane.Model> list = plane.getModel();

    /**
     *
     * @param in InputSream instance through which the connection to the current XML file has been established
     * @throws Exception
     */
    public void parse(InputStream in) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(in);
        //root element==plane
        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            // if it is a node element
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Plane.Model model = new Plane.Model();
                list.add(model);
                Element modelXML = (Element) node;
                model.setName(modelXML.getAttribute("name"));
                model.setModelId(modelXML.getAttribute("modelId"));
                parseModel(modelXML);
            }
        }
        System.out.println(plane);
    }

    /**
     *
     * @param modelXML
     */
    private void parseModel(Element modelXML) {
        NodeList nodes = modelXML.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("origin")) {
                    list.get(list.size() - 1).setOrigin(node.getFirstChild().getNodeValue());
                }
                if (node.getNodeName().equals("parameters")) {
                    parseParameters((Element) node);
                }
                if (node.getNodeName().equals("price")) {
                    parsePrice((Element) node);
                }
                if (node.getNodeName().equals("chars")) {
                    parseChars((Element) node);
                }
            }
        }
    }

    private void parseChars(Element chars) {
        Chars ch = new Chars();
        list.get(list.size() - 1).setChars(ch);
        NodeList nodes = chars.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("type")) {
                    ch.setType(node.getFirstChild().getNodeValue());
                }
                if (node.getNodeName().equals("seat_number")) {
                    ch.setSeatNumber(Byte.valueOf(node.getFirstChild().getNodeValue()));
                }
                if (node.getNodeName().equals("ammunition_load")) {
                    parseAmmunitionLoad((Element) node);
                }
                if (node.getNodeName().equals("radar")) {
                    ch.setRadar(Boolean.valueOf(node.getFirstChild().getNodeValue()));
                }
            }
        }
    }

    private void parseAmmunitionLoad(Element load) {
        AmmunitionLoad ammunitionLoad = new AmmunitionLoad();
        list.get(list.size() - 1).getChars().setAmmunitionLoad(ammunitionLoad);
        NodeList nodes = load.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("missile_number")) {
                    ammunitionLoad.setMissileNumber(Integer.valueOf(node.getFirstChild().getNodeValue()));
                }
            }
        }
    }

    private void parsePrice(Element price) {
        Price pr = new Price();
        list.get(list.size() - 1).setPrice(pr);
        pr.setUnit(price.getAttribute("unit"));
        pr.setValue(Short.valueOf(price.getFirstChild().getNodeValue()));

    }

    private void parseParameters(Element parameters) {
        Parameters pars = new Parameters();
        list.get(list.size() - 1).setParameters(pars);
        NodeList nodes = parameters.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("length")) {
                    pars.setLength(Float.valueOf(node.getFirstChild().getNodeValue()));
                }
                if (node.getNodeName().equals("width")) {
                    pars.setWidth(Float.valueOf(node.getFirstChild().getNodeValue()));
                }
                if (node.getNodeName().equals("height")) {
                    pars.setHeight(Float.valueOf(node.getFirstChild().getNodeValue()));
                }
            }
        }
    }


}
