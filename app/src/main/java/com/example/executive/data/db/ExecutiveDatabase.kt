package com.example.executive.data.db

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.executive.data.dao.CountryDao
import com.example.executive.data.dao.DataConverter
import com.example.executive.data.model.ResponseData


@Database(entities = [ResponseData::class], version = 1, exportSchema = false)
@TypeConverters(value = [DataConverter::class] )
abstract class ExecutiveDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        private var instance: ExecutiveDatabase? = null

        @SuppressLint("SuspiciousIndentation")
        @Synchronized
        fun getInstance(ctx: Context): ExecutiveDatabase {
            if (instance == null)
                instance= Room.databaseBuilder(ctx,ExecutiveDatabase::class.java,
                "executive_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
                return instance!!
        }
        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}