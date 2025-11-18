package com.iubi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing the payload of a message.
 * Contains file URL, user ID, message number, and application ID.
 *
 * @author Kerone Jr.
 * @since 2025-11-12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayloadDto {
    /** URL of the file associated with the message. */
    private String file_url;
    /** Identifier of the user related to the message. */
    private String user_id;
    /** Sequential number of the message. */
    private Integer message_number;
    /** Identifier of the application sending the message. */
    private String app_id;
}