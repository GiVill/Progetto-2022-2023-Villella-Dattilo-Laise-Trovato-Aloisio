package com.example.vintedandroid.client.models;

import java.lang.System;

/**
 * @param id 
 * @param title 
 * @param price 
 * @param description 
 * @param condition 
 * @param creationDate 
 * @param isPrivate 
 * @param endDate 
 * @param imageName 
 * @param brand 
 * @param category 
 * @param userId
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001:\u0002;<B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0014J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\fH\u00c6\u0003\u00a2\u0006\u0002\u0010\"J\u000b\u00102\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0096\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u00105J\u0013\u00106\u001a\u00020\f2\b\u00107\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00108\u001a\u000209H\u00d6\u0001J\t\u0010:\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\n\n\u0002\u0010#\u001a\u0004\b\u000b\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010 \u00a8\u0006="}, d2 = {"Lcom/example/vintedandroid/client/models/BasicInsertionDto;", "", "id", "", "title", "", "price", "", "description", "condition", "creationDate", "isPrivate", "", "endDate", "imageName", "brand", "Lcom/example/vintedandroid/client/models/BasicInsertionDto$Brand;", "category", "Lcom/example/vintedandroid/client/models/BasicInsertionDto$Category;", "userId", "(JLjava/lang/String;FLjava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Boolean;Ljava/lang/Object;Ljava/lang/String;Lcom/example/vintedandroid/client/models/BasicInsertionDto$Brand;Lcom/example/vintedandroid/client/models/BasicInsertionDto$Category;J)V", "getBrand", "()Lcom/example/vintedandroid/client/models/BasicInsertionDto$Brand;", "getCategory", "()Lcom/example/vintedandroid/client/models/BasicInsertionDto$Category;", "getCondition", "()Ljava/lang/String;", "getCreationDate", "()Ljava/lang/Object;", "getDescription", "getEndDate", "getId", "()J", "getImageName", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getPrice", "()F", "getTitle", "getUserId", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;FLjava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Boolean;Ljava/lang/Object;Ljava/lang/String;Lcom/example/vintedandroid/client/models/BasicInsertionDto$Brand;Lcom/example/vintedandroid/client/models/BasicInsertionDto$Category;J)Lcom/example/vintedandroid/client/models/BasicInsertionDto;", "equals", "other", "hashCode", "", "toString", "Brand", "Category", "app_debug"})
public final class BasicInsertionDto {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = null;
    private final float price = 0.0F;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String description = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String condition = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object creationDate = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Boolean isPrivate = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object endDate = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String imageName = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.vintedandroid.client.models.BasicInsertionDto.Brand brand = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.vintedandroid.client.models.BasicInsertionDto.Category category = null;
    private final long userId = 0L;
    
    /**
     * @param id 
     * @param title 
     * @param price 
     * @param description 
     * @param condition 
     * @param creationDate 
     * @param isPrivate 
     * @param endDate 
     * @param imageName 
     * @param brand 
     * @param category 
     * @param userId
     */
    @org.jetbrains.annotations.NotNull
    public final com.example.vintedandroid.client.models.BasicInsertionDto copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String title, float price, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String condition, @org.jetbrains.annotations.Nullable
    java.lang.Object creationDate, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isPrivate, @org.jetbrains.annotations.Nullable
    java.lang.Object endDate, @org.jetbrains.annotations.Nullable
    java.lang.String imageName, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.BasicInsertionDto.Brand brand, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.BasicInsertionDto.Category category, long userId) {
        return null;
    }
    
    /**
     * @param id 
     * @param title 
     * @param price 
     * @param description 
     * @param condition 
     * @param creationDate 
     * @param isPrivate 
     * @param endDate 
     * @param imageName 
     * @param brand 
     * @param category 
     * @param userId
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * @param id 
     * @param title 
     * @param price 
     * @param description 
     * @param condition 
     * @param creationDate 
     * @param isPrivate 
     * @param endDate 
     * @param imageName 
     * @param brand 
     * @param category 
     * @param userId
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * @param id 
     * @param title 
     * @param price 
     * @param description 
     * @param condition 
     * @param creationDate 
     * @param isPrivate 
     * @param endDate 
     * @param imageName 
     * @param brand 
     * @param category 
     * @param userId
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public BasicInsertionDto(long id, @org.jetbrains.annotations.NotNull
    java.lang.String title, float price, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String condition, @org.jetbrains.annotations.Nullable
    java.lang.Object creationDate, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isPrivate, @org.jetbrains.annotations.Nullable
    java.lang.Object endDate, @org.jetbrains.annotations.Nullable
    java.lang.String imageName, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.BasicInsertionDto.Brand brand, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.BasicInsertionDto.Category category, long userId) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final float getPrice() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCondition() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCreationDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean isPrivate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImageName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.BasicInsertionDto.Brand component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.BasicInsertionDto.Brand getBrand() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.BasicInsertionDto.Category component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.BasicInsertionDto.Category getCategory() {
        return null;
    }
    
    public final long component12() {
        return 0L;
    }
    
    public final long getUserId() {
        return 0L;
    }
    
    /**
     * Values: ADIDAS,PUMA,NIKE,MICROSOFT,XIAOMI,NOTHING,GUESS,PUMPLING,FILA,ONZE,GOJIANG,REBOOK,NEWBALANCE,ASIX,SONY
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/example/vintedandroid/client/models/BasicInsertionDto$Brand;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ADIDAS", "PUMA", "NIKE", "MICROSOFT", "XIAOMI", "NOTHING", "GUESS", "PUMPLING", "FILA", "ONZE", "GOJIANG", "REBOOK", "NEWBALANCE", "ASIX", "SONY", "app_debug"})
    public static enum Brand {
        /*public static final*/ ADIDAS /* = new ADIDAS(null) */,
        /*public static final*/ PUMA /* = new PUMA(null) */,
        /*public static final*/ NIKE /* = new NIKE(null) */,
        /*public static final*/ MICROSOFT /* = new MICROSOFT(null) */,
        /*public static final*/ XIAOMI /* = new XIAOMI(null) */,
        /*public static final*/ NOTHING /* = new NOTHING(null) */,
        /*public static final*/ GUESS /* = new GUESS(null) */,
        /*public static final*/ PUMPLING /* = new PUMPLING(null) */,
        /*public static final*/ FILA /* = new FILA(null) */,
        /*public static final*/ ONZE /* = new ONZE(null) */,
        /*public static final*/ GOJIANG /* = new GOJIANG(null) */,
        /*public static final*/ REBOOK /* = new REBOOK(null) */,
        /*public static final*/ NEWBALANCE /* = new NEWBALANCE(null) */,
        /*public static final*/ ASIX /* = new ASIX(null) */,
        /*public static final*/ SONY /* = new SONY(null) */;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String value = null;
        
        Brand(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getValue() {
            return null;
        }
    }
    
    /**
     * Values: ELETTRONICA,COLLEZIONI,ABBIGLIAMENTO,BARCHE,SPORT,DONNA,UOMO,BAMBINI
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/example/vintedandroid/client/models/BasicInsertionDto$Category;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ELETTRONICA", "COLLEZIONI", "ABBIGLIAMENTO", "BARCHE", "SPORT", "DONNA", "UOMO", "BAMBINI", "app_debug"})
    public static enum Category {
        /*public static final*/ ELETTRONICA /* = new ELETTRONICA(null) */,
        /*public static final*/ COLLEZIONI /* = new COLLEZIONI(null) */,
        /*public static final*/ ABBIGLIAMENTO /* = new ABBIGLIAMENTO(null) */,
        /*public static final*/ BARCHE /* = new BARCHE(null) */,
        /*public static final*/ SPORT /* = new SPORT(null) */,
        /*public static final*/ DONNA /* = new DONNA(null) */,
        /*public static final*/ UOMO /* = new UOMO(null) */,
        /*public static final*/ BAMBINI /* = new BAMBINI(null) */;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String value = null;
        
        Category(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getValue() {
            return null;
        }
    }
}