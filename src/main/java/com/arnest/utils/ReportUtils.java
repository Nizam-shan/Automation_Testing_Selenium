/*
 * Copyright (c) 2025.
 * Automation Framework Selenium - Arnest
 */

package com.arnest.utils;

import com.arnest.exceptions.FrameworkException;
import com.arnest.exceptions.InvalidPathForExtentReportFileException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.arnest.constants.FrameworkConstants.*;

public class ReportUtils {

    private ReportUtils() {

    }

    public static String createExtentReportPath() {
        File reportFolder = new File(EXTENT_REPORT_FOLDER_PATH);
        if (!reportFolder.exists()) {
            reportFolder.mkdir();
        }

        String link;

        if (OVERRIDE_REPORTS.trim().equals(NO)) {
            System.out.println("OVERRIDE_REPORTS = " + OVERRIDE_REPORTS);
            link = EXTENT_REPORT_FOLDER_PATH + File.separator + DateUtils.getCurrentDate() + "_" + EXTENT_REPORT_FILE_NAME;
        } else {
            System.out.println("OVERRIDE_REPORTS = " + OVERRIDE_REPORTS);
            link = EXTENT_REPORT_FOLDER_PATH + File.separator + EXTENT_REPORT_FILE_NAME;
        }

        System.out.println("Created link report: " + link);
        return link;
    }


    public static void openReports(String linkReport) {
        if (OPEN_REPORTS_AFTER_EXECUTION.trim().equalsIgnoreCase(YES)) {
            try {
                Desktop.getDesktop().browse(new File(linkReport).toURI());
            } catch (FileNotFoundException fileNotFoundException) {
                throw new InvalidPathForExtentReportFileException("Extent Report file you are trying to reach is not found", fileNotFoundException.fillInStackTrace());
            } catch (IOException ioException) {
                throw new FrameworkException("Extent Report file you are trying to reach got IOException while reading the file", ioException.fillInStackTrace());
            }
        }
    }
}

