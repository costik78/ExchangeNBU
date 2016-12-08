package ua.ibis.nbuapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by conti on 01.12.2016.
 */
public class PropertiesValues {

    static final String propertyFileName = "config.properties";

    private static Properties prop;

    private PropertiesValues() {
    }

    private static void init() {

        prop = new Properties();

        try(InputStream in = PropertiesValues.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            prop.load(in);
        } catch (IOException e) {
            MyDialog.exceptionMessage(e);
        }
    }

    public static Properties get() {

        if (prop == null) {
            init();
        }

        return prop;
    }
}
