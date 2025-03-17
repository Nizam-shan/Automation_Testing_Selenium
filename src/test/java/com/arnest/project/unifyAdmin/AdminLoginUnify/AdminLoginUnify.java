//package com.arnest.projects.unifyAdmin.AdminLoginUnify;
//
//import com.arnest.constants.FrameworkConstants;
//import com.arnest.keywords.WebUI;
//import com.arnest.projects.unify.CommonPageUnify;
//import org.openqa.selenium.By;
//
//import static com.arnest.keywords.WebUI.*;
//
//public class AdminLoginUnify extends CommonPageUnify {
//
//
//    final By SuccessMesgDashboard = By.xpath("//label[text()='Building without lease']");
//
//    public void openUrl() {
//        WebUI.openWebsite(FrameworkConstants.URL_UNIFY);
//        WebUI.waitForPageLoaded();
//        WebUI.verifyElementVisible(loginConfirmPageText, "Page is not loaded");
//    }
//
//    public void adminLoginSuccessUnify(String email, String password) {
//        openUrl();
//        sleep(2);
//        WebUI.setText(inputEmail, email);
//        WebUI.setText(inputPassword, password);
//        WebUI.clickElement(loginButtonClick);
//        WebUI.waitForPageLoaded();
//        WebUI.sleep(4);
//        WebUI.verifyEquals(getTextElement(SuccessMesgDashboard).trim(), "Building without lease");
//    }
//}


package com.arnest.project.unifyAdmin.AdminLoginUnify;

import com.arnest.constants.FrameworkConstants;
import com.arnest.project.unify.CommonPageUnify;
import org.openqa.selenium.By;

import static com.arnest.keywords.WebUI.*;

public class AdminLoginUnify extends CommonPageUnify {

    final By messageRequiredEmail = By.xpath("//div[text()=\"Please input your email!\"]");
    final By messageRequiredPassword = By.xpath("//label[text()='Invalid email or password']");
    final By messagePasswordNotExist = By.xpath("//div[text()='Please input your password!']");

    //open the URL
    public void openUrl() {
        openWebsite(FrameworkConstants.URL_UNIFY);
        System.out.println(FrameworkConstants.URL_UNIFY);
        waitForPageLoaded();
        verifyElementVisible(loginConfirmPageText, "Page is not loaded");
    }

    // both null case
    public void loginFailedWithEmailNull() {
        openUrl();
        sleep(2);
        clickElement(loginButtonClick);
        sleep(1);
        verifyEquals(getTextElement(messageRequiredEmail).trim(), "Please input your email!");
    }

    // email not exist
    public void loginFailedWithEmailDoesNotExist(String password) {
        openUrl();
        sleep(2);

        setText(inputPassword, password);
        clickElement(loginButtonClick);
        sleep(1);
        verifyEquals(getTextElement(messageRequiredEmail).trim(), "Please input your email!");
    }

    // password not exist
    public void loginFailedWithPasswordDoesNotExist(String email) {
        openUrl();
        sleep(2);
        setText(inputEmail, email);

        clickElement(loginButtonClick);
        sleep(1);
        verifyEquals(getTextElement(messagePasswordNotExist).trim(), "Please input your password!");
    }

    // password Wrong
    public void loginFailedWithWrongPassword(String email, String password) {
        openUrl();
        sleep(2);
        setText(inputEmail, email);
        setText(inputPassword, password);
        clickElement(loginButtonClick);
        sleep(2);
        verifyEquals(getTextElement(messageRequiredPassword).trim(), "Invalid email or password");
    }

    // invalid email address
    public void loginWithInvalidEmail(String email, String password) {
        openUrl();
        sleep(1);
        setText(inputEmail, email);
        setText(inputPassword, password);
        clickElement(loginButtonClick);
        sleep(2);
        verifyEquals(getTextElement(invalidEmail).trim(), "User not found");

    }

    public void loginSuccessUnify(String email, String password) {
        openUrl();
        sleep(2);
        setText(inputEmail, email);
        setText(inputPassword, password);
        clickElement(loginButtonClick);
        sleep(2);
        verifyEquals(getTextElement(SuccessMesgDashboard).trim(), "Building Updates");
    }
}

