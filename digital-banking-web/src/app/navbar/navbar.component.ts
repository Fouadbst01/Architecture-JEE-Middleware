import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataserviceService } from '../dataservice.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isDarkmode:boolean=false;
  seleced:String='Home';

  constructor(private router:Router,private data:DataserviceService,public authService:AuthService) { }

  ngOnInit(): void {
    this.data.darkmode.subscribe(isDarkmode=>this.isDarkmode=isDarkmode);
  }

  changeMode(){
    this.data.changeTheme();
  }

  changeListeItem(value:String){
    this.seleced=value;
  }

  logout(){
    this.authService.logout()
    this.router.navigate(['/login']);
  }
}
