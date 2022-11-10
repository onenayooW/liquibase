package com.example.liquibase.db

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.Where
import javax.persistence.*

/**
 * File int table should be removed when it is not associated with any object after 24 hours
 * TCDraftFile is concept of "Upload First" to improve the UX in Frontend part.
 * TCDraftFile brings the File Object to be in the "Staging" state.
 * Unless, the file will be automatically removed by the scheduler within 24 hours.
 */
@Entity
@Table(
    indexes = [
        Index(name = "IDX_TCDRAFTFILE_CREATED_AT", columnList = "created_at", unique = false),
        Index(name = "IDX_TCDRAFTFILE_UPDATED_AT", columnList = "updated_at", unique = false),
        Index(name = "IDX_TCDRAFTFILE_DELETED_AT", columnList = "deleted_at", unique = false),
        Index(name = "IDX_TCDRAFTFILE_USER", columnList = "user_id", unique = false),
        Index(name = "IDX_TCDRAFTFILE_ORIGINAL_FILE_HASH", columnList = "original_file_hash", unique = false),
        Index(name = "IDX_TCDRAFTFILE_USER_ENCODED_HASH", columnList = "user_id,original_file_hash", unique = false)
    ]
)
@Where(clause = "deleted_at is NULL ")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@javax.persistence.Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
open class TCDraftFile : BaseTCFile()
