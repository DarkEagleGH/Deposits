package Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigProperties {
    private static Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        URL configProperties = ClassLoader.getSystemResource("config.properties");
        try {
            PROPERTIES.load(configProperties.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}