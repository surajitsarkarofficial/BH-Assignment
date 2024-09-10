package manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    public static Object getProperty(String propName){
        try {
            String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "config.properties";
            FileInputStream fis = new FileInputStream(new File(filePath));
            Properties properties = new Properties();
            properties.load(fis);
            return properties.getProperty(propName);
        }catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("**********Config File not found at specified location .********** ");
        }
        return null;
    }
}
