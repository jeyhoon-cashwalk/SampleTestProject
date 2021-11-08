package com.gooseok.sample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gooseok.sample.network.GitUserInfo

@Database(entities = [GitUserInfo::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gitUserDao() : GitUserDao

    companion object{
        private val DB_NAME = "room-db"
        private var instance : AppDatabase? = null

        fun getInstance(ctx : Context):AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(ctx)
            }
        }

        private fun buildDatabase(ctx : Context) : AppDatabase{
            return Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }
    }
}