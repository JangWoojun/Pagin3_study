package com.woojun.pagin3_study

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: RoomDB? = null

        @Synchronized
        fun getInstance(context: Context): RoomDB = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                RoomDB::class.java, "User"
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    fillInDb(context.applicationContext)
                }
            }).build()
        }

        private fun fillInDb(context: Context) {
            CoroutineScope(Dispatchers.IO).launch {
                getInstance(context).userDao().insert(
                    getInitRoomData()
                )
            }
        }
    }
}

fun getInitRoomData():List<User>{
    val data = arrayListOf<User>()
    for(i in 0 until 50){
        data.add(User("${FIRST_NAME[i]}@${LAST_NAME[i]}","https://picsum.photos/200", FIRST_NAME[i],
            LAST_NAME[i]))
    }
    return data
}
private val FIRST_NAME = arrayListOf(
    "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "Cras", "pharetra", "nec", "ligula", "vel", "consequat", "Duis", "quis", "neque", "volutpat", "pellentesque", "orci", "id", "laoreet", "magna", "Duis",
    "ullamcorper", "sapien", "in", "tortor", "rutrum", "quis", "egestas", "tortor", "gravida", "Suspendisse", "potenti", "Praesent", "finibus", "ac", "ligula", "et", "sodales", "Ut", "non", "ante", "at", "mauris", "tincidunt", "pulvinar",
    "Orci", "varius"
)
private val LAST_NAME = arrayListOf(
    "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "Curabitur", "convallis", "quis", "ante", "a", "laoreet", "Aliquam", "vulputate", "vel", "massa", "ac", "efficitur", "Cras", "pulvinar", "euismod", "purus",
    "Praesent", "ut", "semper", "velit", "In", "varius", "hendrerit", "massa", "et", "eleifend", "Nam", "faucibus", "pulvinar", "eros", "Morbi", "lacinia", "arcu", "sit", "amet", "dui", "luctus", "eget", "viverra", "turpis", "elementum",
    "Fusce"
)