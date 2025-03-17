package com.arnest.project.unifyAdmin.CreateBuildingUnify;


import com.arnest.constants.FrameworkConstants;
import com.arnest.keywords.WebUI;
import com.arnest.project.unify.CommonPageUnify;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Map;

import static com.arnest.keywords.WebUI.*;

public class CreateBuildingUnify extends CommonPageUnify {

    final By sideBarBuildingsClick = By.xpath("//*[@id=\"buildings\"]");
    final By verifyButton = By.xpath("//span[text()=\"Create Building\"]");
    final By verifyCreateBuildingPage = By.xpath("//label[text()=\"Create Building\" and @class=\"defaultFont16Bold lineHeight20\"] ");
    final By verifyFormText = By.xpath("//label[text()=\"General information\"]");
    final By InputBuildingName = By.xpath("//input[@placeholder=\"Name of building or property\"]");
    final By StrataPlanNumber = By.xpath("//input[@placeholder=\"Building strata plan number\"]");
    final By address = By.xpath("//input[@placeholder=\"Search address\"]");
    final By developerName = By.xpath("//input[@placeholder=\"Name of developer\"]");
    final By developerContact = By.xpath("//*[@id=\"__next\"]/section/section/main/div/div/div/div[1]/div[2]/div/form/div[1]/div/div[13]/div[2]/div/div/div/div/div/input");
    final By propertyName = By.xpath("//input[@placeholder=\"Name of property management \"]");
    final By propertyContact = By.xpath("//*[@id=\"__next\"]/section/section/main/div/div/div/div[1]/div[2]/div/form/div[1]/div/div[14]/div[2]/div/div/div/div/div/input");
    final By noOfFloor = By.xpath("//input[@placeholder=\"Total number of floors\"]");
    final By noOfunits = By.xpath("//input[@placeholder=\"Total number of units\"]");
    final By buildingYears = By.xpath("//input[@placeholder=\"ex) 2021\"]");
    final By neighBourHood = By.xpath("//input[@placeholder=\"ex) Downtown\"]");
    final By description = By.xpath("//div[@data-placeholder=\"Briefly describe about the building. (2-3 sentences recommended)\"]");
    final By openTimeZoneDropDown = By.xpath("//*[@id=\"__next\"]/section/section/main/div/div/div/div[1]/div[2]/div/form/div[1]/div/div[17]/div/div/div/div/div/div[1]");
    final By propertManager = By.xpath("//label[contains(text(), 'Property manager contact')]/following-sibling::div/div/div/div/div//input[@placeholder=\"(888) 888-8888\"]");
    final By buildingSecurityContact = By.xpath("//label[contains(text(), 'Building security contact')]/following-sibling::div/div/div/div/div//input[@placeholder=\"(888) 888-8888\"]");
    final By mgtEmailAddress = By.xpath("//input[@placeholder=\"Enter email address\"]");
    final By allowedVisitorPerMonth = By.xpath("//input[@placeholder=\"Enter number\"]");
    final By approvalsCustomEmail = By.xpath("//input[@class=\"ant-input adminInput\" and @placeholder=\"\"]");
    final By maxHoldVacationDays = By.xpath("//input[@placeholder=\"Enter number of days\" and @tabindex=\"0\"]");
    final By maxHoldDays = By.xpath("//*[@id=\"maxParcelHoldDays\"]");
    final By sendFirstInvoice = By.xpath("//button[@id=\"sendFirstInvoice\"]");
    final By createBtn = By.xpath("//span[text()=\"Create\"]");
    final By nextBtn = By.xpath("//span[text()=\"Next\"]");
    final By ennableCreditCard = By.xpath("//button[@id=\"isCreditCardPaymentEnabled\"]");
    final By ennableRentViaPad = By.xpath("//button[@id=\"isDebitCardPaymentEnabled\"]");
    final By searchTextverifyBuilding = By.xpath("//input[@placeholder='Search ...']");
    final By inputLogo = By.xpath("//input[@id='displayImageLogo']");
    final By inputImage = By.xpath("//input[@id='displayImageHome']");
    final By searchresultToTop = By.xpath("//tbody/tr[1]/td[2]");


    public void BuildingsSidebarClick() {
        sleep(2);
        WebUI.scrollToElementAtBottom(this.sideBarBuildingsClick);
        sleep(1);
        WebUI.clickElement(this.sideBarBuildingsClick);
        sleep(2);
        WebUI.verifyEquals(getTextElement(this.verifyButton).trim(), "Create Building");
    }


    public void BuildingsCreateButtonClick() {
        WebUI.clickElement(this.verifyButton);
        sleep(2);
        WebUI.verifyEquals(getTextElement(this.verifyCreateBuildingPage).trim(), "Create Building");
    }

