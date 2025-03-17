


package com.arnest.helpers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class JsonHelpers {
    private DocumentContext jsonContext;

    public void setJsonFile(String jsonPath) {
        try {
            String jsonContent = readJsonContent(jsonPath);
            if (jsonContent == null || jsonContent.isEmpty()) {
                throw new FileNotFoundException("JSON content is empty or not found: " + jsonPath);
            }
            jsonContext = JsonPath.parse(jsonContent);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + jsonPath, e);
        }
    }

    public Object getData(String key) {
        if (jsonContext == null) {
            throw new IllegalStateException("JSON context is not initialized. Call setJsonFile() first.");
        }
        return jsonContext.read(key);
    }

    public Object getEntairJson() {
        if (jsonContext == null) {
            throw new IllegalStateException("JSON context is not initialized. Call setJsonFile() first.");
        }
        return jsonContext.json();
    }


    private String readJsonContent(String jsonPath) throws IOException {
        String basePath;
        ClassLoader classLoader = JsonHelpers.class.getClassLoader();

        // Step 1: Check external directory (e.g., "dataFile/")
        File externalResources = new File("dataFile/");
        System.out.println("Checking external path: " + externalResources.getAbsolutePath());

        if (externalResources.exists()) {
            basePath = externalResources.getAbsolutePath() + "/";
            System.out.println("Loaded from external source: " + basePath);
        } else {
            // Step 2: Check if running inside a JAR (Classpath)
            URL resourceUrl = classLoader.getResource("resources/");
            if (resourceUrl != null) {
                basePath = "resources/";
                System.out.println("Loaded from JAR resources: " + basePath);
            } else {

                basePath = "src/test/resources/";
                System.out.println("Loaded from IDE: " + basePath);
            }
        }


        File file = new File(basePath + jsonPath);
        System.out.println("file" + file);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }
        }

        // Try to load the file as a resource from the classpath
        InputStream inputStream = classLoader.getResourceAsStream(basePath + jsonPath);
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }
        }

        throw new RuntimeException("Error reading JSON file: " + jsonPath);
    }

}