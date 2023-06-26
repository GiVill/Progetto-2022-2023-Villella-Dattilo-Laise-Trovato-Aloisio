import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {Component} from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
import { BrandComponent } from './Components/Querys/brand/brand.component';
import { CookieService } from 'ngx-cookie-service';
import { SearchComponent } from './Components/Querys/search/search.component';
import { CategoryComponent } from './Components/Querys/category/category.component';

import {FormsModule} from "@angular/forms";
import {MainNavComponent} from "./Components/Components/main-nav/main-nav.component";
import {ProductCardComponent} from "./Components/Components/Cards/insertion-card/product-card.component";
import {InsertionPageComponent} from "./Components/Pages/Insertion/insertion-page/insertion-page.component";
import {UserInsertionComponent} from "./Components/Pages/User/user-insertion/user-insertion.component";
import {CatalogComponent} from "./Components/Pages/Insertion/catalog/catalog.component";
import {ProfileComponent} from "./Components/Pages/User/profile/profile.component";
import {CartComponent} from "./Components/Pages/cart/cart.component";
import {RegistrazioneComponent} from "./Components/Pages/User/registrazione/registrazione.component";
import {LoginComponent} from "./Components/Pages/User/login/login.component";
import {
  CartInsertionCardComponent
} from "./Components/Components/Cards/cart-insertion-card/cart-insertion-card.component";
import { CreateInsertionComponent } from './Components/Pages/Insertion/create-insertion/create-insertion.component';




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
    RegistrazioneComponent,
    LoginComponent,
    CreateInsertionComponent,
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
