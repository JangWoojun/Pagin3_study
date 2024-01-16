package com.woojun.pagin3_study

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM User ORDER BY id ASC")
    fun allSelect(): PagingSource<Int, User>

    @Query("SELECT * FROM User ORDER BY id ASC")
    fun select(): List<User>

    @Insert
    fun insert(user: List<User>)
}

