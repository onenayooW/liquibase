package com.example.liquibase.db

import javax.persistence.*

@MappedSuperclass
open class TCItemFile : TCBaseEntity() {

    @Column(name = "item_id")
    open var itemId: Long = 0

    @Column
    open var orderBy: Long = 0

    @Column(name = "file_id", insertable = false, updatable = false)
    open var fileId: Long = 0

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", referencedColumnName = "id", insertable = false, updatable = false)
    var file: TCFile? = null
}
