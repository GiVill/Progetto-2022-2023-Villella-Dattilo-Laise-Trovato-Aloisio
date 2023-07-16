package com.example.vintedandroid;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001e\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\b\u0010\b\u001a\u00020\u0001H\u0007\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a#\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u0011\u001a\u0017\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e*\u00020\u0013\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"ApplicationBottomBar", "", "navController", "Landroidx/navigation/NavHostController;", "ApplicationTopBar", "searchText", "Landroidx/compose/runtime/MutableState;", "", "DefaultPreview", "checkForInternet", "", "context", "Landroid/content/Context;", "imageToByteArray", "", "", "imagePath", "(Landroid/content/Context;Ljava/lang/String;)[Ljava/lang/Byte;", "toByteArray", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;)[Ljava/lang/Byte;", "app_debug"})
public final class MainActivityKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void ApplicationTopBar(@org.jetbrains.annotations.NotNull
    androidx.compose.runtime.MutableState<java.lang.String> searchText, @org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ApplicationBottomBar(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    private static final boolean checkForInternet(android.content.Context context) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.Byte[] imageToByteArray(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String imagePath) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.Byte[] toByteArray(@org.jetbrains.annotations.NotNull
    android.graphics.Bitmap $this$toByteArray) {
        return null;
    }
    
    @androidx.compose.runtime.Composable
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    public static final void DefaultPreview() {
    }
}