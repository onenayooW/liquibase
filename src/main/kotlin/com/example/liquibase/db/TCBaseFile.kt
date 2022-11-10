package com.example.liquibase.db

import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseTCFile : TCBaseEntity() {
    /**
     * File encoded hash SHA256
     */
    @Column(name = "original_file_hash", columnDefinition = "char(64)")
    open var originalFileHash: String? = null

    /**
     * Original file name ex. something.png
     */
    @Column(length = 1024, nullable = false)
    open lateinit var originalFileName: String

    /**
     * System file name without extension
     * ex. originalFileName is "something.png" fileName will be "XXXXX-YYYYY-og" (UUID auto generated)
     */
    @Column(length = 128, nullable = false)
    open lateinit var fileName: String

    /**
     * File extension ex. originalFileName is "something.png" extension will be "png"
     */
    @Column(length = 32, nullable = false)
    open lateinit var extension: String

    /**
     * HTTP Content-Type
     * ex. image/jpeg
     */
    @Column(length = 32, nullable = false)
    open var mimeType: String? = null

    /**
     * NOTE: we SUPPRESS protocol and endpoint and file name here. Start with Slash '/' and End without Slash '/'
     * ex. /sub1/sub2
     */
    @Column(length = 2048, nullable = false)
    open lateinit var subUrlPath: String

    /**
     * NOTE: If S3 driver is selected, This originUri will be full S3 URI
     * ex. s3://static.truck2hand.com/public/users/2klvOZ6ObP/7c4bbaa3-0b00-4fc6-97a0-4655d638d635.jpg
     * ex. public/v2/item/jarQYYQdrP/image/8401030a4b96a79734f3500222d053655406466657854e7786f16b9/
     */
    @Column(length = 2048, nullable = false)
    open lateinit var physicalSubPath: String

    @Column(nullable = false, columnDefinition = "ENUM('s3', 'local')")
    @Enumerated(EnumType.STRING)
    open lateinit var driver: FileDriver

    /**
     * defines the access permission to this file
     */
    @Column(nullable = false, columnDefinition = "ENUM('public', 'private')")
    @Enumerated(EnumType.STRING)
    open lateinit var permission: FilePermission

    /**
     * File size in Byte
     */
    @Column
    open var size: Long? = null

    /**
     * The owner of the file
     */
    @Column(name = "user_id")
    open var userId: Long = -1
}

enum class FilePermission() {
    /**
     * Every one can read the file
     */
    public,

    /**
     * Only Admin and the owner of the file can read the file
     */
    private
}

enum class FileDriver() {
    s3,
    local
}
