package com.example.liquibase.repository.db

import com.example.liquibase.db.TCBaseEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.util.*

@NoRepositoryBean
interface IBaseRepository
<BaseEntityGenericClass : TCBaseEntity> : PagingAndSortingRepository<BaseEntityGenericClass, Long> {

    @Query("select a from #{#entityName} a where a.createdAt >= :afterDateTime")
    fun findByCreatedAtAfter(
        @Param("afterDateTime") afterDateTime: Date?
    ): MutableList<BaseEntityGenericClass>?

    @Query("select a from #{#entityName} a where a.updatedAt >= :afterDateTime")
    fun findByUpdatedAtAfter(
        @Param("afterDateTime") afterDateTime: Date?
    ): MutableList<BaseEntityGenericClass>?

    @Modifying
    @Query("UPDATE #{#entityName} u set u.deletedAt = CURRENT_TIMESTAMP where u.id = ?1")
    fun softDelete(id: Long)
}
