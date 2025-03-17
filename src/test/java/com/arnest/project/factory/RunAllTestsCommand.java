package com.arnest.project.factory;

import com.arnest.listeners.AnnotationTransformer;
import org.testng.TestNG;

import com.arnest.project.command.Command;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;


public class RunAllTestsCommand implements Command {

    @Override
    public void execute() {
//        System.out.println("Executing all test cases...");
//        TestNG testNG = new TestNG();
//
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        URL suiteFileURL = classLoader.getResource("resources/suites/BuildApp.xml");
//        if (suiteFileURL == null) {
//            System.out.println("Test suite file not found : " + suiteFileURL);
//            return;
//        }
//        String suiteFilePath = suiteFileURL.getPath();
//        System.out.println("path" + suiteFilePath);
//        testNG.setTestSuites(Collections.singletonList(suiteFilePath));
//        testNG.run();
        TestNG testNG = new TestNG();
        AnnotationTransformer ext = new AnnotationTransformer();
        try {

            InputStream suiteInputStream = getClass().getClassLoader().getResourceAsStream("resources/suites/BuildApp.xml");
            if (suiteInputStream == null) {
                System.out.println("Test suite file not found : " + suiteInputStream);
                return;
            }
            Path tempFile = Files.createTempFile("BuildApp", ".xml");
            tempFile.toFile().deleteOnExit();
            try (OutputStream outputStream = new FileOutputStream(tempFile.toFile())) {
                suiteInputStream.transferTo(outputStream);

            }
            System.out.println("Using extracted suite file: " + tempFile.toAbsolutePath());
            testNG.setTestSuites(Collections.singletonList(tempFile.toAbsolutePath().toString()));
            testNG.addListener(ext);
            testNG.run();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
