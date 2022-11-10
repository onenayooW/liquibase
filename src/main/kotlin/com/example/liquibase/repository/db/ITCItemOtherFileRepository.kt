package com.example.liquibase.repository.db

import com.example.liquibase.db.TCItemPrivateImageFile
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ITCItemOtherFileRepository : IBaseRepository<TCItemPrivateImageFile> {

    @Query(
        value = "select * from TCITEM_PRIVATE_IMAGE_FILE tpif inner join TCFILE tf on tpif.FILE_ID = tf.ID where tpif.ITEM_ID = :itemId and tf.DELETED_AT is null order by tpif.order_by asc",
        nativeQuery = true
    )
    fun findAllByItemId(itemId: Long): MutableList<TCItemPrivateImageFile>?
}
