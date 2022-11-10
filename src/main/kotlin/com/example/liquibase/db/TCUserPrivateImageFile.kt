package com.example.liquibase.db

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(
    indexes = [
        Index(name = "IDX_TCUSERPRIVATEIMAGEFILE_CREATED_AT", columnList = "created_at", unique = false),
        Index(name = "IDX_TCUSERPRIVATEIMAGEFILE_UPDATED_AT", columnList = "updated_at", unique = false),
        Index(name = "IDX_TCUSERPRIVATEIMAGEFILE_DELETED_AT", columnList = "deleted_at", unique = false),
        Index(name = "IDX_TCUSERPRIVATEIMAGEFILE_USER_ID", columnList = "user_id", unique = false),
        Index(name = "IDX_TCUSERPRIVATEIMAGEFILE_FILE_ID", columnList = "file_id", unique = false)
    ]
)
@SQLDelete(sql = "UPDATE tcuser_private_image_file SET deletedAt = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is NULL")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@javax.persistence.Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class TCUserPrivateImageFile : TCUserFile() {

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    var imageFile: TCOtherFile? = null
}
