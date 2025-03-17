package com.arnest.project.unify;

import com.arnest.project.unify.loginUnify.LoginPageUnify;
import com.arnest.project.unify.models.DashBoardUnify;
import com.arnest.project.unify.models.UnifyForgetPassword;
import com.arnest.project.unifyAdmin.AdminLoginUnify.AdminLoginUnify;
import com.arnest.project.unifyAdmin.CreateBuildingUnify.CreateBuildingUnify;
import org.openqa.selenium.By;

public class CommonPageUnify {
    public By invalidEmail = By.xpath("//label[text()='User not found']");
    public By loginConfirmPageText = By.xpath("//label[text()=\"Welcome Back\"]");
    public By inputEmail = By.xpath("//input[@placeholder=\"user@example.com\"]");
    public By inputPassword = By.xpath("//input[@placeholder=\"Password\"]");
    public By loginButtonClick = By.xpath("//button[@class=\"ant-btn ant-btn-default clientLargeBlackButton clientLargeBlackEnabledButton\"]");
    public By SuccessMesgDashboard = By.xpath("//h1[text()='Building Updates']");

    private LoginPageUnify loginPage;
    private UnifyForgetPassword forgotPasswordPage;
    private AdminLoginUnify adminLogin;
    private DashBoardUnify adminDashBoardUnify;
    private CreateBuildingUnify adminCreateAmenity;

    public LoginPageUnify getLoginPageUnify() {
        if (loginPage == null) {
            loginPage = new LoginPageUnify();
        }
        return loginPage;
    }


    public UnifyForgetPassword getForgotPasswordUnify() {
        if (forgotPasswordPage == null) {
            forgotPasswordPage = new UnifyForgetPassword();
        }
        return forgotPasswordPage;
    }

    public AdminLoginUnify getAdminLoginUnify() {
        if (adminLogin == null) {
            adminLogin = new AdminLoginUnify();
        }
        return adminLogin;
    }

    public DashBoardUnify getAdminDashBoardUnify() {
        if (adminDashBoardUnify == null) {
            adminDashBoardUnify = new DashBoardUnify();
        }
        return adminDashBoardUnify;
    }

    public CreateBuildingUnify getAdminCreateAmenityUnify() {
        if (adminCreateAmenity == null) {
            adminCreateAmenity = new CreateBuildingUnify();
        }
        return adminCreateAmenity;
    }


}
