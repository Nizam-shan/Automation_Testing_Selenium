/*
 * Copyright (c) 2025.
 * Automation Framework Selenium - Arnest
 */

package com.arnest.report;

import com.arnest.constants.FrameworkConstants;
import com.arnest.driver.DriverManager;
import com.arnest.enums.Browser;
import com.arnest.helpers.FileHelpers;
import com.arnest.utils.BrowserInfoUtils;
import com.arnest.utils.LogUtils;
//import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
//import io.qameta.allure.Allure;
//import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.arnest.constants.FrameworkConstants.EXPORT_VIDEO_PATH;
import static org.openqa.selenium.OutputType.BYTES;

public class AllureManager {

    private AllureManager() {
    }

    public static void setAllureEnvironmentInformation() {
//        AllureEnvironmentWriter.allureEnvironmentWriter(
//                ImmutableMap.<String, String>builder().
//                        put("Test URL", FrameworkConstants.URL_UNIFY).
//                        put("Target Execution", FrameworkConstants.TARGET).
//                        put("Global Timeout", String.valueOf(FrameworkConstants.WAIT_DEFAULT)).
//                        put("Page Load Timeout", String.valueOf(FrameworkConstants.WAIT_PAGE_LOADED)).
//                        put("Headless Mode", FrameworkConstants.HEADLESS).
//                        put("Local Browser", String.valueOf(Browser.CHROME)).
//                        put("Remote URL", FrameworkConstants.REMOTE_URL).
//                        put("Remote Port", FrameworkConstants.REMOTE_PORT).
//                        build());

        System.out.println("Allure Reports is installed.");

    }

    //    @Attachment(value = "Failed test Screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return new byte[0];
    }

    //    @Attachment(value = "Step Screenshot", type = "image/png")
    public static byte[] takeScreenshotStep() {
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return new byte[0];
    }

    //    @Attachment(value = "Browser Information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        return BrowserInfoUtils.getOSInfo();
    }


    //Text attachments for Allure
//    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
//    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    public static void addAttachmentVideoAVI() {
        if (FrameworkConstants.VIDEO_RECORD.toLowerCase().trim().equals(FrameworkConstants.YES)) {
            //Get file Last Modified in folder
            File video = FileHelpers.getFileLastModified(EXPORT_VIDEO_PATH);
//                if (video != null) {
//                    Allure.addAttachment("Video record AVI", "video/avi", new FileInputStream(video), "avi");
//                } else {
//                    LogUtils.warn("Video record not found.");
//                    LogUtils.warn("Can not attachment Video in Allure report");
//                }

        }
    }

    public static void addAttachmentVideoMP4() {
        //Get file Last Modified in folder
        File video = FileHelpers.getFileLastModified(EXPORT_VIDEO_PATH);
        //File video = new File("ExportData/Videos/SampleVideo.mp4");
//            if (video != null) {
//                Allure.addAttachment("Video record MP4", "video/mp4", new FileInputStream(video), "mp4");
//            } else {
//                LogUtils.warn("Video record not found.");
//                LogUtils.warn("Can not attachment Video in Allure report");
//            }

    }

}
