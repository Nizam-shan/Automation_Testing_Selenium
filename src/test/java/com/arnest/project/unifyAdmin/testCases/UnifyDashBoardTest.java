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

public class UnifyDashBoardTest extends BaseTest {
    final CommonPageUnify commonPageUnify = new CommonPageUnify();
    private ExcelHelpers excel;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        this.excel = new ExcelHelpers();
        this.excel.setExcelFile(FrameworkConstants.EXCEL_UNIFY_LOGIN, "Login");
    }

    @FrameworkAnnotation(author = {AuthorType.Nizam, AuthorType.Nizam}, category = {CategoryType.REGRESSION, CategoryType.SANITY, CategoryType.SMOKE})
    @Test(groups = {"REGRESSION"})
    public void UF_LoginSuccessToDashBoard() {
        this.commonPageUnify.getAdminDashBoardUnify().dashBoardLoginSuccessUnify(this.excel.getCellDataWithCustomName("loginSuccessUnify", "email"), this.excel.getCellDataWithCustomName("loginSuccessUnify", "password"));
    }
}
