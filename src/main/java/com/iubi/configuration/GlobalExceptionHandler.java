package com.iubi.configuration;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the entire Spring Boot application.
 * Handles all uncaught exceptions and returns a standardized error response.
 * Uses internationalized messages for error logging and response.
 * 
 * @author Kerone Jr.
 * @since 2025-11-12
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * MessageSource for internationalized messages.
     */
    @Autowired
    private MessageSource messageSource;
    
    /**
     * Logger for error messages.
     */
    private static final Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles all uncaught exceptions in the application.
     * Logs the error and returns a generic internal error message.
     *
     * @param ex the exception thrown
     * @param locale the locale for message translation
     * @return ResponseEntity with internal error message and HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex, Locale locale) {
    	
    	if (locale.getLanguage().isEmpty())
    		locale = LocaleContextHolder.getLocale();
    	
        LOG.error(messageSource.getMessage("captured.error", null, locale), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("internal.error", null, locale));

    }
}