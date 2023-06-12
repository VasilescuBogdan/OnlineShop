import {Component, Inject, OnInit} from '@angular/core';
import {CartItemDto} from "../_dtos/cartItem.dto";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {lastValueFrom} from "rxjs";
import {CartService} from "../_services/cart.service";
import {ProductDto} from "../_dtos/product.dto";

@Component({
  selector: 'app-cart-dialog',
  template: `
    <h1 mat-dialog-title>{{ data.name }}</h1>
    <div mat-dialog-content>
      <mat-form-field>
        <mat-label>Quantity</mat-label>
        <input matInput type="number" [(ngModel)]="cartItem.quantity">
      </mat-form-field>
    </div>
    <div mat-dialog-actions>
      <button mat-button (click)="addCartItem(cartItem)">Submit</button>
      <span class="toolbar-spacer"></span>
      <button mat-button color="warn" mat-dialog-close>Close</button>
    </div>
  `,
  styles: [`
    .toolbar-spacer {
      flex: 1 1 auto;
    }
  `]
})
export class CartDialogComponent implements OnInit{

  cartItem: CartItemDto = {
    product: {
      id: 0,
      name: '',
      specifications: '',
      category: '',
      provider: '',
      stock: 0,
      price: 0,
      image: '',
    },
    quantity: 0
  }

  constructor(@Inject(MAT_DIALOG_DATA) public data: ProductDto, private cartService: CartService) {
  }

  public async addCartItem(cartItem: CartItemDto) {
    console.log(cartItem);
    try {
      const response = await lastValueFrom(this.cartService.addCartItem(cartItem));
      console.log(response);
      location.reload();
    } catch (err) {
      console.log(err);
    }
  }

  ngOnInit(): void {
    console.log(this.data);
    this.cartItem.product = this.data;
  }
}
