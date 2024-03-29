import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
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
import {ProductCardComponent} from "./Components/Components/Cards/product-card/product-card.component";
import {InsertionPageComponent} from "./Components/Pages/Insertion/insertion-page/insertion-page.component";
import {CatalogComponent} from "./Components/Pages/Insertion/catalog/catalog.component";
import {ProfileComponent} from "./Components/Pages/User/profile/profile.component";
import {CartComponent} from "./Components/Pages/cart/cart.component";
import {LoginComponent} from "./Components/Pages/User/login/login.component";
import {CartProductCardComponent} from "./Components/Components/Cards/cart-product-card/cart-product-card.component";
import { CreateInsertionComponent } from './Components/Pages/Insertion/create-insertion/create-insertion.component';
import { JwtModule  } from '@auth0/angular-jwt';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MyprofileComponent } from './Components/Pages/User/myprofile/myprofile.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NotFoundComponent } from './Components/Pages/Errors/not-found/not-found.component';
import { NotAuthorizedComponent } from './Components/Pages/Errors/not-authorized/not-authorized.component';
import { BadComponent} from "./Components/Pages/Errors/badRequest/bad.component";
import { RefusedComponent } from './Components/Pages/Errors/refused/refused.component';
import { OrderCardComponent } from './Components/Components/Cards/order-card/order-card.component';
import {InsertionService} from "./api/insertion.service";
import {AuthService} from "./api/auth.service";
import {UserService} from "./api/user.service";
import {CartService} from "./api/cart.service";
import {OrderService} from "./api/order.service";
import {OfferService} from "./api/offer.service";
import {DashboardComponent} from "./Components/Pages/Admin/dashboard/dashboard.component";
import {ProfileCardComponent} from "./Components/Components/Cards/profile-card/profile-card.component";
import {BottomOfTheBarrelComponent} from "./Components/Components/bottom-of-the-barrel/bottom-of-the-barrel.component";
import {ChatComponent} from "./Components/Pages/chat/chat.component";
import {ChatService} from "./api/chat.service";
import {OfferCardComponent} from "./Components/Components/Cards/offer-card/offer-card.component";
import {PrivateComponent} from "./Components/Pages/Insertion/private/private.component";
import {ClipboardModule} from "ngx-clipboard";
import {ChatMessageService} from "./api/chatMessage.service";
import {OfferProfileCardComponent} from "./Components/Components/Cards/offer-profile-card/offer-profile-card.component";
import {MatDialogModule} from "@angular/material/dialog";
import {OrderComponent} from "./Components/Pages/Order/order.component";
import {ImageService} from "./api/image.service";
import {UserServiceNoCookie} from "./api/user.service.noCookie";






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
    ProfileComponent,
    CartComponent,
    CartProductCardComponent,
    LoginComponent,
    PrivateComponent,
    CreateInsertionComponent,
    MyprofileComponent,
    NotFoundComponent,
    NotAuthorizedComponent,
    BadComponent,
    RefusedComponent,
    OfferCardComponent,
    OrderCardComponent,
    OrderComponent,
    DashboardComponent,
    CartComponent,
    OfferProfileCardComponent,
    ProfileCardComponent,
    BottomOfTheBarrelComponent,
    ChatComponent,



  ],
  imports: [
    FormsModule,
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
    FormsModule,
    MatSnackBarModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('jwtToken');
        },
        allowedDomains: ['localhost'], // Domini consentiti per l'invio del token
        disallowedRoutes: ['example.com/api/login'] // Rotte disabilitate per l'invio del token
      }
    })
  ],
  providers: [CookieService,ChatService,ClipboardModule, UserServiceNoCookie ,JwtHelperService,ImageService,InsertionService,MyprofileComponent,ChatMessageService,AuthService,UserService,CartService,OrderService,OfferService],
  bootstrap: [AppComponent]
})
export class AppModule { }
