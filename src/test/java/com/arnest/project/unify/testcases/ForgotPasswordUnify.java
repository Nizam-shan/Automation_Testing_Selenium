package com.arnest.project.unify.testcases;

import com.arnest.annotations.FrameworkAnnotation;
import com.arnest.common.BaseTest;
import com.arnest.constants.FrameworkConstants;
import com.arnest.enums.AuthorType;
import com.arnest.enums.CategoryType;
import com.arnest.helpers.ExcelHelpers;
import com.arnest.project.unify.CommonPageUnify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPasswordUnify extends BaseTest {

    private CommonPageUnify commonPageUnify = new CommonPageUnify();
    private ExcelHelpers excel;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        excel = new ExcelHelpers();
        excel.setExcelFile(FrameworkConstants.EXCEL_UNIFY_LOGIN, "Sheet2");
    }

    // forgot password invalid user
    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.SANITY})
    @Test(groups = {"SANITY"})
    public void UF_forgotPasswordInvalidUser() {

        commonPageUnify.getForgotPasswordUnify().forgotPasswordInValidUser(excel.getCellDataWithCustomName("forgotPasswordInValidUser", "email"));
    }


    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.SANITY, CategoryType.SMOKE})
    @Test(groups = {"SANITY", "SMOKE"})
    public void UF_forgotPasswordSuccess() {
        commonPageUnify.getForgotPasswordUnify().forgotPasswordSuccess(excel.getCellDataWithCustomName("forgotPasswordSuccess", "email"));
    }

    @Test(groups = {"REGRESSION",})
    public void UF_forgotPasswordSuccesstest() {
        System.out.println("came test regree 1");
    }

    @Test(groups = {"REGRESSION",})
    public void UF_forgotPasswordSuccesstest1() {
        System.out.println("came test regree 2");
    }


}
