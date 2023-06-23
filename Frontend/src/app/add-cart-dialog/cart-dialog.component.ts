import {Component, Inject, OnInit} from '@angular/core';
import {CartItemDto} from "../_dtos/cartItem.dto";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {CartService} from "../_services/cart.service";
import {GetProductDto} from "../_dtos/product.dto";

@Component({
  selector: 'app-add-cart-dialog',
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
export class CartDialogComponent implements OnInit {

  cartItem: CartItemDto = {
    id: 0,
    product: {
      id: 0,
      name: '',
      specifications: '',
      category: '',
      provider: '',
      points: 0,
      price: 0,
      discount: {
        id: 0,
        value: 0,
        points: 0,
      }
    },
    quantity: 0,
    isReduced: false,
  }

  constructor(@Inject(MAT_DIALOG_DATA) public data: GetProductDto, private cartService: CartService) {
  }

  public addCartItem(cartItem: CartItemDto) {
    console.log(cartItem);
    this.cartService.addCartItem(cartItem).subscribe(
      (response) => {
        console.log(response);
        location.reload();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  ngOnInit(): void {
    console.log(this.data);
    this.cartItem.product = this.data;
  }
}
