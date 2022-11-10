package com.example.liquibase.repository.db

import com.example.liquibase.db.TCDraftImageFile
import org.springframework.stereotype.Repository

@Repository
interface ITCDraftImageFileRepository : IBaseRepository<TCDraftImageFile> {

    fun findAllByIdIn(ids: MutableList<Long>): MutableList<TCDraftImageFile>?
}
