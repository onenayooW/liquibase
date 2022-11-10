package com.example.liquibase.db

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@SQLDelete(sql = "UPDATE tcfile SET deletedAt = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is NULL")
@DiscriminatorValue(value = "image")
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
//@javax.persistence.Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class TCImageFile : TCFile() {

    @Column
    var width: Double? = null

    @Column
    var height: Double? = null
}
