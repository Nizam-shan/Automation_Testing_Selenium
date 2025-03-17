package com.arnest.project.unifyAdmin.testCases;

import com.arnest.annotations.FrameworkAnnotation;
import com.arnest.common.BaseTest;
import com.arnest.enums.AuthorType;
import com.arnest.enums.CategoryType;
import com.arnest.helpers.JsonHelpers;
import com.arnest.keywords.WebUI;
import com.arnest.project.unify.CommonPageUnify;
import org.testng.annotations.Test;

public class CreateBuildingAdminUnifyTest extends BaseTest {
    final CommonPageUnify commonPageUnify = new CommonPageUnify();

    @FrameworkAnnotation(author = {AuthorType.Nizam}, category = {CategoryType.SANITY, CategoryType.REGRESSION, CategoryType.SMOKE})
    @Test(groups = {"SMOKE", "SANITY"})
    public void UF_LoginSuccessToDashBoard() {
        JsonHelpers json = new JsonHelpers();
        json.setJsonFile("datajson/createBuilding.json");
        Object building = json.getEntairJson();
        commonPageUnify.getAdminDashBoardUnify().dashBoardLoginSuccessUnify("dharshan.cs@c-c-c.ca", "password");
        commonPageUnify.getAdminCreateAmenityUnify().BuildingsSidebarClick();
        commonPageUnify.getAdminCreateAmenityUnify().BuildingsCreateButtonClick();
        String buildingName = commonPageUnify.getAdminCreateAmenityUnify().BuildingCreateForm(building);
        WebUI.waitForPageLoaded();
        commonPageUnify.getAdminDashBoardUnify().dashBoardLoginSuccessUnify("dharshan.cs@c-c-c.ca", "password");
        commonPageUnify.getAdminCreateAmenityUnify().BuildingsSidebarClick();
        commonPageUnify.getAdminCreateAmenityUnify().verifyItem(buildingName);
    }


}
