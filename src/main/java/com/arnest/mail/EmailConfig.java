/*
 * Copyright (c) 2025.
 * Automation Framework Selenium - Arnest
 */

package com.arnest.mail;

import static com.arnest.constants.FrameworkConstants.*;

/**
 * Data for Sending email after execution
 */
public class EmailConfig {

    //Remember to create an app password (App Password) for Gmail before sending
    //If you use Hosting's email, it's normal
    //Enable Override Report and Send mail in config file => src/test/resources/config/config.properties
    //OVERRIDE_REPORTS=yes
    //send_email_to_users=yes

    public static final String SERVER = SMTP_SERVER;
    public static final String PORT = SMTP_PORT;

    public static final String FROM = SMTP_FROM;
    public static final String PASSWORD = SMTP_PASSWORD;

    public static final String[] TO = SMTP_TO.split(",");
    public static final String SUBJECT = REPORT_TITLE;
}