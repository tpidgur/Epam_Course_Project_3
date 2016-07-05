package parsers;

import generated.Plane;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import generated.Plane.Model;
import generated.Plane.Model.Price;
import generated.Plane.Model.Chars;
import generated.Plane.Model.Chars.AmmunitionLoad;
import generated.Plane.Model.Parameters;

/**
 * Streaming API for XML(STAX) parser
 * Created by Pidhurska Tetiana on 03.07.2016.
 */
public class STAXParser {
    /**
     * list of flags that enable to read/write the Characters events
     */
    boolean bModelName;
    boolean bOrigin;
    boolean bChars;
    boolean bType;
    boolean bSeatNumber;
    boolean bAmmunitionLoad;
    boolean bMissileNumber;
    boolean bRadar;
    boolean bLength;
    boolean bParameters;
    boolean bWidth;
    boolean bHeight;
    boolean bPrice;
    /**
     * new instance of plane model
     */
    Plane plane = new Plane();
    /**
     * List of models of the plane has been created
     */
    List<Plane.Model> list = plane.getModel();

    /**
     * @param file FileReader instance through which the connection to the current XML file has been established
     */
    public void parse(FileReader file) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(file);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElementCase(event);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        charactersCase(event);
                        break;
                }
            }
            System.out.println(plane);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    /**
     * based on that flag that is currently set to true modifies the value of the Plane class components
     *
     * @param event - XMLEvent that includes the value of corresponding  field  class
     */
    public void charactersCase(XMLEvent event) {
        Characters characters = event.asCharacters();
        if (bOrigin) {
            list.get(list.size() - 1).setOrigin(characters.getData());
            bOrigin = false;
        }
        if (bChars) {
            list.get(list.size() - 1).setChars(new Chars());
            bChars = false;
        }
        if (bType) {
            list.get(list.size() - 1).getChars().setType(characters.getData());
            bType = false;
        }
        if (bSeatNumber) {
            list.get(list.size() - 1).getChars().setSeatNumber(Byte.valueOf(characters.getData()));
            bSeatNumber = false;
        }
        if (bAmmunitionLoad) {
            list.get(list.size() - 1).getChars().setAmmunitionLoad(new AmmunitionLoad());
            bAmmunitionLoad = false;
        }
        if (bMissileNumber) {
            list.get(list.size() - 1).getChars().getAmmunitionLoad().setMissileNumber(Integer.valueOf(characters.getData()));
            bMissileNumber = false;
        }
        if (bRadar) {
            list.get(list.size() - 1).getChars().setRadar(Boolean.valueOf(characters.getData()));
            bRadar = false;
        }
        if (bParameters) {
            list.get(list.size() - 1).setParameters(new Parameters());
            bParameters = false;
        }
        if (bLength) {
            list.get(list.size() - 1).getParameters().setLength(Float.valueOf(characters.getData()));
            bLength = false;
        }
        if (bWidth) {
            list.get(list.size() - 1).getParameters().setWidth(Float.valueOf(characters.getData()));
            bWidth = false;
        }
        if (bHeight) {
            list.get(list.size() - 1).getParameters().setHeight(Float.valueOf(characters.getData()));
            bHeight = false;
        }
        if (bPrice) {
            list.get(list.size() - 1).getPrice().setValue(Short.valueOf(characters.getData()));
            bPrice = false;
        }
    }

    /**
     * sets the corresponding flags as true in order to modify them
     *
     * @param event -  XMLEvent that includes the value of corresponding  field  class
     */
    public void startElementCase(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        String qName = startElement.getName().getLocalPart();
        if (qName.equalsIgnoreCase("model")) {
            parseModel(startElement);
        } else if (qName.equalsIgnoreCase("origin")) {
            bOrigin = true;
        } else if (qName.equalsIgnoreCase("chars")) {
            bChars = true;
        } else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        } else if (qName.equalsIgnoreCase("seat_number")) {
            bSeatNumber = true;
        } else if (qName.equalsIgnoreCase("ammunition_load")) {
            bAmmunitionLoad = true;
        } else if (qName.equalsIgnoreCase("missile_number")) {
            bMissileNumber = true;
        } else if (qName.equalsIgnoreCase("radar")) {
            bRadar = true;
        } else if (qName.equalsIgnoreCase("parameters")) {
            bParameters = true;
        } else if (qName.equalsIgnoreCase("length")) {
            bLength = true;
        } else if (qName.equalsIgnoreCase("width")) {
            bWidth = true;
        } else if (qName.equalsIgnoreCase("height")) {
            bHeight = true;
        } else if (qName.equalsIgnoreCase("price")) {
            parsePrice(startElement);
            bPrice = true;
        }
    }

    /**
     * sets attributes to the Price object
     *
     * @param startElement - element that is reported for each Start Tag in the document
     */
    public void parsePrice(StartElement startElement) {
        Price price = new Price();
        list.get(list.size() - 1).setPrice(price);
        Iterator<Attribute> attributes = startElement.getAttributes();
        price.setUnit(attributes.next().getValue());
    }

    /**
     * sets attributes to the Model object
     *
     * @param startElement - element that is reported for each Start Tag in the document
     */
    public void parseModel(StartElement startElement) {
        Model model = new Model();
        Iterator<Attribute> attributes = startElement.getAttributes();
        model.setName(attributes.next().getValue());
        model.setModelId(attributes.next().getValue());
        list.add(model);
    }
}
