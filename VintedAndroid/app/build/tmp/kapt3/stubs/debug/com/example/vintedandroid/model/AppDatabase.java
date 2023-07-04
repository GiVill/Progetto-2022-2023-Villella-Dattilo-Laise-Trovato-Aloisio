package com.example.vintedandroid.model;

import java.lang.System;

@androidx.room.Database(entities = {com.example.vintedandroid.model.dto.UserDto.class, com.example.vintedandroid.model.dto.OrderDto.class, com.example.vintedandroid.model.dto.PaymentDto.class, com.example.vintedandroid.model.dto.BuyingOfferDto.class, com.example.vintedandroid.model.dto.BasicInsertionDto.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/example/vintedandroid/model/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "basicInsertionDao", "Lcom/example/vintedandroid/model/dao/BasicInsertionDao;", "buyingOfferDao", "Lcom/example/vintedandroid/model/dao/BuyingOfferDao;", "orderDao", "Lcom/example/vintedandroid/model/dao/OrderDao;", "paymentDao", "Lcom/example/vintedandroid/model/dao/PaymentDao;", "userDao", "Lcom/example/vintedandroid/model/dao/UserDao;", "Companion", "app_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull
    public static final com.example.vintedandroid.model.AppDatabase.Companion Companion = null;
    @kotlin.jvm.Volatile
    private static volatile com.example.vintedandroid.model.AppDatabase instance;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.vintedandroid.model.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.vintedandroid.model.dao.PaymentDao paymentDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.vintedandroid.model.dao.OrderDao orderDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.vintedandroid.model.dao.BuyingOfferDao buyingOfferDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.vintedandroid.model.dao.BasicInsertionDao basicInsertionDao();
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/vintedandroid/model/AppDatabase$Companion;", "", "()V", "instance", "Lcom/example/vintedandroid/model/AppDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.vintedandroid.model.AppDatabase getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}