import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./_components/home/home.component";
import {LoginComponent} from "./_components/login/login.component";
import {ForbiddenComponent} from "./_components/forbidden/forbidden.component";
import {AuthGuard} from "./_auth/auth.guard";
import {AddProductComponent} from "./_components/add-product/add-product.component";
import {ProfileComponent} from "./_components/profile/profile.component";
import {RegisterComponent} from "./_components/register/register.component";
import {TransactionListComponent} from "./_components/transaction-list/transaction-list.component";
import {ProductResolverService} from "./_services/product-resolver.service";

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {
        path: 'add-product',
        component: AddProductComponent,
        canActivate: [AuthGuard],
        data: {roles: ['ADMIN']},
        resolve: {product: ProductResolverService}
    },
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard], data: {roles: ['CLIENT']}},
    {path: 'transaction', component: TransactionListComponent, canActivate: [AuthGuard], data: {roles: ['CLIENT']}},
    {path: 'forbidden', component: ForbiddenComponent},
    {path: 'registration', component: RegisterComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
