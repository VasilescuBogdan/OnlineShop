import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../_services/product.service";
import {ProductDto} from "../../_dtos/product.dto";
import {UserAuthService} from "../../_services/user-auth.service";
import {MatDialog} from "@angular/material/dialog";
import {CartDialogComponent} from "../../_pop-ups/add-cart-dialog/cart-dialog.component";
import {AddDiscountDialogComponent} from "../../_pop-ups/add-discount-dialog/add-discount-dialog.component";
import {Router} from "@angular/router";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    products: ProductDto[] = [];

    constructor(private productService: ProductService, private userAuthService: UserAuthService, public dialog: MatDialog, private router: Router) {
    }

    ngOnInit(): void {
        this.getAllProducts();
    }

    public getAllProducts() {
        this.productService.getAllProducts().subscribe(
            (response) => {
                console.log(response);
                this.products = response;
            }
        );
    }

    public isAdmin() {
        return this.userAuthService.isAdmin();
    }

    public isClient() {
        return this.userAuthService.isClient();
    }

    public deleteProduct(productId: number | null) {
        if (productId) {
            this.productService.deleteProduct(productId).subscribe({
                next: (response) => {
                    console.log(response);
                    this.getAllProducts();
                },
                error: (error) => {
                    console.log(error);
                }
            });
        }
    }

    openCartDialog(product: ProductDto) {
        this.dialog.open(CartDialogComponent, {data: product});
    }

    openDiscountDialog(productId: number | null) {
        this.dialog.open(AddDiscountDialogComponent, {data: productId})
    }

    removeDiscount(productId: number) {
        this.productService.deleteDiscount(productId).subscribe({
            next: (response) => {
                console.log(response);
                this.getAllProducts();
            },
            error: (error) => {
                console.log(error);
            }
        });
    }

    editProduct(id: number | null) {
        this.router.navigate(['/add-product', {productId: id}]).then(r => console.log(r));
    }
}
