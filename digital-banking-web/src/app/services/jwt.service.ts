import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";
import { LocalStorgeService } from './local-storge.service';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

    jwtToken!: string;
    decodedToken!: { [key: string]: string };

    constructor(private storegService:LocalStorgeService) {
    }


    setToken(token: string) {
      if (token) {
        this.jwtToken = token;
      }
    }

    private decodeToken() {
      if (this.jwtToken) {
      this.decodedToken = jwt_decode(this.jwtToken);
      }
    }

    getDecodeToken() {
      return jwt_decode(this.jwtToken);
    }

    getEmail() {
      this.decodeToken();
      return this.decodedToken ? this.decodedToken["sub"] : null;
    }

    getRole() {
      this.decodeToken();
      return this.decodedToken ? this.decodedToken['roles'] : null;
    }

    getExpiryTime(){
      this.decodeToken();
      return this.decodedToken ? this.decodedToken['exp'] : null;
    }

    isTokenExpired(): boolean {
      const expiryTime: number = +this.getExpiryTime()!;
      if (expiryTime) {
        return ((1000 * expiryTime) - (new Date()).getTime()) < 5000;
      } else {
        return false;
      }
    }

    checkAccessToken(): boolean{
      this.jwtToken =this.storegService.get("access-token")!;
      return this.isTokenExpired()
    }
  
}
