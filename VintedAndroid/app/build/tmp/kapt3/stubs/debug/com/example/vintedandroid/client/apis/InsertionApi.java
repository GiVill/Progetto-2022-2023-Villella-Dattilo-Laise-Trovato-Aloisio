package com.example.vintedandroid.client.apis;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000fJ\u0016\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000fJ\u0016\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0003J&\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0003\u00a8\u0006%"}, d2 = {"Lcom/example/vintedandroid/client/apis/InsertionApi;", "Lcom/example/vintedandroid/client/infrastructure/ApiClient;", "basePath", "", "(Ljava/lang/String;)V", "addInsertion", "Lcom/example/vintedandroid/client/models/BasicInsertionDto;", "body", "all4", "Lcom/example/vintedandroid/client/models/PageBasicInsertionDto;", "page", "", "deleteAllInsertionByUserId", "", "userId", "", "deleteInsertionById", "id", "generateCapabilities", "idInsertion", "getByBrand", "brand", "getByCategory", "category", "getByTitle", "title", "getInsertionById", "getInsertionByUserId", "idUser", "getPrivateInsertion", "token", "modifyInsertionById", "", "insertionId", "price", "", "description", "app_debug"})
public final class InsertionApi extends com.example.vintedandroid.client.infrastructure.ApiClient {
    
    public InsertionApi() {
        super(null);
    }
    
    public InsertionApi(@org.jetbrains.annotations.NotNull
    java.lang.String basePath) {
        super(null);
    }
    
    /**
     * @param body  
     * @return BasicInsertionDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.BasicInsertionDto addInsertion(@org.jetbrains.annotations.NotNull
    com.example.vintedandroid.client.models.BasicInsertionDto body) {
        return null;
    }
    
    /**
     * @param page  
     * @return PageBasicInsertionDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PageBasicInsertionDto all4(int page) {
        return null;
    }
    
    /**
     * @param userId  
     * @return void
     */
    public final void deleteAllInsertionByUserId(long userId) {
    }
    
    /**
     * @param id  
     * @return void
     */
    public final void deleteInsertionById(long id) {
    }
    
    /**
     * @param idInsertion  
     * @return kotlin.String
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final java.lang.String generateCapabilities(long idInsertion) {
        return null;
    }
    
    /**
     * @param brand  
     * @param page  
     * @return PageBasicInsertionDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PageBasicInsertionDto getByBrand(@org.jetbrains.annotations.NotNull
    java.lang.String brand, int page) {
        return null;
    }
    
    /**
     * @param category  
     * @param page  
     * @return PageBasicInsertionDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PageBasicInsertionDto getByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category, int page) {
        return null;
    }
    
    /**
     * @param title  
     * @param page  
     * @return PageBasicInsertionDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PageBasicInsertionDto getByTitle(@org.jetbrains.annotations.NotNull
    java.lang.String title, int page) {
        return null;
    }
    
    /**
     * @param id  
     * @return BasicInsertionDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.BasicInsertionDto getInsertionById(long id) {
        return null;
    }
    
    /**
     * @param idUser  
     * @param page  
     * @return PageBasicInsertionDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PageBasicInsertionDto getInsertionByUserId(long idUser, int page) {
        return null;
    }
    
    /**
     * @param token  
     * @return BasicInsertionDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.BasicInsertionDto getPrivateInsertion(@org.jetbrains.annotations.NotNull
    java.lang.String token) {
        return null;
    }
    
    /**
     * @param insertionId  
     * @param title  
     * @param price  
     * @param description  
     * @return kotlin.Boolean
     */
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final boolean modifyInsertionById(long insertionId, @org.jetbrains.annotations.NotNull
    java.lang.String title, float price, @org.jetbrains.annotations.NotNull
    java.lang.String description) {
        return false;
    }
}