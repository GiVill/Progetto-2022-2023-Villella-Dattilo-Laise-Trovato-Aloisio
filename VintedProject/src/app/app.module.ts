import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {Component} from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainNavComponent } from './Components/main-nav/main-nav.component';
import { ProductCardComponent } from './Components/product-card/product-card.component';
import { InsertionPageComponent } from './Components/insertion-page/insertion-page.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTreeModule} from '@angular/material/tree';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from "@angular/material/input";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { HttpClientModule} from "@angular/common/http";
import { BrandComponent } from './Components/brand/brand.component';
import { CookieService } from 'ngx-cookie-service';
import { SearchComponent } from './Components/search/search.component';
import { CategoryComponent } from './Components/category/category.component';



@NgModule({
  declarations: [
    AppComponent,
    MainNavComponent,
    ProductCardComponent,
    InsertionPageComponent,
    HomePageComponent,
    BrandComponent,
    SearchComponent,
    CategoryComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MatMenuModule,
        MatFormFieldModule,
        MatIconModule,
        MatButtonModule,
        MatTreeModule,
        MatSidenavModule,
        MatCardModule,
        MatInputModule,
        BrowserAnimationsModule,
        HttpClientModule

    ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
