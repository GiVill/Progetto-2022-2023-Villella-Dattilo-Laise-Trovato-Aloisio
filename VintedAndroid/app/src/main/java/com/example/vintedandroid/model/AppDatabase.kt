package com.example.vintedandroid.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.vintedandroid.dto.BasicInsertionDto
import com.example.vintedandroid.dto.BuyingOfferDto
import com.example.vintedandroid.dto.OrderDto
import com.example.vintedandroid.dto.PaymentDto
import com.example.vintedandroid.dto.UserDto
import com.example.vintedandroid.model.dao.BasicInsertionDao
import com.example.vintedandroid.model.dao.BuyingOfferDao
import com.example.vintedandroid.model.dao.OrderDao
import com.example.vintedandroid.model.dao.PaymentDao
import com.example.vintedandroid.model.dao.UserDao

@Database(entities = [UserDto::class, OrderDto::class, PaymentDto::class, BuyingOfferDto::class, BasicInsertionDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun paymentDao(): PaymentDao
    abstract fun orderDao(): OrderDao
    abstract fun buyingOfferDao(): BuyingOfferDao
    abstract fun basicInsertionDao(): BasicInsertionDao

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
                    ).build()
                    instance = localInstance
                }
                return localInstance
            }
        }
    }
}