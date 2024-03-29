import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserService} from "../../_services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private userService: UserService, private router: Router) {
  }

  register(registerForm: NgForm) {
    this.userService.register(registerForm.value).subscribe({
      next:(response) => {
        console.log(response);
        this.router.navigate(['/login']).then(r => console.log(r));
      },
      error:(error) => {
        console.log(error);
      }
    })
  }
}
