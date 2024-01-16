package com.woojun.pagin3_study

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val email: String?,
    val avatar: String?,

    @ColumnInfo(name = "first_name") val firstName: String?,

    @ColumnInfo(name = "last_name") val lastName: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)