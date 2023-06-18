import {Component, OnInit} from '@angular/core';
import {ProductService} from "../_services/product.service";
import {ProductDto} from "../_dtos/product.dto";
import {UserAuthService} from "../_services/user-auth.service";
import {CartService} from "../_services/cart.service";
import {MatDialog} from "@angular/material/dialog";
import {CartDialogComponent} from "../add-cart-dialog/cart-dialog.component";
import {AddDiscountDialogComponent} from "../add-discount-dialog/add-discount-dialog.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: ProductDto[] = [];

  constructor(private productService: ProductService, private userAuthService: UserAuthService, public dialog: MatDialog) {
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

  public deleteProduct(productID: number) {
    this.productService.deleteProduct(productID).subscribe(
      (response) => {
        console.log(response);
        location.reload();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openCartDialog(product: ProductDto) {
    this.dialog.open(CartDialogComponent, {data: product});
  }

  openDiscountDialog(productId: number) {
    this.dialog.open(AddDiscountDialogComponent, {data: productId})
  }

  hasDiscount(productId: number) {
    let hasDiscount= false;
    this.productService.getDiscount(productId).subscribe(
      (response) => {
        hasDiscount = response != null;
      },
      (err) => {
        console.log(err);
      }
    )
    return hasDiscount;
  }

  removeDiscount(productId: number) {
    console.log(productId);
    this.productService.deleteDiscount(productId).subscribe(
      (response) => {
        console.log(response);
        location.reload();
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
