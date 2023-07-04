package com.example.vintedandroid.client.apis;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0011\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r\u00a8\u0006\u0014"}, d2 = {"Lcom/example/vintedandroid/client/apis/PaymentApi;", "Lcom/example/vintedandroid/client/infrastructure/ApiClient;", "basePath", "", "(Ljava/lang/String;)V", "addPayment", "Lcom/example/vintedandroid/client/models/PaymentDto;", "body", "all1", "", "()[Lcom/example/vintedandroid/client/models/PaymentDto;", "deleteOffer", "idPayment", "", "getAllPaymentByUser", "Lcom/example/vintedandroid/client/models/PagePaymentDto;", "userId", "page", "", "getById1", "app_debug"})
public final class PaymentApi extends com.example.vintedandroid.client.infrastructure.ApiClient {
    
    public PaymentApi() {
        super(null);
    }
    
    public PaymentApi(@org.jetbrains.annotations.NotNull
    java.lang.String basePath) {
        super(null);
    }
    
    /**
     * @param body  
     * @return PaymentDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PaymentDto addPayment(@org.jetbrains.annotations.NotNull
    com.example.vintedandroid.client.models.PaymentDto body) {
        return null;
    }
    
    /**
     * @return kotlin.Array<PaymentDto>
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PaymentDto[] all1() {
        return null;
    }
    
    /**
     * @param idPayment  
     * @return kotlin.String
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final java.lang.String deleteOffer(long idPayment) {
        return null;
    }
    
    /**
     * @param userId  
     * @param page  
     * @return PagePaymentDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PagePaymentDto getAllPaymentByUser(long userId, int page) {
        return null;
    }
    
    /**
     * @param idPayment  
     * @return PaymentDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.PaymentDto getById1(long idPayment) {
        return null;
    }
}