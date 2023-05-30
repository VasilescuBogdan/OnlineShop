import {Component, OnInit} from '@angular/core';
import {UserService} from "../_services/user.service";
import {lastValueFrom} from "rxjs";
import {ProfileDto} from "../_dtos/profile.dto";

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

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.getProfile();
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

  deleteItem(id: number) {
    console.log(id);
  }
}
