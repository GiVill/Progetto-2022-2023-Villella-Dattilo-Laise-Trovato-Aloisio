package com.example.vintedandroid.client.apis;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0011\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\f\u00a8\u0006\u0011"}, d2 = {"Lcom/example/vintedandroid/client/apis/UserApi;", "Lcom/example/vintedandroid/client/infrastructure/ApiClient;", "basePath", "", "(Ljava/lang/String;)V", "addUser", "Lcom/example/vintedandroid/client/models/UserDto;", "body", "all", "", "()[Lcom/example/vintedandroid/client/models/UserDto;", "deleteUserById", "", "idUser", "", "getById", "swagger", "app_debug"})
public final class UserApi extends com.example.vintedandroid.client.infrastructure.ApiClient {
    
    public UserApi() {
        super(null);
    }
    
    public UserApi(@org.jetbrains.annotations.NotNull
    java.lang.String basePath) {
        super(null);
    }
    
    /**
     * @param body  
     * @return UserDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.UserDto addUser(@org.jetbrains.annotations.NotNull
    com.example.vintedandroid.client.models.UserDto body) {
        return null;
    }
    
    /**
     * this is the list of user
     * Get endpoint for user
     * @return kotlin.Array<UserDto>
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.UserDto[] all() {
        return null;
    }
    
    /**
     * @param idUser  
     * @return void
     */
    public final void deleteUserById(long idUser) {
    }
    
    /**
     * @param idUser  
     * @return UserDto
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public final com.example.vintedandroid.client.models.UserDto getById(long idUser) {
        return null;
    }
    
    /**
     * @return void
     */
    public final void swagger() {
    }
}