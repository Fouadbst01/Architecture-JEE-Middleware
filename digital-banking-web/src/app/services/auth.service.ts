import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { LocalStorgeService } from './local-storge.service';
import { JwtService } from './jwt.service';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,private localStorge:LocalStorgeService,private jwtService: JwtService) { }

  public login(username: string, password: string):Observable<Object>{
    const header = new HttpHeaders();
    header.set("content-type","application/x-www-form-urlencoded");
    const payload = new HttpParams()
      .set('username', username)
      .set('password', password);
    return this.http.post(environment.hostLink + "/login", payload,{headers:header});
  }

  public refreshToken(){
    //console.log("here "+'Bearer ' +this.localStorge.get("refresh-token"));
    const payload = new HttpHeaders()
      .set("authorization", 'Bearer ' +this.localStorge.get("refresh-token")!);
    this.http.get(environment.hostLink+"/refreshToken",{headers:payload}).subscribe({
      next: data => {
        const res:Map<string,string> = new Map(Object.entries(data));
        this.localStorge.set("access-token",res.get("access-token")!)
      },
      error: err => {
        console.log(err)
      }
    });
  }

  public isLoged():boolean{
    return this.localStorge.get("access-token") == null;
  }

  public logout(){
    this.localStorge.remove("access-token")
    this.localStorge.remove("refresh-token")
  }
}
