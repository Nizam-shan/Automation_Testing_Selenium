//package com.arnest.project.factory;
//
//import com.arnest.project.command.Command;
//import org.testng.TestNG;
//
//import java.io.File;
//import java.net.JarURLConnection;
//import java.net.URL;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.jar.JarFile;
//
//public class RunTestCaseCommand implements Command {
//    private static final Map<String, String> TEST_CASE_MAP = new HashMap<>();
//
//    static {
//        loadTestSuites();
//    }
//
//    private final String testCase;
//
//    public RunTestCaseCommand(String testCase) {
//        this.testCase = testCase;
//    }
//
//    @Override
//    public void execute() {
//        System.out.println("Testing with regards to test case");
//        String suitePath = TEST_CASE_MAP.get(testCase);
//        if (suitePath == null) {
//            System.out.println("No test case found" + testCase);
//            return;
//        }
//        System.out.println("Testing" + testCase);
//        TestNG testNG = new TestNG();
//        testNG.setTestSuites(Collections.singletonList(suitePath));
//        testNG.run();
//
//    }
//
//    private static void loadTestSuites() {
//        try {
//
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            URL resources = classLoader.getResource("resources/suites");
//            File testSuiteDir = new File(resources.toURI());
//            System.out.println("resou : " + resources);
//
//
//            if (resources == null) {
//                System.out.println("Test suite filder is empty or not found" + resources);
//                return;
//            }
//
//
//            if (!testSuiteDir.exists() || !testSuiteDir.isDirectory()) {
//                System.out.println("Test suite is invalid");
//                return;
//            }
//            for (File file : testSuiteDir.listFiles()) {
//                if (file.isFile() && file.getName().endsWith(".xml")) {
//                    String testName = file.getName().replace(".xml", "");
//                    TEST_CASE_MAP.put(testName, file.getAbsolutePath());
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//}

//
package com.arnest.project.factory;

import com.arnest.listeners.AnnotationTransformer;
import com.arnest.listeners.TestListener;
import com.arnest.project.command.Command;
import org.testng.TestNG;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class RunTestCaseCommand implements Command {
    private static final Map<String, String> TEST_CASE_MAP = new HashMap<>();
    private static final String TEST_SUITE_FOLDER = "resources/suites";

    static {
        loadTestSuites();
    }

    private final String testCase;

    public RunTestCaseCommand(String testCase) {
        this.testCase = testCase;
    }

    @Override
    public void execute() {
        System.out.println("Executing test case: " + testCase);
        String suitePath = TEST_CASE_MAP.get(testCase);

        if (suitePath == null) {
            System.out.println("No test case found: " + testCase);
            return;
        }

        System.out.println("Running test case: " + testCase);
        TestNG testNG = new TestNG();
        AnnotationTransformer ext = new AnnotationTransformer();
        TestListener testListener = new TestListener();
        testNG.setTestSuites(Collections.singletonList(suitePath));
        testNG.addListener(ext);
        testNG.addListener(testListener);
        testNG.run();
    }

    private static void loadTestSuites() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resourceUrl = classLoader.getResource(TEST_SUITE_FOLDER);

            if (resourceUrl == null) {
                System.out.println("Test suite folder is empty or not found: " + TEST_SUITE_FOLDER);
                return;
            }

            System.out.println("Loading test suites from: " + resourceUrl);

            if ("file".equals(resourceUrl.getProtocol())) {
                File testSuiteDir = new File(resourceUrl.toURI());
                loadTestFiles(testSuiteDir);
            } else if ("jar".equals(resourceUrl.getProtocol())) {
                loadJarTestFiles(resourceUrl);
            }
        } catch (Exception e) {
            System.err.println("Error loading test suites: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void loadTestFiles(File testSuiteDir) {
        if (!testSuiteDir.exists() || !testSuiteDir.isDirectory()) {
            System.out.println("Invalid test suite directory: " + testSuiteDir.getAbsolutePath());
            return;
        }

        for (File file : testSuiteDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".xml")) {
                String testName = file.getName().replace(".xml", "");
                TEST_CASE_MAP.put(testName, file.getAbsolutePath());
            }
        }
    }

    private static void loadJarTestFiles(URL jarResource) {
        try {
            JarURLConnection jarURLConnection = (JarURLConnection) jarResource.openConnection();
            JarFile jarFile = jarURLConnection.getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().startsWith(TEST_SUITE_FOLDER) && entry.getName().endsWith(".xml")) {
                    String testName = entry.getName().substring(entry.getName().lastIndexOf("/") + 1).replace(".xml", "");

                    // Extract to temp file for TestNG compatibility
                    String tempFilePath = extractJarFileToTemp(jarFile, entry);
                    TEST_CASE_MAP.put(testName, tempFilePath);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading test cases from JAR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String extractJarFileToTemp(JarFile jarFile, JarEntry entry) throws IOException {
        InputStream inputStream = jarFile.getInputStream(entry);
        Path tempFile = Files.createTempFile("TestSuite-", ".xml");
        tempFile.toFile().deleteOnExit();

        try (OutputStream outputStream = new FileOutputStream(tempFile.toFile())) {
            inputStream.transferTo(outputStream);
        }

        System.out.println("Extracted JAR test suite: " + tempFile.toAbsolutePath());
        return tempFile.toAbsolutePath().toString();
    }
}

