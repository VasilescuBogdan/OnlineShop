import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {UserAuthService} from "../_services/user-auth.service";
import {Router} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private userAuthService: UserAuthService, private router: Router) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.headers.get("No-Auth") === 'True') {
            return next.handle(req.clone());
        }

        const token = this.userAuthService.getToken();
        req = this.addToken(req, token);

        return next.handle(req).pipe(
            catchError(
                (error: HttpErrorResponse) => {
                    console.error(error.status);
                    if (error.status === 401) {
                        this.router.navigate(['/login']).then(r => console.log(r));
                    } else if (error.status === 403) {
                        this.router.navigate(['/forbidden']).then(r => console.log(r))
                    }
                    return throwError(() => new Error("Something is wrong"));
                }
            )
        );
    }

    private addToken(request: HttpRequest<any>, token: string) {
        return request.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
    }

}
