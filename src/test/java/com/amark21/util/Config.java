package com.amark21.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
import java.io.InputStream;

public class Config {
    private static final Logger log= LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES="config/default.properties";
    private static Properties properties;

    public static void initialize()
    {
        //load default properties
        properties=loadProperty();
        //check for override
        for(String key: properties.stringPropertyNames()) {
            if(System.getProperties().containsKey(key))
            {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        //print
        log.info("Properties");
        for(String key: properties.stringPropertyNames())
        {
            log.info("{}:{}",key,properties.getProperty(key));
        }
    }

    public static String get(String key)
    {
        return properties.getProperty(key);
    }
    public static Properties loadProperty()
    {
        Properties properties=new Properties();
        try(InputStream stream=ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
            properties.load(stream);
        }
        catch (Exception e) {
            log.error("Unable to read property file {}.", DEFAULT_PROPERTIES,e);
        }
        return properties;
    }

}
