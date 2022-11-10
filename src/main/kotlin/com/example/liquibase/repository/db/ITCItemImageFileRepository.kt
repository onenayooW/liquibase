package com.example.liquibase.repository.db

import com.example.liquibase.db.TCItemImageFile
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ITCItemImageFileRepository : IBaseRepository<TCItemImageFile> {

    @Query(
        value = "select count(tf.ID) from TCITEM_IMAGE_FILE tmf inner join TCFILE tf on tmf.FILE_ID = tf.ID where tf.ORIGINAL_FILE_HASH in (:encodedHashes) and tf.USER_ID = :userId and tf.DELETED_AT is null",
        nativeQuery = true
    )
    fun countAllByEncodedHashes(
        @Param("encodedHashes") encodedHashes: MutableList<String>,
        @Param("userId") userId: Long
    ): Int

    @Query(
        value = "select * from TCITEM_IMAGE_FILE tmf inner join TCFILE tf on tmf.FILE_ID = tf.ID where tmf.ITEM_ID = :itemId and tf.DELETED_AT is null order by tmf.order_by asc",
        nativeQuery = true
    )
    fun findAllByItemIdOrderByOrderByAsc(@Param("itemId") itemId: Long): MutableList<TCItemImageFile>?
}
