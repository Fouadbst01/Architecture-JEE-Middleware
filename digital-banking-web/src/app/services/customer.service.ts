import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { Customer, CustomerPage } from '../model/customer.model';
import { LocalStorgeService } from './local-storge.service';
import { JwtService } from './jwt.service';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient, private storgeService: LocalStorgeService, private jwtService: JwtService, private auth: AuthService) { }

  public getCustomers(keyword: String,currentPage:number,pageSize:number): Observable<CustomerPage> {

    if (this.jwtService.checkAccessToken()) {
     
      this.auth.refreshToken();
    }

    var baseLink: string = "/customers";
    var accessToken: string = this.storgeService.get("access-token")!;
    this.jwtService.setToken(accessToken)


    const headerDict = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + accessToken
    }
    const requestOptions = {
      headers: new HttpHeaders(headerDict),
    };
    
    if (keyword != "")
      baseLink += "?keyword=" + keyword+"&";
    else
      baseLink +="?"
    let page="page="+currentPage+"&";
    let size="size="+pageSize;
    return this.http.get<CustomerPage>(environment.hostLink + baseLink+page+size, requestOptions);
  }

  public deleteCustomer(idCustomer: String) {
    if (this.jwtService.checkAccessToken()) {
     
      this.auth.refreshToken();
    }

    var baseLink: string = "/customers";
    var accessToken: string = this.storgeService.get("access-token")!;
    this.jwtService.setToken(accessToken)


    const headerDict = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + accessToken
    }
    const requestOptions = {
      headers: new HttpHeaders(headerDict),
    };
    return this.http.delete(environment.hostLink + "/customers/" + idCustomer/*{headers}*/, requestOptions)
  }

}
