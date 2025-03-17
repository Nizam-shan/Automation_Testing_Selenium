//package com.arnest.project;
//
//import com.arnest.listeners.AnnotationTransformer;
//
//import com.arnest.project.unify.testcases.ForgotPasswordUnify;
//import com.arnest.project.unifyAdmin.testCases.CreateBuildingAdminUnifyTest;
//
//import java.io.IOException;
//
//import org.testng.TestNG;
//
//
//public class TestRunner {
//    static TestNG testNG;
//
//    public static void main(String[] args) throws IOException {
////		TestListener testListener = new TestListener();
//        AnnotationTransformer ext = new AnnotationTransformer();
//        testNG = new TestNG();
//        testNG.setTestClasses(new Class[]{ForgotPasswordUnify.class});
////		testNG.addListener(testListener);
//        testNG.addListener(ext);
////        testNG.addListener(new AnnotationTransformer());
//        testNG.run();
//    }
//}
package com.arnest.project;
import com.arnest.project.command.Command;
import com.arnest.project.factory.CommandFactory;

public class TestRunner {
    public static void main(String[] args) {
        Command command = CommandFactory.getCommand(args);
        if (command != null) {
            command.execute();
        }
    }
}