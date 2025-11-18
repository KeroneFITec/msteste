package com.iubi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing the header of a message.
 * Contains metadata such as message ID, timestamp, source, routing key, and version.
 *
 * @author Kerone Jr.
 * @since 2025-11-12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeaderDto {
    /** Unique identifier for the message. */
    private String message_id;
    /** Timestamp when the message was created. */
    private String timestamp;
    /** Name of the source service. */
    private String source_service;
    /** Routing key for message delivery. */
    private String routing_key;
    /** Version of the message format. */
    private String message_version;
}