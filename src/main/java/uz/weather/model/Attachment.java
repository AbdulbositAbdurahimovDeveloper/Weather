package uz.weather.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.weather.model.Abs.AbsLongEntity;

import java.util.Objects;

/**
 * Represents a file attachment stored in multiple locations:
 * 1. A primary object storage service like MinIO.
 * 2. Optionally, cached in a Telegram channel for fast delivery to bot users.
 * <p>
 * This entity stores metadata about the uploaded file for both storage systems.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "attachments")
@SQLDelete(sql = "UPDATE attachments SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted=false")
public class Attachment extends AbsLongEntity {

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false, unique = true)
    private String generatedFileName;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private long size;

    // --- MinIO Storage Metadata ---
    @Column(nullable = false)
    private String bucketName;

    @Column(nullable = false)
    private String objectKey;

    // --- Telegram Storage Metadata ---
    /**
     * The unique and persistent file identifier received from the Telegram Bot API.
     * This ID can be used to send the file to any user without re-uploading it.
     * This field is nullable because not all attachments might be sent to Telegram.
     */
    @Column(name = "telegram_file_id")
    private String telegramFileId;

    /**
     * The ID of the message that contains the file in the private Telegram channel.
     * Useful for reference or administrative tasks like deleting the file from the channel.
     * This field is also nullable.
     */
    @Column(name = "telegram_message_id")
    private Integer telegramMessageId;

    private boolean deleted = false;

}