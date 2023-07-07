import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./Components/home-page/home-page.component";
import {InsertionPageComponent} from "./Components/Pages/Insertion/insertion-page/insertion-page.component";
import {BrandComponent} from "./Components/Querys/brand/brand.component";
import {SearchComponent} from "./Components/Querys/search/search.component";
import {CategoryComponent} from "./Components/Querys/category/category.component";
import {CatalogComponent} from "./Components/Pages/Insertion/catalog/catalog.component";
import {RegistrazioneComponent} from "./Components/Pages/User/registrazione/registrazione.component";
import {LoginComponent} from "./Components/Pages/User/login/login.component";
import {CartComponent} from "./Components/Pages/cart/cart.component";
import {ProfileComponent} from "./Components/Pages/User/profile/profile.component";
import {CreateInsertionComponent} from "./Components/Pages/Insertion/create-insertion/create-insertion.component";
import {MyprofileComponent} from "./Components/Pages/User/myprofile/myprofile.component";


const routes: Routes = [
  {path : '', component: HomePageComponent},
  {path : 'insertion/:id', component:InsertionPageComponent},
  {path : 'brand/:brandName', component: BrandComponent },
  {path : 'search/:searchstring', component: SearchComponent },
  {path : 'category/:categoryName', component: CategoryComponent },
  {path : 'catalog', component:CatalogComponent},
  {path : 'cart', component:CartComponent},
  {path : 'singin', component:RegistrazioneComponent},
  {path : 'login', component:LoginComponent},
  {path:  'profile/:userid', component: ProfileComponent},
  {path : 'newinsertion', component:CreateInsertionComponent},
  {path: 'myprofile', component: MyprofileComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
