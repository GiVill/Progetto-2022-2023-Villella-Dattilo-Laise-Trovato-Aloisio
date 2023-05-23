import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductCardComponent } from './Components/product-card/product-card.component';
import { MainNavComponent } from './Components/main-nav/main-nav.component';

const routes: Routes = [
  {path : 'product', component: ProductCardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
