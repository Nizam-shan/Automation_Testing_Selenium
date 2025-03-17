package com.arnest.project.unify.testcases;

import com.arnest.annotations.FrameworkAnnotation;
import com.arnest.common.BaseTest;
import com.arnest.constants.FrameworkConstants;
import com.arnest.enums.AuthorType;
import com.arnest.enums.CategoryType;
import com.arnest.helpers.ExcelHelpers;
import com.arnest.project.unify.CommonPageUnify;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// baseTest as webdriver config
//@Epic("Regression Test CMS")
//@Feature("Unify Login Test")
public class LoginUnifyTest extends BaseTest {

    private CommonPageUnify commonPageUnify = new CommonPageUnify();
    private ExcelHelpers excel;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        excel = new ExcelHelpers();
        excel.setExcelFile(FrameworkConstants.EXCEL_UNIFY_LOGIN, "Login");
    }

    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.REGRESSION, CategoryType.SMOKE, CategoryType.SANITY})
    @Test(groups = {"SANITY", "SMOKE"})
    public void UF_UserLoginSuccess() {
        commonPageUnify.getLoginPageUnify().adminLoginSuccessUnify(excel.getCellDataWithCustomName("adminLoginSuccessUnify", "email"), excel.getCellDataWithCustomName("adminLoginSuccessUnify", "password"));

    }


}
