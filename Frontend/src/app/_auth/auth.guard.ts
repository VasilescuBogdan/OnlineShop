import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {UserAuthService} from "../_services/user-auth.service";
import {UserService} from "../_services/user.service";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private userAuthService: UserAuthService, private router: Router, private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (this.userAuthService.getToken() !== null) {
      const roles = route.data["roles"] as string[];

      if (roles) {
        const match = this.userService.roleMatch(roles);

        if (match) {
          return true;
        } else {
          this.router.navigate(['/forbidden']).then(
              r => console.log(r),
              err => console.log(err)
          );
          return false;
        }
      }
    }

    this.router.navigate(['/login']).then(
        r => console.log(r),
        err => console.log(err)
    );
    return false;
  }
}
