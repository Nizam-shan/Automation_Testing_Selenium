/*
 * Copyright (c) 2025.
 * Automation Framework Selenium - Arnest
 */

package com.arnest.exceptions;

/**
 * FrameworkException extends RuntimeException - because I want to terminate the program when the Exception
 * comes
 */
@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException {

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
