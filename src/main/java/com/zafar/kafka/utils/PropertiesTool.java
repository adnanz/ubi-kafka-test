package com.zafar.kafka.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author: Adnan Zafar
 * Date: Nov 27, 2019
 */
public class PropertiesTool {
    private static Properties properties;

    static {
        try (InputStream input = PropertiesTool.class.getClassLoader().getResourceAsStream("application.properties")) {

            properties = new Properties();

            if (input == null) {
                throw new RuntimeException("Unable to find property file");
            }

            //load a properties file from class path
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getParameter(String key) {
        return properties.getProperty(key);
    }
}
