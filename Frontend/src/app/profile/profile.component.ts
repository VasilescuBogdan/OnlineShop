import {Component, OnInit} from '@angular/core';
import {UserService} from "../_services/user.service";
import {lastValueFrom} from "rxjs";
import {ProfileDto} from "../_dtos/profile.dto";
import {CartService} from "../_services/cart.service";

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

  displayedColumns: string[] = ['name', 'value', 'quantity', 'price', 'delete-button'];

  constructor(private userService: UserService, private cartService: CartService) {
  }

  ngOnInit(): void {
    this.getProfile();
    console.log(this.getTotalCost());
  }

  public async getProfile() {
    try {
      const response = await lastValueFrom(this.userService.getUserProfile());
      console.log(response);
      this.profile = response;
    } catch (err) {
      console.log(err);
    }
  }

  public async deleteItem(id: number) {
    console.log(id);
    try {
      await this.cartService.deleteCartItem(id).toPromise();
      location.reload();
    } catch (err) {
      console.log(err);
    }

  }

  getTotalCost() {
    return this.profile.cart.map(t => t).reduce((acc, value) => acc + value.quantity * value.product.price, 0);
  }
}
