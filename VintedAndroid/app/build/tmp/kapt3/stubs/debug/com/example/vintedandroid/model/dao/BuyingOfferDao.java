package com.example.vintedandroid.model.dao;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\'\u00a8\u0006\b"}, d2 = {"Lcom/example/vintedandroid/model/dao/BuyingOfferDao;", "", "getAll", "", "Lcom/example/vintedandroid/model/dto/BuyingOfferDto;", "getById", "buyingId", "", "app_debug"})
public abstract interface BuyingOfferDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "select * from buyingOfferDto")
    public abstract java.util.List<com.example.vintedandroid.model.dto.BuyingOfferDto> getAll();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "select * from buyingOfferDto where id = :buyingId")
    public abstract com.example.vintedandroid.model.dto.BuyingOfferDto getById(long buyingId);
}