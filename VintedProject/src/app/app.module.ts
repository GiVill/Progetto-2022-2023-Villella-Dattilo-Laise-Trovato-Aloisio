import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {Component} from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainNavComponent } from './Components/main-nav/main-nav.component';
import { ProductCardComponent } from './Components/insertion-card/product-card.component';
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
import { CatalogComponent } from './Components/catalog/catalog.component';
import { UserInsertionComponent } from './Components/user-insertion/user-insertion.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { CartComponent } from './Components/cart/cart.component';
import { CartInsertionCardComponent } from './Components/cart-insertion-card/cart-insertion-card.component';
import { RegistrazoneComponent } from './Components/registrazione/registrazione.component';
import {FormsModule} from "@angular/forms";



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
    CatalogComponent,
    UserInsertionComponent,
    ProfileComponent,
    CartComponent,
    CartInsertionCardComponent,
    RegistrazoneComponent,
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
    HttpClientModule,
    FormsModule

  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
