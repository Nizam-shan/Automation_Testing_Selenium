package com.arnest.project.unify.loginUnify;

import com.arnest.constants.FrameworkConstants;
import com.arnest.keywords.WebUI;
import com.arnest.project.unify.CommonPageUnify;
import org.openqa.selenium.By;

import static com.arnest.keywords.WebUI.*;

public class LoginPageUnify extends CommonPageUnify {

    final By SuccessMesgDashboard = By.xpath("//label[@class=\"clientUserNameTitle cursorPointer\"]");

    public void openUrl() {
        WebUI.openWebsite(FrameworkConstants.URL_UNIFY);
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(loginConfirmPageText, "Page is not loaded");
    }

    public void adminLoginSuccessUnify(String email, String password) {
        openUrl();
        sleep(2);
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(loginButtonClick);
        WebUI.waitForPageLoaded();
        WebUI.sleep(4);
        WebUI.verifyEquals(getTextElement(SuccessMesgDashboard), "BUILDING WITHOUT LEASE");
    }
}
