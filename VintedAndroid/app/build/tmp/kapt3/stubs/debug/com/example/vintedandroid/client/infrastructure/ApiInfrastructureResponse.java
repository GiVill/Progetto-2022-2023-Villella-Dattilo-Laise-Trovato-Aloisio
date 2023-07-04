package com.example.vintedandroid.client.infrastructure;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005R$\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t0\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/example/vintedandroid/client/infrastructure/ApiInfrastructureResponse;", "T", "", "responseType", "Lcom/example/vintedandroid/client/infrastructure/ResponseType;", "(Lcom/example/vintedandroid/client/infrastructure/ResponseType;)V", "headers", "", "", "", "getHeaders", "()Ljava/util/Map;", "getResponseType", "()Lcom/example/vintedandroid/client/infrastructure/ResponseType;", "statusCode", "", "getStatusCode", "()I", "app_debug"})
public abstract class ApiInfrastructureResponse<T extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull
    private final com.example.vintedandroid.client.infrastructure.ResponseType responseType = null;
    
    public ApiInfrastructureResponse(@org.jetbrains.annotations.NotNull
    com.example.vintedandroid.client.infrastructure.ResponseType responseType) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.vintedandroid.client.infrastructure.ResponseType getResponseType() {
        return null;
    }
    
    public abstract int getStatusCode();
    
    @org.jetbrains.annotations.NotNull
    public abstract java.util.Map<java.lang.String, java.util.List<java.lang.String>> getHeaders();
}