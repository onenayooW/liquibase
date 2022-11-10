package com.example.liquibase.repository.db

import com.example.liquibase.db.TCDraftOtherFile
import org.springframework.stereotype.Repository

@Repository
interface ITCDraftOtherFileRepository : IBaseRepository<TCDraftOtherFile> {

    fun findAllByIdIn(ids: MutableList<Long>): MutableList<TCDraftOtherFile>?
}
