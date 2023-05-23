import {Component} from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserService} from "../_services/user.service";
import {lastValueFrom} from "rxjs";
import {UserAuthService} from "../_services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService: UserService, private userAuthService: UserAuthService, private router: Router) {
  }

  async login(loginForm: NgForm) {
    try {
      const response:any = await lastValueFrom(this.userService.login(loginForm.value));
      const role = response.user.role;
      this.userAuthService.setRole(role);
      this.userAuthService.setToken(response.jwtToken);

      await this.router.navigate(['']);

    } catch (error) {
      console.log(error);
    }
  }
}
