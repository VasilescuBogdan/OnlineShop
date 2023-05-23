import {Component, OnInit} from '@angular/core';
import {UserService} from "../_services/user.service";
import {lastValueFrom} from "rxjs";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  message: any;
  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.forUser();
  }

  async forUser() {
    try {
      const response:any = await lastValueFrom(this.userService.forUser());
      console.log(response);
      this.message = response;
    } catch (error) {
      console.log(error);
    }
  }
}
