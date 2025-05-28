package com.coutinho.estereofinance.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coutinho.estereofinance.data.entity.*
import com.coutinho.estereofinance.data.local.dao.*
import com.coutinho.estereofinance.data.utils.Converters

@Database(
    entities = [User::class, Category::class, Moviment::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun movimentDao(): MovimentDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "estereo_finance.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}