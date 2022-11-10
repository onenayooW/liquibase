package com.example.liquibase.repository.db

import com.example.liquibase.db.TCDraftFile
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ITCDraftFileRepository : IBaseRepository<TCDraftFile> {

    fun findAllByIdIn(ids: MutableList<Long>): MutableList<TCDraftFile>?

    @Query(
        nativeQuery = true,
        value = "select * from TCDRAFT_FILE tdf where tdf.CREATED_AT > now() - (CAST(:expireTime AS INTEGER) * INTERVAL '1' HOUR)"
    )
    fun findIdAllByCreatedAtNotMoreThanExpireTime(@Param("expireTime") expireTime: Int): MutableList<TCDraftFile>?
}
