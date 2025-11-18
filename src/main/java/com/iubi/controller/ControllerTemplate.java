package com.iubi.controller;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.Controller;

import com.iubi.dto.HeaderDto;
import com.iubi.dto.MessageDto;
import com.iubi.dto.PayloadDto;
import com.iubi.service.MessageService;

/**
 * REST controller template for generating logs and sending test messages.
 *
 * @author Kerone Jr.
 * @since 2025-11-12
 */
@RestController
public class ControllerTemplate {
    /**
     * MessageSource for internationalized messages.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Logger for controller messages.
     */
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    /**
     * Service for sending messages.
     */
    @Autowired
    private MessageService messageService;

    /**
     * Endpoint to generate logs and send a test message.
     *
     * @param locale the locale for message translation
     * @return log message string
     */
    @GetMapping("/generate_logs")
    public String generateLogs(Locale locale) {
    	
    	if (locale.getLanguage().isEmpty())
    		locale = LocaleContextHolder.getLocale();
    	
        LOG.info(messageSource.getMessage("log.info.example", null, locale));
        LOG.debug(messageSource.getMessage("log.debug.example", null, locale));
        LOG.warn(messageSource.getMessage("log.warn.example", null, locale));
        LOG.error(messageSource.getMessage("log.error.example", null, locale));
        
        //"fila", "routingKey"
        messageService.sendMessage(createTestMessage());
        
        return messageSource.getMessage("log.message", null, locale);
    }

    /**
     * Creates a test message for demonstration purposes.
     *
     * @return MessageDto instance
     */
	private MessageDto createTestMessage() {
		MessageDto message = MessageDto.builder()
				.header(HeaderDto.builder()
						.message_id(UUID.randomUUID().toString())
						.timestamp(LocalDateTime.now().toString())
						.source_service("ControllerTemplate")
						.routing_key("notification-service-queue")
						.message_version("1.0")
						.build())
				.payload(PayloadDto.builder()
						.file_url("https://teste")
						.user_id("123456")
						.message_number(123)
						.app_id("notification-service")
						.build())
				.build();
		return message;
	}
    
}