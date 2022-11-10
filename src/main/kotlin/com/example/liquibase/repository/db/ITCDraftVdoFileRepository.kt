package com.example.liquibase.repository.db

import com.example.liquibase.db.TCDraftVdoFile
import org.springframework.stereotype.Repository

@Repository
interface ITCDraftVdoFileRepository : IBaseRepository<TCDraftVdoFile> {

    fun findAllByIdIn(ids: MutableList<Long>): MutableList<TCDraftVdoFile>?
}
