package com.example.vintedandroid.client.apis;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\t\u00a8\u0006\n"}, d2 = {"Lcom/example/vintedandroid/client/apis/AuthApi;", "Lcom/example/vintedandroid/client/infrastructure/ApiClient;", "basePath", "", "(Ljava/lang/String;)V", "login", "body", "Lcom/example/vintedandroid/client/models/LoginUserDto;", "signUp", "Lcom/example/vintedandroid/client/models/NewUserDto;", "app_debug"})
public final class AuthApi extends com.example.vintedandroid.client.infrastructure.ApiClient {
    
    public AuthApi() {
        super(null);
    }
    
    public AuthApi(@org.jetbrains.annotations.NotNull
    java.lang.String basePath) {
        super(null);
    }
    
    /**
     * @param body  
     * @return kotlin.String
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final java.lang.String login(@org.jetbrains.annotations.NotNull
    com.example.vintedandroid.client.models.LoginUserDto body) {
        return null;
    }
    
    /**
     * @param body  
     * @return kotlin.String
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final java.lang.String signUp(@org.jetbrains.annotations.NotNull
    com.example.vintedandroid.client.models.NewUserDto body) {
        return null;
    }
}