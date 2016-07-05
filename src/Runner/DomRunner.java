package Runner;

import parsers.DomParser;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point for the Dom parser
 * Created by Pidhurska Tetiana on 02.07.2016.
 */
public class DomRunner {
    public static void main(String[] args) {
        String fileName = "./src/settings.xml";
        runParsers(fileName);
    }

    /**
     * runs the dom parser
     * @param fileName path name in the file system by which a FileInputStream is created
     */
    private static void runParsers(String fileName) {
        try {
            DomParser dom = new DomParser();
            dom.parse(new FileInputStream(fileName));
        } catch (Exception ex) {
            Logger.getLogger(DomRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

