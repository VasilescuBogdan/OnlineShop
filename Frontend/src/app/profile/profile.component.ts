import {Component, OnInit} from '@angular/core';
import {UserService} from "../_services/user.service";
import {ProfileDto} from "../_dtos/profile.dto";
import {CartService} from "../_services/cart.service";
import {CartItemDto} from "../_dtos/cartItem.dto";
import {TransactionService} from "../_services/transaction.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile: ProfileDto = {
    username: '',
    firstName: '',
    lastName: '',
    email: '',
    number: '',
    points: 0,
    cart: []
  }
  TotalPoints = 0;
  TotalPrice = 0;

  displayedColumns: string[] = ['name', 'price', 'points', 'quantity', 'total-price', 'total-points', 'actions'];

  constructor(private userService: UserService, private cartService: CartService, private transactionService: TransactionService) {
  }

  ngOnInit(): void {
    this.getProfile();
  }

  public getProfile() {
    this.userService.getUserProfile().subscribe(
      (response) => {
        this.profile = response;
        this.calculateTotal();
      },
      (error) => {
        console.log(error);
      }
    );

  }

  public deleteItem(id: number) {
    console.log(id);
    this.cartService.deleteCartItem(id).subscribe(
      () => {
        this.getProfile();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  public calculateTotal() {
    this.TotalPoints = 0;
    this.TotalPrice = 0;
    for (const item of this.profile.cart) {
      let actualPrice: number;
      let actualPoints: number;
      if (!item.isReduced) {
        actualPrice = item.product.price * item.quantity;
        actualPoints = item.product.points * item.quantity;
      } else {
        actualPrice = (item.product.price - item.product.price * item.product.discount.value / 100) * item.quantity;
        actualPoints = -item.product.discount.points * item.quantity;
      }
      this.TotalPrice += actualPrice;
      this.TotalPoints += actualPoints;
    }
  }

  onCheckBoxChange(item: CartItemDto) {
    this.cartService.setIsReduced(item.id, item.isReduced).subscribe(
      () => {
        this.getProfile();
      },
      (error) => {
        console.log(error);
      }
    )
  }

  incrementQuantity(item: CartItemDto) {
    item.quantity++;
    this.cartService.setQuantity(item.id, item.quantity).subscribe(
      () => {
        this.getProfile();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  decrementQuantity(item: CartItemDto) {
    if (item.quantity !== 1) {
      item.quantity--;
      this.cartService.setQuantity(item.id, item.quantity).subscribe(
        () => {
          this.getProfile();
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  pay(totalPrice: number, totalPoints: number) {
    this.transactionService.addTransaction(totalPrice, totalPoints).subscribe(
      (response) => {
        console.log(response);
        this.getProfile();
      },
      (error) => {
        console.log(error);
      }
    );
    this.getProfile();
  }

}
