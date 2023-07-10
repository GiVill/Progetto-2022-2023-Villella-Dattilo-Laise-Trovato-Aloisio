package com.example.vintedandroid.client.models;

import java.lang.System;

/**
 * @param totalElements 
 * @param totalPages 
 * @param size 
 * @param content 
 * @param number 
 * @param sort 
 * @param pageable 
 * @param first 
 * @param last 
 * @param numberOfElements 
 * @param empty
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b*\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010+\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001eJ\u0010\u0010,\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010-\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001eJ\u0010\u0010.\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010/\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\u0010\u00100\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001eJ\u000b\u00101\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\u0010\u00104\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\u0098\u0001\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010H\u00c6\u0001\u00a2\u0006\u0002\u00106J\u0013\u00107\u001a\u00020\u00102\b\u00108\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00109\u001a\u00020\u0005H\u0016J\t\u0010:\u001a\u00020;H\u00d6\u0001R\u001b\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001c\u0010\u0019R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b \u0010\u001eR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b#\u0010\u001eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b&\u0010\'R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b)\u0010\u001e\u00a8\u0006<"}, d2 = {"Lcom/example/vintedandroid/client/models/PagePaymentDto;", "", "totalElements", "", "totalPages", "", "size", "content", "", "Lcom/example/vintedandroid/client/models/PaymentDto;", "number", "sort", "Lcom/example/vintedandroid/client/models/SortObject;", "pageable", "Lcom/example/vintedandroid/client/models/PageableObject;", "first", "", "last", "numberOfElements", "empty", "(Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;[Lcom/example/vintedandroid/client/models/PaymentDto;Ljava/lang/Integer;Lcom/example/vintedandroid/client/models/SortObject;Lcom/example/vintedandroid/client/models/PageableObject;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "getContent", "()[Lcom/example/vintedandroid/client/models/PaymentDto;", "[Lcom/example/vintedandroid/client/models/PaymentDto;", "getEmpty", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getFirst", "getLast", "getNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNumberOfElements", "getPageable", "()Lcom/example/vintedandroid/client/models/PageableObject;", "getSize", "getSort", "()Lcom/example/vintedandroid/client/models/SortObject;", "getTotalElements", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTotalPages", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;[Lcom/example/vintedandroid/client/models/PaymentDto;Ljava/lang/Integer;Lcom/example/vintedandroid/client/models/SortObject;Lcom/example/vintedandroid/client/models/PageableObject;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;)Lcom/example/vintedandroid/client/models/PagePaymentDto;", "equals", "other", "hashCode", "toString", "", "app_debug"})
public final class PagePaymentDto {
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long totalElements = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer totalPages = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer size = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.vintedandroid.client.models.PaymentDto[] content = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer number = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.vintedandroid.client.models.SortObject sort = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.vintedandroid.client.models.PageableObject pageable = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Boolean first = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Boolean last = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer numberOfElements = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Boolean empty = null;
    
    /**
     * @param totalElements 
     * @param totalPages 
     * @param size 
     * @param content 
     * @param number 
     * @param sort 
     * @param pageable 
     * @param first 
     * @param last 
     * @param numberOfElements 
     * @param empty
     */
    @org.jetbrains.annotations.NotNull
    public final com.example.vintedandroid.client.models.PagePaymentDto copy(@org.jetbrains.annotations.Nullable
    java.lang.Long totalElements, @org.jetbrains.annotations.Nullable
    java.lang.Integer totalPages, @org.jetbrains.annotations.Nullable
    java.lang.Integer size, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.PaymentDto[] content, @org.jetbrains.annotations.Nullable
    java.lang.Integer number, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.SortObject sort, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.PageableObject pageable, @org.jetbrains.annotations.Nullable
    java.lang.Boolean first, @org.jetbrains.annotations.Nullable
    java.lang.Boolean last, @org.jetbrains.annotations.Nullable
    java.lang.Integer numberOfElements, @org.jetbrains.annotations.Nullable
    java.lang.Boolean empty) {
        return null;
    }
    
    /**
     * @param totalElements 
     * @param totalPages 
     * @param size 
     * @param content 
     * @param number 
     * @param sort 
     * @param pageable 
     * @param first 
     * @param last 
     * @param numberOfElements 
     * @param empty
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public PagePaymentDto() {
        super();
    }
    
    public PagePaymentDto(@org.jetbrains.annotations.Nullable
    java.lang.Long totalElements, @org.jetbrains.annotations.Nullable
    java.lang.Integer totalPages, @org.jetbrains.annotations.Nullable
    java.lang.Integer size, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.PaymentDto[] content, @org.jetbrains.annotations.Nullable
    java.lang.Integer number, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.SortObject sort, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.PageableObject pageable, @org.jetbrains.annotations.Nullable
    java.lang.Boolean first, @org.jetbrains.annotations.Nullable
    java.lang.Boolean last, @org.jetbrains.annotations.Nullable
    java.lang.Integer numberOfElements, @org.jetbrains.annotations.Nullable
    java.lang.Boolean empty) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getTotalElements() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getTotalPages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getSize() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.PaymentDto[] component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.PaymentDto[] getContent() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.SortObject component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.SortObject getSort() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.PageableObject component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.PageableObject getPageable() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getFirst() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getLast() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getNumberOfElements() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getEmpty() {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
}