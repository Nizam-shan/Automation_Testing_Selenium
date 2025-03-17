


package com.arnest.project.unifyAdmin.testCases;


import com.arnest.annotations.FrameworkAnnotation;
import com.arnest.common.BaseTest;
import com.arnest.constants.FrameworkConstants;
import com.arnest.enums.AuthorType;
import com.arnest.enums.CategoryType;
import com.arnest.helpers.ExcelHelpers;
import com.arnest.project.unify.CommonPageUnify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AdminLoginIUnify extends BaseTest {

    private CommonPageUnify commonPageUnify = new CommonPageUnify();
    private ExcelHelpers excel;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        excel = new ExcelHelpers();
        excel.setExcelFile(FrameworkConstants.EXCEL_UNIFY_LOGIN, "Login");
        System.out.println("excel: " + excel);
    }


    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.SANITY})
    @Test(description = "testing", groups = {"SANITY"})
    // both null case
    public void UF_LoginFailWithNoEmail() {
        commonPageUnify.getAdminLoginUnify().loginFailedWithEmailNull();
    }

    // email not exist
    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.SMOKE, CategoryType.SANITY})
    @Test(description = "testing", groups = {"SMOKE", "SANITY"})
    public void UF_LoginFailWithEmailDoesNotExist() {
        commonPageUnify.getAdminLoginUnify().loginFailedWithEmailDoesNotExist(excel.getCellDataWithCustomName("loginFailedWithEmailDoesNotExist", "password"));
    }


    // password not exist
    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.REGRESSION, CategoryType.SANITY})
    @Test(description = "testing", groups = {"SANITY", "REGRESSION"})
    public void UF_LoginFailWithPasswordDoesNotExist() {
        commonPageUnify.getAdminLoginUnify().loginFailedWithPasswordDoesNotExist(excel.getCellDataWithCustomName("loginFailedWithPasswordDoesNotExist", "email"));
    }

    // wrong passsword
    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.SANITY, CategoryType.SMOKE})
    @Test(description = "testing", groups = {"SMOKE", "SANITY"})
    public void UF_LoginFailWithWrongPassword() {
        commonPageUnify.getAdminLoginUnify().loginFailedWithWrongPassword(excel.getCellDataWithCustomName("loginFailedWithWrongPassword", "email"), excel.getCellDataWithCustomName("loginFailedWithWrongPassword", "password"));
    }

    // invalid email address
    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.SANITY, CategoryType.SMOKE})
    @Test(description = "testing", groups = {"SMOKE", "SANITY"})
    public void UF_LoginWithInvalidEmail() {

        commonPageUnify.getAdminLoginUnify().loginWithInvalidEmail(excel.getCellDataWithCustomName("loginWithInvalidEmail", "email"), excel.getCellDataWithCustomName("loginWithInvalidEmail", "password"));
    }


    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.SMOKE, CategoryType.SANITY})
    @Test(description = "testing", groups = {"SMOKE", "SANITY"})
    public void UF_LoginSuccess() {
        commonPageUnify.getAdminLoginUnify().loginSuccessUnify(excel.getCellDataWithCustomName("loginSuccessUnify", "email"), excel.getCellDataWithCustomName("loginSuccessUnify", "password"));

    }
}


