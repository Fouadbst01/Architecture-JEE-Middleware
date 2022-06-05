import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { JwtService } from '../services/jwt.service';
import { LocalStorgeService } from '../services/local-storge.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  form!: FormGroup

  constructor(private router:Router,private formeBuilder: FormBuilder, private authservice: AuthService, private jwtService: JwtService,private localeStorge:LocalStorgeService) { }

  ngOnInit(): void {
    this.form = this.formeBuilder.group({
      email: this.formeBuilder.control("", Validators.email),
      password: this.formeBuilder.control("")
    })
  }

  login() {
    this.authservice.login(this.form.value.email, this.form.value.password).subscribe({
      next: res => {
        const data:Map<string,string> = new Map(Object.entries(res));
        this.jwtService.setToken(data.get("access-token")!);
        this.localeStorge.set("access-token",data.get("access-token")!);
        
        this.jwtService.setToken(data.get("refresh-token")!);
        this.localeStorge.set("refresh-token",data.get("refresh-token")!);
        this.router.navigate(['/customer']);
      },
      error: err => {
        console.log(err)
      }
    })
  }

}
