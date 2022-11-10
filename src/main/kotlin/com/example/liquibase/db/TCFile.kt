package com.example.liquibase.db

import com.fasterxml.jackson.annotation.JsonInclude
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.hibernate.annotations.Where
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(
    indexes = [
        Index(name = "IDX_TCFILE_CREATED_AT", columnList = "created_at", unique = false),
        Index(name = "IDX_TCFILE_UPDATED_AT", columnList = "updated_at", unique = false),
        Index(name = "IDX_TCFILE_DELETED_AT", columnList = "deleted_at", unique = false),
        Index(name = "IDX_TCFILE_USER", columnList = "user_id", unique = false),
        Index(name = "IDX_TCFILE_ORIGINAL_FILE_HASH", columnList = "original_file_hash", unique = false),
        Index(name = "IDX_TCFILE_USER_ENCODED_HASH", columnList = "user_id,original_file_hash", unique = false)
    ]
)
@Where(clause = "deleted_at is NULL ")
@SQLDelete(sql = "UPDATE tcfile SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@TypeDefs(
    value = [TypeDef(name = "json", typeClass = JsonStringType::class)]
)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "file")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonInclude(JsonInclude.Include.NON_NULL)
//@javax.persistence.Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
open class TCFile : BaseTCFile() {

    @Column(
        nullable = false,
        columnDefinition = "ENUM('item_image', 'item_vdo','item_private_image' , 'user_profile_image','user_private_image')"
    )
    @Enumerated(EnumType.STRING)
    open lateinit var category: Category

    @Transient
    open var originalFullFileName: String? = null
        get() {
            return "$fileName.$extension"
        }

    /**
     * NOTE: If S3 driver is selected, This originUri will be full S3 URI
     * ex. s3://static.truck2hand.com/public/users/2klvOZ6ObP/7c4bbaa3-0b00-4fc6-97a0-4655d638d635.jpg
     */
//    @Transient
//    open var fullOriginUri: String? = null
//        get() {
//            return "${appProperty.s3BucketUriEndpoint}$s3SubPath/$originalFullFileName"
//        }

    /**
     * NOTE: If S3 driver is selected, This originUrl will be Object URL
     * ex. https://s3.ap-southeast-1.amazonaws.com/static.truck2hand.com/public/users/2klvOZ6ObP/7c4bbaa3-0b00-4fc6-97a0-4655d638d635.jpg
     */
//    @Transient
//    open var fullOriginUrl: String? = null
//        get() {
//            return "${appProperty.s3BucketUrlEndpoint}$s3SubPath/$originalFullFileName"
//        }

    /**
     * NOTE: we have protocol and endpoint as well.
     * ex. https://www.t2h.com/sub1/sub2/file.png
     */
//    @Transient
//    open var fullUrlPath: String? = null
//        get() {
//            return "${appProperty.imageUrlEndpoint}$subUrlPath/$originalFullFileName"
//        }
}

enum class Category {
    item_image,
    item_vdo,
    item_private_image,
    user_profile_image,
    user_private_image
}