    public String BuildingCreateForm(Object building) {
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(this.verifyFormText, "General info text not found");
        Map<String, Object> buildingMap = (Map<String, Object>) building;
        String leasing = (String) buildingMap.get("leasing");
        String RadioPath = "//*[@id='__next']//label[span[text()='" + leasing.trim() + "']]";
        WebUI.selectRadioButon(RadioPath);
        if ("Disabled".equalsIgnoreCase(leasing)) {
            WebUI.setText(this.StrataPlanNumber, (String) buildingMap.get("strataPlanNumber"));
            WebUI.setText(this.developerName, (String) buildingMap.get("developerName"));
            WebUI.setText(this.developerContact, (String) buildingMap.get("developerContactNumber"));
            WebUI.setText(this.propertyName, (String) buildingMap.get("propertyMgt"));
            WebUI.setText(this.propertyContact, (String) buildingMap.get("propertyMgtContactNumber"));
        } else {
            System.out.println("Ingoring plan no because of cases");
        }
        String generatedBuildingName = (String) buildingMap.get("buildingName") + " " + RandomStringUtils.randomAlphabetic(5);
        WebUI.setText(this.InputBuildingName, generatedBuildingName);
        WebUI.scrollToElementAtTop(this.address);
        WebUI.sleep(1);
        String addressValue = ((String) buildingMap.get("address")).trim();
        WebUI.setText(this.address, addressValue);
        WebUI.waitForPageLoaded();
        By addressString = By.xpath("//div[contains(text(),'" + addressValue + "') and contains(@class,'addressSuggestionContainerItem')]");
        WebUI.clickElement(addressString);
        WebUI.sleep(4);
        WebUI.waitForPageLoaded();
        WebUI.scrollToElementAtTop(this.noOfFloor);
        WebUI.setText(this.noOfFloor, (String) buildingMap.get("noOffloor"));
        WebUI.setText(this.noOfunits, (String) buildingMap.get("noOfunits"));
        WebUI.setText(this.buildingYears, (String) buildingMap.get("builtYr"));
        WebUI.setText(this.neighBourHood, (String) buildingMap.get("neighbourHood"));
        WebUI.clickElement(this.openTimeZoneDropDown);
        WebUI.waitForPageLoaded();
        String timeZonePath = "//label[normalize-space()='" + ((String) buildingMap.get("timeZone")).trim() + "']";
        WebUI.dropDown(timeZonePath);
        waitForPageLoaded();
        WebUI.setText(this.description, (String) buildingMap.get("description"));
        WebUI.scrollToElementAtTop(this.description);
        WebUI.imageUpload(this.inputImage, (String) buildingMap.get("buildingImage"));
        WebUI.scrollToElementAtBottom(this.inputLogo);
        WebUI.imageUpload(this.inputLogo, (String) buildingMap.get("buildingLogo"));
        WebUI.clickElement(this.nextBtn);
        WebUI.waitForPageLoaded();
        List<Map<String, String>> rules = (List<Map<String, String>>) buildingMap.get("rules");
        WebUI.fillRulesInput(rules);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(this.nextBtn);
        WebUI.setText(this.propertManager, (String) buildingMap.get("propertMgtCont"));
        WebUI.setText(this.buildingSecurityContact, (String) buildingMap.get("buildingSecurityCont"));
        WebUI.setText(this.mgtEmailAddress, (String) buildingMap.get("mgtmail"));
        WebUI.clickElement(this.nextBtn);
        WebUI.waitForPageLoaded();
        String amenityApproval = "//*[@id='__next']//label[span[text()='" + ((String) buildingMap.get("amenityApproval")) + "']]";
        WebUI.waitForPageLoaded();
        WebUI.selectRadioButon(amenityApproval);
        if ("Custom Email".equalsIgnoreCase((String) buildingMap.get("amenityApproval"))) {
            WebUI.setText(this.approvalsCustomEmail, (String) buildingMap.get("customEmail"));
        }
        String marketPlacePath = "//label[contains(text(), 'Marketplace item approval settings (Community)')]/following-sibling::div//input[@type='radio' and @value='" + ((String) buildingMap.get("marketItemAppr")).trim() + "']/ancestor::label";
        WebUI.selectRadioButon(marketPlacePath);
        String discussionPath = "//label[contains(text(), 'Discussion post approval settings (Community) *')]/following-sibling::div//input[@type='radio' and @value='" + ((String) buildingMap.get("discussionPostApproval")).trim() + "']/ancestor::label";
        WebUI.selectRadioButon(discussionPath);
        WebUI.setText(this.allowedVisitorPerMonth, (String) buildingMap.get("allowedVisitorPerMonth"));
        String parcelHoldDaysPath = "//label[contains(text(), 'Parcel vacation mode settings *')]/following-sibling::div//input[@type='radio' and @value='" + ((String) buildingMap.get("parcelHoldDays")).trim() + "']/ancestor::label";
        WebUI.selectRadioButon(parcelHoldDaysPath);
        WebUI.scrollToElementAtTop(this.maxHoldDays);
        WebUI.setText(this.maxHoldVacationDays, (String) buildingMap.get("parcelMaxVacationDays"));
        WebUI.setText(this.maxHoldDays, (String) buildingMap.get("parcelMaxHoldDays"));
        WebUI.sleep(1);
        if ("yes".equalsIgnoreCase((String) buildingMap.get("enableCreditCardPay"))) {
            WebUI.clickElement(this.ennableCreditCard);
        }
        if ("yes".equalsIgnoreCase((String) buildingMap.get("enableRentPaymentViaPad"))) {
            WebUI.clickElement(this.ennableRentViaPad);
        }
        if ("Enabled".equalsIgnoreCase(leasing)) {
            WebUI.clickElement(this.nextBtn);
            WebUI.waitForPageLoaded();
            WebUI.clickElement(this.sendFirstInvoice);
        }
        WebUI.sleep(1);
        WebUI.clickElement(this.createBtn);
//        WebUI.waitForPageLoaded();
        WebUI.waitForUrlContain(FrameworkConstants.URL_UNIFY, 4);
        WebUI.sleep(5);
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(this.loginConfirmPageText, 30);
        WebUI.verifyElementVisible(this.loginConfirmPageText, "Not success");

        return generatedBuildingName;

    }


    public void verifyItem(String value) {
        WebUI.waitForPageLoaded();
        setText(this.searchTextverifyBuilding, value, Keys.ENTER);
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
        WebUI.verifyEquals(getTextElement(this.searchresultToTop), value, "Not found");
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        WebUI.clickElement(this.searchresultToTop);
        String verifyBuildingNameAfterSearch = "//label[text()='" + value + "']";
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        WebUI.verifyElementVisible(By.xpath(verifyBuildingNameAfterSearch));
        WebUI.sleep(4);

    }


}
