package com.iubi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a message with header and payload.
 * Used for transferring message data between services.
 *
 * @author Kerone Jr.
 * @since 2025-11-12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    /** Header information for the message. */
    private HeaderDto header;
    /** Payload data of the message. */
    private PayloadDto payload;
}