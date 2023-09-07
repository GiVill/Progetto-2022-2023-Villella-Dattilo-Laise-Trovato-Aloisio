package com.example.vintedandroid.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vintedandroid.model.dao.CartDao
import com.example.vintedandroid.model.dto.UserDatabaseDto
import com.example.vintedandroid.model.dto.CartDto
import com.example.vintedandroid.model.dao.UserDatabaseDao


@Database(entities = [UserDatabaseDto::class, CartDto::class], version = 4, exportSchema = false) //Il valore di version deve essere equivalente (o superiore di 1) al numero di entities inserite
//@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDatabaseDao(): UserDatabaseDao
    abstract fun cartDao(): CartDao


    companion object {
        // A volatile variable is never cached, and it is modified/read from the main memory.
        // Any change made by one thread is visible to all other threads.
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // We guarantee that the database is initialized once
            synchronized(this) {
                var localInstance = instance // Needed for smart cast
                if (localInstance == null) {
                    localInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "android-database" // The name of the database
                    ).fallbackToDestructiveMigration().build()
                    instance = localInstance
                }
                return localInstance
            }
        }
    }
}