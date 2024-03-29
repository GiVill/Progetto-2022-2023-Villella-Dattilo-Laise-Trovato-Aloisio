import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./Components/home-page/home-page.component";
import {InsertionPageComponent} from "./Components/Pages/Insertion/insertion-page/insertion-page.component";
import {BrandComponent} from "./Components/Querys/brand/brand.component";
import {SearchComponent} from "./Components/Querys/search/search.component";
import {CategoryComponent} from "./Components/Querys/category/category.component";
import {CatalogComponent} from "./Components/Pages/Insertion/catalog/catalog.component";
import {LoginComponent} from "./Components/Pages/User/login/login.component";
import {CartComponent} from "./Components/Pages/cart/cart.component";
import {ProfileComponent} from "./Components/Pages/User/profile/profile.component";
import {CreateInsertionComponent} from "./Components/Pages/Insertion/create-insertion/create-insertion.component";
import {MyprofileComponent} from "./Components/Pages/User/myprofile/myprofile.component";
import {NotFoundComponent} from "./Components/Pages/Errors/not-found/not-found.component";
import {NotAuthorizedComponent} from "./Components/Pages/Errors/not-authorized/not-authorized.component";
import {BadComponent} from "./Components/Pages/Errors/badRequest/bad.component";
import {RefusedComponent} from "./Components/Pages/Errors/refused/refused.component";
import {ProfileEditComponent} from "./Components/Pages/User/profile-edit/profile-edit.component";
import {FormsModule} from "@angular/forms";
import {DashboardComponent} from "./Components/Pages/Admin/dashboard/dashboard.component";
import {ChatComponent} from "./Components/Pages/chat/chat.component";
import {PrivateComponent} from "./Components/Pages/Insertion/private/private.component";
import {OrderComponent} from "./Components/Pages/Order/order.component";



const routes: Routes = [
  {path : '', component: HomePageComponent},
  {path : 'insertion/:id', component:InsertionPageComponent},
  {path : 'brand/:brandName', component: BrandComponent },
  {path : 'search/:searchstring', component: SearchComponent },
  {path : 'category/:categoryName', component: CategoryComponent },
  {path : 'catalog', component:CatalogComponent},
  {path : 'cart', component:CartComponent},
  {path : 'login', component:LoginComponent},
  {path:  'profile/:userid', component: ProfileComponent},
  {path : 'newinsertion', component:CreateInsertionComponent},
  {path: 'myprofile', component: MyprofileComponent},
  {path: '404', component: NotFoundComponent},
  {path: '403', component: NotAuthorizedComponent},
  {path: '400', component: BadComponent},
  {path: 'refused', component: RefusedComponent},
  {path: 'dash', component: DashboardComponent},
  {path: 'chat', component: ChatComponent},
  {path: 'private/:token', component: PrivateComponent},
  {path: 'orders/:order', component: OrderComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes), FormsModule],
    declarations: [
        ProfileEditComponent
    ],
    exports: [RouterModule, ProfileEditComponent]
})
export class AppRoutingModule { }
