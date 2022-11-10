package com.example.liquibase.repository.db

import com.example.liquibase.db.TCItemVdoFile
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ITCItemVdoFileRepository : IBaseRepository<TCItemVdoFile> {

    @Query(
        value = "select * from tcitem_vdo_file tvd inner join TCFILE tf on tvd.FILE_ID = tf.ID where tvd.ITEM_ID = :itemId and tf.DELETED_AT is null order by tvd.order_by asc",
        nativeQuery = true
    )
    fun findAllByItemId(itemId: Long): MutableList<TCItemVdoFile>?
}
