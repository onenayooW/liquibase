package com.example.liquibase.db

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(
    indexes = [
        Index(name = "IDX_TCITEMVDOFILE_CREATED_AT", columnList = "created_at", unique = false),
        Index(name = "IDX_TCITEMVDOFILE_UPDATED_AT", columnList = "updated_at", unique = false),
        Index(name = "IDX_TCITEMVDOFILE_DELETED_AT", columnList = "deleted_at", unique = false),
        Index(name = "IDX_TCITEMVDOFILE_ITEM_ID", columnList = "item_id", unique = false),
        Index(name = "IDX_TCITEMVDOFILE_FILE_ID", columnList = "file_id", unique = false)
    ]
)
@SQLDelete(sql = "UPDATE tcitem_vdo_file SET deletedAt = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is NULL")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@javax.persistence.Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class TCItemVdoFile : TCItemFile() {

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    var vdoFile: TCVdoFile? = null
}
