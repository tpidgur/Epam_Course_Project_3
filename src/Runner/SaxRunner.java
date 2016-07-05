package Runner;

import parsers.SAXParser;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point for the Sax runner
 * Created by Pidhurska Tetiana on 02.07.2016.
 */
public class SaxRunner {
    public static void main(String[] args) {
        try {
            String fileName = "./src/settings.xml";
            new SAXParser().parse( new FileInputStream(fileName) );
        } catch (Exception ex) {
            Logger.getLogger(SaxRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
