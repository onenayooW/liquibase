package com.example.liquibase.db

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class TCUserFile : TCBaseEntity() {

    @Column(name = "user_id")
    open var userId: Long = 0

    @Column
    open var orderBy: Long = 0

    @Column(name = "file_id", insertable = false, updatable = false)
    open var fileId: Long = 0
}
