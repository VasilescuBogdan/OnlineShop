import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginRequest} from "../_dtos/loginRequest.dto";
import {UserAuthService} from "./user-auth.service";
import {ProfileDto} from "../_dtos/profile.dto";
import {RegistrationDto} from "../_dtos/registration.dto";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    BASE_PATH = "http://localhost:8090/api/users";

    requestHeader = new HttpHeaders({"No-Auth": "True"});

    constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) {
    }

    public login(loginData: LoginRequest) {
        return this.httpClient.post(this.BASE_PATH + "/authentication", loginData, {headers: this.requestHeader});
    }

    public roleMatch(allowedRoles: string[]) {
        let isMatch = false;
        const userRole = this.userAuthService.getRole();

        if (userRole !== null && userRole) {
            for (let role of allowedRoles) {
                if (userRole === role) {
                    isMatch = true;
                }
            }
        }

        return isMatch;
    }

    public getUserProfile() {
        return this.httpClient.get<ProfileDto>(this.BASE_PATH + "/profile");
    }

    public register(registration: RegistrationDto) {
        return this.httpClient.post(this.BASE_PATH + "/registration", registration, {headers: this.requestHeader});
    }


}
