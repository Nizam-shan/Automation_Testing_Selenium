/// /package com.arnest.project.factory;
/// /
/// /import com.arnest.project.command.Command;
/// /import org.testng.TestNG;
/// /
/// /import java.io.File;
/// /import java.io.FileOutputStream;
/// /import java.io.InputStream;
/// /import java.net.URL;
/// /import java.util.Collections;
/// /import java.util.HashMap;
/// /import java.util.Map;
/// /
/// /public class RunTestGroupCommand implements Command {
/// /    private static final Map<String, String> GROUP_CASE_MAP = new HashMap<>();
/// /
/// /    static {
/// /        loadGroupSuites();
/// /    }
/// /
/// /    String testcase;
/// /    String group;
/// /
/// /    public RunTestGroupCommand(String testcase, String group) {
/// /        this.testcase = testcase;
/// /        this.group = group;
/// /    }
/// /
/// // /    @Override
/// // /    public void execute() {
/// // /        System.out.println("Testing with regards to group");
/// // /        String resourcePath = GROUP_CASE_MAP.get(testcase + ":" + group);
/// // /        if (resourcePath == null) {
/// // /            System.out.println("No test group found" + testcase);
/// // /            return;
/// // /        }
/// // /        System.out.println("Testing" + testcase);
/// // /        URL suiteURL = getClass().getClassLoader().getResource(resourcePath);
/// // /        if (suiteURL == null) {
/// // /            System.out.println("No test group found at " + resourcePath);
/// // /            return;
/// // /        }
/// // /        System.out.println("executing test case" + testcase + group);
/// // /        TestNG testNG = new TestNG();
/// // /        testNG.setTestSuites(Collections.singletonList(suiteURL.getPath()));
/// // /        testNG.run();
/// // /    }
/// /
/// /    @Override
/// /    public void execute() {
/// /        System.out.println("Testing with regards to group");
/// /        String resourcePath = GROUP_CASE_MAP.get(testcase + ":" + group);
/// /        if (resourcePath == null) {
/// /            System.out.println("No test group found for " + testcase);
/// /            return;
/// /        }
/// /        System.out.println("Testing " + testcase);
/// /
/// /        // Load resource from JAR
/// /        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
/// /            if (inputStream == null) {
/// /                System.out.println("No test suite found at: " + resourcePath);
/// /                return;
/// /            }
/// /
/// /            // Create a temporary file to store the extracted XML
/// /            File tempFile = File.createTempFile("testng-suite", ".xml");
/// /            tempFile.deleteOnExit();
/// /
/// /            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
/// /                byte[] buffer = new byte[1024];
/// /                int bytesRead;
/// /                while ((bytesRead = inputStream.read(buffer)) != -1) {
/// /                    outputStream.write(buffer, 0, bytesRead);
/// /                }
/// /            }
/// /
/// /            System.out.println("Executing test case: " + testcase + " " + group);
/// /            TestNG testNG = new TestNG();
/// /            testNG.setTestSuites(Collections.singletonList(tempFile.getAbsolutePath()));
/// /            testNG.run();
/// /
/// /        } catch (Exception e) {
/// /            System.out.println("Error loading test suite: " + e.getMessage());
/// /            e.printStackTrace();
/// /        }
/// /    }
/// /
/// /    private static void loadGroupSuites() {
/// /        try {
/// /            GROUP_CASE_MAP.put("ForgotPasswordUnify:regression", "resources/suites/UNIFY/groups/REGRESSIONSuite.xml");
/// /            GROUP_CASE_MAP.put("ForgotPasswordUnify:sanity", "resources/suites/UNIFY/groups/SANITYSuite.xml");
/// /            GROUP_CASE_MAP.put("ForgotPasswordUnify:smoke", "resources/suites/UNIFY/groups/SMOKESuite.xml");
/// /
/// /
/// /            GROUP_CASE_MAP.put("LoginTestUnify:regression", "resources/suites/UNIFY/groups/REGRESSIONSuite.xml");
/// /            GROUP_CASE_MAP.put("LoginTestUnify:sanity", "resources/suites/UNIFY/groups/SANITYSuite.xml");
/// /            GROUP_CASE_MAP.put("LoginTestUnify:smoke", "resources/suites/UNIFY/groups/SMOKESuite.xml");
/// /
/// /            GROUP_CASE_MAP.put("AdminDashBoard:regression", "resources/suites/AdminUnify/groups/REGRESSIONSuite.xml");
/// /            GROUP_CASE_MAP.put("AdminDashBoard:sanity", "resources/suites/AdminUnify/groups/SANITYSuite.xml");
/// /            GROUP_CASE_MAP.put("AdminDashBoard:smoke", "resources/suites/AdminUnify/groups/SMOKESuite.xml");
/// /
/// /
/// /            GROUP_CASE_MAP.put("AdminLoginTestUnify:regression", "resources/suites/AdminUnify/groups/REGRESSIONSuite.xml");
/// /            GROUP_CASE_MAP.put("AdminLoginTestUnify:sanity", "resources/suites/AdminUnify/groups/SANITYSuite.xml");
/// /            GROUP_CASE_MAP.put("AdminLoginTestUnify:smoke", "resources/suites/AdminUnify/groups/SMOKESuite.xml");
/// /
/// /
/// /            GROUP_CASE_MAP.put("CreateBuilding:regression", "resources/suites/AdminUnify/groups/REGRESSIONSuite.xml");
/// /            GROUP_CASE_MAP.put("CreateBuilding:sanity", "resources/suites/AdminUnify/groups/SANITYSuite.xml");
/// /            GROUP_CASE_MAP.put("CreateBuilding:smoke", "resources/suites/AdminUnify/groups/SMOKESuite.xml");
/// /        } catch (Exception e) {
/// /            System.out.println("Error" + e.getMessage());
/// /            throw new RuntimeException(e);
/// /        }
/// /    }
/// /}
//

