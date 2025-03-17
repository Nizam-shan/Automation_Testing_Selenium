

package com.arnest.helpers;

import com.arnest.utils.LanguageUtils;
import com.arnest.utils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelpers {

    private static final Properties properties = new Properties();
    private static final String TEST_RESOURCE_PATH = "src/test/";

    static {
        loadAllFiles();
    }

    private PropertiesHelpers() {
        // Private constructor to prevent instantiation
    }

    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();

        String basePath;

        //  loading  resource file
        URL resourceUrl = PropertiesHelpers.class.getClassLoader().getResource("config/");
        System.out.println("resuircxeurl" + resourceUrl);
        if (resourceUrl != null) {
            basePath = "config/";
            System.out.println("Loaded from internal resources: " + basePath);
        } else {
            //  external location
            File externalResources = new File("dataFile/config/");
            if (externalResources.exists()) {
                basePath = externalResources.getAbsolutePath() + "/";
                System.out.println("Loaded from external source: " + basePath);
            } else {
                basePath = "resources/config/";
                System.out.println("Loaded from jar: " + basePath);
            }
        }
        System.out.println("Final path: " + basePath);
        files.add(basePath + "config.properties");
        files.add(basePath + "data.properties");


        try {
            System.out.println("property");
            properties.clear();


            for (String f : files) {
                InputStream inputStream = null;
                File file = new File(f);
                if (file.exists()) {
                    inputStream = new FileInputStream(f);
                    System.out.println("from exter" + f);
                } else {
                    inputStream = PropertiesHelpers.class.getClassLoader().getResourceAsStream(f);
                    if (inputStream != null) {
                        System.out.println("Loaded from classpath: " + f);
                    }
//                        inputStream = new FileInputStream(f);
                }

                if (inputStream != null) {
                    Properties tempProp = new Properties();
                    tempProp.load(inputStream);
                    properties.putAll(tempProp);
                    System.out.println("Loaded properties file: " + f);
                } else {
                    LogUtils.warn("Warning !! Cannot find property file: " + f);
                }


            }
            LogUtils.info("Loaded all properties files.");
            return properties;

        } catch (IOException e) {
            LogUtils.warn("Warning !! Cannot load all files.");
            return new Properties();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getValue(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            LogUtils.warn("Property '" + key + "' not found in config.properties. Using default value.");
            return "";
        }
        return LanguageUtils.convertCharset_ISO_8859_1_To_UTF8(value);
    }

}
