package com.iubi.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iubi.dto.MessageDto;
import com.iubi.messagingservice.api.IMessagingService;
import com.iubi.service.MessageService;

import jakarta.annotation.PostConstruct;

/**
 * Implementation of MessageService for publishing messages using IMessagingService.
 *
 * @author Kerone Jr.
 * @since 2025-11-12
 */
@Service
public class MessageServiceImpl implements MessageService {
    /** Messaging service for publishing messages. */
    @Autowired
    private IMessagingService messagingService;

	@Value("${APP_MESSAGING_EXCHANGE_NAME}")
	private String exchange;
	
	@Value("${SOURCE_ROUTING_KEY}")
	private String sourceRoutingKey;
	
	@Value("${DESTINATION_ROUTING_KEY}")
	private String destinationRoutingKey;
	
	@Value("${spring.application.name}")
	private String subscription;    
    
	private static final Logger LOG = LogManager.getLogger(MessageServiceImpl.class);	;
	
    /**
     * This method is called after the bean has been constructed.
     * It subscribes to the messaging service to listen for incoming messages.
     *
     * @param message the message to publish
     */
	@PostConstruct
    public void messagingSubscription() {
        messagingService.subscribe(
        	sourceRoutingKey, // destination/routingKey
    		subscription, // subscriptionId
            (body, context) -> {
    			LOG.info("Message received: " + body);
            });
    }
	
    /**
     * Publishes a message to the specified exchange and routing key.
     *
     * @param message the message to publish
     */
    @Override
    public void sendMessage(MessageDto message) {
    	LOG.info("Sending message: " + message.toString());
		messagingService.publish(exchange, destinationRoutingKey, message);
	}

}