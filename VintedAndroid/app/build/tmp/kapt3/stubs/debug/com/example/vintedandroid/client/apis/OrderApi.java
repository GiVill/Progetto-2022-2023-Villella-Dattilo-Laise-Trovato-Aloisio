package com.example.vintedandroid.client.apis;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f\u00a8\u0006\u0011"}, d2 = {"Lcom/example/vintedandroid/client/apis/OrderApi;", "Lcom/example/vintedandroid/client/infrastructure/ApiClient;", "basePath", "", "(Ljava/lang/String;)V", "addOrder", "Lcom/example/vintedandroid/client/models/OrderDto;", "body", "all2", "Lcom/example/vintedandroid/client/models/PageOrderDto;", "page", "", "deleteOrderById", "", "id", "", "getOrderById", "app_debug"})
public final class OrderApi extends com.example.vintedandroid.client.infrastructure.ApiClient {
    
    public OrderApi() {
        super(null);
    }
    
    public OrderApi(@org.jetbrains.annotations.NotNull
    java.lang.String basePath) {
        super(null);
    }
    
    /**
     * @param body  
     * @return OrderDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.OrderDto addOrder(@org.jetbrains.annotations.NotNull
    com.example.vintedandroid.client.models.OrderDto body) {
        return null;
    }
    
    /**
     * @param page  
     * @return PageOrderDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PageOrderDto all2(int page) {
        return null;
    }
    
    /**
     * @param id  
     * @return void
     */
    public final void deleteOrderById(long id) {
    }
    
    /**
     * @param id  
     * @return OrderDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.OrderDto getOrderById(long id) {
        return null;
    }
}