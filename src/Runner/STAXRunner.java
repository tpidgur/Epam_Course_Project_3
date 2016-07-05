package Runner;


import parsers.STAXParser;

import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point for the STAX runner
 * Created by Pidhurska Tetiana on 03.07.2016.
 */
public class STAXRunner {
    public static void main(String[] args) {
        String fileName = "./src/settings.xml";
        runParsers(fileName);
    }

    /**
     * runs the STAX parser
     *
     * @param fileName path name in the file system by which a FileInputStream is created
     */
    private static void runParsers(String fileName) {
        try {
            STAXParser stax = new STAXParser();
            stax.parse(new FileReader(fileName));
        } catch (Exception ex) {
            Logger.getLogger(DomRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
