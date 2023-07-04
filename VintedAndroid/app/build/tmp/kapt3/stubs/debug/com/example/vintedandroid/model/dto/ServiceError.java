package com.example.vintedandroid.model.dto;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\r"}, d2 = {"Lcom/example/vintedandroid/model/dto/ServiceError;", "", "timestamp", "Ljava/time/LocalDateTime;", "url", "", "message", "(Ljava/time/LocalDateTime;Ljava/lang/String;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getTimestamp", "()Ljava/time/LocalDateTime;", "getUrl", "app_debug"})
public final class ServiceError {
    @org.jetbrains.annotations.NotNull
    private final java.time.LocalDateTime timestamp = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String url = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String message = null;
    
    public ServiceError(@org.jetbrains.annotations.NotNull
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    java.time.LocalDateTime timestamp, @org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    java.lang.String message) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.time.LocalDateTime getTimestamp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMessage() {
        return null;
    }
}