package com.arnest.project.unify.models;


import com.arnest.constants.FrameworkConstants;
import com.arnest.project.unify.CommonPageUnify;

import static com.arnest.keywords.WebUI.*;

public class DashBoardUnify extends CommonPageUnify {
   
    public void openUrl() {
        openWebsite(FrameworkConstants.URL_UNIFY);
        System.out.println(FrameworkConstants.URL_UNIFY);
        waitForPageLoaded();
        verifyElementVisible(loginConfirmPageText, "Page is not loaded");
    }

    public void dashBoardLoginSuccessUnify(String email, String password) {
        openUrl();
        sleep(2);
        setText(inputEmail, email);
        setText(inputPassword, password);
        clickElement(loginButtonClick);
        sleep(2);
        verifyEquals(getTextElement(SuccessMesgDashboard).trim(), "Building Updates");
    }
}
