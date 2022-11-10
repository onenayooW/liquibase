package com.example.liquibase.db

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@SQLDelete(sql = "UPDATE tcdraft_file SET deletedAt = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is NULL")
@DiscriminatorValue(value = "vdo")
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
class TCDraftVdoFile : TCDraftFile()
