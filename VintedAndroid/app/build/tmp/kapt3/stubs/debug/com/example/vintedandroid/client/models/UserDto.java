package com.example.vintedandroid.client.models;

import java.lang.System;

/**
 * @param id 
 * @param nickName 
 * @param firstName 
 * @param lastName 
 * @param imageName 
 * @param email 
 * @param birthDate 
 * @param gender 
 * @param addressStreet 
 * @param addressNumber 
 * @param addressCity 
 * @param addressCap 
 * @param addressState 
 * @param addressRegion
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0001;B\u00a5\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0013J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\'\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00b2\u0001\u00104\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u00105J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00109\u001a\u00020\u000eH\u00d6\u0001J\t\u0010:\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0019\u0010\u0015R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018\u00a8\u0006<"}, d2 = {"Lcom/example/vintedandroid/client/models/UserDto;", "", "id", "", "nickName", "firstName", "lastName", "imageName", "email", "birthDate", "gender", "Lcom/example/vintedandroid/client/models/UserDto$Gender;", "addressStreet", "addressNumber", "", "addressCity", "addressCap", "addressState", "addressRegion", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/vintedandroid/client/models/UserDto$Gender;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getAddressCap", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAddressCity", "()Ljava/lang/String;", "getAddressNumber", "getAddressRegion", "getAddressState", "getAddressStreet", "getBirthDate", "getEmail", "getFirstName", "getGender", "()Lcom/example/vintedandroid/client/models/UserDto$Gender;", "getId", "getImageName", "getLastName", "getNickName", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/vintedandroid/client/models/UserDto$Gender;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/example/vintedandroid/client/models/UserDto;", "equals", "", "other", "hashCode", "toString", "Gender", "app_debug"})
public final class UserDto {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String nickName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String firstName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String lastName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String imageName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String email = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String birthDate = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.vintedandroid.client.models.UserDto.Gender gender = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String addressStreet = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer addressNumber = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String addressCity = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer addressCap = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String addressState = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String addressRegion = null;
    
    /**
     * @param id 
     * @param nickName 
     * @param firstName 
     * @param lastName 
     * @param imageName 
     * @param email 
     * @param birthDate 
     * @param gender 
     * @param addressStreet 
     * @param addressNumber 
     * @param addressCity 
     * @param addressCap 
     * @param addressState 
     * @param addressRegion
     */
    @org.jetbrains.annotations.NotNull
    public final com.example.vintedandroid.client.models.UserDto copy(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String nickName, @org.jetbrains.annotations.NotNull
    java.lang.String firstName, @org.jetbrains.annotations.Nullable
    java.lang.String lastName, @org.jetbrains.annotations.Nullable
    java.lang.String imageName, @org.jetbrains.annotations.Nullable
    java.lang.String email, @org.jetbrains.annotations.Nullable
    java.lang.String birthDate, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.UserDto.Gender gender, @org.jetbrains.annotations.Nullable
    java.lang.String addressStreet, @org.jetbrains.annotations.Nullable
    java.lang.Integer addressNumber, @org.jetbrains.annotations.Nullable
    java.lang.String addressCity, @org.jetbrains.annotations.Nullable
    java.lang.Integer addressCap, @org.jetbrains.annotations.Nullable
    java.lang.String addressState, @org.jetbrains.annotations.Nullable
    java.lang.String addressRegion) {
        return null;
    }
    
    /**
     * @param id 
     * @param nickName 
     * @param firstName 
     * @param lastName 
     * @param imageName 
     * @param email 
     * @param birthDate 
     * @param gender 
     * @param addressStreet 
     * @param addressNumber 
     * @param addressCity 
     * @param addressCap 
     * @param addressState 
     * @param addressRegion
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * @param id 
     * @param nickName 
     * @param firstName 
     * @param lastName 
     * @param imageName 
     * @param email 
     * @param birthDate 
     * @param gender 
     * @param addressStreet 
     * @param addressNumber 
     * @param addressCity 
     * @param addressCap 
     * @param addressState 
     * @param addressRegion
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * @param id 
     * @param nickName 
     * @param firstName 
     * @param lastName 
     * @param imageName 
     * @param email 
     * @param birthDate 
     * @param gender 
     * @param addressStreet 
     * @param addressNumber 
     * @param addressCity 
     * @param addressCap 
     * @param addressState 
     * @param addressRegion
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public UserDto(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String nickName, @org.jetbrains.annotations.NotNull
    java.lang.String firstName, @org.jetbrains.annotations.Nullable
    java.lang.String lastName, @org.jetbrains.annotations.Nullable
    java.lang.String imageName, @org.jetbrains.annotations.Nullable
    java.lang.String email, @org.jetbrains.annotations.Nullable
    java.lang.String birthDate, @org.jetbrains.annotations.Nullable
    com.example.vintedandroid.client.models.UserDto.Gender gender, @org.jetbrains.annotations.Nullable
    java.lang.String addressStreet, @org.jetbrains.annotations.Nullable
    java.lang.Integer addressNumber, @org.jetbrains.annotations.Nullable
    java.lang.String addressCity, @org.jetbrains.annotations.Nullable
    java.lang.Integer addressCap, @org.jetbrains.annotations.Nullable
    java.lang.String addressState, @org.jetbrains.annotations.Nullable
    java.lang.String addressRegion) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNickName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFirstName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLastName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImageName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBirthDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.UserDto.Gender component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.vintedandroid.client.models.UserDto.Gender getGender() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAddressStreet() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getAddressNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAddressCity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getAddressCap() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAddressState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAddressRegion() {
        return null;
    }
    
    /**
     * Values: MALE,FEMALE,OTHER
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lcom/example/vintedandroid/client/models/UserDto$Gender;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "MALE", "FEMALE", "OTHER", "app_debug"})
    public static enum Gender {
        /*public static final*/ MALE /* = new MALE(null) */,
        /*public static final*/ FEMALE /* = new FEMALE(null) */,
        /*public static final*/ OTHER /* = new OTHER(null) */;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String value = null;
        
        Gender(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getValue() {
            return null;
        }
    }
}