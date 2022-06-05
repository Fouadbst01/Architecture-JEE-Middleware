import { Component,OnInit } from '@angular/core';
import { DataserviceService } from './dataservice.service';
import { JwtService } from './services/jwt.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'digital-banking-web';
  isDarkEnable:boolean=false;

  constructor(private data:DataserviceService,private jwtService:JwtService){
    this.data.darkmode.subscribe(isDarkEnable => this.isDarkEnable=isDarkEnable);
  }
  

  ngOnInit(): void {
    
  }
}
