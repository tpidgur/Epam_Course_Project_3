/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runner;


import generated.Plane;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Entry point for the JAXB
 *
 * @author Pidhurska Tetiana
 */
public class JAXBRunner {
    public static void main(String[] args) throws Exception {
        String filename = "./src/settings.xml";
        // create JAXBContext which will be used to create a Binder
        JAXBContext context = JAXBContext.newInstance(Plane.class);
        // unmarshaller obj to convert xml data to java content tree
        Unmarshaller unm = context.createUnmarshaller();
        // unmarshaller xml data to java content tree
        Plane plane = (Plane) unm.unmarshal(new File(filename));
        System.out.println(plane);
    }
}
