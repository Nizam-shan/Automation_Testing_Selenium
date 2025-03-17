package com.arnest.project.unify.models;

import com.arnest.constants.FrameworkConstants;
import com.arnest.project.unify.CommonPageUnify;
import org.openqa.selenium.By;

import static com.arnest.keywords.WebUI.*;

public class UnifyForgetPassword extends CommonPageUnify {

    final By forgotPasswordConfirmPageText = By.xpath("//*[@id=\"__next\"]/div[2]/div/div/label[1]");
    final By clickForgotPassword = By.xpath("//*[@id=\"basic\"]/div[3]/label");
    final By inputEmail = By.xpath("//*[@id=\"basic\"]/div[1]/div/div/div/div[1]/div/input");
    final By clickSendRequestBtn = By.xpath("//*[@id=\"basic\"]/div[2]/button");
    final By successMessageForgotPassword = By.xpath("//div[text()='Email sent']");

    public void openUrl() {
        openWebsite(FrameworkConstants.URL_UNIFY);
        waitForPageLoaded();
        clickElement(clickForgotPassword);
        sleep(2);
        verifyElementVisible(forgotPasswordConfirmPageText, "Page is not loaded");
    }

    public void forgotPasswordInValidUser(String email) {
        openUrl();
        setText(inputEmail, email);
        clickElement(clickSendRequestBtn);
        verifyEquals(getTextElement(invalidEmail).trim(), "User not found");
    }
    
    public void forgotPasswordSuccess(String email) {
        openUrl();
        setText(inputEmail, email);
        clickElement(clickSendRequestBtn);
        sleep(2);
        verifyEquals(getTextElement(successMessageForgotPassword).trim(), "Email sent");
    }

}
