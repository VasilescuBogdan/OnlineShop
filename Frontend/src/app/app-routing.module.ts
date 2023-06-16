import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {ForbiddenComponent} from "./forbidden/forbidden.component";
import {AuthGuard} from "./_auth/auth.guard";
import {AddProductComponent} from "./add-product/add-product.component";
import {ProfileComponent} from "./profile/profile.component";
import {RegisterComponent} from "./register/register.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'addProduct', component: AddProductComponent, canActivate: [AuthGuard], data: {roles: ['ADMIN']}},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard], data: {roles: ['CLIENT']}},
  {path: 'forbidden', component: ForbiddenComponent},
  {path: 'registration', component: RegisterComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
