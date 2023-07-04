package com.example.vintedandroid.client.infrastructure;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J-\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\t0\b\"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0084\bJ(\u0010\r\u001a\u00020\u000e\"\u0006\b\u0000\u0010\t\u0018\u00012\u0006\u0010\u000f\u001a\u0002H\t2\b\b\u0002\u0010\u0010\u001a\u00020\u0003H\u0084\b\u00a2\u0006\u0002\u0010\u0011J,\u0010\u0012\u001a\u0004\u0018\u0001H\t\"\u0006\b\u0000\u0010\t\u0018\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0010\u001a\u00020\u0003H\u0084\b\u00a2\u0006\u0002\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0016"}, d2 = {"Lcom/example/vintedandroid/client/infrastructure/ApiClient;", "", "baseUrl", "", "(Ljava/lang/String;)V", "getBaseUrl", "()Ljava/lang/String;", "request", "Lcom/example/vintedandroid/client/infrastructure/ApiInfrastructureResponse;", "T", "requestConfig", "Lcom/example/vintedandroid/client/infrastructure/RequestConfig;", "body", "requestBody", "Lokhttp3/RequestBody;", "content", "mediaType", "(Ljava/lang/Object;Ljava/lang/String;)Lokhttp3/RequestBody;", "responseBody", "Lokhttp3/ResponseBody;", "(Lokhttp3/ResponseBody;Ljava/lang/String;)Ljava/lang/Object;", "Companion", "app_debug"})
public class ApiClient {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String baseUrl = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.vintedandroid.client.infrastructure.ApiClient.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    protected static final java.lang.String ContentType = "Content-Type";
    @org.jetbrains.annotations.NotNull
    protected static final java.lang.String Accept = "Accept";
    @org.jetbrains.annotations.NotNull
    protected static final java.lang.String JsonMediaType = "application/json";
    @org.jetbrains.annotations.NotNull
    protected static final java.lang.String FormDataMediaType = "multipart/form-data";
    @org.jetbrains.annotations.NotNull
    protected static final java.lang.String XmlMediaType = "application/xml";
    @org.jetbrains.annotations.NotNull
    private static final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlin.properties.ReadWriteProperty defaultHeaders$delegate = null;
    @org.jetbrains.annotations.NotNull
    private static final java.util.Map<java.lang.String, java.lang.String> jsonHeaders = null;
    
    public ApiClient(@org.jetbrains.annotations.NotNull
    java.lang.String baseUrl) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final okhttp3.OkHttpClient getClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.Map<java.lang.String, java.lang.String> getDefaultHeaders() {
        return null;
    }
    
    public static final void setDefaultHeaders(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.Map<java.lang.String, java.lang.String> getJsonHeaders() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0084T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0084T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0084T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0084T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0084T\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\rRI\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000f8F@FX\u0087\u008e\u0002\u00a2\u0006\u0018\n\u0004\b\u0016\u0010\u0017\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R(\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000f8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\u0013\u00a8\u0006\u001b"}, d2 = {"Lcom/example/vintedandroid/client/infrastructure/ApiClient$Companion;", "", "()V", "Accept", "", "ContentType", "FormDataMediaType", "JsonMediaType", "XmlMediaType", "client", "Lokhttp3/OkHttpClient;", "getClient$annotations", "getClient", "()Lokhttp3/OkHttpClient;", "<set-?>", "", "defaultHeaders", "getDefaultHeaders$annotations", "getDefaultHeaders", "()Ljava/util/Map;", "setDefaultHeaders", "(Ljava/util/Map;)V", "defaultHeaders$delegate", "Lkotlin/properties/ReadWriteProperty;", "jsonHeaders", "getJsonHeaders$annotations", "getJsonHeaders", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final okhttp3.OkHttpClient getClient() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic
        @java.lang.Deprecated
        public static void getClient$annotations() {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.Map<java.lang.String, java.lang.String> getDefaultHeaders() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic
        @java.lang.Deprecated
        public static void getDefaultHeaders$annotations() {
        }
        
        public final void setDefaultHeaders(@org.jetbrains.annotations.NotNull
        java.util.Map<java.lang.String, java.lang.String> p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.Map<java.lang.String, java.lang.String> getJsonHeaders() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic
        @java.lang.Deprecated
        public static void getJsonHeaders$annotations() {
        }
    }
}