package com.iubi.controller;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.Controller;

/**
 * REST controller for the /status endpoint.
 * Returns a localized status message.
 *
 * @author Kerone Jr.
 * @since 2025-11-12
 */
@RestController
public class StatusController {
    /**
     * Logger for controller messages.
     */
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    /**
     * MessageSource for internationalized messages.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Endpoint to return a localized status message.
     *
     * @param locale the locale for message translation
     * @return status message string
     */
    @GetMapping("/status")
    public String status(Locale locale) {
    	
    	if (locale.getLanguage().isEmpty())
    		locale = LocaleContextHolder.getLocale();
    	
    	LOG.info(messageSource.getMessage("application.running", null, locale));
    	
        return messageSource.getMessage("application.running", null, locale);
    }
}