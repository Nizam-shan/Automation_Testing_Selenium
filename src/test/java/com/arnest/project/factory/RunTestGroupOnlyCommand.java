package com.arnest.project.factory;

import com.arnest.listeners.AnnotationTransformer;
import com.arnest.project.command.Command;
import org.testng.TestNG;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RunTestGroupOnlyCommand implements Command {
    private final String group;

    public RunTestGroupOnlyCommand(String group) {
        System.out.println("Group" + group);
        this.group = group.toUpperCase();
    }

    @Override
    public void execute() {
        List<String> suiteFiles = getSuiteFilePath(group);
        if (suiteFiles.isEmpty()) {
            System.out.println("group not found");
            return;
        }
        TestNG testNG = new TestNG();
        AnnotationTransformer ext = new AnnotationTransformer();
        testNG.setTestSuites(suiteFiles);
        testNG.addListener(ext);
        testNG.run();
    }

    private List<String> getSuiteFilePath(String group) {
        System.out.println("group" + group);
        List<String> suiteFiles = new ArrayList<>();
//        String basePath = "resources/suites/";
        String adminPath = "resources/suites/AdminUnify/groups/" + group + "Suite.xml";
        String unifyPath = "resources/suites/UNIFY/groups/" + group + "Suite.xml";
//        suiteFiles.add(adminPath);
//        suiteFiles.add(unifyPath);
        getInputStreamPath(suiteFiles, adminPath);
        getInputStreamPath(suiteFiles, unifyPath);
        return suiteFiles;
    }

    private void getInputStreamPath(List<String> suiteFiles, String path) {
        InputStream resources = getClass().getClassLoader().getResourceAsStream(path);
        if (resources == null) {
            System.out.println("Path not founf" + path);
            return;
        }
        if (resources != null) {
            try {
                File tempFile = File.createTempFile("tempSuite", ".xml");
                tempFile.deleteOnExit();
                try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = resources.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                suiteFiles.add(tempFile.getAbsolutePath());

            } catch (Exception e) {
                System.err.println("Error writing temp file for: " + path);
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Suite file not found: " + path);
        }

    }
}
