/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;


import java.io.InputStream;
import java.util.List;

import generated.Plane;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author Pifhurska Tetiana
 */

public class SAXParser {

    Plane plane = new Plane();
    String temp;
    boolean isFuel = false;
    List<Plane.Model> list = plane.getModel();


    class SaxHandler implements ContentHandler {

        @Override
        public void setDocumentLocator(Locator locator) {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void startDocument() throws SAXException {
            //  throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void endDocument() throws SAXException {
            //  throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            //  throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {//можем парсить атрибуты
            if ("model".equals(localName)) {
                Plane.Model model = new Plane.Model();
                model.setName(atts.getValue("name"));
                model.setModelId(atts.getValue("modelId"));
                plane.getModel().add(model);
            } else if (localName.equals("price")) {
                Plane.Model.Price pr = new Plane.Model.Price();
                pr.setUnit(atts.getValue("unit"));
                list.get(list.size() - 1).setPrice(pr);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
//        List<Plane.Model> list = plane.getModel();
            if (localName.equals("origin")) {
                list.get(list.size() - 1).setOrigin(temp);
            } else if (localName.equals("price")) {
                list.get(list.size() - 1).getPrice().setValue(Short.valueOf(temp));
            } else if (localName.equals("type")) {
                if (list.get(list.size() - 1).getChars() == null) {
                    Plane.Model.Chars chars = new Plane.Model.Chars();
                    list.get(list.size() - 1).setChars(chars);
                }
                list.get(list.size() - 1).getChars().setType(temp);
            } else if (localName.equals("seat_number")) {
                if (list.get(list.size() - 1).getChars() == null) {
                    Plane.Model.Chars chars = new Plane.Model.Chars();
                    list.get(list.size() - 1).setChars(chars);
                }
                list.get(list.size() - 1).getChars().setSeatNumber(Byte.valueOf(temp));
            } else if (localName.equals("missile_number")) {
                if (list.get(list.size() - 1).getChars() == null) {
                    Plane.Model.Chars chars = new Plane.Model.Chars();
                    list.get(list.size() - 1).setChars(chars);
                }
                list.get(list.size() - 1).getChars().setAmmunitionLoad(new Plane.Model.Chars.AmmunitionLoad());
                list.get(list.size() - 1).getChars().getAmmunitionLoad().setMissileNumber(Integer.valueOf(temp));

            } else if (localName.equals("radar")) {
                if (list.get(list.size() - 1).getChars() == null) {
                    Plane.Model.Chars chars = new Plane.Model.Chars();
                    list.get(list.size() - 1).setChars(chars);
                }
                list.get(list.size() - 1).getChars().setRadar(Boolean.valueOf(temp));
            } else if (localName.equals("length")) {
                if (list.get(list.size() - 1).getParameters() == null) {
                    Plane.Model.Parameters parameters = new Plane.Model.Parameters();
                    list.get(list.size() - 1).setParameters(parameters);
                }
                list.get(list.size() - 1).getParameters().setLength(Float.valueOf(temp));
            } else if (localName.equals("width")) {
                if (list.get(list.size() - 1).getParameters() == null) {
                    Plane.Model.Parameters parameters = new Plane.Model.Parameters();
                    list.get(list.size() - 1).setParameters(parameters);
                }
                list.get(list.size() - 1).getParameters().setWidth(Float.valueOf(temp));
            } else if (localName.equals("height")) {
                if (list.get(list.size() - 1).getParameters() == null) {
                    Plane.Model.Parameters parameters = new Plane.Model.Parameters();
                    list.get(list.size() - 1).setParameters(parameters);
                }
                list.get(list.size() - 1).getParameters().setHeight(Float.valueOf(temp));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {//щоб дістати значення елементів
            temp = new String(ch, start, length);

        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void skippedEntity(String name) throws SAXException {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public void parse(InputStream in) throws Exception {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        SaxHandler contentHandler = new SaxHandler();
        reader.setContentHandler(contentHandler);
        reader.parse(new InputSource(in));
        System.out.println(plane);
    }
}
