import {Component, OnInit} from '@angular/core';
import {ProductService} from "../_services/product.service";
import {lastValueFrom, Observable} from "rxjs";
import {ProductDto} from "../_dtos/product.dto";
import {UserAuthService} from "../_services/user-auth.service";
import {CartItemDto} from "../_dtos/cartItem.dto";
import {CartService} from "../_services/cart.service";
import {MatDialog} from "@angular/material/dialog";
import {CartDialogComponent} from "../cart-dialog/cart-dialog.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: ProductDto[] = [];

  constructor(private productService: ProductService, private userAuthService: UserAuthService, private cartService: CartService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getAllProducts();
  }

  public async getAllProducts() {
    try {
      const response = await lastValueFrom(this.productService.getAllProducts());
      console.log(response);
      this.products = response;
    } catch (err) {
      console.log(err);
    }
  }

  public isAdmin() {
    return this.userAuthService.isAdmin();
  }

  public isClient() {
    return this.userAuthService.isClient();
  }

  public async deleteProduct(productID: number) {
    try {
      await this.productService.deleteProduct(productID).toPromise();
      location.reload();
    } catch (err) {
      console.log(err);
    }
  }

  openDialog(product: ProductDto) {
    this.dialog.open(CartDialogComponent, { data: product});
  }
}
