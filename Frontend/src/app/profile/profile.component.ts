import {Component, OnInit} from '@angular/core';
import {UserService} from "../_services/user.service";
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
  TotalPoints = 0;
  TotalValue = 0;

  displayedColumns: string[] = ['name', 'value', 'quantity', 'price', 'delete-button'];

  constructor(private userService: UserService, private cartService: CartService) {
  }

  ngOnInit(): void {
    this.getProfile();
  }

  public getProfile() {
    this.userService.getUserProfile().subscribe(
      (response) => {
        console.log(response);
        this.profile = response;
      },
      (error) => {
        console.log(error);
      }
    );

  }

  public deleteItem(id: number) {
    console.log(id);
    this.cartService.deleteCartItem(id).subscribe(
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
