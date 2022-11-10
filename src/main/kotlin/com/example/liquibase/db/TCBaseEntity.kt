package com.example.liquibase.db

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class TCBaseEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = true, updatable = true, unique = true, nullable = false)
    open var id: Long? = null

    @Column(name = "created_at")
    @JsonIgnore
    open var createdAt: Date? = null

    @Column(name = "updated_at")
    @JsonIgnore
    open var updatedAt: Date? = null

    @Column(name = "deleted_at")
    @JsonIgnore
    open var deletedAt: Date? = null

    @Version
    @JsonIgnore
    open var version: Long? = null

    @PrePersist
    open fun prePersist() {
        Date().run {
            createdAt = this
            updatedAt = this
        }
    }

    @PreUpdate
    open fun preUpdate() {
        Date().run {
            updatedAt = this
        }
    }
}