package com.arnest.project.factory;

import com.arnest.listeners.AnnotationTransformer;
import com.arnest.project.command.Command;
import org.testng.TestNG;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RunTestGroupCommand implements Command {
    private static final Map<String, String> GROUP_CASE_MAP = new HashMap<>();

    static {
        loadGroupSuites();
    }

    private final String testcase;
    private final String group;

    public RunTestGroupCommand(String testcase, String group) {

        this.testcase = testcase;
        this.group = group.toUpperCase();
    }

    @Override
    public void execute() {
        System.out.println("Testing with regards to group: " + group);
        String resourcePath = GROUP_CASE_MAP.get(testcase + ":" + group);
        System.out.println("Available test groups: " + GROUP_CASE_MAP);
        System.out.println("Resource Path: " + resourcePath);
        if (resourcePath == null) {
            System.out.println("No test group found for " + testcase + " in group " + group);
            return;
        }

        System.out.println("Executing test case: " + testcase + " with group: " + group);

        // Load the test suite XML from resources
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                System.out.println("No test suite found at: " + resourcePath);
                return;
            }

            // Create a temporary file to store the extracted XML
            File tempFile = File.createTempFile("testng-suite", ".xml");
            tempFile.deleteOnExit();

            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Configure and run TestNG
            TestNG testNG = new TestNG();
            AnnotationTransformer ext = new AnnotationTransformer();
            testNG.setTestSuites(Collections.singletonList(tempFile.getAbsolutePath()));
            testNG.setGroups(group);
            testNG.addListener(ext);
            testNG.run();

        } catch (Exception e) {
            System.out.println("Error loading test suite: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadGroupSuites() {
        try {
            GROUP_CASE_MAP.put("ForgotPasswordUnify:REGRESSION", "resources/suites/UNIFY/ForgotPasswordUnify.xml");
            GROUP_CASE_MAP.put("ForgotPasswordUnify:SANITY", "resources/suites/UNIFY/ForgotPasswordUnify.xml");
            GROUP_CASE_MAP.put("ForgotPasswordUnify:SMOKE", "resources/suites/UNIFY/ForgotPasswordUnify.xml");

            GROUP_CASE_MAP.put("LoginTestUnify:REGRESSION", "resources/suites/UNIFY/LoginTestUnify.xml");
            GROUP_CASE_MAP.put("LoginTestUnify:SANITY", "resources/suites/UNIFY/LoginTestUnify.xml");
            GROUP_CASE_MAP.put("LoginTestUnify:SMOKE", "resources/suites/UNIFY/LoginTestUnify.xml");

            GROUP_CASE_MAP.put("AdminDashBoard:REGRESSION", "resources/suites/AdminUnify/AdminDashBoard.xml");
            GROUP_CASE_MAP.put("AdminDashBoard:SANITY", "resources/suites/AdminUnify/AdminDashBoard.xml");
            GROUP_CASE_MAP.put("AdminDashBoard:SMOKE", "resources/suites/AdminUnify/AdminDashBoard.xml");

            GROUP_CASE_MAP.put("AdminLoginTestUnify:REGRESSION", "resources/suites/AdminUnify/AdminLoginTestUnify.xml");
            GROUP_CASE_MAP.put("AdminLoginTestUnify:SANITY", "resources/suites/AdminUnify/AdminLoginTestUnify.xml");
            GROUP_CASE_MAP.put("AdminLoginTestUnify:SMOKE", "resources/suites/AdminUnify/AdminLoginTestUnify.xml");

            GROUP_CASE_MAP.put("CreateBuilding:REGRESSION", "resources/suites/AdminUnify/CreateBuilding.xml");
            GROUP_CASE_MAP.put("CreateBuilding:SANITY", "resources/suites/AdminUnify/CreateBuilding.xml");
            GROUP_CASE_MAP.put("CreateBuilding:SMOKE", "resources/suites/AdminUnify/CreateBuilding.xml");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}


