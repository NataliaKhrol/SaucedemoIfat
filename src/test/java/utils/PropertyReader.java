package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final String PROPERTIES_PATH = "/config.properties";
    private static volatile Properties properties;

    private PropertyReader() {
    }

    public static String getProperty(String propertyName) {
        return getProperties().getProperty(propertyName);
    }

    public static Properties getProperties() {
        Properties result = properties;
        if (result == null) {
            synchronized (PropertyReader.class) {
                result = properties;
                if (result == null) {
                    result = loadProperties();
                    properties = result;
                }
            }
        }
        return result;
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream stream = PropertyReader.class.getResourceAsStream(PROPERTIES_PATH)) {
            if (stream == null) {
                throw new IllegalStateException("Properties file not found on classpath: " + PROPERTIES_PATH);
            }
            props.load(stream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load properties file: " + PROPERTIES_PATH, e);
        }
        return props;
    }
}
