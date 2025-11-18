package com.iubi.service;

import com.iubi.dto.MessageDto;

/**
 * Service interface for sending messages.
 * Defines contract for message publishing operations.
 *
 * @author Kerone Jr.
 * @since 2025-11-12
 */
public interface MessageService {
    /**
     * Sends a message to the messaging system.
     *
     * @param message the message to send
     */
    void sendMessage(MessageDto message);
}